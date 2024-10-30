package com.jiale.design.strategy;

/**
 * 环境类
 */
public class Context {
    private StrategyPattern strategy;


    public Context(StrategyPattern strategy){
        this.strategy = strategy;
    }

    public void executeStrategy(String msg){
         strategy.doOperation(msg);
    }

}
