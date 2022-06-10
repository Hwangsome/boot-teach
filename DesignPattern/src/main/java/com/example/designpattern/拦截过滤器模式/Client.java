package com.example.designpattern.拦截过滤器模式;

public class Client {
   FilterManager filterManager;
 
   public void setFilterManager(FilterManager filterManager){
      this.filterManager = filterManager;
   }
 
   public void sendRequest(String request){
      filterManager.filterRequest(request);
   }
}