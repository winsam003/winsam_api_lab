package com.winsam.apilab.winsam_api_lab.comm.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ************************************************************************
 * File Name   : AuthRControllerPayload
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
public class AuthRControllerPayload {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthLoginRequest{
        String userId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenRefreshRequest{
        String refreshToken;
    }

}
