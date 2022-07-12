package com.bh

fun main() {
    for (i in 1..8){
       val result = """
           CREATE VIEW  lodging_inventory_dailydata0${i}_rateplanlevelcostprice As SELECT * from lodging_inventory_dailydata0${i}_rateplanlevelcostprice;
    """.trimIndent()
        println(result)
    }

}