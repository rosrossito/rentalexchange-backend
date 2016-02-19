package com.upteam.auth.test.service;

import com.upteam.auth.vo.*;

import java.util.List;

/**
 * Created by opasichnyk on 11/25/2015.
 */
public interface AuthService {
    void registration(RegistrationRequestVO request);
    void confirmRegistration(RegistrationConfirmRequestVO request);
    void login(LoginRequestVO request);
    void changePasswordRequest(ChangePasswordRequestVO request);
    void changePassword(ChangePasswordVO request);
    TestVO test();
}
