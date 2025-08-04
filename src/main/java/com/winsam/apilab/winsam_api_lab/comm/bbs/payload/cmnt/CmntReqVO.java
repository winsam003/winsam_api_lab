package com.winsam.apilab.winsam_api_lab.comm.bbs.payload.cmnt;

import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm.BasicVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmntReqVO extends BasicVO {
    private int cmnt_no;
    private int bbs_no;
    private String cmnt_user;
    private String cmnt_cnts;
    private String use_at;
}
