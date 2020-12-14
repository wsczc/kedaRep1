package com.icis.dao.impl;

import com.icis.dao.IRouteDao;
import com.icis.pojo.Category;
import com.icis.pojo.Route;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class IRouteDaoImpl implements IRouteDao {
    private  final JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    //根据cid查询旅游线路  查询前缀一般为 get select query
    @Override
    public List<Route> getRouteByCid(Integer cid) {
        String sql="SELECT * FROM tab_route WHERE cid=?";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid);
    }

    //查询总记录数
    @Override
    public Integer getRouteCount(Integer cid) {
        String sql="select count(*) from tab_route where cid=?";
        return this.template.queryForObject(sql,Integer.class,cid);
    }

    //分页
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        try {
            String sql = "select * from tab_route where cid=? limit ?,?";
            List<Route> query = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, start, pageSize);
            System.out.println(query.toString());
            return  query;
        }catch (Exception e){
            return null;
        }


    }

    @Override
    public List<Route> getHotRoute(String cid) {
        String sql="SELECT * FROM tab_route where cid=? ORDER BY price";
        return this.template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid);
    }

    @Override
    public List<Route> getIndexRoute(String cid) {
        String sql="SELECT *FROM tab_route where cid=? ORDER BY price;";
        return this.template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid);
    }

    @Override
    public List<Category> getIndexCategory() {
        String sql="SELECT * FROM tab_category ORDER BY cid;";
        return this.template.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }

}
