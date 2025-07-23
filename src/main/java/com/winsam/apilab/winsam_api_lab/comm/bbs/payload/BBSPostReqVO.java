package com.winsam.apilab.winsam_api_lab.comm.bbs.payload;

import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm.BasicVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BBSPostReqVO extends BasicVO {
    private String BBSSubject;
    private String BBSContent;
}
