package com.admin.sbsbackend.services.impl;


import com.admin.sbsbackend.controllers.utils.JwtUtil;
import com.admin.sbsbackend.dtos.ValidateTokenDTO;
import com.admin.sbsbackend.exceptions.InvalidTokenException;
import com.admin.sbsbackend.exceptions.UserAlreadyExists;
import com.admin.sbsbackend.exceptions.UserNotFoundException;
import com.admin.sbsbackend.models.Session;
import com.admin.sbsbackend.models.SessionStatus;
import com.admin.sbsbackend.models.User;
import com.admin.sbsbackend.repos.SessionRepo;
import com.admin.sbsbackend.repos.UserRepo;
import com.admin.sbsbackend.services.AdminUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepo userRepository;
    private final SessionRepo sessionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SecretKey secretKey;

    public AdminUserServiceImpl(UserRepo userRepository, SessionRepo sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SecretKey secretKey) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.secretKey = secretKey;
    }


    @Override
    public Session login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        if (!bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            throw new UserNotFoundException("Invalid Credential");
        }

        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("userId", user.get().getId());
        jsonForJWT.put("createdAt", new Date());
        jsonForJWT.put("expiresAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        JwtUtil.setSecretKey(secretKey);
        String token = JwtUtil.generateToken(jsonForJWT);
        Session session = new Session();
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(user.get());
        session.setExpiryAt(Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        session = sessionRepository.save(session);

        return session;
    }

    @Override
    public void validateToken(ValidateTokenDTO token) {
        Optional<Session> sessionOptional = sessionRepository.findByToken(token.getToken());
        Optional<User> user = userRepository.findById(UUID.fromString(token.getUserId()));
        if(!(sessionOptional.isPresent() && sessionOptional.get().getSessionStatus().equals(SessionStatus.ACTIVE))){
            throw new InvalidTokenException("Invalid Token");
        }
        else if(sessionOptional.get().getExpiryAt().before(new Date())){
            sessionOptional.get().setSessionStatus(SessionStatus.ENDED);
            sessionRepository.save(sessionOptional.get());
            throw new InvalidTokenException("Invalid Token");
        }
        if(user.isEmpty()){
            throw new UserNotFoundException("Invalid User");
        }
    }

    @Override
    public User signup(String email, String password) throws UserAlreadyExists {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new UserAlreadyExists("User already exists with email: " + email);
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Override
    public void logout(String token) {
        Optional<Session> sessionOptional = sessionRepository.findByToken(token);
        if(sessionOptional.isEmpty()){
            throw new InvalidTokenException("Invalid Token");
        }
        sessionOptional.get().setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(sessionOptional.get());
    }
}
