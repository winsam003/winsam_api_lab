package com.winsam.apilab.winsam_api_lab.comm.mapper;

import com.winsam.apilab.winsam_api_lab.comm.auth.entity.Memb_infoVO;
import com.winsam.apilab.winsam_api_lab.comm.auth.entity.TokenDTO;

public interface MembInfoPostgre {

    // 회원 정보 불러오기
    Memb_infoVO getMembInfo(Memb_infoVO memb_infoVO);

    // 회원 정보 저장
    int membInfoInsert(TokenDTO tokenDTO);

}
