package com.winsam.apilab.winsam_api_lab.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ************************************************************************
 * File Name   : TestRController
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
@RequestMapping("/test")
public class TestRController {

    @PostMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("성공");
    }

}
