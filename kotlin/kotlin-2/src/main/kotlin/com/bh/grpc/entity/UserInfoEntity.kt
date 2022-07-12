package com.bh.grpc.entity

data class UserInfoEntity(
    val id: String,
    val name: String,
    val hobbys: List<HobbyEntity>,
    val sex: SexEntity,
    val age: Int
)

data class HobbyEntity(
    val id: String,
    val name: String
)

data class MaleEntity(
    val name: String
)

data class FeMaleEntity(
    val name: String
)

data class SexEntity(
    val male: MaleEntity? = null,
    val feMale: FeMaleEntity? = null
)
