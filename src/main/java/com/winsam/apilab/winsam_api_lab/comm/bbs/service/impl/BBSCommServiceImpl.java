package com.winsam.apilab.winsam_api_lab.comm.bbs.service.impl;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
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

    @Override
    public List<BBSPostVO> getBBSList() {
        return bbsPostPostgre.getBBSPostList();
    }
}
