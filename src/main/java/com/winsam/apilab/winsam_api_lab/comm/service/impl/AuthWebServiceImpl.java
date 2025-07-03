package com.winsam.apilab.winsam_api_lab.comm.service.impl;

import org.springframework.stereotype.Service;
import com.winsam.apilab.winsam_api_lab.comm.service.AuthWebService;

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

    TokenProvider tokenProvider = new TokenProvider();
    @Override
    public Map<String, String> getAuthJwtToken(String userId) {

        String accessToken = tokenProvider.createAccessToken(userId);
        String refreshToken = tokenProvider.createRefreshToken(userId);

        Map<String, String> jwtToken = new HashMap<>();

        jwtToken.put("accessToken", accessToken);
        jwtToken.put("refreshToken", refreshToken);

        System.out.println(tokenProvider.parseToken(jwtToken.get("accessToken")));
        System.out.println(tokenProvider.parseToken(jwtToken.get("refreshToken")));

        return jwtToken;
    }

    @Override
    public Map<String, String> getNewAuthJwtToken(String refreshToken) {

        String userId = tokenProvider.getUserIdByRefreshToken(refreshToken);
        String newAccessToken = tokenProvider.createAccessToken(userId);
        String newRefreshToken = tokenProvider.createRefreshToken(userId);


        Map<String, String> jwtToken = new HashMap<>();

        jwtToken.put("accessToken", newAccessToken);
        jwtToken.put("refreshToken", newRefreshToken);

        System.out.println(tokenProvider.parseToken(jwtToken.get("accessToken")));
        System.out.println(tokenProvider.parseToken(jwtToken.get("refreshToken")));
        return jwtToken;
    }
}
