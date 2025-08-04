package com.winsam.apilab.winsam_api_lab.comm.bbs.web;

import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.*;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.cmnt.CmntReqVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.cmnt.CmntResVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.service.BBSCommService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bbs")
public class BbsRController {

    BBSCommService bbsCommService;

    public BbsRController(BBSCommService bbsCommService) {
        this.bbsCommService = bbsCommService;
    }

    // 게시판 리스트 GET
    @GetMapping("/list/{bbs_numb}/{page}/{size}")
    public ResponseEntity<?> getBBSPostList(@PathVariable String bbs_numb, @PathVariable int page, @PathVariable int size){

        BBSListReqVO reqVO = new BBSListReqVO();
        reqVO.setBbs_numb(bbs_numb);
        reqVO.setPage(page);
        reqVO.setSize(size);

        BBSListResVO resultList = bbsCommService.getBBSList(reqVO);

        return ResponseEntity.ok(resultList);
    }// 게시판 리스트 GET

    // 게시판 상세 GET
    @GetMapping("/detail/{bbs_numb}/{post_numb}")
    public ResponseEntity<?> getBBSPostDetail(@PathVariable String bbs_numb, @PathVariable int post_numb){

        BBSPostDetailReqVO reqVO = new BBSPostDetailReqVO();
        reqVO.setBbs_numb(bbs_numb);
        reqVO.setPost_numb(post_numb);

        BBSPostDetailResVO resVO = bbsCommService.getBBSPostDetail(reqVO);

        return ResponseEntity.ok(resVO);
    }// 게시판 상세 GET

    // 게시글 등록
    @PostMapping("/write")
    public ResponseEntity<?> PostBBSPost(@RequestBody BBSPostReqVO reqVO){

        BBSPostResVO resVO = new BBSPostResVO();

        resVO = bbsCommService.getBBSPost(reqVO);

        return ResponseEntity.ok(resVO);
    }// 게시글 등록

    // 게시글 수정
    @PatchMapping("/update")
    public ResponseEntity<?> PatchBBSPost(@RequestBody BBSPatchReqVO reqVO){

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

    // 댓글 읽기
    @GetMapping("/cmnt/list/{bbs_no}")
    public ResponseEntity<?> getCmntList(@PathVariable int bbs_no){
        CmntReqVO reqVO = new CmntReqVO();
        reqVO.setBbs_no(bbs_no);

        CmntResVO resVO = bbsCommService.getCmntList(reqVO);

        return ResponseEntity.ok(resVO);
    }// 댓글 읽기
    // 댓글 등록
    @PostMapping("/cmnt/post")
    public ResponseEntity<?> PostCmnt(@RequestBody CmntReqVO reqVO){
        bbsCommService.postCmnt(reqVO);

        return ResponseEntity.ok().build();
    }// 댓글 등록
    // 댓글 수정
    @PatchMapping("/cmnt/patch")
    public ResponseEntity<?> PatchCmnt(@RequestBody CmntReqVO reqVO){
        bbsCommService.patchCmnt(reqVO);
        return ResponseEntity.ok().build();
    }// 댓글 수정
}
