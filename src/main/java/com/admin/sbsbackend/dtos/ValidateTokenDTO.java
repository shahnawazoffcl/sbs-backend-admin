package com.admin.sbsbackend.dtos;


public class ValidateTokenDTO {
    public ValidateTokenDTO(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public ValidateTokenDTO() {
    }

    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
