package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 水果实现类
 * @author tangtaiming
 * @date 2022.05.17 23:26
 */
public class FruitDAOImpl implements FruitDAO {

    private PreparedStatement ps = null;

    private Connection conn = null;

    private ResultSet rs = null;

    @Override
    public List<Fruit> findFruitList() {
        List<Fruit> fruits = new ArrayList<>();
        try {
            //加载jar
            Class.forName("org.gjt.mm.mysql.Driver");
            //驱动器获取连接对象
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String pwd = "tangtaiming123";
            conn = DriverManager.getConnection(url, user, pwd);
            //编写sql
            String sql = "select * from t_fruit";
            //获取预处理对象
            ps = conn.prepareStatement(sql);
            //执行查询
            rs = ps.executeQuery();
            //处理结果
            while (rs.next()) {
                int fid = rs.getInt(1);
                String fname = rs.getString(2);
                int price = rs.getInt(3);
                int count = rs.getInt(4);
                String remark = rs.getString(5);

                Fruit fruit = new Fruit(fid, fname, price, count, remark);
                fruits.add(fruit);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return fruits;
    }

    @Override
    public Fruit findFruitOne(String fname) {
        try {
            //加载jar
            Class.forName("org.gjt.mm.mysql.Driver");
            //驱动器获取连接对象
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String pwd = "tangtaiming123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            //编写sql
            String sql = "select * from t_fruit where fname=?";
            //获取预处理对象
            PreparedStatement ps = conn.prepareStatement(sql);
            //填充参数
            ps.setString(1, fname);
            //执行查询
            ResultSet rs = ps.executeQuery();
            //处理结果
            if (rs.next()) {
                int fid = rs.getInt(1);
                String rsfname = rs.getString(2);
                int price = rs.getInt(3);
                int count = rs.getInt(4);
                String remark = rs.getString(5);

                Fruit fruit = new Fruit(fid, rsfname, price, count, remark);
                return fruit;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        try {
            //加载jar
            Class.forName("org.gjt.mm.mysql.Driver");
            //驱动器获取连接对象
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String pwd = "tangtaiming123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            //编写sql
            String sql = "insert into t_fruit values (0, ?, ?, ?, ?)";
            //获取预处理对象
            PreparedStatement ps = conn.prepareStatement(sql);
            //填充参数
            ps.setString(1, fruit.getFname());
            ps.setInt(2, fruit.getPrice());
            ps.setInt(3, fruit.getFcount());
            ps.setString(4, fruit.getRemark());
            //执行更新
            int count = ps.executeUpdate();
            //处理结果
            return count > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteFruit(String fname) {
        try {
            //加载jar
            Class.forName("org.gjt.mm.mysql.Driver");
            //驱动器获取连接对象
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String pwd = "tangtaiming123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            //编写sql
            String sql = "delete from t_fruit where fname=?";
            //获取预处理对象
            PreparedStatement ps = conn.prepareStatement(sql);
            //填充参数
            ps.setString(1, fname);
            //执行更新
            int count = ps.executeUpdate();
            //处理结果
            return count > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        try {
            //加载jar
            Class.forName("org.gjt.mm.mysql.Driver");
            //驱动器获取连接对象
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String pwd = "tangtaiming123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            //编写sql
            String sql = "update t_fruit set fcount=? where fid=?";
            //获取预处理对象
            PreparedStatement ps = conn.prepareStatement(sql);
            //填充参数
            ps.setInt(1, fruit.getFcount());
            ps.setInt(2, fruit.getFid());
            //执行更新
            int count = ps.executeUpdate();
            //处理结果
            return count > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
