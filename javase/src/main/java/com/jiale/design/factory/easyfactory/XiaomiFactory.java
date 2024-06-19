package com.jiale.design.factory.easyfactory;

public class XiaomiFactory {
    public  Xiaomi create(int type){
        if (type == 13) {
            return new Xiaomi13();
        }
        return new Xiaomi14();
    }
}
