package com.winsam.apilab.winsam_api_lab.comm.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * ************************************************************************
 * File Name   : RedisService
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

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(15)); // 레디스 15분까지 저장
    }

    public Object getValue(String key) {
        return (Object) redisTemplate.opsForValue().get(key);
    }
}