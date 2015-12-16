package com.upteam.auth.service;

import com.upteam.auth.component.ConfirmRegistrationEmail;
import com.upteam.auth.component.EmailGenerator;
import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.UserRegistrationEmail;
import com.upteam.auth.domain.ActivationLink;
import com.upteam.auth.domain.Status;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.exception.InvalidConfirmRegistrationLinkException;
import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.repository.ActivationLinkRepository;
import com.upteam.auth.repository.SystemUserRepository;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private EmailGenerator generator;

    @Override
    public void registration(RegistrationRequestVO request) throws UserAlreadyExistException {
        if (systemUserRepository.searchByEmail(request.getEmail()) == null) {
            SystemUser systemUser = new SystemUser();
            systemUser.setEmail(request.getEmail());
            systemUser.setLogin(request.getLogin());
            systemUser.setPassword(request.getPassword());
            systemUser.setImage(request.getImage());
            systemUser.setStatus(Status.temporary);
            systemUserRepository.create(systemUser);

            UserRegistrationEmail userRegistrationEmail = new UserRegistrationEmail(request.getEmail());

            emailSender.sendEmail(userRegistrationEmail);
        } else {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public void confirmRegistration(RegistrationConfirmRequestVO request) {
        ActivationLink link = activationLinkRepository.getLinkByUUID(request.getUuid());

        if (link != null) {
            SystemUser user = systemUserRepository.getById(link.getSystemuser_id());
            if (user != null & user.getStatus() == Status.temporary) {
                user.setPassword(request.getPassword());
                user.setStatus(Status.active);
                systemUserRepository.update(user);
                ConfirmRegistrationEmail confirmRegistrationEmail = new ConfirmRegistrationEmail(user.getEmail());
                emailSender.sendEmail(generator);
                activationLinkRepository.delete(link.getId());
            } else {
                throw new InvalidConfirmRegistrationLinkException();
            }

        } else {
            throw new InvalidConfirmRegistrationLinkException();
        }
    }


}