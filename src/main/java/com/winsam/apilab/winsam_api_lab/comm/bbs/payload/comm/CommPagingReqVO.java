package com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CommPagingReqVO {
    private int page;
    private int size;
    private int offset;
}

