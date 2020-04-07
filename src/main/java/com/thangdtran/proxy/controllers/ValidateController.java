package com.thangdtran.proxy.controllers;

import com.thangdtran.proxy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateController {

    @Autowired
    JWTUtils jwtUtils;

    @PostMapping(path = "/getToken", produces = "multipart/form-data")
    public String validateUser(@RequestParam("username") String username, @RequestParam("passw") String passw) {
        System.out.println(username+"|"+passw);
        String token = jwtUtils.generateToken(username);
        return token;
    }
}
