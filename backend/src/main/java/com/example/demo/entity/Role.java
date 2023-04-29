package com.example.demo.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
