package com.winsam.apilab.winsam_api_lab.comm.bbs.web;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.service.BBSCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bbs")
public class bbsRController {

    BBSCommService bbsCommService;

    public bbsRController(BBSCommService bbsCommService) {
        this.bbsCommService = bbsCommService;
    }

    @GetMapping("/getBBSList")
    public ResponseEntity<?> getBBSPostList(){

        List<BBSPostVO> resultList = bbsCommService.getBBSList();

        return ResponseEntity.ok(resultList);
    }

}
