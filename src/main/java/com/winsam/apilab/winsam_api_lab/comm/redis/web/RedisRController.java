package com.winsam.apilab.winsam_api_lab.comm.redis.web;

import com.winsam.apilab.winsam_api_lab.comm.redis.payload.RedisRControllerPayload;
import com.winsam.apilab.winsam_api_lab.comm.redis.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ************************************************************************
 * File Name   : RedisRController
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
@RestController
@RequestMapping("/redis")
public class RedisRController {

    private final RedisService redisService;

    public RedisRController(RedisService redisService) {
        this.redisService = redisService;
    }


    // 키-값 저장
    @PostMapping("/set")
    public ResponseEntity<String> setValue(@RequestBody RedisRControllerPayload.RedisSetValueRequest req) {
        redisService.setValue(req.getKey(), req.getValue());
        return ResponseEntity.ok("Set success");
    }

    // 키로 값 조회
    @GetMapping("/get")
    public ResponseEntity<Object> getValue(@RequestBody RedisRControllerPayload.RedisGetValueRequest req) {
        Object value = redisService.getValue(req.getKey());
        if (value == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value);
    }
}
