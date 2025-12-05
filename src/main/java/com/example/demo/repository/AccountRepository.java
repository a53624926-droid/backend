package com.example.demo.repository;


import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Dummy
@Repository
public interface AccountRepository extends JpaRepository <User, Long> {
    Optional<User> findByUserEmail(String address);
}
