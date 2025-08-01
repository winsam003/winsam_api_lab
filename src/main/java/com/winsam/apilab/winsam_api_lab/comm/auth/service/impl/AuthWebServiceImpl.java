package com.winsam.apilab.winsam_api_lab.comm.auth.service.impl;

import com.winsam.apilab.winsam_api_lab.comm.auth.entity.TokenDTO;
import com.winsam.apilab.winsam_api_lab.comm.auth.payload.AuthRControllerPayload;
import com.winsam.apilab.winsam_api_lab.comm.auth.service.AuthWebService;
import com.winsam.apilab.winsam_api_lab.comm.auth.token.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * ************************************************************************
 * File Name   : authWebService
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
@Service
public class AuthWebServiceImpl implements AuthWebService {
    private final RestTemplate restTemplate = new RestTemplate();
    TokenProvider tokenProvider = new TokenProvider();
    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.secret}")
    private String googleClientSecret;
    @Override
    public Map<String, String> getAuthJwtToken(AuthRControllerPayload.AuthLoginRequest req) {

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserName(req.getUserInfo().get("name").toString());
        tokenDTO.setUserNickName(req.getUserInfo().get("nickName").toString());
        tokenDTO.setUserEmail(req.getUserInfo().get("email").toString());
        tokenDTO.setUserRole(req.getUserInfo().get("userRole").toString());
        tokenDTO.setPicture(req.getUserInfo().get("picture").toString());
        tokenDTO.setEmail_verified((Boolean) req.getUserInfo().get("email_verified"));

        String accessToken = tokenProvider.createAccessToken(tokenDTO);
        String refreshToken = tokenProvider.createRefreshToken(tokenDTO);

        Map<String, String> jwtToken = new HashMap<>();

        jwtToken.put("accessToken", accessToken);
        jwtToken.put("refreshToken", refreshToken);
        tokenDTO.setAccessToken(jwtToken.get("accessToken"));
        tokenDTO.setRefreshToken(jwtToken.get("refreshToken"));

        return jwtToken;
    }

    @Override
    public Map<String, String> getNewAuthJwtToken(AuthRControllerPayload.TokenRefreshRequest req) {

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setRefreshToken(req.getRefreshToken());

        String newAccessToken = tokenProvider.createAccessToken(tokenDTO);
        String newRefreshToken = tokenProvider.createRefreshToken(tokenDTO);


        Map<String, String> jwtToken = new HashMap<>();

        jwtToken.put("accessToken", newAccessToken);
        jwtToken.put("refreshToken", newRefreshToken);
        tokenDTO.setAccessToken(jwtToken.get("accessToken"));
        tokenDTO.setRefreshToken(jwtToken.get("refreshToken"));

        System.out.println(tokenProvider.parseToken(tokenDTO.getAccessToken()));
        System.out.println(tokenProvider.parseToken(tokenDTO.getRefreshToken()));
        return jwtToken;
    }

    @Override
    public Map<String, Object> processGoogleOAuthCode(String code) {
        // 1. 인가 코드로 access token 요청
        String tokenUrl = "https://oauth2.googleapis.com/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", googleClientId);
        params.add("client_secret", googleClientSecret);
        params.add("redirect_uri", "http://localhost:8080/auth/google/callback");
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        Map<String, Object> tokenResponse = restTemplate.postForObject(tokenUrl, request, Map.class);

        String accessToken = (String) tokenResponse.get("access_token");

        // 2. access token으로 사용자 정보 조회
        HttpHeaders userInfoHeaders = new HttpHeaders();
        userInfoHeaders.setBearerAuth(accessToken);
        HttpEntity<String> userInfoRequest = new HttpEntity<>(userInfoHeaders);

        ResponseEntity<Map> userInfoResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                userInfoRequest,
                Map.class
        );

        Map<String, Object> userInfo = userInfoResponse.getBody();

        // 3. 여기서 DB 처리 등 로그인/회원가입 로직 넣어도 됨

        // 4. JWT 토큰 생성 후 결과 반환 (예시로 간단히 userInfo만 반환)
        return userInfo;
    }
}
