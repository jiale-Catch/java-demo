package com.jiale.design.strategy;

public class OperationOne implements StrategyPattern{
    @Override
    public void doOperation(String msg) {
        System.out.println("OperationOne doOperation msg:"+ msg);
    }
}
