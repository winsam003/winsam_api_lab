package com.winsam.apilab.winsam_api_lab.comm.bbs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp reg_date;
    private String updt_user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp updt_date;
}
