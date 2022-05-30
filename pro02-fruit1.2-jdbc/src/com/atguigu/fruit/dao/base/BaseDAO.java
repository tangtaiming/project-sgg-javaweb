package com.atguigu.fruit.dao.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangtaiming
 * @date 2022.05.19 0:01
 */
public abstract class BaseDAO<T> {

    protected PreparedStatement ps = null;

    protected Connection conn = null;

    protected ResultSet rs = null;

    protected final String DRIVER = "org.gjt.mm.mysql.Driver";

    protected final String URL = "jdbc:mysql://localhost:3306/test";

    protected final String USER = "root";

    protected final String PWD = "tangtaiming123";

    protected Class entiryClass;

    public BaseDAO() {
        //getClass() 获取Class对象，当前我们执行的是new FruitDAOImpl() , 创建的是FruitDAOImpl的实例
        //那么子类构造方法内部首先会调用父类（BaseDAO）的无参构造方法
        //因此此处的getClass()会被执行，但是getClass获取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()获取到的是BaseDAO的Class
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //获取到的<T>中的T的真实的类型
        Type actualType = actualTypeArguments[0];
        try {
            entiryClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     */
    protected Connection getConn() {
        try {
            //加载jar
            Class.forName(DRIVER);
            //驱动器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接
     * @param rs
     * @param ps
     * @param conn
     */
    protected void close(ResultSet rs, PreparedStatement ps, Connection conn) {
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

    /**
     * 执行更新， 返回影响行数
     * @param sql
     * @param params
     * @return
     */
    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        try {
            conn = getConn();
            //获取预处理对象
            if (insertFlag) {
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = conn.prepareStatement(sql);
            }
            //填充参数
            setParams(params, ps);
            //执行更新
            int count = ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next())
                return ((Long) rs.getLong(1)).intValue();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        return 0;
    }

    /**
     * 执行查询 返回List
     * @param sql
     * @param params
     * @return
     */
    protected List<T> executeQuery(String sql, Object... params) {
        List<T> entiryList = new ArrayList<>();
        try {
            conn = getConn();
            //获取预处理对象
            ps = conn.prepareStatement(sql);
            //填充参数
            setParams(params, ps);
            //执行查询
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                T entiry = (T) entiryClass.newInstance();

                for (int x = 0; x < columnCount; x++) {
                    String columnName = rsmd.getColumnName(x + 1);
                    Object columnValue = rs.getObject(x + 1);
                    setValue(entiry, columnName, columnValue);
                }
                entiryList.add(entiry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        //处理结果
        return entiryList;
    }

    /**
     * 执行查询 返回单个实体对象
     * @param sql
     * @param params
     * @return
     */
    protected T load(String sql, Object... params) {
        try {
            conn = getConn();
            //获取预处理对象
            ps = conn.prepareStatement(sql);
            //填充参数
            setParams(params, ps);
            //执行查询
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T entiry = (T) entiryClass.newInstance();
                for (int x = 0; x < columnCount; x++) {
                    String columnName = rsmd.getColumnName(x + 1);
                    Object columnValue = rs.getObject(x + 1);
                    setValue(entiry, columnName, columnValue);
                }
                //处理结果
                return entiry;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);
        }
        //处理结果
        return null;
    }

    /**
     * 设置参数
     * @param params
     * @param ps
     * @throws SQLException
     */
    private void setParams(Object[] params, PreparedStatement ps) throws SQLException {
        for (int x = 0; x < params.length; x++) {
            ps.setObject(x + 1, params [x]);
        }
    }

    /**
     * 通过反射技术给obj对象的property属性赋propertyValue值
     * @param obj
     * @param property
     * @param propertyValue
     */
    private void setValue(Object obj, String property, Object propertyValue) {
        Class clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(property);
            if (null != field) {
                field.setAccessible(true);
                field.set(obj, propertyValue);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
