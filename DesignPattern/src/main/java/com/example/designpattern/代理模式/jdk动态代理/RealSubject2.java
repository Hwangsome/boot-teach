package com.example.designpattern.代理模式.jdk动态代理;

public class RealSubject2 implements ISubject {
    @Override
    public void doAction() { System.out.println("Real Action2 Here!"); }
}