package com.example.demo.service;

public enum ACCOUNT_SERVICE_ERROR_MESSAGE {
    DUPLICATED_ADDRESS("이미 가입한 회원입니다."),
    EMAIL_NOT_FOUND("이메일을 찾을 수 없습니다."),
    WRONG_PASSWORD("비밀번호가 잘못되었습니다.");
    private final String message;
    ACCOUNT_SERVICE_ERROR_MESSAGE(String s) {
        message = s;
    }
    public String content() {
        return message;
    }
}
