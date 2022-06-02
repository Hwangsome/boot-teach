package com.bh.kotlin.part2

import com.bh.kotlin.part4.Calculator
import io.kotest.core.spec.style.BehaviorSpec

/**
 * 受喜欢以BDD风格编写测试的人欢迎，BehaviorSpec允许您使用given, when, then.
 */
class MyBehaviorSpec: BehaviorSpec({
    val number1 =100
    val number2 =10
    given("two value to caculate") {
        `when`("two value is no equal zero") {
            then("calculate") {
                // test code
               val calculator= Calculator()
                calculator.divide(number1,number2)
            }
        }
        `when`("I throw it away") {
            then("it should come back") {
                // test code
                println("away...")
            }
        }
    }

})