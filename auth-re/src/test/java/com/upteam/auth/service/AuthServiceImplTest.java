package com.upteam.auth.service;

import com.upteam.auth.component.EmailSender;
import com.upteam.auth.component.emailgenerator.EmailGenerator;
import com.upteam.auth.component.emailgenerator.EmailGeneratorRegistration;
import com.upteam.auth.component.emailgenerator.EmailGeneratorRestorePasswordRequest;
import com.upteam.auth.domain.ActivationLink;
import com.upteam.auth.domain.Activity;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.domain.domainenum.ActivityType;
import com.upteam.auth.domain.domainenum.LinkType;
import com.upteam.auth.domain.domainenum.SystemUserStatus;
import com.upteam.auth.repository.ActivationLinkRepository;
import com.upteam.auth.repository.ActivityRepository;
import com.upteam.auth.repository.SystemUserRepository;
import com.upteam.auth.exception.*;
import com.upteam.auth.vo.ChangePasswordRequestVO;
import com.upteam.auth.vo.LoginRequestVO;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by olegls2000 on 12/23/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplTest {

    private static final String TEST_EMAIL = "test@test.com";
    private static final String TEST_PASSWORD = "qa1234";
    private static final String TEST_UUID = "987654321";
    private static final long TEST_ID = 23;


    @Mock
    private SystemUserRepository mockSystemUserRepository;
    @Mock
    private ActivationLinkRepository mockActivationLinkRepository;
    @Mock
    private EmailSender mockEmailSender;
    @Mock
    private Environment mockEnv;
    @Mock
    private ActivityRepository mockActivityRepository;

    @InjectMocks
    private AuthServiceImpl authService = new AuthServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    //public void registration(RegistrationRequestVO request)-----------------------------------------------------------
    // TESTS METHODS:

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

    //TODO - add positive scenario
    @Test
    public void positiveRegistrationScenario() {

        RegistrationRequestVO request = new RegistrationRequestVO();
        request.setEmail(TEST_EMAIL);
        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(TEST_EMAIL);
        systemUser.setPassword(TEST_PASSWORD);
        systemUser.setStatus(SystemUserStatus.temporary);
        mockSystemUserRepository.save(systemUser);

        UUID uuidGenerator = UUID.randomUUID();
        String uuid = uuidGenerator.toString();
        LocalDateTime toDateTime = LocalDateTime.now();

        ActivationLink activationLink = new ActivationLink();
        activationLink.setEffectiveDate(toDateTime);
        activationLink.setUuid(uuid);
        activationLink.setType(LinkType.confirmRegistration);
        activationLink.setSystemuserId(systemUser.getId());
        mockActivationLinkRepository.save(activationLink);

        String registrationConfirmLink = mockEnv.getProperty("ui.host") + ":" + mockEnv.getProperty("ui.port") + "/user/registration-confirm/?uuid=" + uuid;
        EmailGenerator emailGeneratorRegistration =
                new EmailGeneratorRegistration(request.getEmail(), registrationConfirmLink, systemUser);
        mockEmailSender.sendEmail(emailGeneratorRegistration);

        Activity activity = new Activity();
        activity.setSystemUserId(systemUser.getId());
        activity.setActivityType(ActivityType.systemUserRegistration);
        activity.setDescription("Registration user");
        activity.setActivityTime(LocalDateTime.now());
        mockActivityRepository.save(activity);

        authService.registration(request);
        when(mockSystemUserRepository.searchByEmail(TEST_EMAIL)).thenReturn(systemUser);
        when(mockSystemUserRepository.save(systemUser)).thenReturn(systemUser);
        when(mockActivationLinkRepository.save(activationLink)).thenReturn(activationLink);
        when(mockActivationLinkRepository.getLinkBySystemUserID(TEST_ID)).thenReturn(activationLink);
        when(mockActivationLinkRepository.getLinkByUUID(TEST_UUID)).thenReturn(activationLink);
        when(mockActivityRepository.save(activity)).thenReturn(activity);
        when(mockEnv.getProperty(anyString())).thenReturn("property-value");
        verify(mockSystemUserRepository).searchByEmail(anyString());
        verify(mockEmailSender).sendEmail(emailGeneratorRegistration);
    }


    //public void login(LoginRequestVO request)-------------------------------------------------------------------------
    // TESTS METHODS:

    @Test(expected = EmailIsAbsentException.class)
    public void loginWillThrowEmailIsAbsentException() {
        LoginRequestVO request = new LoginRequestVO();
        request.setEmail(null);
        authService.login(request);
    }

    @Test(expected = IncorrectLoginException.class)
    public void loginWillThrowIncorrectLoginException() {
        LoginRequestVO request = new LoginRequestVO();
        request.setEmail(TEST_EMAIL);
        request.setPassword(TEST_PASSWORD);
        authService.login(request);
    }

    @Test(expected = NonActiveAccountException.class)
    public void loginWillThrowNonActiveAccountException() {
        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(TEST_EMAIL);
        systemUser.setPassword(TEST_PASSWORD);
        systemUser.setStatus(SystemUserStatus.temporary);
        mockSystemUserRepository.save(systemUser);

        LoginRequestVO request = new LoginRequestVO();
        request.setEmail(TEST_EMAIL);
        request.setPassword(TEST_PASSWORD);
        when(mockSystemUserRepository.searchByEmail(TEST_EMAIL)).thenReturn(systemUser);
        authService.login(request);

    }

    @Test(expected = BlockedAccountException.class)
    public void loginWillThrowBlockedAccountException() {
        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(TEST_EMAIL);
        systemUser.setPassword(TEST_PASSWORD);
        systemUser.setStatus(SystemUserStatus.blocked);
        mockSystemUserRepository.save(systemUser);

        LoginRequestVO request = new LoginRequestVO();
        request.setEmail(TEST_EMAIL);
        request.setPassword(TEST_PASSWORD);
        when(mockSystemUserRepository.searchByEmail(TEST_EMAIL)).thenReturn(systemUser);
        authService.login(request);

    }

    @Test
    public void positiveLoginScenario() {

        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(TEST_EMAIL);
        systemUser.setPassword(TEST_PASSWORD);
        systemUser.setStatus(SystemUserStatus.active);
        mockSystemUserRepository.save(systemUser);

        LoginRequestVO request = new LoginRequestVO();
        request.setEmail(TEST_EMAIL);
        request.setPassword(TEST_PASSWORD);

        when(mockSystemUserRepository.searchByEmail(TEST_EMAIL)).thenReturn(systemUser);
        authService.login(request);
        verify(mockSystemUserRepository).searchByEmail(anyString());
    }

    //public void confirmRegistration(RegistrationConfirmRequestVO request)---------------------------------------------
    // TESTS METHODS:

    @Test(expected = EmptyPasswordException.class)
    public void confirmRegistrationWillThrowPasswordAbsentException() {
        RegistrationConfirmRequestVO request = new RegistrationConfirmRequestVO();
        request.setPassword(null);
        authService.confirmRegistration(request);
    }

    @Test(expected = EmptyUuidException.class)
    public void confirmRegistrationWillThrowUuidAbsentException() {
        RegistrationConfirmRequestVO request = new RegistrationConfirmRequestVO();
        request.setPassword(TEST_PASSWORD);
        request.setUuid(null);
        authService.confirmRegistration(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void confirmRegistrationWillThrowInvalidRequestException() {
        RegistrationConfirmRequestVO request = null;
        authService.confirmRegistration(request);
    }

    @Test(expected = InvalidConfirmRegistrationLinkException.class)
    public void confirmRegistrationWillThrowInvalidConfirmRegistrationLinkExceptionByNullLink() {
        RegistrationConfirmRequestVO request = new RegistrationConfirmRequestVO();
        request.setPassword(TEST_PASSWORD);
        request.setUuid(TEST_UUID);
        ActivationLink link = null;
        when(mockActivationLinkRepository.getLinkByUUID(TEST_UUID)).thenReturn(link);
        authService.confirmRegistration(request);
        verify(mockActivationLinkRepository).getLinkByUUID(anyString());
    }

    @Test(expected = InvalidConfirmRegistrationLinkException.class)
    public void confirmRegistrationWillThrowInvalidConfirmRegistrationLinkExceptionByLinkTypeRestorePassword() {
        RegistrationConfirmRequestVO request = new RegistrationConfirmRequestVO();
        request.setPassword(TEST_PASSWORD);
        request.setUuid(TEST_UUID);
        ActivationLink link = new ActivationLink();
        link.setType(LinkType.restorePassword);
        when(mockActivationLinkRepository.getLinkByUUID(TEST_UUID)).thenReturn(link);
        authService.confirmRegistration(request);
        verify(mockActivationLinkRepository).getLinkByUUID(anyString());
    }

    @Test(expected = InvalidConfirmRegistrationLinkException.class)
    public void confirmRegistrationWillThrowInvalidConfirmRegistrationLinkExceptionByLinkTypeChangePassword() {
        RegistrationConfirmRequestVO request = new RegistrationConfirmRequestVO();
        request.setPassword(TEST_PASSWORD);
        request.setUuid(TEST_UUID);
        ActivationLink link = new ActivationLink();
        link.setType(LinkType.changePassword);
        when(mockActivationLinkRepository.getLinkByUUID(TEST_UUID)).thenReturn(link);
        authService.confirmRegistration(request);
        verify(mockActivationLinkRepository).getLinkByUUID(anyString());
    }

    @Test(expected = InvalidConfirmRegistrationLinkException.class)
    public void confirmRegistrationWillThrowInvalidConfirmRegistrationLinkExceptionByActivationLinkPeriod() {
        RegistrationConfirmRequestVO request = new RegistrationConfirmRequestVO();
        request.setPassword(TEST_PASSWORD);
        request.setUuid(TEST_UUID);
        ActivationLink link = new ActivationLink();
        link.setType(LinkType.confirmRegistration);
        link.setEffectiveDate(LocalDateTime.now().minusMinutes(500));
        when(mockEnv.getProperty(anyString())).thenReturn("300");
        when(mockActivationLinkRepository.getLinkByUUID(TEST_UUID)).thenReturn(link);
        authService.confirmRegistration(request);
        verify(mockActivationLinkRepository).getLinkByUUID(anyString());

    }

    @Test(expected = InvalidConfirmRegistrationLinkException.class)
    public void confirmRegistrationWillThrowInvalidConfirmRegistrationLinkExceptionByNullUser() {
        RegistrationConfirmRequestVO request = new RegistrationConfirmRequestVO();
        request.setPassword(TEST_PASSWORD);
        request.setUuid(TEST_UUID);
        ActivationLink link = new ActivationLink();
        link.setType(LinkType.confirmRegistration);
        link.setEffectiveDate(LocalDateTime.now().minusSeconds(2));
        SystemUser user = null;
        when(mockEnv.getProperty(anyString())).thenReturn("300");
        when(mockActivationLinkRepository.getLinkByUUID(TEST_UUID)).thenReturn(link);
        when(mockSystemUserRepository.findOne(TEST_ID)).thenReturn(user);
        authService.confirmRegistration(request);
        verify(mockActivationLinkRepository).getLinkByUUID(anyString());
        verify(mockSystemUserRepository.findOne(anyLong()));

    }

    @Test(expected = InvalidConfirmRegistrationLinkException.class)
    public void confirmRegistrationWillThrowSystemUserProblemException() {
        RegistrationConfirmRequestVO request = new RegistrationConfirmRequestVO();
        request.setPassword(TEST_PASSWORD);
        request.setUuid(TEST_UUID);
        ActivationLink link = new ActivationLink();
        link.setType(LinkType.confirmRegistration);
        link.setEffectiveDate(LocalDateTime.now().minusSeconds(2));
        SystemUser user = new SystemUser();
        user.setStatus(SystemUserStatus.delete);
        when(mockEnv.getProperty(anyString())).thenReturn("300");
        when(mockActivationLinkRepository.getLinkByUUID(TEST_UUID)).thenReturn(link);
        when(mockSystemUserRepository.findOne(TEST_ID)).thenReturn(user);
        authService.confirmRegistration(request);
        verify(mockActivationLinkRepository).getLinkByUUID(anyString());
        verify(mockSystemUserRepository.findOne(anyLong()));

    }

    //public void changePasswordRequest(ChangePasswordRequestVO request)------------------------------------------------
    // TESTS METHODS:
    //TODO: coverage method...

    @Test
    public void positivechangePasswordRequestScenario() {

        SystemUser systemUser = new SystemUser();
        ChangePasswordRequestVO request = new ChangePasswordRequestVO();
        request.setEmail(TEST_EMAIL);
        systemUser.setId(TEST_ID);

        mockSystemUserRepository.searchByEmail(TEST_EMAIL);
        verify(mockSystemUserRepository).searchByEmail(TEST_EMAIL);

        ActivationLink activationLink = new ActivationLink();
        mockActivationLinkRepository.getLinkBySystemUserID(TEST_ID);
        verify(mockActivationLinkRepository).getLinkBySystemUserID(TEST_ID);

        mockActivationLinkRepository.delete(activationLink.getId());
        verify(mockActivationLinkRepository).delete(activationLink.getId());

        mockActivationLinkRepository.save(activationLink);
        verify(mockActivationLinkRepository).save(activationLink);

        EmailGeneratorRestorePasswordRequest emailRestorePassword = new EmailGeneratorRestorePasswordRequest(request.getEmail(), anyString());
        mockEmailSender.sendEmail(emailRestorePassword);
        verify(mockEmailSender).sendEmail(emailRestorePassword);

        Activity activity = new Activity();
        mockActivityRepository.save(activity);
        verify(mockActivationLinkRepository).save(activationLink);
    }

    @Test(expected = EmailIsAbsentException.class)
    public void changePasswordRequestWillThrowEmailIsAbsentException() {
        ChangePasswordRequestVO request = new ChangePasswordRequestVO();
        request.setEmail(null);
        authService.changePasswordRequest(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void changePasswordRequestWillThrowInvalidRequestException() {
        ChangePasswordRequestVO request = null;
        authService.changePasswordRequest(request);
    }

    @Test(expected = NonActiveAccountException.class)
    public void changePasswordRequestWillThrowNonActiveAccountExceptionByNullUser() {
        ChangePasswordRequestVO request = new ChangePasswordRequestVO();
        request.setEmail(TEST_EMAIL);
        SystemUser systemUser = null;
        authService.changePasswordRequest(request);
    }

    @Test(expected = NonActiveAccountException.class)
    public void changePasswordRequestWillThrowNonActiveAccountExceptionByNonActiveUserStatus() {
        ChangePasswordRequestVO request = new ChangePasswordRequestVO();
        request.setEmail(TEST_EMAIL);
        SystemUser systemUser = new SystemUser();
        systemUser.setStatus(SystemUserStatus.blocked);
        authService.changePasswordRequest(request);
    }

    //public void changePassword(ChangePasswordVO request)--------------------------------------------------------------
    // TESTS METHODS:
    //TODO: coverage method...

}

