package com.atguigu.jdbc;

import com.entiry.Fruit;

import java.sql.*;

/**
 * 查询 单个
 * @author tangtaiming
 * @date 2022.05.15 23:36
 */
public class Demo05 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //引用jar
        Class.forName("org.gjt.mm.mysql.Driver");
        //驱动器加载
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pwd = "tangtaiming123";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        //编写sql语句
        String sql = "select * from t_fruit where fid=?";
        //创建预处理对象
        PreparedStatement ps = conn.prepareStatement(sql);
        //填充参数
        ps.setInt(1, 2);
        //执行更新
        ResultSet rs = ps.executeQuery();
        //解析结果集
        if (rs.next()) {
            //1表示读取当前行的第一列的数据
            //getInt ， 因为这一列是int类型，所以使用getInt
            //getInt(结果集的列名)
            //int fid = rs.getInt("fid");
            int fid = rs.getInt(1);
            String fname = rs.getString(2);
            int price = rs.getInt(3);
            int fcount = rs.getInt(4);
            String remark = rs.getString(5);

            Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
            System.out.println(fruit.toString());
        }
        //关闭连接
        rs.close();
        ps.close();
        conn.close();
    }

}
