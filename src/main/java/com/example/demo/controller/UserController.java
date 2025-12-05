package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.LoginService;
import com.example.demo.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final LoginService loginService;
    private final SignUpService signUpService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginRequestDTO request){
        Map<String, Object> resp = new HashMap<>();
        try{
            boolean result = loginService.login(request);
            // 토큰 생성 <- 의존성 추가

            // create a response
            resp.put("success", result);
            return resp;
        }catch(Exception e){
            boolean result = false;

            // create a response
            resp.put("success",result);
            resp.put("error_message",e.getMessage());
            return resp;
        }

    }


    @PostMapping("/signup")
    public Map<String,Object> signUp(@RequestBody UserDTO newUser){
        Map<String, Object> resp = new HashMap<>();
        try{
            boolean result = signUpService.createAccount(newUser);

            resp.put("success", result);
            return resp;
        }catch(Exception e){
            boolean result = false;

            // create a response
            resp.put("success",result);
            resp.put("error_message",e.getMessage());
            return resp;
        }
    }

}
