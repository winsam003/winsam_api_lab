package com.winsam.apilab.winsam_api_lab.comm.bbs.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BBSPostDetailReqVO {
    private String bbs_numb;
    private int post_numb;
}
