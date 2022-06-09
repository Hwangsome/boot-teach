package com.example.designpattern.适配器模式.objectadapter;

public class Phone {
    public static void main(String[] args) {
         int voltage = new VoltageAdapter(new Voltage220V()).outPut5V();
        System.out.println("voltage = " + voltage+"V");
    }
}
