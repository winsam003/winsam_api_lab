package com.winsam.apilab.winsam_api_lab.comm.bbs.payload;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.BBSPostVO;
import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm.CommPagingResVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BBSListResVO {
    private List<BBSPostVO> BBSList;
    private CommPagingResVO pageInfo;
}
