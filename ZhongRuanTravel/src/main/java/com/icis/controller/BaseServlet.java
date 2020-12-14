package com.icis.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/*
* 基础Servlet 可以理解为一个调度器(中转)
* */
public class BaseServlet extends HttpServlet {
    //子类重写父类service方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获得请求的路径
        //1.1根据请求的路径  获得具体方法名
        String requestURI = req.getRequestURI();
            //System.out.println("请求路径:"+requestURI);
        //1.2获得对应的servlet的字节码  this
             //System.out.println("当前servlet:"+this);
       // Class<? extends BaseServlet> aClass = this.getClass();
        Class servletClass = this.getClass();

        //2.根据方法名找到对应方法
        //  截取字符串获得方法名
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
             //System.out.println("获得方法名:"+methodName);
        //3.获得对应的成员方法
        try {
            Method method = servletClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            method.invoke(this,req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
