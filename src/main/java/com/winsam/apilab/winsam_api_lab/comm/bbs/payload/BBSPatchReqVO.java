package com.winsam.apilab.winsam_api_lab.comm.bbs.payload;

import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm.BasicVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BBSPatchReqVO extends BasicVO {
    private String bbs_numb;
    private int post_numb;
    private String post_subj;
    private String post_cnts;
}
