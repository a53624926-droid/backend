package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.LoginService;
import com.example.demo.service.SignUpService;
import com.example.demo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO request){
        Map<String, Object> resp = new HashMap<>();
        try{
            LoginUserDTO user  = loginService.login(request);

            // JWT 토큰 생성
            String token = jwtUtil.generateToken(
                user.getUserId(),
                user.getName()
            );

            resp.put("success",true);
            resp.put("user",user);
            resp.put("token",token);
            return ResponseEntity.ok(resp);
        }catch(Exception e){
            // create a response
            resp.put("success",false);
            resp.put("error_message",e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }

    }


    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserDTO newUser){
        Map<String, Object> resp = new HashMap<>();
        try{
            boolean result = signUpService.createAccount(newUser);
            resp.put("success",result);
            return ResponseEntity.created(null).body(resp);

        }catch(Exception e){
            boolean result = false;

            // create a response
            resp.put("success",result);
            resp.put("error_message",e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

}
