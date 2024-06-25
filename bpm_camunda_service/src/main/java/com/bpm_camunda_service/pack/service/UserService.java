package com.bpm_camunda_service.pack.service;

import com.bpm_camunda_service.pack.entity.User;
import com.bpm_camunda_service.pack.model.request.LoginRequest;
import com.bpm_camunda_service.pack.model.request.RegisterRequest;
import com.bpm_camunda_service.pack.model.response.Commonresponse;
import com.bpm_camunda_service.pack.model.response.UserResponse;
import com.bpm_camunda_service.pack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public String register(RegisterRequest request) throws Exception{
        Commonresponse response = new Commonresponse();
        if(userRepository.existsByEmail(request.getEmail())){
         throw new UserPrincipalNotFoundException("Invalid Email id or password");
        }
        User user = modelMapper.map(request,User.class);
        userRepository.save(user);
        return "Registerd Successfully";
    }

    public UserResponse login(LoginRequest request) throws Exception{
        if(!(userRepository.existsByEmail(request.getEmail()))){
            throw new UserPrincipalNotFoundException("Invalid Email id or password");
        }
        User user = userRepository.findByEmail(request.getEmail());
        if(!(user.getPassword().equals(request.getPassword()))){
            throw new UserPrincipalNotFoundException("Invalid Email id or password");
        }
        else{
            UserResponse response = modelMapper.map(user,UserResponse.class);
            return response;
        }
    }

}
