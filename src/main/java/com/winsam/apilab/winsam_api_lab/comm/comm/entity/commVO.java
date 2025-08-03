package com.winsam.apilab.winsam_api_lab.comm.comm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class commVO {
    private Timestamp reg_date;
    private String reg_user;
    private String use_at;
}
