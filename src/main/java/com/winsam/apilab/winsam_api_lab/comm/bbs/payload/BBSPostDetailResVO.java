package com.winsam.apilab.winsam_api_lab.comm.bbs.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
    Timestamp reg_date;
    String updt_user;
    Timestamp updt_date;
}
