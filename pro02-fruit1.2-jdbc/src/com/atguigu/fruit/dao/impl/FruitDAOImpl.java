package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * 水果实现类
 * @author tangtaiming
 * @date 2022.05.17 23:26
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> findFruitList() {
        String sql = "select * from t_fruit";
        return super.executeQuery(sql);
    }

    @Override
    public Fruit findFruitOne(String fname) {
        String sql = "select * from t_fruit where fname=?";
        return super.load(sql, fname);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        //编写sql
        String sql = "insert into t_fruit values (0, ?, ?, ?, ?)";
        //执行更新， 返回影响行数
        return super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark()) > 0;
    }

    @Override
    public boolean deleteFruit(String fname) {
        //编写sql
        String sql = "delete from t_fruit where fname=?";
        return super.executeUpdate(sql, fname) > 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        //编写sql
        String sql = "update t_fruit set fcount=? where fid=?";
        return super.executeUpdate(sql, fruit.getFcount(), fruit.getFid()) > 0;
    }

}
