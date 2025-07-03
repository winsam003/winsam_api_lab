package com.winsam.apilab.winsam_api_lab.comm.auth.service;
import com.winsam.apilab.winsam_api_lab.comm.auth.payload.AuthRControllerPayload;
import org.springframework.stereotype.Service;

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

public interface AuthWebService {

    Map<String, String> getAuthJwtToken(AuthRControllerPayload.AuthLoginRequest req);
    Map<String, String> getNewAuthJwtToken(AuthRControllerPayload.TokenRefreshRequest req);

}
