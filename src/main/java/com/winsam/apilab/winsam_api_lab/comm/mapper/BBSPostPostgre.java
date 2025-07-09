package com.winsam.apilab.winsam_api_lab.comm.mapper;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.BBSListReqVO;

import java.util.List;

public interface BBSPostPostgre {

    List<BBSPostVO> getBBSPostList(BBSListReqVO reqVO);

    int getBBSPostListCount();
}
