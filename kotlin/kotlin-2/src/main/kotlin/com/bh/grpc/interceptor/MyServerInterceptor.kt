package com.bh.grpc.interceptor

import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import io.grpc.Status

/**
 * 1、作用时机？
请求被具体的Handler相应前。

2、可以做什么？
a）访问认证
b）请求日志记录及监控
c）代理转发
 */
class MyServerInterceptor: ServerInterceptor {
    /**
     * ServerCallHandler：定义用以实现请求处理的接口类。
     * headers：请求头部信息。
     * next：下一个拦截器。
     */
    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        call: ServerCall<ReqT, RespT>?,
        headers: Metadata?,
        next: ServerCallHandler<ReqT, RespT>?
    ): ServerCall.Listener<ReqT> {
        //提取认证信息
        val auth_token = headers!!.get(Metadata.Key.of("auth_token", Metadata.ASCII_STRING_MARSHALLER))
        if (auth_token.isNullOrEmpty()) {
            call!!.close(Status.UNAUTHENTICATED.withDescription("auth_token is empty"), headers)
            return object : ServerCall.Listener<ReqT>() {}
        }
        return next!!.startCall(call, headers)
    }
}