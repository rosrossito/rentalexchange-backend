package com.upteam.auth.service;

import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.emailgenerator.EmailGenerator;
import com.upteam.auth.component.emailgenerator.EmailGeneratorConfirmRegistration;
import com.upteam.auth.component.emailgenerator.EmailGeneratorRegistration;
import com.upteam.auth.component.emailgenerator.EmailRestorePassword;
import com.upteam.auth.domain.*;
import com.upteam.auth.exception.*;
import com.upteam.auth.repository.ActivationLinkRepository;
import com.upteam.auth.repository.ActivityRepository;
import com.upteam.auth.repository.SystemUserRepository;
import com.upteam.auth.vo.LoginRequestVO;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
import com.upteam.auth.vo.ChangePasswordRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public void registration(RegistrationRequestVO request) {
        if (systemUserRepository.searchByEmail(request.getEmail()) != null) {
            throw new UserAlreadyExistException();
        }
        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(request.getEmail());
        systemUser.setPassword(request.getPassword());
        systemUser.setStatus(SystemUserStatus.temporary);
        systemUserRepository.create(systemUser);

        UUID uuidGenerator = UUID.randomUUID();
        String uuid = uuidGenerator.toString();
        LocalDateTime toDateTime = LocalDateTime.now();

        ActivationLink activationLink = new ActivationLink();
        activationLink.setEffectiveDate(toDateTime);
        activationLink.setUuid(uuid);
        activationLink.setSystemuserId(systemUser.getId());
        activationLinkRepository.create(activationLink);

        String registrationConfirmLink = env.getProperty("ui.host") + ":" + env.getProperty("ui.port") + "/user/registration-confirm/" + uuid;
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
        activityRepository.create(activity);

    }

    @Override
    public void confirmRegistration(RegistrationConfirmRequestVO request) {

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

        SystemUser user = systemUserRepository.getById(link.getSystemuserId());
        if (user == null) {
            throw new InvalidConfirmRegistrationLinkException();
        }
        if (user.getStatus() == SystemUserStatus.delete || user.getStatus() == SystemUserStatus.blocked) {
            throw new SystemUserProblemException();
        }
        user.setPassword(request.getPassword());
        user.setStatus(SystemUserStatus.active);
        systemUserRepository.update(user);

        String activateUserLink = env.getProperty("ui.host") + ":" + env.getProperty("ui.port") + "/" + user.getEmail();
        EmailGeneratorConfirmRegistration confirmRegistrationEmail = new EmailGeneratorConfirmRegistration(user.getEmail(), activateUserLink);

        emailSender.sendEmail(confirmRegistrationEmail);
        activationLinkRepository.delete(link.getId());

        Activity activity = new Activity();
        activity.setSystemUserId(user.getId());
        activity.setActivityType(ActivityType.systemUserConfirmRegistration);
        activity.setDescription("Confirm registration user");
        activity.setActivityTime(LocalDateTime.now());
        activityRepository.create(activity);
    }

    @Override
    public void login(LoginRequestVO request) {
        SystemUser systemUser = systemUserRepository.searchByEmail(request.getEmail());

        if (systemUser == null || systemUser.getEmail() == null || !systemUser.getPassword().equals(request.getPassword())) {
            throw new IncorrectLoginException();

        } else if (systemUser.getStatus() == SystemUserStatus.temporary) {
            throw new NonActiveAccountException();

        } else if (systemUser.getStatus() == SystemUserStatus.blocked || systemUser.getStatus() == SystemUserStatus.delete) {
            throw new BlockedAccountException();
        }

    }


    @Override
    public void changePasswordRequest(ChangePasswordRequestVO request) {

        if(request.getEmail()==null) {
        if (systemUserRepository.searchByEmail(request.getEmail()) != null) {

            //SystemUser systemUser = new SystemUser();
            SystemUser systemUser = systemUserRepository.searchByEmail(request.getEmail());
            systemUser.setEmail(request.getEmail());
            systemUser.setLogin(request.getLogin());

            UUID uuidGenerator = UUID.randomUUID();
            String uuid = uuidGenerator.toString();
            LocalDateTime toDateTime = LocalDateTime.now();

            ActivationLink activationLink = new ActivationLink();
            activationLink.setEffectiveDate(toDateTime);
            activationLink.setUuid(uuid);
            activationLink.setSystemuserId(systemUser.getId());

            String restorePasswordLink = env.getProperty("ui.host") + ":" + env.getProperty("ui.port") + "/" + uuid;

            activationLinkRepository.create(activationLink);

            EmailRestorePassword emailRestorePassword = new EmailRestorePassword (request.getEmail(), restorePasswordLink);
            emailSender.sendEmail(emailRestorePassword);

            activationLinkRepository.create(activationLink);

            LOG.info("Activation link for restore password was successfully sent to user with email: " + systemUser.getEmail() +
                    ", status - " + systemUser.getStatus().toString() + ".");

            Activity activity = new Activity();
            activity.setSystemUserId(systemUser.getId());
            activity.setActivityType(ActivityType.systemUserRestorePassword);
            activity.setDescription("Restore password of user");
            activity.setActivityTime(LocalDateTime.now());
            activityRepository.create(activity);

        }else {throw new AccountIsNotActiveException();}
        }else {throw new EmailIsAbsentException();}


    }

}