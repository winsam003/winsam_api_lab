package com.winsam.apilab.winsam_api_lab.comm.mapper;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.*;

import java.util.List;

public interface BBSPostPostgre {

    // 게시글 수
    int getBBSPostListCount(BBSListReqVO reqVO);

    // 게시글 리스트
    List<BBSPostVO> getBBSPostList(BBSListReqVO reqVO);

    // 게시글 상세
    BBSPostDetailResVO getBBSPostDetail(BBSPostDetailReqVO reqVO);

    // 게시글 조회수 plus
    int PlusBBSPostReadCnt(BBSPostDetailReqVO reqVO);

    // 게시글 등록
    int postBBSPost(BBSPostReqVO reqVO);

    // 게시글 수정
    int patchBBSPost(BBSPatchReqVO reqVO);

    // 게시글 삭제
    int deleteBBSPost(BBSDeleteReqVO reqVO);
}
