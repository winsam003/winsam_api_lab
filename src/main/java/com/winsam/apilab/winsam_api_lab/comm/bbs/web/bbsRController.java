package com.winsam.apilab.winsam_api_lab.comm.bbs.web;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.*;
import com.winsam.apilab.winsam_api_lab.comm.bbs.service.BBSCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bbs")
public class bbsRController {

    BBSCommService bbsCommService;

    public bbsRController(BBSCommService bbsCommService) {
        this.bbsCommService = bbsCommService;
    }

    // 게시판 리스트 GET
    @GetMapping("/list")
    public ResponseEntity<?> getBBSPostList(BBSListReqVO reqVO){

        BBSListResVO resultList = bbsCommService.getBBSList(reqVO);

        return ResponseEntity.ok(resultList);
    }// 게시판 리스트 GET

    // 게시판 상세 GET
    @GetMapping("/detail")
    public ResponseEntity<?> getBBSPostDetail(BBSPostDetailReqVO reqVO){
        return ResponseEntity.ok().build();
    }// 게시판 상세 GET

    // 게시글 등록
    @PostMapping("/write")
    public ResponseEntity<?> PostBBSPost(BBSPostReqVO reqVO){

        BBSPostResVO resVO = new BBSPostResVO();

        resVO = bbsCommService.getBBSPost(reqVO);

        return ResponseEntity.ok(resVO);
    }// 게시글 등록

    // 게시글 수정
    @PatchMapping("/update")
    public ResponseEntity<?> PatchBBSPost(BBSPatchReqVO reqVO){

        BBSPatchResVO resVO = new BBSPatchResVO();

        resVO = bbsCommService.patchBBSPost(reqVO);

        return ResponseEntity.ok(resVO);
    }// 게시글 수정

    // 게시글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> DeleteBBSPost(BBSDeleteReqVO reqVO){

        BBSDeleteResVO resVO = new BBSDeleteResVO();

        resVO = bbsCommService.deleteBBSPost(reqVO);

        return ResponseEntity.ok().build();
    }// 게시글 삭제


}
