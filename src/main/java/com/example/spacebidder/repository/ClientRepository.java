package com.example.spacebidder.repository;


import com.example.spacebidder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<User, Long> {

}
