package com.admin.sbsbackend.controllers;

import com.admin.sbsbackend.controllers.utils.AdminUserControllerUtils;
import com.admin.sbsbackend.dtos.UserRequestDTO;
import com.admin.sbsbackend.dtos.UserResponseDTO;
import com.admin.sbsbackend.exceptions.UserAlreadyExists;
import com.admin.sbsbackend.models.Session;
import com.admin.sbsbackend.models.User;
import com.admin.sbsbackend.services.AdminUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<Session> login(@RequestBody UserRequestDTO userRequestDTO) {
        Session session = adminUserService.login(userRequestDTO.getEmail(), userRequestDTO.getPassword());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Authorization", session.getToken());
        responseHeaders.add("ExpiryAt", session.getExpiryAt().toString());
        responseHeaders.add("Access-Control-Expose-Headers", "Authorization");
        responseHeaders.add("Access-Control-Expose-Headers", "ExpiryAt");

        return ResponseEntity.ok().headers(responseHeaders).body(session);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO userRequestDTO) throws UserAlreadyExists {
        User user = adminUserService.signup(userRequestDTO.getEmail(), userRequestDTO.getPassword());
        UserResponseDTO userResponseDTO = AdminUserControllerUtils.mapUserToUserResponseDTO(user);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("token") String token) {
        adminUserService.logout(token);
        return ResponseEntity.ok("Logged out successfully");
    }
}
