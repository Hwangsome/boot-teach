package com.example.designpattern.适配器模式.classadapter;


/**
 * 1. 模式的结构
 * 适配器模式（Adapter）包含以下主要角色。
 * 目标（Target）接口：当前系统业务所期待的接口，它可以是抽象类或接口。
 * 适配者（Adaptee）类：它是被访问和适配的现存组件库中的组件接口。
 * 适配器（Adapter）类：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者。
 *
 * 实现时使用了构成原则： 适配器实现了其中一个对象的接口， 并对另一个对象进行封装。 所有流行的编程语言都可以实现适配器。
 */
public class Phone {

        /**
         * 充电
         * @param iVoltage5V
         */
        public void charging(Voltage5V iVoltage5V) {
            if (iVoltage5V.outPut5V() == 5) {
                System.out.println("电压为5V，可以充电～～");
            } else if (iVoltage5V.outPut5V() > 5) {
                System.out.println("电压大于5V，不能充电～～");
            }
        }
    }