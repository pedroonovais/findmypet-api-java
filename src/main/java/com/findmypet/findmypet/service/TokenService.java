package com.findmypet.findmypet.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.findmypet.findmypet.model.Pessoa;
import com.findmypet.findmypet.model.Token;
import com.findmypet.findmypet.model.UserRole;


@Service
public class TokenService {

    private Instant experiesAt = LocalDateTime.now().plusMinutes(120).toInstant(ZoneOffset.ofHours(-3));
    private Algorithm algorithm = Algorithm.HMAC256("secret-muito-secreto-que-ninguem-pode-saber");

    public Token createToken(Pessoa user){
        var jwt = JWT.create()
            .withSubject(user.getIdPessoa().toString())
            .withClaim("email", user.getEmail())
            .withClaim("role", user.getRole().toString())
            .withExpiresAt(experiesAt)
            .sign(algorithm);

        return new Token(jwt, "Bearer", user.getEmail());
    }

    public Pessoa getUserFromToken(String jwt) {
        var jwtVerified = JWT.require(algorithm).build().verify(jwt);
        return Pessoa.builder()
                .idPessoa(Long.valueOf(jwtVerified.getSubject()))
                .email(jwtVerified.getClaim("email").toString())
                .role(UserRole.USER)
                .build();
        
    }
    
}
