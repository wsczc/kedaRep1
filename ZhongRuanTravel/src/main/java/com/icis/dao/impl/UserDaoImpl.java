package com.icis.dao.impl;

import com.icis.dao.UserDao;
import com.icis.pojo.Category;
import com.icis.pojo.Route;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import com.icis.utils.Md5Util;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());


    //添加注册用户
    @Override
    public void addUser(User user) {
            String sql="INSERT INTO tab_user (username,password,name,birthday,sex,telephone,email,status,code) VALUES(?,?,?,?,?,?,?,?,?);";
            this.jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getName(),
                    user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());
    }

    //注册查询重名
    @Override
    public User getUserName(String username) {
        try {
            String sql="select username from tab_user where username=?";
            return this.jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        }catch (Exception e){
            return null;
        }
    }


    //激活用户
    @Override
    public void setStatus(String code) {
        String sql="UPDATE tab_user SET status='Y' WHERE code=?";
        int update = this.jdbcTemplate.update(sql, code);
        System.out.println(update);
    }

    //登录查询用户
    @Override
    public User getUserByUserNameAndPwd(User user) {
        try{
            String sql="SELECT * FROM tab_user WHERE username=? and password=? ";
            return this.jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getUsername(),user.getPassword());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Category> getCateory() {
        try{
            String sql="SELECT * FROM tab_category ORDER BY cid";
            return  this.jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Category.class));
        }catch (Exception e){
            return  null;
        }
    }



}
