# 如何自定义一个starter？
启动器的核心其实就是自动配置类，AutoConfigurationImportSelector是从`spring.factories`中加载自动配置类，
因此只需要将自定义的自动配置类设置在该文件中即可。

# 注解总结
1. @Configuration： 该注解标志这是一个配置类，自动配置类可以不加该注解。 
2. @EnableConfigurationProperties：这个配置也是经常使用了，使得指定的属性配置生效。一般自动配置类都需要从全局属性配置中读取自定义的配置，这就是一个开关。 
3. @ConditionalOnXxxx：该注解是自动配置类的核心了，自动配置类既要启动时自动配置，又要保证用户用户自定义的配置覆盖掉自动配置，该注解就是一个条件语句，只有当指定条件成立才会执行某操作。不理解的，请看作者前面的一篇文章：这类注解都不知道，还说用过Spring Boot~
4. @AutoConfigureAfter：指定自动配置类的执行先后顺序，下文详细介绍。 
5. @AutoConfigureBefore：指定自动配置列的执行先后顺序，下文详细介绍。 
6. @AutoConfigureOrder：指定自动配置类的优先级，下文详细介绍。

# 步骤
1. 准备自己的自动配置类
启动器的灵魂核心就是自动配置类，因此需要首先创建一个自动配置类，如下：
```java
package com.bh.config;

import com.bh.properties.HelloProperties;
import com.bh.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication    // 条件配置类，该注解表示在web环境下才生效，相关的其它条件可以使用@ConditionXXX
@EnableConfigurationProperties(HelloProperties.class) // 表示HelloProperties作为配置类使用
public class HelloServiceAutoConfiguration {

    @Autowired
    HelloProperties helloProperties;	// 作为配置类目的就是想在sayHello方法返会的字符串加上前缀和后缀

    @Bean
    public HelloService helloService() { // 将HelloService注入到IOC容器
        HelloService service = new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }
}

```
2. 将自动配置类设置在spring.factories
标注了@Configuration注解的自动配置类如果不放在spring.factories文件中，仅仅是一个普通的配置类而已。想要其成为自动配置类，需要在spring.factories文件中设置，如下：
```spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.bh.config.HelloServiceAutoConfiguration
```
经过以上的配置，粗略的启动器完成了，只需要打包，然后Maven引入即可工作