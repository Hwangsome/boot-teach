package com.bh.permission.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    /**
     * 权限管理
     */
    public static Map<Integer,String[]> urlPermission=new HashMap<>();
    static {
        String[] frist={"/url1","/url2","/url3","/url4","/url5","/url6","/url7"};//用户1所拥有的URL权限
        String[] second={"/url1","/url2","/url3","/url4","/url5"};//用户2所拥有的URL权限
        String[] third={"/url1","/url2","/url3"};//用户3所拥有的URL权限
        //表示"1" 这个用户拥有的访问权限
        urlPermission.put(1,frist);
        //表示"2" 这个用户拥有的访问权限
        urlPermission.put(2,second);
        //表示"3" 这个用户拥有的访问权限
        urlPermission.put(3,third);
    }

    /**
     * 操作权限管理
     */
    public static Map<Integer,String[]> operationPermission=new HashMap<>();
    static {
        String[] frist={"insert","delete","select","update"};//用户1所拥有的操作权限
        String[] second={"insert","select","update"};//用户2所拥有的URL权限
        String[] third={"select"};//用户3所拥有的URL权限
        operationPermission.put(1,frist);
        operationPermission.put(2,second);
        operationPermission.put(3,third);

    }
}