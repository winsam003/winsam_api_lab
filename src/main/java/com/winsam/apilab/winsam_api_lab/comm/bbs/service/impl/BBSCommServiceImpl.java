package com.winsam.apilab.winsam_api_lab.comm.bbs.service.impl;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.*;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm.CommPagingResVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.utill.UtillBox;
import com.winsam.apilab.winsam_api_lab.comm.mapper.BBSPostPostgre;
import com.winsam.apilab.winsam_api_lab.comm.bbs.service.BBSCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BBSCommServiceImpl implements BBSCommService {

    BBSPostPostgre bbsPostPostgre;

    @Autowired
    public BBSCommServiceImpl(BBSPostPostgre bbsPostPostgre) {
        this.bbsPostPostgre = bbsPostPostgre;
    }


    // 게시글 리스트
    @Override
    public BBSListResVO getBBSList(BBSListReqVO reqVO) {

        // 1. 선언
        BBSListResVO resVO = new BBSListResVO();
        CommPagingResVO pagingVO = new CommPagingResVO();

        // 2. 총 게시글 수 , 페이징 정보
        int totalCount = bbsPostPostgre.getBBSPostListCount(reqVO);
        pagingVO = UtillBox.setPagingRes(reqVO.getPage(), reqVO.getSize(), totalCount);

        // 3. 게시글 리스트
        int offset = UtillBox.calculateOffset(reqVO.getPage(), reqVO.getSize());
        reqVO.setOffset(offset);
        List<BBSPostVO> BBSPostVOList = bbsPostPostgre.getBBSPostList(reqVO);

        // 4. set
        resVO.setBBSList(BBSPostVOList);
        resVO.setPageInfo(pagingVO);

        return resVO;
    }// 게시글 리스트


    // 게시글 상세
    @Override
    public BBSPostDetailResVO getBBSPostDetail(BBSPostDetailReqVO reqVO) {

        BBSPostDetailResVO resVO = new BBSPostDetailResVO();

        bbsPostPostgre.PlusBBSPostReadCnt(reqVO);
        resVO = bbsPostPostgre.getBBSPostDetail(reqVO);

        return resVO;
    }// 게시글 상세


    // 게시글 등록
    @Override
    public BBSPostResVO getBBSPost(BBSPostReqVO reqVO) {

        BBSPostResVO resVO = new BBSPostResVO();

        if(reqVO.getReg_user() == null || "".equals(reqVO.getReg_user())){
            reqVO.setReg_user("system");
        }

        if(bbsPostPostgre.postBBSPost(reqVO) > 0){
            resVO = null;
        }else{
            // exception
        }

        return resVO;
    }// 게시글 등록

    // 게시글 수정
    @Override
    public BBSPatchResVO patchBBSPost(BBSPatchReqVO reqVO) {

        BBSPatchResVO resVO = new BBSPatchResVO();

        if(reqVO.getReg_user() == null || "".equals(reqVO.getReg_user())){
            reqVO.setUpdt_user("system");
        }

        if(bbsPostPostgre.patchBBSPost(reqVO) > 0){
            resVO = null;
        }else{
            // exception
        }

        return resVO;
    }// 게시글 수정

    @Override
    public BBSDeleteResVO deleteBBSPost(BBSDeleteReqVO reqVO) {

        BBSDeleteResVO resVO = new BBSDeleteResVO();

        if(bbsPostPostgre.deleteBBSPost(reqVO) > 0){
            resVO = null;
        }else{
            // exception
        }

        return resVO;
    }
}

