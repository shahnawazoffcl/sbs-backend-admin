package com.admin.sbsbackend.controllers.utils;


import com.admin.sbsbackend.dtos.JwtPayloadDTO;
import com.admin.sbsbackend.dtos.UserResponseDTO;
import com.admin.sbsbackend.dtos.ValidateTokenDTO;
import com.admin.sbsbackend.exceptions.InvalidTokenException;
import com.admin.sbsbackend.models.SessionStatus;
import com.admin.sbsbackend.models.User;
import com.admin.sbsbackend.services.AdminUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class AdminUserControllerUtils {
    private final AdminUserService adminUserService;

    public AdminUserControllerUtils(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    public static UserResponseDTO mapUserToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }

    public void validateUser(String token) {
        try {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String header = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper objectMapper = new ObjectMapper();
            SessionStatus response;
            JwtPayloadDTO jwtPayload = null;
            jwtPayload = objectMapper.readValue(payload, JwtPayloadDTO.class);
            ValidateTokenDTO validateTokenDTO = new ValidateTokenDTO(jwtPayload.getUserId(), token);
            adminUserService.validateToken(validateTokenDTO);
        } catch (InvalidTokenException e) {
            throw new InvalidTokenException("Invalid Token");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
