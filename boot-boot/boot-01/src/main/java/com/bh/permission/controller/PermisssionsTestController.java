package com.bh.permission.controller;

import com.bh.permission.customeranno.UrlAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：HB
 * @date ：Created in 2022/1/16 22:58
 * @description：
 */
@RestController
public class PermisssionsTestController {


    @RequestMapping("/url1")
    @UrlAnnotation(type = "insert")
    public String url1(){
        return "恭喜你有访问此url的权限";
    }


    @UrlAnnotation(type = "select")
    @RequestMapping("/url2")
    public String url2(){
        return "恭喜你有访问此url的权限";
    }

    @UrlAnnotation(type = "select")
    @RequestMapping("/url3")
    public String url3(){
        return "恭喜你有访问此url的权限";
    }

    @UrlAnnotation(type = "select")
    @RequestMapping("/url4")
    public String url4(){
        return "恭喜你有访问此url的权限";
    }


    @UrlAnnotation(type = "delete")
    @RequestMapping("/url5")
    public String url5(){
        return "恭喜你有访问此url的权限";
    }

    @UrlAnnotation(type = "update")
    @RequestMapping("/url6")
    public String url6(){
        return "恭喜你有访问此url的权限";
    }

    @UrlAnnotation(type = "delete")
    @RequestMapping("/url7")
    public String url7(){
        return "恭喜你有访问此url的权限";
    }
}
