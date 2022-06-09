package com.example.designpattern.适配器模式.interfaceadapter;

public class Client {
    public static void main(String[] args) {
        //用户需要使用哪个方法就去实现适配器中的哪个方法
        InterfaceAdapter interfaceAdapter = new InterfaceAdapter(){
            @Override
            public void m1() {
                System.out.println("m1...");
            }
        };
        interfaceAdapter.m1();
    }
}
