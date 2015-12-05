package com.upteam.auth.service;

import com.upteam.auth.component.EmailSender;
import com.upteam.auth.domain.ActivationLink;
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

    @Override
    public void registration(RegistrationRequestVO request) {
        //TODO REN-31 [BackEnd] REST для регистрации >Vlad
    }

    @Override
    public void confirmRegistration(RegistrationConfirmRequestVO request) {
        // TODO REN-32 [BackEnd] REST для подтверждения регистрации c отправкой писем >Kostik
        Long systemUserId = activationLinkRepository.getSystemUserIDbyUUID(request.getUuid());
        Long activationLinkId = activationLinkRepository.getActivationLinkIDbyUUID(request.getUuid());
        ActivationLink link = activationLinkRepository.getLinkByUUID(request.getUuid());
        SystemUser user;
        if (link != null) {
            user = systemUserRepository.getById(systemUserId);
            user.setPassword(request.getPassword());
            //user.setStatusSystemUser(SystemUser.status.active);
            systemUserRepository.update(user);
            emailSender.sendEmail(link);
            activationLinkRepository.delete(activationLinkId);
        } else System.out.println("Link wasn't found!!!");
    }
}
