package com.jiale.design;

public class SingletonPattern {
}

/**
 * 私有静态变量 uniqueInstance 被延迟实例化，
 * 这样做的好处是，如果没有用到该类，
 * 那么就不会实例化 uniqueInstance，从而节约资源。
 * 这个实现在多线程环境下是不安全的，
 * 如果多个线程能够同时进入 if (uniqueInstance == null) ，
 * 并且此时 uniqueInstance 为 null，
 * 那么会有多个线程执行 uniqueInstance = new Singleton();
 * 语句，这将导致多次实例化 uniqueInstance
 */
class Singleton {

    private static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}

/**
 * 线程不安全问题主要是由于 uniqueInstance 被多次实例化，
 * 采取直接实例化 uniqueInstance 的方式就不会产生线程不安全问题。
 * 但是直接实例化的方式也丢失了延迟实例化带来的节约资源的好处
 */
class staticSingleton{
    private static staticSingleton uniqueInstance = new staticSingleton();

    private staticSingleton(){

    }

    public static staticSingleton getUniqueInstance(){
        if(uniqueInstance == null){
            uniqueInstance =new staticSingleton();
        }
        return uniqueInstance;
    }
}

class volatileSingleton{
    private volatile static volatileSingleton uniqueInstance;

    private volatileSingleton(){}

    public static volatileSingleton getUniqueInstance(){
        if (uniqueInstance == null){
            synchronized (volatileSingleton.class){
                if (uniqueInstance == null){
                    uniqueInstance =new volatileSingleton();
                }
            }
        }
        return uniqueInstance;
    }
}

/**
 * 静态内部类实现当 Singleton 类加载时，静态内部类 SingletonHolder 没有被加载进内存。只有当调用 getUniqueInstance() 方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，此时初始化 INSTANCE 实例。这种方式不仅具有延迟初始化的好处，而且由虚拟机提供了对线程安全的支持。
 * ------
 * 著作权归@pdai所有
 * 原文链接：https://pdai.tech/md/dev-spec/pattern/2_singleton.html
 */
class staticClassSingleton{
    private staticClassSingleton(){

    }
    private static class SingletonHolder {
        private static final staticClassSingleton INSTANCE = new staticClassSingleton();
    }

    public static staticClassSingleton getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }
}