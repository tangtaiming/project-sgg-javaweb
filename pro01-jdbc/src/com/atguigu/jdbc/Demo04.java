package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 更新操作 修改 和 删除
 * @author tangtaiming
 * @date 2022.05.15 23:36
 */
public class Demo04 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //引用jar
        Class.forName("org.gjt.mm.mysql.Driver");
        //驱动器加载
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pwd = "tangtaiming123";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        //编写sql语句
        String sql = "delete from t_fruit where fid=?";
        //创建预处理对象
        PreparedStatement ps = conn.prepareStatement(sql);
        //填充参数
        ps.setInt(1, 35);
        //执行更新
        int count = ps.executeUpdate();
        System.out.println("结果=" + ((count > 0) ? "删除成功!" : "删除失败"));
        //关闭连接
        ps.close();
        conn.close();
    }

}
