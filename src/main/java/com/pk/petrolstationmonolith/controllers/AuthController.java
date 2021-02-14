package com.pk.petrolstationmonolith.controllers;

import com.pk.petrolstationmonolith.models.auth.UserCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @PostMapping("/login")
    public void login(@RequestBody UserCredentials credentials) {

    }



}
