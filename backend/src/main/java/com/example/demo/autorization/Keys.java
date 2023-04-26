package com.example.demo.autorization;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Keys {
    public static SecretKey hmacShaKeyFor(byte[] keyBytes) {
        String algorithm = "HmacSHA256"; // Используем алгоритм HMAC-SHA256
        return new SecretKeySpec(keyBytes, algorithm);
    }
}
