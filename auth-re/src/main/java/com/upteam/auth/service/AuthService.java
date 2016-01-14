package com.upteam.auth.service;

import com.upteam.auth.vo.*;

/**
 * Created by opasichnyk on 11/25/2015.
 */
public interface AuthService {
    public void registration(RegistrationRequestVO request);
    public void confirmRegistration(RegistrationConfirmRequestVO request);
    public void login(LoginRequestVO request);
    public void changePasswordRequest(ChangePasswordRequestVO request);
    public void changePassword(ChangePasswordVO request);
}
