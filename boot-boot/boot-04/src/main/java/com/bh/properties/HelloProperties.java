package com.bh.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.InputStream;

/**
 * @ConfigurationProperties作用：
 * 将配置文件中配置的每一个属性的值，映射到这个组件中；
 * 告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 * 参数 prefix = “hello” : 将配置文件中的hello下面的所有属性一一对应
 * 只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
 *
 * 方式1： @ConfigurationProperties +@Component
 * 方式2： @ConfigurationProperties + @ConfigurationPropertiesScan("com.bh.properties")
 * 方式3： @ConfigurationProperties(prefix = "author") + @ConstructorBinding +@ConfigurationPropertiesScan("com.bh.properties")
 */
@ConfigurationProperties(prefix = "hello") // 对外提供的前缀，相当于其它引入当前starter在properties文件使用hello.属性即可对下面属性进行赋值
@Data

public class HelloProperties {
    private String prefix; // 成员属性，意思是前缀名
    private String suffix; // 成员属性，意思是后缀名
}