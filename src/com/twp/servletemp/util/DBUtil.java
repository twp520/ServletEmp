package com.twp.servletemp.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by tianweiping on 2017/6/20.
 */
public class DBUtil {
    //驱动名称
    private static String driverName;
    //数据库地址  url格式为： jdbc:数据库厂商://数据库ip:端口(mysql一般都是3306)/数据库名
    private static String dbUrl;
    //数据库用户名
    private static String dbUser;
    //数据库用户密码
    private static String dbPassWord;

    private static BasicDataSource bds;

    static {
        String path = "com/twp/servletemp/util/db.properties";
        Properties properties = new Properties();
        try {
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream(path));
            driverName = properties.getProperty("driver");
            dbUrl = properties.getProperty("dbUrl");
            dbUser = properties.getProperty("user");
            dbPassWord = properties.getProperty("password");
//            Class.forName(driverName);

            //bds中已经有了创建好的链接
            bds = new BasicDataSource();
            bds.setDriverClassName(driverName);
            bds.setUrl(dbUrl);
            bds.setUsername(dbUser);
            bds.setPassword(dbPassWord);
            bds.setInitialSize(10); //初始化的连接数
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库链接
     *
     * @return 数据库链接
     */
    public static Connection getDbConnection() {
        Connection connection = null;
        try {
            if (bds != null) {
                connection = bds.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库链接
     *
     * @param con 数据库链接
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
