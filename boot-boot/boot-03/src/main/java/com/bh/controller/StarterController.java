package com.bh.controller;

import com.bh.properties.HelloProperties;
import com.bh.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试自定义启动器 ：boot-04 是自定义的启动器
 */
@RestController
public class StarterController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello1")
    public ResponseEntity hello(){
        String bh = helloService.sayHello("bh");
        return ResponseEntity.ok(bh);
    }
    @RequestMapping("/hello2")
    public ResponseEntity hello2(){
        final HelloProperties helloProperties = helloService.getHelloProperties();
        return ResponseEntity.ok(helloProperties);
    }
}
