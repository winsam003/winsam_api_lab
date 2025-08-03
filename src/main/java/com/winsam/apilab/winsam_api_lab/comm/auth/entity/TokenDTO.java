package com.winsam.apilab.winsam_api_lab.comm.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.winsam.apilab.winsam_api_lab.comm.comm.entity.commVO;

/**
 * ************************************************************************
 * File Name   : TokenDTO
 * Author      : Dev-004
 * Date        : 2025-07-03
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO extends commVO{
    String userName;
    String userNickName;
    String userEmail;
    String userRole;
    String picture;
    String accessToken;
    String refreshToken;
    String social_root;
    boolean email_verified;
}
