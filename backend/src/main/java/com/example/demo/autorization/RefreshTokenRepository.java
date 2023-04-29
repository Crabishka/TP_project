package com.example.demo.autorization;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<JwtEntity, Long> {

    JwtEntity findByPhoneNumber(String phoneNumber);
}
