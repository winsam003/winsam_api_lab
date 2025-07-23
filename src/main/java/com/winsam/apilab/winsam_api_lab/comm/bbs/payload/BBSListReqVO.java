package com.winsam.apilab.winsam_api_lab.comm.bbs.payload;

import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm.CommPagingReqVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BBSListReqVO extends CommPagingReqVO {
    private String bbs_numb;
}
