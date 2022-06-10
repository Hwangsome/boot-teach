package com.example.designpattern.拦截过滤器模式;

public class Target {
   public void execute(String request){
      System.out.println("Executing request: " + request);
   }
}