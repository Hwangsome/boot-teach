package com.bh.grpc.interceptor

import io.grpc.CallCredentials
import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.ClientCall
import io.grpc.ClientInterceptor
import io.grpc.ForwardingClientCall
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import java.util.concurrent.TimeUnit

/**
 * 1、作用时机？
请求被分发出去之前。

2、可以做什么？
a)、请求日志记录及监控
b)、添加请求头数据、以便代理转发使用
c)、请求或者结果重写
通常，如果要提供认证信息的话，可以使用 CallCredentials 实现，虽然，拦截器里也可以通过设置  CallOptions 来提供。

1、method：MethodDescriptor 类型，表示请求方法。包括方法全限定名称、请求服务名称、请求、结果、序列化工具、幂等等。
2、callOptions：此次请求的附带信息。
3、next：执行此次 RPC 请求的抽象链接管道（Channel）
 */
class MyClientInterceptor: ClientInterceptor {
    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        method: MethodDescriptor<ReqT, RespT>?,
        callOptions: CallOptions?,
        next: Channel?
    ): ClientCall<ReqT, RespT> {
        println("method:$method,callOptions:$callOptions,next:$next")
        /**
         * 可以看到我们的实现里，没有实现任何逻辑，直接执行了 next.newCall 继续执行客户端的此次调用。
         * next.newCall 只能在当前上下文中执行，每次调用以及返回都必须是一个完整地回路，逃逸使用会导致不必要的内存泄漏问题。
         */
//        return next!!.newCall(method, callOptions)
//        return next!!.newCall(method,
//            callOptions!!.withDeadlineAfter(500, TimeUnit.MILLISECONDS)
////          为client设置认证信息
////                .withCallCredentials()
//        )

        /**
         * 日志记录
         */
        callOptions!!.withDeadlineAfter(500, TimeUnit.MILLISECONDS)
        return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next!!.newCall(method, callOptions)) {
            override fun sendMessage(message: ReqT?) {
                println("sendMessage:$message")
                super.sendMessage(message)
            }

            /**
             * grpc 开始调用方法之前会先执行这个方法
             * 具体可以看 ClientCall.start()
             */
            override fun start(responseListener: Listener<RespT>?, headers: Metadata?) {
                // 在客户端调用之前，设置auth_token
                headers!!.put(Metadata.Key.of("auth_token", Metadata.ASCII_STRING_MARSHALLER), "valid_token");
                println("start:$responseListener,$headers")
                super.start(responseListener, headers)
            }
        }
    }

}