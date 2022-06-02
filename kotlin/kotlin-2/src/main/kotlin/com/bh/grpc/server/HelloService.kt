package com.bh.grpc.server

import com.bh.grpc.helloworld.HelloReply
import com.bh.grpc.helloworld.HelloRequest
import com.bh.grpc.helloworld.HelloServiceGrpcKt
import com.bh.grpc.server.HelloService
import io.grpc.ServerBuilder


/**
 * 测试的时候：先启动grpc的服务器
 */
fun helloServer() {

    val helloService = HelloService()
    val server = ServerBuilder
        .forPort(15001)
        .addService(helloService)
        .build()

    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        server.awaitTermination()
    })

    server.start()
    server.awaitTermination()
}

fun main(args: Array<String>) {
    helloServer()
}

class HelloService : HelloServiceGrpcKt.HelloServiceImplBase() {
    override suspend fun hello(request: HelloRequest): HelloReply {
        return HelloReply.newBuilder()
            .setMessage("Hello, ${request.name}")
            .build()
    }
}