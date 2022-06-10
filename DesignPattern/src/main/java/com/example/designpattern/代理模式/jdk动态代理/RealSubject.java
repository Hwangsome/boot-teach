package com.example.designpattern.代理模式.jdk动态代理;

// 业务实现类 //
public class RealSubject implements ISubject {
    @Override
    public void doAction() { System.out.println("Real Action Here!"); }
}