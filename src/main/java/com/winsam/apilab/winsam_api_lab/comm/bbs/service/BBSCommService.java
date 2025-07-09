package com.winsam.apilab.winsam_api_lab.comm.bbs.service;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.BBSListReqVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.BBSListResVO;

import java.util.List;

public interface BBSCommService {


    public BBSListResVO getBBSList(BBSListReqVO reqVO);

}
