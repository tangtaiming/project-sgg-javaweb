package com.atguigu.jdbc;

import java.sql.*;

/**
 * 新增操作
 * @author tangtaiming
 * @date 2022.05.15 4:45
 */
public class Demo02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1 加载jar
        Class.forName("org.gjt.mm.mysql.Driver");
        //2 通过驱动管理器获取连接对象
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pwd = "tangtaiming123";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        //3 编写SQL 语句
        //id / fname / price / fcount / remark
        String sql = "insert into t_fruit values(0, ?, ?, ?, ?)";
        //4 创建预处理对象
        PreparedStatement ps = conn.prepareCall(sql);
        //5 填充参数
        ps.setString(1, "榴莲");
        ps.setInt(2, 100);
        ps.setInt(3, 100);
        ps.setString(4, "好吃的不得了");
        //6 执行更新，返回影响行数
        int count = ps.executeUpdate();
        System.out.println(count > 0 ? "添加成功!" : "添加失败!");
        //7 释放资源 关闭连接 1.先关闭预处理 2.后关闭连接对象
        ps.close();
        conn.close();
    }

}
