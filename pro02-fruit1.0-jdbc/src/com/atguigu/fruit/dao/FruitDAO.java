package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * 水果类 数据层
 * @author tangtaiming
 * @date 2022.05.17 23:21
 */
public interface FruitDAO {

    /**
     * 查询库存列表
     * @return
     */
    List<Fruit> findFruitList();

    /**
     * 根据名称查询特点库存
     * @param fname
     * @return
     */
    Fruit findFruitOne(String fname);

    /**
     * 新增库存
     * @param fruit
     * @return
     */
    boolean addFruit(Fruit fruit);

    /**
     * 删除特定库存记录
     * @param fname
     * @return
     */
    boolean deleteFruit(String fname);

    /**
     * 修改库存
     * @param fruit
     * @return
     */
    boolean updateFruit(Fruit fruit);

}
