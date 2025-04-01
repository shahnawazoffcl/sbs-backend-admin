package com.admin.sbsbackend.repos;

import com.admin.sbsbackend.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepo extends JpaRepository<Session, UUID>{

    Optional<Session> findByToken(String token);
}
