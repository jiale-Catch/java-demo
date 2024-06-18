package com.jiale.design.decoration;

/**
 * 炒面类（具体角色）
 */
public class FireNoodle extends FastFood{
    public FireNoodle(String description, int price , int num) {
        super(description, price ,num);
    }

    @Override
    public int cost() {
        return getPrice() * getNum();
    }

    @Override
    public String Description() {
        return getNum() + "份"+ getDescription();
    }
}
