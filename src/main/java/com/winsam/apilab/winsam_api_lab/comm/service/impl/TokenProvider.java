package com.winsam.apilab.winsam_api_lab.comm.service.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.core.Authentication;

/**
 * ************************************************************************
 * File Name   : TokenProvider
 * Author      : Dev-004
 * Date        : 2025-07-02
 * Description :
 * -------------------------- Modification Log ----------------------------
 * 버젼 :                  수정자 :
 * 날짜 :
 * 내용 :
 * ------------------------------------------------------------------------
 * 1.0 :  : 작성자 :
 * 내용 :
 * ************************************************************************
 */
public class TokenProvider {
    private static final String SECRET_KEY = "winsamSuperSecretKeyMustBeAtLeast32BytesLong!";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 15; // 15분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7일

    private final Key key;

    public TokenProvider() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Access Token 생성
    public String createAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh Token 생성
    public String createRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 검증 및 정보 추출
    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new IllegalArgumentException("유효하지 않은 JWT 토큰입니다.", e);
        }
    }

    public String getUserIdByRefreshToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (JwtException e) {
            throw new IllegalArgumentException("유효하지 않은 JWT 토큰입니다.", e);
        }
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            // parseToken 메서드가 토큰 파싱과 만료 검증을 함
            parseToken(token);
            return true;  // 예외가 안 나면 유효한 토큰
        } catch (ExpiredJwtException e) {
            System.out.println("토큰 만료됨");
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("유효하지 않은 토큰");
        }
        return false;
    }



    public Authentication getAuthentication(String token) {
        Claims claims = parseToken(token);

        String userId = claims.getSubject();

        // 예: 권한 정보가 클레임에 있으면 여기서 꺼내서 GrantedAuthority 리스트로 만들기
        // 지금은 간단히 ROLE_USER 권한 하나만 부여
        return new UsernamePasswordAuthenticationToken(userId, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
