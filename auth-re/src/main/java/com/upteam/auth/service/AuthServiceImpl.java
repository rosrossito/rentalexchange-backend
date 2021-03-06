package com.upteam.auth.service;

import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.emailgenerator.*;
import com.upteam.auth.domain.ActivationLink;
import com.upteam.auth.domain.Activity;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.domain.domainenum.ActivityType;
import com.upteam.auth.domain.domainenum.LinkType;
import com.upteam.auth.domain.domainenum.SystemUserStatus;
import com.upteam.auth.exception.*;
import com.upteam.auth.repository.ActivationLinkRepository;
import com.upteam.auth.repository.ActivityRepository;
import com.upteam.auth.repository.SystemUserRepository;
import com.upteam.auth.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by opasichnyk on 11/25/2015.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private ActivationLinkRepository activationLinkRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private ActivityRepository activityRepository;

    @Resource
    private Environment env;

    @Override
    @Transactional
    public void registration(RegistrationRequestVO request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        if (request.getEmail() == null) {
            throw new EmailIsAbsentException();
        }

        if (systemUserRepository.searchByEmail(request.getEmail()) != null) {
            throw new UserAlreadyExistException();
        }

        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(request.getEmail());
        systemUser.setStatus(SystemUserStatus.temporary);
        systemUserRepository.save(systemUser);

        UUID uuidGenerator = UUID.randomUUID();
        String uuid = uuidGenerator.toString();
        LocalDateTime toDateTime = LocalDateTime.now();

        ActivationLink activationLink = new ActivationLink();
        activationLink.setEffectiveDate(toDateTime);
        activationLink.setUuid(uuid);
        activationLink.setType(LinkType.confirmRegistration);
        activationLink.setSystemuserId(systemUser.getId());
        activationLinkRepository.save(activationLink);

        String registrationConfirmLink = env.getProperty("ui.host") + ":" + env.getProperty("ui.port") + "/user-registration-confirm" + "?" + "uuid" + "=" + uuid;
        EmailGenerator emailGeneratorRegistration =
                new EmailGeneratorRegistration(request.getEmail(), registrationConfirmLink, systemUser);
        emailSender.sendEmail(emailGeneratorRegistration);

        LOG.info("User with email: " + systemUser.getEmail() +
                " successfully registered, status - " + systemUser.getStatus().toString() + ".");

        Activity activity = new Activity();
        activity.setSystemUserId(systemUser.getId());
        activity.setActivityType(ActivityType.systemUserRegistration);
        activity.setDescription("Registration user");
        activity.setActivityTime(LocalDateTime.now());
        activityRepository.save(activity);
    }

    @Override
    @Transactional
    public void confirmRegistration(RegistrationConfirmRequestVO request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        ActivationLink link = activationLinkRepository.getLinkByUUID(request.getUuid());

        if (link == null || link.getType() != LinkType.confirmRegistration) {
            throw new InvalidConfirmRegistrationLinkException();
        }
        long linkLifePeriodSec = Long.valueOf(env.getProperty("activation.link.period"));
        LocalDateTime toDateTime = LocalDateTime.now();
        LocalDateTime fromDateTime = link.getEffectiveDate();
        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
        long deltaSec = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);
        if (deltaSec > linkLifePeriodSec) {
            activationLinkRepository.delete(link.getId());
            throw new InvalidConfirmRegistrationLinkException();
        }

        SystemUser user = systemUserRepository.findOne(link.getSystemuserId());
        if (user == null) {
            throw new InvalidConfirmRegistrationLinkException();
        }
        if (user.getStatus() == SystemUserStatus.active) {
            throw new UserAlreadyRegisteredException();
        }
        if (user.getStatus() != SystemUserStatus.temporary) {
            throw new BlockedAccountException();
        }
        user.setPassword(request.getPassword());
        user.setStatus(SystemUserStatus.active);
        systemUserRepository.save(user);

        String activateUserLink = env.getProperty("ui.host") + ":" + env.getProperty("ui.port") + "/" + user.getEmail();
        EmailGeneratorConfirmRegistration confirmRegistrationEmail = new EmailGeneratorConfirmRegistration(user.getEmail(), activateUserLink);

        emailSender.sendEmail(confirmRegistrationEmail);
        activationLinkRepository.delete(link.getId());

        Activity activity = new Activity();
        activity.setSystemUserId(user.getId());
        activity.setActivityType(ActivityType.systemUserConfirmRegistration);
        activity.setDescription("Confirm registration user");
        activity.setActivityTime(LocalDateTime.now());
        activityRepository.save(activity);
    }

    @Override
    public void login(LoginRequestVO request) {
        if (request == null) {
            throw new InvalidRequestException();
        }
        if (request.getEmail() == null) {
            throw new EmailIsAbsentException();
        }
        SystemUser systemUser = systemUserRepository.searchByEmail(request.getEmail());

        if (systemUser == null || !systemUser.getPassword().equals(request.getPassword())) {
            throw new IncorrectLoginException();

        } else if (systemUser.getStatus() == SystemUserStatus.temporary) {
            throw new NonActiveAccountException();

        } else if (systemUser.getStatus() == SystemUserStatus.blocked || systemUser.getStatus() == SystemUserStatus.delete) {
            throw new BlockedAccountException();
        }
    }


    @Override
    public void changePasswordRequest(ChangePasswordRequestVO request) {
        if (request == null) {
            throw new InvalidRequestException();
        }
        if (request.getEmail() == null) {
            throw new EmailIsAbsentException();
        }
        SystemUser systemUser = systemUserRepository.searchByEmail(request.getEmail());
        if (systemUser == null || systemUser.getStatus() != SystemUserStatus.active) {
            throw new NonActiveAccountException();
        }
        UUID uuidGenerator = UUID.randomUUID();
        String uuid = uuidGenerator.toString();
        LocalDateTime toDateTime = LocalDateTime.now();

        ActivationLink activationLinkOld = activationLinkRepository.getLinkBySystemUserID(systemUser.getId());
        if (activationLinkOld != null) {
            activationLinkRepository.delete(activationLinkOld.getId());
        }
        ActivationLink activationLink = new ActivationLink();

        activationLink.setEffectiveDate(toDateTime);
        activationLink.setUuid(uuid);
        activationLink.setType(LinkType.changePassword);
        activationLink.setSystemuserId(systemUser.getId());

        String restorePasswordLink = env.getProperty("ui.host") + ":" + env.getProperty("ui.port") + "/user-change-password-confirm?uuid=" + uuid;

        activationLinkRepository.save(activationLink);

        EmailGeneratorRestorePasswordRequest emailRestorePassword = new EmailGeneratorRestorePasswordRequest(request.getEmail(), restorePasswordLink);
        emailSender.sendEmail(emailRestorePassword);

        Activity activity = new Activity();
        activity.setSystemUserId(systemUser.getId());
        activity.setActivityType(ActivityType.systemUserRestorePassword);
        activity.setDescription("Restore password of user");
        activity.setActivityTime(LocalDateTime.now());
        activityRepository.save(activity);

        LOG.info("Link to the web page for restore password was successfully sent to user with email: " + systemUser.getEmail() + ".");
    }

    @Override
    public void changePassword(ChangePasswordVO request) {
        if (request == null) {
            throw new InvalidRequestException();
        }
//        if (request.getPassword() == null) {
//            throw new EmptyPasswordException();
//        }
        if (request.getUuid() == null) {
            throw new EmptyUuidException();
        }
        //password validation
        //TODO fix validation
     /*   if (request.getPassword().length() < 8 || request.getPassword().length() > 20) {
            throw new InvalidPasswordFormatException();
        }
        if (!request.getPassword().matches(".*[A-Z].*")) {
            throw new InvalidPasswordFormatException();
        }
        if (!request.getPassword().matches(".*[a-z].*")) {
            throw new InvalidPasswordFormatException();
        }
        if (!request.getPassword().matches(".*[0-9].*") && !request.getPassword().matches(".*[`~!@#$%^&*()\\\\-_=+\\\\\\\\\\\\|\\\\[{\\\\]};:'\\\",<.>/?].*")) {
            throw new InvalidPasswordFormatException();
        }*/
        //link missing or wrong link type check
        ActivationLink link = activationLinkRepository.getLinkByUUID(request.getUuid());
        if (link == null || link.getType() != LinkType.changePassword) {
            throw new InvalidChangePasswordLinkException();
        }
        //link overdue check
        long linkLifePeriodSec = Long.valueOf(env.getProperty("activation.link.period"));
        LocalDateTime permissibleLinkLifetime = link.getEffectiveDate().plusSeconds(linkLifePeriodSec);
        if (LocalDateTime.now().isAfter(permissibleLinkLifetime)) {
            activationLinkRepository.delete(link.getId());
            throw new InvalidChangePasswordLinkException();
        }
        //user missing and wrong user status check
        SystemUser user = systemUserRepository.findOne(link.getSystemuserId());
        if (user == null) {
            throw new SystemUserProblemException();
        }
        if (user.getStatus() == SystemUserStatus.delete || user.getStatus() == SystemUserStatus.blocked) {
            throw new BlockedAccountException();
        }
        if (user.getStatus() == SystemUserStatus.temporary) {
            throw new NonActiveAccountException();
        }
        //renewing user password
        user.setPassword(request.getPassword());
        systemUserRepository.save(user);
        //sending email and link delete
        EmailGenerator confirmRegistrationEmail = new EmailGeneratorChangePassword(user.getEmail());

        emailSender.sendEmail(confirmRegistrationEmail);
        activationLinkRepository.delete(link.getId());
        //activity
        Activity activity = new Activity();
        activity.setSystemUserId(user.getId());
        activity.setActivityType(ActivityType.systemUserChangePassword);
        activity.setDescription("User password change");
        activity.setActivityTime(LocalDateTime.now());
        activityRepository.save(activity);
    }

    @Override
    public UserInfoVO getUserInfo(String login) {
        SystemUser systemUser = systemUserRepository.searchByLogin(login);
        if (systemUser == null || systemUser.getStatus() != SystemUserStatus.active) {
            throw new SystemUserProblemException();
        }
        UserInfoVO result = new UserInfoVO();
        result.setEmail(systemUser.getEmail());
        result.setAvatar(systemUser.getImage());
        return result;
    }

    @Override
    public TestVO test() {
        List<SystemUser> systemUsers = systemUserRepository.findAll();
        List<UserInfoVO> userInfoVOs = new ArrayList<UserInfoVO>();
        for (SystemUser user : systemUsers) {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setEmail(user.getEmail());
            userInfoVOs.add(userInfoVO);
        }
        TestVO result = new TestVO();
        result.setUsers(userInfoVOs);
        return result;
    }

}