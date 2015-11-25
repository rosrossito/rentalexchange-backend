package com.upteam.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@RestController
public class UserController {
    //14.2 Locating the main application class
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }


}
