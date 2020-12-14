package com.icis.service;

import com.icis.pojo.Category;
import com.icis.pojo.PageBean;
import com.icis.pojo.Route;

import java.util.List;

public interface IRouteService {

     List<Route> getRouteByCid(Integer cid) ;

     PageBean<Route> findpageRoute(String cid,String currentPage,String pageSize);

     List<Route> getHotRoute(String cid);

     List<Route> getIndexRoute(String cid);

     List<Category> getIndexCategory();
}
