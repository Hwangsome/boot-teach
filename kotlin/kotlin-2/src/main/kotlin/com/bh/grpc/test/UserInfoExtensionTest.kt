package com.bh.grpc.test

import com.bh.grpc.entity.FeMaleEntity
import com.bh.grpc.entity.HobbyEntity
import com.bh.grpc.entity.SexEntity
import com.bh.grpc.entity.UserInfoEntity
import com.bh.grpc.entity.toContract
import com.bh.grpc.entity.toDomain
import com.bh.grpc.user.UserInfoContract


fun main() {
    var userInfoEntity = UserInfoEntity(
        "1",
        "bh",
        listOf(HobbyEntity("1", "it"), HobbyEntity("2", "oo")),
        SexEntity(feMale = FeMaleEntity("女")),
        age = 18
    )
    var userInfoEntitytoContract = userInfoEntity.toContract()
    println("userInfoEntitytoContract=$userInfoEntitytoContract")

    var userInfoEntitytoDomain = userInfoEntitytoContract.toDomain()
    println("userInfoEntitytoDomain=$userInfoEntitytoDomain")

    val build = UserInfoContract.newBuilder().apply {
        id = "0"
    }.build()
    println("build：$build")
}
