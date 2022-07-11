package com.bh.grpc.client

//import com.bh.grpc.entity.HelloRequestEntity
//import com.bh.grpc.helloworld.HelloRequest
//
////fun main() {
////    val toContract = HelloRequestEntity("bh", listOf("1", "2", "3")).toContract()
////    println("toContract:$toContract")
////}
////
////fun HelloRequestEntity.toContract(): HelloRequest {
////    return this.let { helloRequestEntity ->
////        HelloRequest.newBuilder().apply {
////            name = helloRequestEntity.name
////            val helloRequestEntityContract = if (helloRequestEntity.hobbys.minOf { it.toInt() == 0 }) helloRequestEntity.hobbys
////            else helloRequestEntity.hobbys + HelloRequestEntity("a",listOf("0"))
////        }
////    }.build()
////}
