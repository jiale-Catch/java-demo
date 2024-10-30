package com.jiale.strategy;

/**
 * 具体实现方法
 */
public class OperationTwo implements StrategyPattern{
    @Override
    public void doOperation(String msg) {
        System.out.println("OperationTwo doOperation msg:" + msg);
    }
}
