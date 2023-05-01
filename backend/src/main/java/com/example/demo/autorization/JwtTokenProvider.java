package com.example.demo.autorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret.refresh}")
    private String refreshSecretKey;

    @Value("${jwt.secret.access}")
    private String accessSecretKey;
    @Value("${jwt.access.validity}")
    private long accessValidity;
    @Value("${jwt.refresh.validity}")
    private long refreshValidity;

    public String generateAccessToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(accessSecretKey);
        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessValidity))
                .withClaim("id", user.getId())
                .withClaim("authority", user.getRole().getAuthority())
                .sign(algorithm);
    }

    public String generateRefreshToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(refreshSecretKey);
        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshValidity))
                .sign(algorithm);
    }
    public UsernamePasswordAuthenticationToken getAuthTokenFromJwt(String jwtToken) {
        DecodedJWT decodedJWT = decodeAccessJWT(jwtToken);
        String username = decodedJWT.getSubject();
        String authority = decodedJWT.getClaim("authority").asString();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority));
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public String getUsernameFromJwt(String jwtToken) {
        DecodedJWT decodedJWT = decodeRefreshJWT(jwtToken);
        return decodedJWT.getSubject();
    }

    private DecodedJWT decodeAccessJWT(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(accessSecretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(jwtToken);
    }

    private DecodedJWT decodeRefreshJWT(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(refreshSecretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(jwtToken);
    }
}


