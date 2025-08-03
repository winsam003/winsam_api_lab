package com.winsam.apilab.winsam_api_lab.comm.auth.service;
import com.winsam.apilab.winsam_api_lab.comm.auth.entity.Memb_infoVO;
import com.winsam.apilab.winsam_api_lab.comm.auth.entity.TokenDTO;
import com.winsam.apilab.winsam_api_lab.comm.auth.payload.AuthRControllerPayload;

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

    public Map<String, String> getAuthJwtToken(AuthRControllerPayload.AuthLoginRequest req);
    public Map<String, String> getNewAuthJwtToken(AuthRControllerPayload.TokenRefreshRequest req);
    public Map<String, Object> processGoogleOAuthCode(String code);
    public Memb_infoVO getMemberInfo(Memb_infoVO memb_infoVO);
    public void setMemberInfo(TokenDTO tokenDTO);

}
