package com.bh.grpc.client

import com.bh.grpc.helloworld.HelloRequest
import com.bh.grpc.helloworld.HelloServiceGrpc
import io.grpc.ManagedChannelBuilder
import mu.KLogging


fun helloClient() {

    val channel = ManagedChannelBuilder.forAddress("localhost", 15001)
        .usePlaintext()
        .build()
    val stub = HelloServiceGrpc.newBlockingStub(channel)
    val response = stub.hello(HelloRequest.newBuilder().setName("bhuang").build())
    println(response.message)
}

fun main(args: Array<String>) {
    helloClient()
}