package com.winsam.apilab.winsam_api_lab.comm.bbs.utill;

import com.winsam.apilab.winsam_api_lab.comm.bbs.payload.comm.CommPagingResVO;

public class UtillBox {

    public static int calculateOffset(int page, int size) {
        if (page <= 0) page = 1;
        return (page - 1) * size;
    }

    public static int calculateTotalPages(int total, int size){
        if(size <= 0) return 0;
        return (int) Math.ceil((double) total / size);
    }

    public static boolean hasPrevious(int page){
        return page > 1;
    }

    public static boolean hasNext(int page, int totalPages){
        return page < totalPages;
    }

    public static CommPagingResVO setPagingRes(int page, int size, int total) {

        CommPagingResVO res = new CommPagingResVO();

        res.setPage(page);
        res.setSize(size);
        res.setTotalElements(total);
        res.setTotalPages(calculateTotalPages(total, size));
        res.setHasNextPage(hasNext(page, total));
        res.setHasPreviousPage(hasPrevious(page));

        return res;
    }

}
