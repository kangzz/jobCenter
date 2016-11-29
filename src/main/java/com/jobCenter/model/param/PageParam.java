package com.jobCenter.model.param;

import java.io.Serializable;

/**
 * Created by kangzz on 16/11/28.
 */
public class PageParam implements Serializable{

    private static final long serialVersionUID = -3247211629767471775L;
    private Integer pageSize;
    private Integer pageNum;
    private Integer startNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public Integer getStartNum() {
        if(pageSize != null && pageNum!=null) {
            pageNum = pageNum < 1 ? 1 : pageNum;
            startNum = (pageNum - 1) * pageSize;
        }
        return startNum;
    }
    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

}
