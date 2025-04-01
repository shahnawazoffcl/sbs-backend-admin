package com.admin.sbsbackend.dtos;

public class UserRequestDTO {
    private String email;
    private String password;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
