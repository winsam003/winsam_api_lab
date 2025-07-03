package com.winsam.apilab.winsam_api_lab.config.security.jwt;

import com.winsam.apilab.winsam_api_lab.comm.auth.entity.TokenDTO;
import com.winsam.apilab.winsam_api_lab.comm.auth.token.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * ************************************************************************
 * File Name   : TokenProcessFilter
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
public class TokenProcessFilter extends OncePerRequestFilter {

    TokenProvider tokenProvider = new TokenProvider();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(resolveToken(request));
        if (tokenDTO.getAccessToken() != null && tokenProvider.validateToken(tokenDTO.getAccessToken())) {
            Authentication auth = tokenProvider.getAuthentication(tokenDTO);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    // 특정 요청은 토큰 검사를 안하도록 추가
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/auth/login");
    }


    // token 꺼내기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // "Bearer " 이후 실제 토큰 부분만 리턴
        }
        return null;
    }
}
