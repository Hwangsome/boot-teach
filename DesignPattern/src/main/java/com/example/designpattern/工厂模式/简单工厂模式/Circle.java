package com.example.designpattern.工厂模式.简单工厂模式;

public class Circle implements Shape {
 
   @Override
   public void draw() {
      System.out.println("Inside Circle::draw() method.");
   }
}