package com.icis.dao;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

public interface UserDao {
    //添加注册用户
     void addUser(User user);

   User getUserName(String user);

    void setStatus(String code);

    User getUserByUserNameAndPwd(User user);

    List<Category> getCateory();


}
