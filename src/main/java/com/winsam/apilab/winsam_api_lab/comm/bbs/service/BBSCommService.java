package com.winsam.apilab.winsam_api_lab.comm.bbs.service;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.*;

import java.util.List;

public interface BBSCommService {

    // 게시글 리스트
    public BBSListResVO getBBSList(BBSListReqVO reqVO);

    // 게시글 상세
    public BBSPostDetailResVO getBBSPostDetail(BBSPostDetailReqVO reqVO);

    // 게시글 등록
    public BBSPostResVO getBBSPost(BBSPostReqVO reqVO);

    // 게시글 수정
    public BBSPatchResVO patchBBSPost(BBSPatchReqVO reqVO);

    // 게시글 삭제
    public BBSDeleteResVO deleteBBSPost(BBSDeleteReqVO reqVO);

}
