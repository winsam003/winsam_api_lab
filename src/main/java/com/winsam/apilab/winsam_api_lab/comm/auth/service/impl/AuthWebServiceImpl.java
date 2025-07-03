package com.winsam.apilab.winsam_api_lab.comm.auth.service.impl;

import com.winsam.apilab.winsam_api_lab.comm.auth.entity.TokenDTO;
import com.winsam.apilab.winsam_api_lab.comm.auth.payload.AuthRControllerPayload;
import com.winsam.apilab.winsam_api_lab.comm.auth.service.AuthWebService;
import com.winsam.apilab.winsam_api_lab.comm.auth.token.TokenProvider;
import org.springframework.stereotype.Service;

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
    public Map<String, String> getAuthJwtToken(AuthRControllerPayload.AuthLoginRequest req) {

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserId(req.getUserId());

        String accessToken = tokenProvider.createAccessToken(tokenDTO);
        String refreshToken = tokenProvider.createRefreshToken(tokenDTO);

        Map<String, String> jwtToken = new HashMap<>();

        jwtToken.put("accessToken", accessToken);
        jwtToken.put("refreshToken", refreshToken);
        tokenDTO.setAccessToken(jwtToken.get("accessToken"));
        tokenDTO.setRefreshToken(jwtToken.get("refreshToken"));

        System.out.println(tokenProvider.parseToken(tokenDTO.getAccessToken()));
        System.out.println(tokenProvider.parseToken(tokenDTO.getRefreshToken()));

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
}
