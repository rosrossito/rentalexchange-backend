package com.upteam.auth.service;

import com.upteam.auth.exception.UserAlreadyExistException;
import com.upteam.auth.vo.RegistrationConfirmRequestVO;
import com.upteam.auth.vo.RegistrationRequestVO;

/**
 * Created by opasichnyk on 11/25/2015.
 */
public interface AuthService {
    public void registration(RegistrationRequestVO request) throws UserAlreadyExistException;
    public void confirmRegistration(RegistrationConfirmRequestVO request);
}
