package com.upteam.auth.service;

import com.upteam.auth.component.ConfirmRegistrationEmail;
import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.UserRegistrationEmail;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    private long linkPeriod;


    @Override
    public void registration(RegistrationRequestVO request) throws UserAlreadyExistException {
        if (systemUserRepository.searchByEmail(request.getEmail()) == null) {
            SystemUser systemUser = new SystemUser();
            systemUser.setEmail(request.getEmail());
            systemUser.setLogin(request.getLogin());
            systemUser.setPassword(request.getPassword());
            systemUser.setImage(request.getImage());
            systemUser.setStatus(SystemUserStatus.temporary);
            systemUserRepository.create(systemUser);

            UserRegistrationEmail userRegistrationEmail = new UserRegistrationEmail(request.getEmail());

            emailSender.sendEmail(userRegistrationEmail);
        } else {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public void confirmRegistration(RegistrationConfirmRequestVO request) {
        LocalDateTime now = LocalDateTime.now();
        ActivationLink link = activationLinkRepository.getLinkByUUID(request.getUuid());

        if (link == null || link.getType() != LinkType.confirmRegistration) {
            throw new InvalidConfirmRegistrationLinkException();
        }
        if (now.toLocalDate() != link.getEffectiveDate().toLocalDate() || now.minusSeconds(linkPeriod).toLocalTime().isBefore(link.getEffectiveDate().toLocalTime())) {
            throw new InvalidConfirmRegistrationLinkException();
        }
        SystemUser user = systemUserRepository.getById(link.getSystemuser_id());
        if (user == null) {
            throw new InvalidConfirmRegistrationLinkException();
        }
        if (user.getStatus() == SystemUserStatus.delete || user.getStatus() == SystemUserStatus.blocked) {
            throw new SystemUserProblemException();
        }
        user.setPassword(request.getPassword());
        user.setStatus(SystemUserStatus.active);
        systemUserRepository.update(user);
        ConfirmRegistrationEmail confirmRegistrationEmail = new ConfirmRegistrationEmail(user.getEmail());
        emailSender.sendEmail(confirmRegistrationEmail, "UTF-8");
        activationLinkRepository.delete(link.getId());
    }
}