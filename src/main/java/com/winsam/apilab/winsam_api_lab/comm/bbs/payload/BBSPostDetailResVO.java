package com.winsam.apilab.winsam_api_lab.comm.bbs.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winsam.apilab.winsam_api_lab.comm.bbs.entity.CmntVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BBSPostDetailResVO {
    int bbs_no;
    String bbs_code;
    String bbs_post_sbjt;
    String bbs_post_cnts;
    String use_at;
    String disp_at;
    String reg_user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    Timestamp reg_date;
    String updt_user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    Timestamp updt_date;
    int read_cnt;
    List<CmntVO> cmntVOList;
}
