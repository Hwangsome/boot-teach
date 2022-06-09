package com.example.designpattern.工厂模式.简单工厂模式;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        new ShapeFactory().getShape("Circle").draw();
    }
}
