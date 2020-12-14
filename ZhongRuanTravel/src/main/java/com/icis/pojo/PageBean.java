package com.icis.pojo;

import java.util.List;

//分页实体类 用于封装和分页功能相关的参数
//1和2  是浏览器 request的请求传递的参数
//345 是服务器 response
public class PageBean<T>{
    //1.当前页
    private  Integer currentPage;
    //2.每页数据大小
    private Integer pageSize;
    //3.返回数据列表
    private List<T> dataList;
    //4总记录数
    private Integer totalCount;
    //5.总页数
    private  Integer totalPage;


    public PageBean() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", dataList=" + dataList +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                '}';
    }

}
