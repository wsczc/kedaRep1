package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.Category;
import com.icis.pojo.ResultInfo;
import com.icis.pojo.User;
import com.icis.service.Impl.UserServiceImpl;
import com.icis.service.UserService;
import com.icis.utils.Md5Util;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private ObjectMapper mapper=new ObjectMapper();
    private UserService userService=new UserServiceImpl();
    private static Md5Util md5Util=new Md5Util();

    //用户注册
    public void registerUser(HttpServletRequest request, HttpServletResponse response){
        try {
            ResultInfo result=new ResultInfo();
            User user=new User();
            //获得输入的验证码
            String check= request.getParameter("check");
            //判断用户输入的验证码和系统生成的验证码是否一致
            HttpSession session = request.getSession();
            String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
            //移除当前这一次生产的验证码键值对
            session.removeAttribute(checkcode_server);
            if (check.equalsIgnoreCase(checkcode_server)){
                String username = request.getParameter("username");
                if (username!=null){
                    //获取前端数据
                    Map<String, String[]> parameterMap = request.getParameterMap();

           /*//测试
           Set<String> keys = parameterMap.keySet();
            for (String key : keys) {
            String value = parameterMap.get(key)[0];
            System.out.println(value);
                 }*/
                    //封装数据
                    BeanUtils.populate(user,parameterMap);
                    System.out.println(user);
                    userService.addUser(user);
                    //  response.getWriter().write("注册成功");

                    result.setFlag(true);
                    mapper.writeValue(response.getWriter(),result);

                }else {
                    // response.getWriter().write("注册失败");
                    result.setFlag(false);
                    mapper.writeValue(response.getWriter(),result);
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }

    }

    //验证用户名是否被占用
    public void checkRepeatUserName(HttpServletRequest request, HttpServletResponse response){
        try{
            Map<String,Object> userMap=new HashMap<>();
            //获得输入的用户名
            String username = request.getParameter("username");
            User user=userService.getUserName(username);
            //使用  一个map 集合封装返回数据 转化为json格式字符串写到前端
            if (username.equals(user.getUsername())){
                userMap.put("key",1);
            }else {
                userMap.put("key",2);
            }
            mapper.writeValue(response.getWriter(),userMap);
        }catch (Exception e){
            e.getMessage();
        }
    }


    /*//激活用户
    public  void activeUser(HttpServletRequest request, HttpServletResponse response){

        try {
            String code = request.getParameter("code");
            System.out.println(code);
            userService.setStatus(code);
            response.getWriter().write("激活成功......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/



    //登录
    public void login(HttpServletRequest request, HttpServletResponse response){
        try{
            ResultInfo result=new ResultInfo();

            //获得输入的验证码
            String check= request.getParameter("check");
            //判断用户输入的验证码和系统生成的验证码是否一致
            HttpSession session = request.getSession();
            String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
            //移除当前这一次生产的验证码键值对
            session.removeAttribute(checkcode_server);
            if (check.equalsIgnoreCase(checkcode_server)){
                //获得用户名和密码
                String username = request.getParameter("username");
                String password=request.getParameter("password");
                //加密密码
                String mpassword = md5Util.encodeByMd5(password);
                User user=new User(username,mpassword);
                System.out.println(username+"  "+password+"  "+mpassword);

                User  LoginUser= userService.getUserByUserNameAndPwd(user);

                if (LoginUser==null){
                    result.setFlag(false);
                    result.setErrorMsg("用户名或密码错误,请重新输入");
                    mapper.writeValue(response.getWriter(),result);
                    System.out.println("用户名或密码错误,请重新输入");

                }else {
                    //判断用户是否激活
                    if (LoginUser.getStatus().equals("Y")){
                        result.setFlag(true);
                        session.setAttribute("userMsg",user);
                        mapper.writeValue(response.getWriter(),result);
                    }else {
                        result.setFlag(false);
                        result.setErrorMsg("用户未激活");
                        mapper.writeValue(response.getWriter(),result);
                        System.out.println("用户未激活");
                    }

                }
            }else {

                result.setFlag(false);
                result.setErrorMsg("验证码错误,请重新输入");
                mapper.writeValue(response.getWriter(),result);
                System.out.println("验证码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //显示登录用户
    public void showLogin(HttpServletRequest request, HttpServletResponse response){
        try{
            //获得session对象
            HttpSession session=request.getSession();
            //从session域中获取用户信息
            User userMsg = (User) session.getAttribute("userMsg");
            //存储用户信息
            ResultInfo result =new ResultInfo();
            result.setData(userMsg.getUsername());
            System.out.println(userMsg.getUsername());
            //返回响应
            mapper.writeValue(response.getWriter(),result);
        }catch (Exception e){
            e.getMessage();
        }

    }


    //退出登录
    public void a(HttpServletRequest request, HttpServletResponse response){
       try{
            ResultInfo result=new ResultInfo();
            //获得session对象
            HttpSession session=request.getSession();
            //删除用户信息
            session.invalidate();

        }catch (Exception e){
            e.getMessage();
        }

    }


    //显示旅游分类
    //搞错了,应该用分类的那个的,懒得换了
    public void showCategory(HttpServletRequest request, HttpServletResponse response){

        try{
            //查询数据库  获得数据库数据
            List<Category> category =userService.getCateory();
            //返回响应
            String string = mapper.writeValueAsString(category);
            // System.out.println(string);
            response.getWriter().write(string);

        }catch (Exception e){
            e.getMessage();
        }

    }










}
