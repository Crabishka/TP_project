//package com.example.demo.autorization;
//
//import com.example.demo.entity.User;
//import io.jsonwebtoken.*;
//import lombok.NonNull;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//@Slf4j
//@Component
//public class JwtProvider {
//
//    private final SecretKey jwtAccessSecret;
//    private final SecretKey jwtRefreshSecret;
//
//    public JwtProvider(
//            @Value("${jwt.secret.access}") String jwtAccessSecret,
//            @Value("${jwt.secret.refresh}") String jwtRefreshSecret
//    ) {
//        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64_decode(jwtAccessSecret));
//        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64_decode(jwtRefreshSecret));
//    }
//
//    public String generateAccessToken(@NonNull User user) {
//        final LocalDateTime now = LocalDateTime.now();
//        final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
//        final Date accessExpiration = Date.from(accessExpirationInstant);
//        return Jwts.builder()
//                .setSubject(user.getName())
//                .setExpiration(accessExpiration)
//                .signWith(SignatureAlgorithm.HS256, jwtAccessSecret)
//                .claim("name", user.getName())
//                .claim("role", user.getRole())
//                .claim("phoneNumber", user.getPhoneNumber())
//                .compact();
//    }
//
//    public String generateRefreshToken(@NonNull User user) {
//        final LocalDateTime now = LocalDateTime.now();
//        final Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
//        final Date refreshExpiration = Date.from(refreshExpirationInstant);
//        return Jwts.builder()
//                .setSubject(user.getName())
//                .setExpiration(refreshExpiration)
//                .signWith(SignatureAlgorithm.HS256, jwtRefreshSecret)
//                .compact();
//    }
//
//    public boolean validateAccessToken(@NonNull String accessToken) {
//        return validateToken(accessToken, jwtAccessSecret);
//    }
//
//    public boolean validateRefreshToken(@NonNull String refreshToken) {
//        return validateToken(refreshToken, jwtRefreshSecret);
//    }
//
//    private boolean validateToken(@NonNull String token, @NonNull Key secret) {
//        try {
////            Jwts.parserBuilder()
////                    .setSigningKey(secret)
////                    .build()
////                    .parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException expEx) {
//            log.error("Token expired", expEx);
//        } catch (UnsupportedJwtException unsEx) {
//            log.error("Unsupported jwt", unsEx);
//        } catch (MalformedJwtException mjEx) {
//            log.error("Malformed jwt", mjEx);
//        } catch (SignatureException sEx) {
//            log.error("Invalid signature", sEx);
//        } catch (Exception e) {
//            log.error("invalid token", e);
//        }
//        return false;
//    }
//
//    public Claims getAccessClaims(@NonNull String token) {
//        return getClaims(token, jwtAccessSecret);
//    }
//
//    public Claims getRefreshClaims(@NonNull String token) {
//        return getClaims(token, jwtRefreshSecret);
//    }
//
//    private Claims getClaims(@NonNull String token, @NonNull Key secret) {
////        return Jwts.parserBuilder()
////                .setSigningKey(secret)
////                .build()
////                .parseClaimsJws(token)
////                .getBody();
//        return null;
//    }
//}