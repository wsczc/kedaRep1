package com.icis.dao;


import com.icis.pojo.Category;
import com.icis.pojo.Route;
import java.util.List;

public interface IRouteDao {
    List<Route> getRouteByCid(Integer cid) ;

    Integer getRouteCount(Integer cid);

    List<Route> findByPage(int cid, int start, int pageSize);

    List<Route> getHotRoute(String  cid);

    List<Route> getIndexRoute(String cid);

    List<Category> getIndexCategory();
}
