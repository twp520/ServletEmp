package com.twp.servletemp.dao;

import com.twp.servletemp.entity.User;

import java.sql.ResultSet;
import java.util.List;

public class UserDao extends BaseDAO<User> {

    public User findByName(String name) throws Exception {
        String sql = "select * from t_user where userName=?";
        List<User> list = find(sql, new Object[]{name});
        if (list != null && list.size() > 0)
            return list.get(0);
        else return null;
    }

    @Override
    public User toEntity(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String userName = rs.getString("userName");
        String pwd = rs.getString("pwd");
        User user = new User(id, userName, pwd);
        return user;
    }
}
