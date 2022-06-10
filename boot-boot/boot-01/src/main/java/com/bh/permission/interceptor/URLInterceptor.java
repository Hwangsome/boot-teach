package com.bh.permission.interceptor;

import com.bh.permission.constant.Constant;
import com.bh.permission.customeranno.UrlAnnotation;
import com.bh.permission.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 权限管理URL拦截器
 * HandlerInterceptor：就是请求进入Controller之前的拦截器
 *
 * 具体讲解可以看interceptor包
 */
public class URLInterceptor implements HandlerInterceptor {
    private Logger log =LoggerFactory.getLogger(URLInterceptor.class);

    // 预处理回调方法，在接口调用之前使用  true代表放行  false代表不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("进入的request {}",request.toString());
        String token = httpServletRequest.getHeader("token");
        log.info("进入的token {}",token);
        String requestURL = httpServletRequest.getRequestURI();
        log.info("进入的URL {}",requestURL);
        //获取到对应的注解上面的type,获取不到默认无权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        UrlAnnotation annotation = method.getAnnotation(UrlAnnotation.class);
        if (annotation == null) {
            throw new APIException("当前用户没有访问路径"+requestURL+"的权限");
        }
        //获取注解的属性type
        String type = annotation.type();
        log.info("用户的操作类型 {}",type);
        //1.根据token判断用户是否登录
        if (token==null){   //正常情况下这里还需判断与redis中的token是否匹配
            // 如果没有token或者token不匹配, 直接抛出异常  提示未登录
            throw  new APIException("当前用户未登录");
        }
        //2.登录成功后 根据用户token中的信息获取到用户对应的URL权限集合
        //token ="xxx"，根据token判断是那个用户具有的URL权限
        //{"/url1","/url2","/url3","/url4","/url5","/url6","/url7"}
        String[] urlPermisssions = Constant.urlPermission.get(Integer.valueOf(token));
        log.info("当前用户具有的请求权限 {}", Arrays.asList(urlPermisssions));

        String[] operationPermissions = Constant.operationPermission.get(Integer.valueOf(token));

        log.info("当前用户具有的操作权限 {}", Arrays.asList(operationPermissions));
        boolean hasURLPermission = false;
        //3.再根据用户对应的URL集合去与当前请求的URL对比  有匹配的则放行  反之则抛出异常
        for (int i =0;i<urlPermisssions.length;i++) {
            if (urlPermisssions[i].equals(requestURL)){
                hasURLPermission = true;
                break;
            }
        }
        boolean hasOperationPermission = false;
        for (int i = 0; i < operationPermissions.length; i++) {
            if (operationPermissions[i].equals(type)){
                hasOperationPermission =true;
            }
        }
        if (hasURLPermission && hasOperationPermission){
            return true;
        }else if (!hasURLPermission){
            throw  new APIException("当前用户没有访问路径" + requestURL + "的权限");
        }else {
            throw  new APIException("当前用户没有对资源的" + type + "的权限");
        }
    }
}