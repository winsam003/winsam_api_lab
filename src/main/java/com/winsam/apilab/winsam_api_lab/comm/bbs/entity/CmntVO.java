package com.winsam.apilab.winsam_api_lab.comm.bbs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmntVO {
    private int cmnt_no;
    private int bbs_no;
    private String cmnt_user;
    private String cmnt_cnts;
    private String use_at;
    private String reg_user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp reg_date;
    private String updt_user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp updt_date;
    private String usernickname;
    private String userrole;
}
