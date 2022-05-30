package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 目标：和数据库建立链接
 *
 * @author tangtaiming
 * @date 2022.05.15 4:15
 */
public class Demo01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1 添加jar
        //2 加载驱动
        Class.forName("org.gjt.mm.mysql.Driver");
        //3 通过驱动管理器获取链接
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pwd = "tangtaiming123";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
    }

}
