package com.example.designpattern.适配器模式.classadapter;

public class Voltage220V {
    int Voltage = 220;

    public int outPut220V() {
        System.out.println(Voltage+"V");
        return Voltage;
    }
}
