package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoginService {
    RepositorySomething userRepository;

    public boolean login(LoginRequestDTO request){
        String email = request.getUser_email();
        String password = request.getPassword();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->  new IllegalArgumentException(ACCOUNT_SERVICE_ERROR_MESSAGE // 주어진 이메일이 없는 경우
                        .EMAIL_NOT_FOUND.content()));

        if (!password.equals(user.getPassword()))
            throw new IllegalArgumentException(ACCOUNT_SERVICE_ERROR_MESSAGE.
                    WRONG_PASSWORD.content()); // 패스워드가 맞지 않는 경우

        return true;
    }


}
