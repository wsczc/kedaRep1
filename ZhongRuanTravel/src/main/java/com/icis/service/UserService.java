package com.icis.service;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

//用户接口 封装一些功能
public interface UserService {
    //注册用户  添加用户
    public void addUser(User user);

    User getUserName(String username);

    void setStatus(String code);

    User getUserByUserNameAndPwd(User user);

    List<Category> getCateory();


}
