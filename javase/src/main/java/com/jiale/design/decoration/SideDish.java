package com.jiale.design.decoration;

/**
 * 配菜(抽象产品)
 */
public class SideDish extends  FastFood{

    private FastFood fastFood;

    public SideDish(FastFood fastFood , String description, int price , int num) {
        super(description, price ,num);
        this.fastFood = fastFood;
    }

    @Override
    public int cost() {
        return fastFood.cost() + getPrice() * getNum();
    }

    @Override
    public String Description() {
        return fastFood.Description() + getDescription();
    }
}
