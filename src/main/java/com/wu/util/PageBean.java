package com.wu.util;

import java.util.List;

public class PageBean<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalCount;
    private List<T> list;

    public PageBean() {
    }

    public PageBean(Integer pageIndex, Integer pageSize, Integer totalPage, Integer totalCount, List<T> list) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.list = list;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        if (pageIndex<1){
            this.pageIndex=1;
        }else {
            this.pageIndex = pageIndex;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize<1){
            this.pageSize=1;
        }else {
            this.pageSize = pageSize;
        }
        setTotalPage();
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        if (totalCount%pageSize==0){
            this.totalPage = totalCount/pageSize;
        }
        if (totalCount%pageSize!=0){
            this.totalPage = totalCount/pageSize+1;
        }
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStartRow(Integer pageIndex){

       return  (pageIndex-1)*pageSize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}
