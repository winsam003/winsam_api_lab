package com.winsam.apilab.winsam_api_lab.comm.bbs.payload.cmnt;

import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.CmntVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmntResVO{
    List<CmntVO> cmntVOList;
}
