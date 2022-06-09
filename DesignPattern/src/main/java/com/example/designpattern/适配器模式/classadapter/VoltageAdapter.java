package com.example.designpattern.适配器模式.classadapter;

public class VoltageAdapter extends Voltage220V implements Voltage5V {

    @Override
    public int outPut5V() {
        System.out.println("5V");
        return outPut220V()/44;
    }
}
