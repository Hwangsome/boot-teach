package com.example.designpattern.建造者模式;

/**
 * 使用场景：
 *  当一个类的构造函数参数个数超过4个，而且这些参数有些是可选的参数，考虑使用构造者模式。
 *
 * 当一个类的构造函数参数超过4个，而且这些参数有些是可选的时，我们通常有两种办法来构建它的对象。
 * 第一：折叠构造函数模式。
 * 第二：Javabean 模式。（提供get/set）.
 * 第一种主要是使用及阅读不方便。你可以想象一下，当你要调用一个类的构造函数时，你首先要决定使用哪一个，然后里面又是一堆参数，如果这些参数的类型很多又都一样，你还要搞清楚这些参数的含义，很容易就传混了。。。那酸爽谁用谁知道。
 * 第二种方式在构建过程中对象的状态容易发生变化，造成错误。因为那个类中的属性是分步设置的，所以就容易出错。
 * 为了解决这两个痛点，builder模式就横空出世了。
 */
public class Computer {
    private final String cpu;//必须
    private final String ram;//必须
    private final int usbCount;//可选
    private final String keyboard;//可选
    private final String display;//可选

    private Computer(Builder builder){
        this.cpu=builder.cpu;
        this.ram=builder.ram;
        this.usbCount=builder.usbCount;
        this.keyboard=builder.keyboard;
        this.display=builder.display;
    }

    /**
     * 1. 在Computer 中创建一个静态内部类 Builder，然后将Computer 中的参数都复制到Builder类中。
     * 2. 在Computer中创建一个private的构造函数，参数为Builder类型
     * 3. 在Builder中创建一个public的构造函数，参数为Computer中必填的那些参数，cpu 和ram。
     * 4. 在Builder中创建设置函数，对Computer中那些可选参数进行赋值，返回值为Builder类型的实例
     * 5. 在Builder中创建一个build()方法，在其中构建Computer的实例并返回
     */
    public static class Builder{
        private String cpu;//必须
        private String ram;//必须
        private int usbCount;//可选
        private String keyboard;//可选
        private String display;//可选

        public Builder(String cup,String ram){
            this.cpu=cup;
            this.ram=ram;
        }

        public Builder setUsbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }
        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }
        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }        
        public Computer build(){
            return new Computer(this);
        }
    }


    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", usbCount=" + usbCount +
                ", keyboard='" + keyboard + '\'' +
                ", display='" + display + '\'' +
                '}';
    }
}