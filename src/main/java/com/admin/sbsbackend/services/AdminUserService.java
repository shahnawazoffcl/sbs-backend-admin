package com.admin.sbsbackend.services;


import com.admin.sbsbackend.dtos.ValidateTokenDTO;
import com.admin.sbsbackend.exceptions.UserAlreadyExists;
import com.admin.sbsbackend.models.Session;
import com.admin.sbsbackend.models.User;

public interface AdminUserService {


    Session login(String email, String password);
    void validateToken(ValidateTokenDTO token);

    User signup(String email, String password) throws UserAlreadyExists;

    void logout(String token);
}
