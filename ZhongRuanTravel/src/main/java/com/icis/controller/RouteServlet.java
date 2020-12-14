package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.Category;
import com.icis.pojo.PageBean;
import com.icis.pojo.Route;
import com.icis.pojo.User;
import com.icis.service.IRouteService;
import com.icis.service.Impl.IRouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//接收Route模块的所有请求
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    // 创建一个service层的类对象
    private IRouteService iRouteService=new IRouteServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();

    //根据cid获得旅游路线  分页
        public void getRouteByCid(HttpServletRequest request, HttpServletResponse response){
            try {
               /* //调用查询 cid
                String cid = request.getParameter("cid");
                //数据转换
                Integer cid1 = Integer.parseInt(cid);
                //调用service查询数据
                List<Route> routes = iRouteService.getRouteByCid(cid1);
                //把集合转化为字符串  传递到前端页面
                String routesStr = mapper.writeValueAsString(routes);
                //把字符数据相应该前端
                response.getWriter().write(routesStr);*/

               //获取数据
                String _cid = request.getParameter("cid");
                String _currentPage = request.getParameter("currentPage");
                String _pageSize = request.getParameter("pageSize");

               // 调用service查询PageBean对象
                PageBean<Route> pageBean = iRouteService.findpageRoute(_cid,_currentPage,_pageSize);
                //返回
                String pageListStr = mapper.writeValueAsString(pageBean);
                response.getWriter().write(pageListStr);

            }catch (Exception e){
                e.printStackTrace();
            }

        }


        //热门推荐
    public void hotRoute(HttpServletRequest request, HttpServletResponse response){
            try{
                String cid = request.getParameter("cid");
                List<Route> hotList =iRouteService.getHotRoute(cid);
                String hot = mapper.writeValueAsString(hotList);
                response.getWriter().write(hot);

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    //首页分类展示
    public void getIndexCategory(HttpServletRequest request, HttpServletResponse response){

        try{
            List<Category> category = iRouteService.getIndexCategory();
            //把集合转化为字符串  传递到前端页面
            String categoryStr = mapper.writeValueAsString(category);
            //把字符数据相应该前端
            response.getWriter().write(categoryStr);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void showIndexRoute(HttpServletRequest request, HttpServletResponse response){

       try{
           String cid = request.getParameter("cid");
           List<Route> routes = iRouteService.getIndexRoute(cid);
           //把集合转化为字符串  传递到前端页面
           String rStr = mapper.writeValueAsString(routes);
           //把字符数据相应该前端
           response.getWriter().write(rStr);

       }catch (Exception e){
           e.printStackTrace();
       }

    }




}
