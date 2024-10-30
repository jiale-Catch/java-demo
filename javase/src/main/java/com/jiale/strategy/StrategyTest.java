package com.jiale.strategy;

/**
 * 策略模式 通常包含一个策略接口和多个具体策略实现类，以及一个环境类。策略接口定义了算法的公共接口，具体策略实现类实现了策略接口，环境类持有一个策略对象，并在运行时根据需要选择不同的策略。
 * 客户端代码通过环境类来使用策略，而环境类可以在运行时根据需要动态地切换策略。
 * @author jiale2001
 * @date 2024/12/27
 */
public class StrategyTest {
    public static void main(String[] args) {
        Context context = new Context(new OperationOne());
        context.executeStrategy("hello");
        context = new Context(new OperationTwo());
        context.executeStrategy("hello");
        context = new Context(new StrategyPattern() {
            @Override
            public void doOperation(String msg) {
                System.out.println("hello world");
            }
        });
        context.executeStrategy("hello");
    }
}
