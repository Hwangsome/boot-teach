package com.example.designpattern.单例模式;

public class Singleton4 {
    private volatile static Singleton4 singleton;

    private Singleton4() {
    }

    /**
     * 双重检查锁定背后的理论是：
     * 在 //2 处的第二次检查使创建两个不同的 Singleton 对象成为不可能。假设有下列事件序列：
     * 线程 1 进入 getInstance() 方法。
     * 由于 instance 为 null，线程 1 在 //1 处进入 synchronized 块。
     * 线程 1 被线程 2 预占。
     * 线程 2 进入 getInstance() 方法。
     * 由于 instance 仍旧为 null，线程 2 试图获取 //1 处的锁。然而，由于线程 1 持有该锁，线程 2 在 //1 处阻塞。
     * 线程 2 被线程 1 预占。
     * 线程 1 执行，由于在 //2 处实例仍旧为 null，线程 1 还创建一个 Singleton 对象并将其引用赋值给 instance。
     * 线程 1 退出 synchronized 块并从 getInstance() 方法返回实例。
     * 线程 1 被线程 2 预占。
     * 线程 2 获取 //1 处的锁并检查 instance 是否为 null。
     * 由于 instance 是非 null 的，并没有创建第二个 Singleton 对象，由线程 1 创建的对象被返回。
     * 双重检查锁定背后的理论是完美的。不幸地是，现实完全不同。双重检查锁定的问题是：并不能保证它会在单处理器或多处理器计算机上顺利运行。
     * 双重检查锁定失败的问题并不归咎于 JVM 中的实现 bug，而是归咎于 Java 平台内存模型。内存模型允许所谓的“无序写入”，这也是这些失败的一个主要原因。
     * 所以，后来人们加上了volatile，为的就是防止指令重排
     * @return
     */
    public static Singleton4 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton4.class) {// 1
                if (singleton == null) { // 2 再判空的理由是，在多线程环境中，防止前面的线程已经创建了对象，其他线程再去创建对象
                    singleton = new Singleton4(); // 3
                }
            }
        }
        return singleton;
    }
}