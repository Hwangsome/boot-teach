package com.example.designpattern.适配器模式.objectadapter;

public class VoltageAdapter implements Voltage5V {
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int outPut5V() {
        System.out.println("5V");
        return voltage220V.outPut220V()/44;
    }
}
