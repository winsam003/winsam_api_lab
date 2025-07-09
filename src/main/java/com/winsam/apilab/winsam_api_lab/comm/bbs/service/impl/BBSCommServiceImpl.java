package com.winsam.apilab.winsam_api_lab.comm.bbs.service.impl;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.BBSListReqVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.BBSListResVO;
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


    @Override
    public BBSListResVO getBBSList(BBSListReqVO reqVO) {

        // 1. 선언
        BBSListResVO resVO = new BBSListResVO();
        CommPagingResVO pagingVO = new CommPagingResVO();

        // 2. 총 게시글 수 , 페이징 정보
        int totalCount = bbsPostPostgre.getBBSPostListCount();
        pagingVO = UtillBox.setPagingRes(reqVO.getPage(), reqVO.getSize(), totalCount);

        // 3. 게시글 리스트
        int offset = UtillBox.calculateOffset(reqVO.getPage(), reqVO.getSize());
        reqVO.setOffset(offset);
        List<BBSPostVO> BBSPostVOList = bbsPostPostgre.getBBSPostList(reqVO);

        // 4. set
        resVO.setBBSList(BBSPostVOList);
        resVO.setPageInfo(pagingVO);

        return resVO;
    }
}
