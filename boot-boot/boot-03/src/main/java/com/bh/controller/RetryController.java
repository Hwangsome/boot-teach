package com.bh.controller;

import com.bh.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用spring retry的步骤:
 * 1. @EnableRetry 添加到配置类中
 */
@RestController
@Slf4j
public class RetryController {
    @Autowired
    private RetryService retryService;


    @RequestMapping("/retryTest1")
    public ResponseEntity retryTest1() throws IllegalAccessException {
        retryService.retryTest();
        return ResponseEntity.ok("");
    }
}
