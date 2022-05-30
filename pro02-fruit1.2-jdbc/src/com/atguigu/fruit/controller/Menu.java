package com.atguigu.fruit.controller;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;
import java.util.Scanner;

/**
 * 菜单
 * @author tangtaiming
 * @date 2022.05.17 19:51
 */
public class Menu {

    private Scanner scanner = new Scanner(System.in);

    private FruitDAO fruitDAO = new FruitDAOImpl();

    public int showMainMenu() {
        System.out.println("=================欢迎使用水果库存系统=====================");
        System.out.println("1.查看水果库存列表");
        System.out.println("2.添加水果库存信息");
        System.out.println("3.查看特定水果库存信息");
        System.out.println("4.水果下架");
        System.out.println("5.退出");
        System.out.println("======================================================");
        System.out.print("请选择:");
        return scanner.nextInt();
    }

    public boolean exit() {
        System.out.print("是否确认退出?(Y/N)");
        String str = scanner.next();
        return !"Y".equalsIgnoreCase(str);
    }

    public void showFruitList() {
        List<Fruit> fruits = fruitDAO.findFruitList();
        System.out.println("---------------------------------------------");
        System.out.println("fid\t\t\t名称\t\t\t价格\t\t\t库存\t\t\t备注");
        if (fruits.size() <= 0) {
            System.out.println("对不起，库存为空！");
        } else {
            for (Fruit fruit : fruits) {
                System.out.println(fruit);
            }
        }
        System.out.println("---------------------------------------------");
    }

    public void addFruit() {
        System.out.print("请输入水果名称:");
        String fname = scanner.next();
        Fruit fruit = fruitDAO.findFruitOne(fname);
        if (null == fruit) {
            System.out.print("请输入水果单价:");
            int price = scanner.nextInt();
            System.out.print("请输入水果库存量:");
            int fcount = scanner.nextInt();
            System.out.print("请输入水果备注:");
            String remark = scanner.next();

            //封装成一个新的fruit对象
            fruit = new Fruit(0, fname, price, fcount, remark);
            fruitDAO.addFruit(fruit);
        } else {
            System.out.print("请输入追加的库存量:");
            int fcount = scanner.nextInt();
            fruit.setFcount(fruit.getFcount() + fcount);
            //调用修改方法
            fruitDAO.updateFruit(fruit);
        }
        System.out.println("添加成功!");
    }

    public void showFruitInfo() {
        System.out.print("请输入水果名称:");
        String fname = scanner.next();
        Fruit fruit = fruitDAO.findFruitOne(fname);
        if (null == fruit) {
            System.out.println("对不起，没有找到指定的水果库存记录！");
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("fid\t\t\t名称\t\t\t价格\t\t\t库存\t\t\t备注");
            System.out.println(fruit);
            System.out.println("---------------------------------------------");
        }
    }

    public void soldOut() {
        System.out.print("请输入下架水果名称:");
        String fname = scanner.next();
        Fruit fruit = fruitDAO.findFruitOne(fname);
        if (null == fruit) {
            System.out.println("对不起, 没有找到需要下架的水果信息!");
        } else {
            System.out.print("是否确认下架? (Y/N)");
            String slt = scanner.next();
            if ("Y".equalsIgnoreCase(slt)) {
                fruitDAO.deleteFruit(fname);
                System.out.println("下架成功");
            }
        }
    }

}
