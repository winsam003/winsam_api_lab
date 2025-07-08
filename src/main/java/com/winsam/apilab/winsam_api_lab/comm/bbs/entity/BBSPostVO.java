package com.winsam.apilab.winsam_api_lab.comm.bbs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BBSPostVO {
    private int bbs_no;
    private String bbs_code;
    private String bbs_post_sbjt;
    private String bbs_post_cnts;
    private String use_at;
    private String disp_at;
    private String reg_user;
    private Timestamp reg_date;
    private String updt_user;
    private Timestamp updt_date;
}
