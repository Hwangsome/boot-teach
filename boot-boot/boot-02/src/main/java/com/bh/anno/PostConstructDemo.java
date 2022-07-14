package com.bh.anno;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 如果想在生成对象时候完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入，
 * 那么就无法在构造函数中实现。为此，可以使用@PostConstruct注解一个方法来完成初始化，
 * @PostConstruct注解的方法将会在依赖注入完成后被自动调用。
 *
 * 该注解是Java jdk提供的注解，而不是Spring框架提供的， JavaEE5引入了@PostConstruct和@PreDestroy两个作用于Servlet生命周期的注解，实现Bean初始化之前和销毁之前的自定义操作。
 *
 * 该注解的方法在整个Bean初始化中的执行顺序：
 * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的初始化方法)
 * 该注解的功能：当依赖注入完成后用于执行初始化的方法，并且只会被执行一次
 *
 * Spring的@PostConstruct注解在方法上，表示此方法是在Spring实例化该Bean之后马上执行此方法，之后才会去实例化其他Bean，并且一个Bean中@PostConstruct注解的方法可以有多个。
 *
 * Constructor >> @Autowired >> @PostConstruct
 * 先执行一个类的构造器，将对象生成之后，在通过自动注入，对依赖进行注入，依赖注入完成后，就可以执行PostConstruct 标记的方法
 *
 * servlet的执行流程：
 * 开始==》web容器加载servlet ==》servlet构造函数==》PostConstruct注解的方法 ==》init ==>service ==>destroy ==》predestroy ==>结束
 *  使用此注解时会影响服务启动时间。服务启动时会扫描WEB-INF/classes的所有文件和WEB-INF/lib下的所有jar包。
 */
@Component
@Slf4j
public class PostConstructDemo {

    @PostConstruct
    public void start(){
        log.info("start...");
    }

    /**
     * 功能与@PostConstruct一样
     */
    @PreDestroy
    public void destroy(){
        log.info("destroy...");
    }
}
