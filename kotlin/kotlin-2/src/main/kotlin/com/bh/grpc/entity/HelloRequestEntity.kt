package com.bh.grpc.entity

data class HelloRequestEntity(
    var name: String,
    var hobbys: List<String>
)
