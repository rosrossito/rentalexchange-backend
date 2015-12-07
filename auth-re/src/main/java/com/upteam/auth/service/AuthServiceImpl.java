package com.upteam.auth.service;

import com.upteam.auth.component.EmailGenerator;
import com.upteam.auth.component.EmailSender;
import com.upteam.auth.controller.AuthController;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.repository.ActivationLinkRepository;
import com.upteam.auth.repository.SystemUserRepository;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void registration(RegistrationRequestVO request) {
        //TODO REN-31 [BackEnd] REST для регистрации >Vlad

        if (systemUserRepository.searchByEmail(request.getEmail()) == null) {

            SystemUser systemUser = new SystemUser();
            systemUserRepository.create(systemUser);
            emailSender.sendEmail(generator);

        } else {
            System.out.println("User already exsist");
        }
    }

    @Override
    public void confirmRegistration(RegistrationConfirmRequestVO request) {
        // TODO REN-32 [BackEnd] REST для подтверждения регистрации c отправкой писем >Kostik
    }


}
