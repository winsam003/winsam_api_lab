package com.winsam.apilab.winsam_api_lab.comm.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.winsam.apilab.winsam_api_lab.comm.comm.entity.commVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memb_infoVO extends commVO{
    private int memb_no;
    private String social_root;
    private String useremail;
    private String usernickname;
    private String userrole;
}
