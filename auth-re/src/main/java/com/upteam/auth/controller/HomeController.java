package com.upteam.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vlad on 26.01.2016.
 */
@RestController
@RequestMapping({"/api/v1/", ""})
public class HomeController {

    @RequestMapping(value = "/secured/hello", method = {RequestMethod.POST, RequestMethod.GET})

    public String hello() {
        return "Hello World !!";
    }
}