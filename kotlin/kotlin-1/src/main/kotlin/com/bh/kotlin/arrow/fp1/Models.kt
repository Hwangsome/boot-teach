package com.bh.kotlin.arrow.fp1

data class Address(
    val id: Int,
    val street: String,
    val city: String,
    val state: String
)

data class Customer(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val addressId: Int
)

data class Order(
    val id: Int,
    val customerId: Int
)