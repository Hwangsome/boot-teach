package com.bh.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hello") // 对外提供的前缀，相当于其它引入当前starter在properties文件使用hello.属性即可对下面属性进行赋值
@Data
public class HelloProperties {
    private String prefix; // 成员属性，意思是前缀名
    private String suffix; // 成员属性，意思是后缀名
}