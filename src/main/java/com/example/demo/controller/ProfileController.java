package com.example.demo.controller;

import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // 1) 프로필 조회 (유저 정보 + 책 리스트)
    @GetMapping("/{userId}")
    public Map<String, Object> getProfile(@PathVariable Long userId) {
        return profileService.getUserProfile(userId);
    }

    // 2) 비밀번호 변경
    @PatchMapping("/{userId}/password")
    public String changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request
    ) {
        String newPassword = request.get("newPassword");
        profileService.changePassword(userId, newPassword);
        return "Password updated";
    }

    // 3) 계정 삭제
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        profileService.deleteUser(userId);
        return "User deleted";
    }
}
