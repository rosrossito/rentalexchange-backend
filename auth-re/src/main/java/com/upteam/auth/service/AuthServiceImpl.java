package com.upteam.auth.service;

import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.emailgenerator.EmailGeneratorConfirmRegistration;
import com.upteam.auth.component.emailgenerator.EmailGeneratorRegistration;
import com.upteam.auth.domain.ActivationLink;
import com.upteam.auth.domain.LinkType;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.domain.SystemUserStatus;
import com.upteam.auth.exception.InvalidConfirmRegistrationLinkException;
import com.upteam.auth.exception.SystemUserProblemException;
import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.repository.ActivationLinkRepository;
import com.upteam.auth.repository.SystemUserRepository;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
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

    @Resource
    private Environment env;

    @Override
    public void registration(RegistrationRequestVO request) {
        if (systemUserRepository.searchByEmail(request.getEmail()) == null) {

            SystemUser systemUser = new SystemUser();
            systemUser.setEmail(request.getEmail());
            systemUser.setLogin(request.getLogin());
            systemUser.setPassword(request.getPassword());
            systemUser.setImage(request.getImage());
            systemUser.setStatus(SystemUserStatus.temporary);
            systemUserRepository.create(systemUser);

            UUID uuidGenerator = UUID.randomUUID();
            String uuid = uuidGenerator.toString();
            LocalDateTime toDateTime = LocalDateTime.now();

            ActivationLink activationLink = new ActivationLink();
            activationLink.setEffectiveDate(toDateTime);
            activationLink.setUuid(uuid);
            activationLink.setSystemuserId(systemUser.getId());

            String registrationConfirmLink = env.getProperty("ui.host") + ":" + env.getProperty("ui.port") + "/" + uuid;

            activationLinkRepository.create(activationLink);

            EmailGeneratorRegistration emailGeneratorRegistration = new EmailGeneratorRegistration(request.getEmail(), registrationConfirmLink, systemUser);
            emailGeneratorRegistration.sendEmailTemplate();
    //        emailSender.sendEmail(emailGeneratorRegistration);

        } else {
            throw new UserAlreadyExistException();
        }
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
        EmailGeneratorConfirmRegistration confirmRegistrationEmail = new EmailGeneratorConfirmRegistration(user.getEmail());
        confirmRegistrationEmail.prepareMail(env.getProperty("support.mail"), env.getProperty("ui.host"), env.getProperty("ui.port"), env.getProperty("ui.name"));
        emailSender.sendEmail(confirmRegistrationEmail);
        activationLinkRepository.delete(link.getId());
    }
}