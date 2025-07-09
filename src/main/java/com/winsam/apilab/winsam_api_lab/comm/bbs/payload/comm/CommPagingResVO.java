package com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommPagingResVO {
    private int page;
    private int size;
    private int totalPages;
    private int totalElements;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
