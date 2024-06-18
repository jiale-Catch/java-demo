package com.jiale.design.decoration;

public class Test {
    public static void main(String[] args) {
        FireNoodle fireNoodle = new FireNoodle("炒面", 10 ,2);
        System.out.println(fireNoodle.Description()  + "价格为" + fireNoodle.cost());
        Egg egg = new Egg(fireNoodle, "加蛋", 2 ,2);
        System.out.println(egg.Description()  + "价格为" + egg.cost());
        System.out.println("-------------------------------------------");
        // 加料
        Ham ham = new Ham(egg, "加火腿", 3 ,2);
        System.out.println(ham.Description()  + "价格为" + ham.cost());
    }
}
