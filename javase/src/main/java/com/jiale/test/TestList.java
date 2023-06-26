package com.jiale.test;

import java.util.ArrayList;

public class TestList {
    public static void main(String[] args) {
        ArrayList<Integer>  integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        System.out.println(integers.size());
        integers.clear();
        System.out.println(integers.size());
    }
}
