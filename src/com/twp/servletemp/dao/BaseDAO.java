package com.twp.servletemp.dao;


import com.twp.servletemp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianweiping on 2017/6/27.
 */
public abstract class BaseDAO<T> {

    //所有find方法的三大差别
    // 1、sql语句不同
    // 2、?参数有几个不确定，参数类型不确定
    // 类型不确定：用object来接受
    // 个数不确定：数组
    // Object[] params
    // 3、返回类型不同 解决：用泛型类
    protected List<T> find(String sql, Object[] params) throws Exception {
        List<T> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getDbConnection();
            pstmt = conn.prepareStatement(sql);
            //问题1、不知道set什么，set几次
            //解决：for循环
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(toEntity(rs));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        return list;
    }

    /***
     * 根据id删除
     * @param sql
     * @param id
     * @return
     * @throws Exception
     */
    protected int deleteById(String sql, int id) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        conn = DBUtil.getDbConnection();
        pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        int result = pst.executeUpdate();
        DBUtil.close(pst);
        DBUtil.close(conn);
        return result;
    }


    /**
     * 将resultSet转换成实体类
     *
     * @param rs 数据库查询结果
     * @return 转换好的实体类
     * @throws Exception
     */
    public abstract T toEntity(ResultSet rs) throws Exception;
}
