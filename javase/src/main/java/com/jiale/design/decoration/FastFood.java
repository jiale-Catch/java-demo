package com.jiale.design.decoration;

public abstract class FastFood {

    /**
     * 描述
     */
    private String description;

    /**
     * 价格
     */
    private int price;

    private  int num;



    public abstract int cost();//返回需要多少钱
    public abstract String Description();//返回当前快餐描述


    public FastFood(String description,int price ,int num){
        this.description=description;
        this.price=price;
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
