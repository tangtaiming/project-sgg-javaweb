package com.atguigu.jdbc;

import com.entiry.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询 所有
 * @author tangtaiming
 * @date 2022.05.15 23:36
 */
public class Demo06 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //引用jar
        Class.forName("org.gjt.mm.mysql.Driver");
        //驱动器加载
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pwd = "tangtaiming123";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        //编写sql语句
        String sql = "select * from t_fruit";
        //创建预处理对象
        PreparedStatement ps = conn.prepareStatement(sql);
        //执行更新
        ResultSet rs = ps.executeQuery();
        //解析结果集
        List<Fruit> fruits = new ArrayList<>();
        while (rs.next()) {
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
            fruits.add(fruit);

        }

        fruits.stream().forEach(System.out::println);
        //关闭连接
        rs.close();
        ps.close();
        conn.close();
    }

}
