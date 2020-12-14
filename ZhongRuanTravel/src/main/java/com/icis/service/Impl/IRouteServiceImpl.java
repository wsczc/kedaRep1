package com.icis.service.Impl;

import com.icis.dao.IRouteDao;
import com.icis.dao.impl.IRouteDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.PageBean;
import com.icis.pojo.Route;
import com.icis.service.IRouteService;
import java.util.List;

public class IRouteServiceImpl implements IRouteService {
    private IRouteDao iRouteDao=new IRouteDaoImpl();

    //查询旅游分类
    @Override
    public List<Route> getRouteByCid(Integer cid) {
        return iRouteDao.getRouteByCid(cid);
    }

    //分页
    @Override
    public PageBean<Route> findpageRoute(String _cid,String _currentPage,String _pageSize) {
        //如果是第一次访问分页 设置当前页和每页大小的默认值
        int cid=0;
        if (_cid!=null&&_cid.length()>0){
            cid = Integer.parseInt(_cid);
        }

        int currentPage=0;//当前页码，如果不传递，则默认为1
        if (_currentPage!=null&&_currentPage.length()>0){
            currentPage= Integer.parseInt(_currentPage);
        }else {
            currentPage=1;
        }

        //每页显示条数，如果不传递，默认每页显示4条记录
        int pageSize=0;
        if (_pageSize!=null&&_pageSize.length()>0){
            pageSize=Integer.parseInt(_pageSize);
        }else {
            pageSize=4;
        }

        //创建一个PageBean对象
        PageBean<Route> pageBean=new PageBean<>();
        //封装 当前页  每一页大小
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);

        /*返回的数据*/
        //设置总记录数
        //调用dao 查询所有数据 (根据条件查询总记录数)
        int totalCount = this.iRouteDao.getRouteCount(cid);
        pageBean.setTotalCount(totalCount);

        //1. 语法：limit 开始的索引,每页查询的条数;
        //	2. 公式：开始的索引 = （当前的页码 - 1） * 每页显示的条数
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = iRouteDao.findByPage(cid,start,pageSize);
        pageBean.setDataList(list);

        //设置总页数 = 总记录数/每页显示条数
        //根据总记录数计算总页数   看看有没有余数,有余数就加一页
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pageBean.setTotalPage(totalPage);


        return pageBean;

    }

    @Override
    public List<Route> getHotRoute(String cid) {
        return  this.iRouteDao.getHotRoute(cid);
    }

    @Override
    public List<Route> getIndexRoute(String cid) {
        return this.iRouteDao.getIndexRoute(cid);
    }

    @Override
    public List<Category> getIndexCategory() {
        return this.iRouteDao.getIndexCategory();
    }
}
