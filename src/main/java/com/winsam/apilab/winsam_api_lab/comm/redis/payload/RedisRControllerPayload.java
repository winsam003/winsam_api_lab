package com.winsam.apilab.winsam_api_lab.comm.redis.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ************************************************************************
 * File Name   : RedisRControllerPayload
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
public class RedisRControllerPayload {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedisSetValueRequest{
        String key;
        Object value;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedisGetValueRequest{
        String key;
    }
}
