package com.jiale.design.factory.easyfactory;

public class Test {

    public static void main(String[] args) {
        XiaomiFactory xiaomiFactory = new XiaomiFactory();
        Xiaomi xiaomi14 = xiaomiFactory.create(14);
        Xiaomi xiaomi13 = xiaomiFactory.create(13);

    }
}
