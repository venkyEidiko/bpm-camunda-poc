package com.bpm_camunda_service.pack.controller;

import com.bpm_camunda_service.pack.entity.User;
import com.bpm_camunda_service.pack.model.request.LoginRequest;
import com.bpm_camunda_service.pack.model.request.RegisterRequest;
import com.bpm_camunda_service.pack.model.response.Commonresponse;
import com.bpm_camunda_service.pack.model.response.UserResponse;
import com.bpm_camunda_service.pack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccountController {

    private final UserService userService;
    private Commonresponse response;

    @PostMapping("/register")
    public Commonresponse register(@RequestBody RegisterRequest request) throws Exception{
        System.out.println(request);
        String message = userService.register(request);
        response = new Commonresponse();
        response.setResult(Arrays.asList(message));
        return  response;
    }

    @PostMapping("/login")
    public Commonresponse login(@RequestBody LoginRequest request) throws Exception{
        response = new Commonresponse();
        UserResponse loginResponse = userService.login(request);
        response.setResult(Arrays.asList(loginResponse));
        return  response;
    }

    @GetMapping("/hello")
    public String test(){
        return "hello";
    }
}
