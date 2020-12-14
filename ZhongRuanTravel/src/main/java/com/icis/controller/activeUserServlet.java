package com.icis.controller;

import com.icis.service.Impl.UserServiceImpl;
import com.icis.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//激活用户
@WebServlet("/activeUser")
public class activeUserServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("sssss");
        String code = request.getParameter("code");
        System.out.println(code);
        userService.setStatus(code);
        response.getWriter().write("激活成功......");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
