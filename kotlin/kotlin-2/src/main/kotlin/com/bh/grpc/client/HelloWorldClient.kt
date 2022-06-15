package com.bh.grpc.client

import com.bh.grpc.helloworld.HelloRequest
import com.bh.grpc.helloworld.HelloServiceGrpc
import com.bh.grpc.interceptor.MyClientInterceptor
import io.grpc.ManagedChannelBuilder
import mu.KLogging


fun helloClient() {

    val channel = ManagedChannelBuilder.forAddress("localhost", 15001)
        .usePlaintext()
//            client 调用的时候添加上拦截器
        .intercept(MyClientInterceptor())
        .build()

    val stub = HelloServiceGrpc.newBlockingStub(channel)
    val response = stub.hello(HelloRequest.newBuilder().setName("bhuang").build())
    println(response.message)
}

fun main(args: Array<String>) {
    helloClient()
}