package com.icis.service.Impl;

import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.utils.MailUtils;
import com.icis.utils.Md5Util;
import com.icis.utils.UuidUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    private static Md5Util md5Util=new Md5Util();
    @Override
    public void addUser(User user) {
        //注册用户
        try {
            //加密密码
            String s = md5Util.encodeByMd5(user.getPassword());
            user.setPassword(s);
            //添加注册码
            String uuid = UuidUtil.getUuid();
            user.setCode(uuid);
            //添加注册 状态
            user.setStatus("N");
            //调用dao
            this.userDao.addUser(user);

            //发送邮件
            MailUtils.sendMail(user.getEmail(),"点击激活账户:"+"http://192.168.4.10:8085/travel/activeUser?code="+uuid+"","激活账号");
            //http://localhost:8085/activeUser?code=
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUserName(String username) {
        return this.userDao.getUserName(username);
    }

    @Override
    public void setStatus(String code) {
        userDao.setStatus(code);
    }

    @Override
    public User getUserByUserNameAndPwd(User user) {
        return  this.userDao.getUserByUserNameAndPwd(user);
    }

    @Override
    public List<Category> getCateory() {
        return this.userDao.getCateory();
    }




}
