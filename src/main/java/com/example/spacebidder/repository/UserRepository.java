package com.example.spacebidder.repository;

import com.example.spacebidder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByClientEmail(String username);

    boolean existsByClientEmail(String email);

  boolean existsByClientLogin(String clientLogin);

  boolean existsByClientEmailAndClientLogin(String clientEmail, String clientLogin);
}