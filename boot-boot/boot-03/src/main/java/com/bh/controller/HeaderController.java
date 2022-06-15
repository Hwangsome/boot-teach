package com.bh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@RestController
@Slf4j
public class HeaderController {


    private Map requestContext = new HashMap();

    /**
     * 将请求中携带的header 中的 token=1111取出来
     * @param token
     * @return
     */
    @RequestMapping("/getHeaderInfo")
    public ResponseEntity getHeaderInfo(
            @RequestHeader("token") String token
    ){
        //token:1111
        requestContext.put("token", token);
        log.info("token: "+token);
        return ResponseEntity.ok(token);
    }


}
