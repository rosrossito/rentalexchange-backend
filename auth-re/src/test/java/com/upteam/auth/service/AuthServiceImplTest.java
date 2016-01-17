package com.upteam.auth.service;

import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.emailgenerator.EmailGenerator;
import com.upteam.auth.component.emailgenerator.EmailGeneratorRegistration;
import com.upteam.auth.domain.ActivationLink;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.exception.EmailIsAbsentException;
import com.upteam.auth.exception.InvalidRequestException;
import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.repository.ActivationLinkRepository;
import com.upteam.auth.repository.SystemUserRepository;
import com.upteam.auth.vo.RegistrationRequestVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by olegls2000 on 12/23/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplTest {

    private static final String TEST_EMAIL = "test@test.com";

    @Mock
    private SystemUserRepository mockSystemUserRepository;
    @Mock
    private ActivationLinkRepository mockActivationLinkRepository;
    @Mock
    private EmailSender mockEmailSender;
    @Mock
    private Environment mockEnv;

    @InjectMocks
    private AuthServiceImpl authService = new AuthServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UserAlreadyExistException.class)
    public void registrationWillThrowUserAlreadyExistException() {

        RegistrationRequestVO request = new RegistrationRequestVO();
        request.setEmail(TEST_EMAIL);
        SystemUser user = new SystemUser();
        ActivationLink activationLink = new ActivationLink();
        EmailGenerator emailGenerator = new EmailGeneratorRegistration(TEST_EMAIL, "", user);

        when(mockSystemUserRepository.searchByEmail(TEST_EMAIL)).thenReturn(user);
        when(mockSystemUserRepository.save(user)).thenReturn(user);
        when(mockActivationLinkRepository.save(activationLink)).thenReturn(activationLink);
        when(mockEnv.getProperty(anyString())).thenReturn("property-value");

        authService.registration(request);

        verify(mockSystemUserRepository).searchByEmail(anyString());
        //verify(mockSystemUserRepository).save((SystemUser)anyObject());
        //verify(mockActivationLinkRepository).save((ActivationLink)anyObject());
        //verify(mockEmailSender).sendEmail(emailGenerator);
    }

    @Test(expected = EmailIsAbsentException.class)
    public void registrationWillThrowEmailIsAbsentException() {
        RegistrationRequestVO request = new RegistrationRequestVO();
        request.setEmail(null);
        authService.registration(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void registrationWillThrowInvalidRequestException() {
        RegistrationRequestVO request = null;
        authService.registration(request);
    }
}
