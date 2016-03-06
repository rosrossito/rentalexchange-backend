package com.upteam.auth.controller;

import com.upteam.auth.service.AuthService;
import com.upteam.auth.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author by opasichnyk
 *         created: 2/24/2016.
 */
@CrossOrigin
@RestController
public class BasicAuthController {

    @Autowired
    AuthService authService;

    @PreAuthorize("#login == authentication.name")
    @RequestMapping(value = "/user/info", method = RequestMethod.GET, params = {"login"})
    @ResponseBody
    public UserInfoVO getUserInfo(@RequestParam(value = "login") String login) {
        return authService.getUserInfo(login);
    }
}
