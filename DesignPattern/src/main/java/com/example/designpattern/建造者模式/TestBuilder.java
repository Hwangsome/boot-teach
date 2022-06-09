package com.example.designpattern.建造者模式;

public class TestBuilder {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("因特尔", "三星")
                .setDisplay("三星24寸")
                .setKeyboard("罗技")
                .setUsbCount(2)
                .build();
        System.out.println("computer = " + computer);

    }
}
