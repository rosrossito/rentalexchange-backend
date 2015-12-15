package com.upteam.auth.service;

import com.upteam.auth.component.EmailGenerator;
import com.upteam.auth.component.EmailGeneratorImpl;
import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.EmailSenderImpl;
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

import java.util.ArrayList;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private ActivationLinkRepository activationLinkRepository;

    @Autowired
    private EmailSender emailSender;

    private EmailGenerator generator;

    @Override
    public void registration(RegistrationRequestVO request) throws UserAlreadyExistException {
        //TODO REN-31 [BackEnd] REST для регистрации >Vlad

        if (systemUserRepository.searchByEmail(request.getEmail()) == null) {

            SystemUser systemUser = new SystemUser();

            systemUser.setEmail(request.getEmail());
            systemUser.setLogin(request.getLogin());
            systemUser.setPassword(request.getPassword());
            systemUser.setImage(request.getImage());
            systemUser.setStatus(Status.temporary);

            systemUserRepository.create(systemUser);
            emailSender.sendEmail(generator);

        } else {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public void confirmRegistration(RegistrationConfirmRequestVO request) {
        // TODO REN-32 [BackEnd] REST для подтверждения регистрации c отправкой писем >Kostik
        ActivationLink link = activationLinkRepository.getLinkByUUID(request.getUuid());
        SystemUser user = systemUserRepository.getById(link.getSystemuser_id());
        if (link != null && user != null && user.getStatus() == Status.temporary) {
            user.setPassword(request.getPassword());
            user.setStatus(Status.active);
            systemUserRepository.update(user);
            generator = new EmailGeneratorImpl(new ArrayList<String>() {{
                add("kostik.molodoi@gmail.com");
            }},
                    "Уведомление об активации учётной записи пользователя",
                    "Тело письма:\n" +
                            "Здравствуйте!\n" +
                            " \n" +
                            "Учётная запись пользователя с данным e-mail " + user.getEmail() + " активирована.\n" +
                            " \n" +
                            "Для входа в личный кабинет перейдите по <a href=" + link + ">этой ссылке</a>\n" +
                            "(или откройте в интернет-браузере ссылку " + link + ").",
                    "rentalexchange-backend-team");
            emailSender = new EmailSenderImpl();
            emailSender.sendEmail(generator);

            activationLinkRepository.delete(link.getId());
        } else {
            throw new InvalidConfirmRegistrationLinkException();
        }
    }

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}