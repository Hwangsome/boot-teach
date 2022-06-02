package com.bh.kotlin.arrow.fp1

import arrow.fx.IO

val addresses = listOf(
  Address(1, "123 Anywhere Street", "Chicago", "IL")
)

val customers = listOf(
  Customer(1, "Matt", "Moore", 1)
)

val orders = listOf(
  Order(1, 1)
)

fun getOrder(id: Int) = IO<Order> {
  println("[${Thread.currentThread().name}],getOrder")
  orders.find { it.id == id }!!
}

fun getCustomer(id: Int) = IO<Customer> {
  println("[${Thread.currentThread().name}],getCustomer")
  customers.find { it.id == id }!!
}

fun getAddress(id: Int) = IO<Address> {
  println("[${Thread.currentThread().name}],getAddress")
  addresses.find { it.id == id }!!
}