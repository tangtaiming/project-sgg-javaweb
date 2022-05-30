package com.atguigu.fruit.view;

import com.atguigu.fruit.controller.Menu;

/**
 * @author tangtaiming
 * @date 2022.05.17 19:55
 */
public class Client {

    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean flag = true;
        while (flag) {
            int option = menu.showMainMenu();
            switch (option) {
                case 1:
                    menu.showFruitList();
                    break;
                case 2:
                    menu.addFruit();
                    break;
                case 3:
                    menu.showFruitInfo();
                    break;
                case 4:
                    menu.soldOut();
                    break;
                case 5:
                    flag = menu.exit();
                    break;
                default:
                    System.out.println("你不按套路出牌!");
                    break;
            }
        }
        System.out.println("谢谢使用! 再见");
    }

}
