package com.atguigu.jdbc;

import java.sql.*;

/**
 * 查询 数量
 * @author tangtaiming
 * @date 2022.05.15 23:36
 */
public class Demo07 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //引用jar
        Class.forName("org.gjt.mm.mysql.Driver");
        //驱动器加载
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pwd = "tangtaiming123";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        //编写sql语句
        String sql = "select count(*) from t_fruit";
        //创建预处理对象
        PreparedStatement ps = conn.prepareStatement(sql);
        //执行更新
        ResultSet rs = ps.executeQuery();
        //解析结果集
        if (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("总数量 = " + count);
        }
        //关闭连接
        rs.close();
        ps.close();
        conn.close();
    }

}
