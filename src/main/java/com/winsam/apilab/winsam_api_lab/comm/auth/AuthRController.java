package com.winsam.apilab.winsam_api_lab.comm.auth;

import com.winsam.apilab.winsam_api_lab.comm.payload.AuthRControllerPayload;
import com.winsam.apilab.winsam_api_lab.comm.service.AuthWebService;
import com.winsam.apilab.winsam_api_lab.comm.service.impl.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ************************************************************************
 * File Name   : authRController
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
@RestController
@RequestMapping("/auth")
public class AuthRController {

    @Autowired
    AuthWebService authWebService;

    TokenProvider tokenProvider = new TokenProvider();

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute AuthRControllerPayload.AuthLoginRequest req){

        Map<String, String> returnToken = authWebService.getAuthJwtToken(req.getUserId());

        return ResponseEntity.ok(returnToken);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody AuthRControllerPayload.TokenRefreshRequest req) {
        String refreshToken = req.getRefreshToken();

        if (!tokenProvider.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }


        Map<String, String> returnToken = authWebService.getNewAuthJwtToken(req.getRefreshToken());

        return ResponseEntity.ok(returnToken);
    }
}
