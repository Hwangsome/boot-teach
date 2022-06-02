package com.bh.kotlin.part2

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength

/**
 * FunSpec允许您通过调用test使用字符串参数调用的函数来描述测试，然后将测试本身作为 lambda 来创建测试。
 */
class MyFunSpec:FunSpec({

    test("String length should return the length of the string").config(enabled=true) {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }


    /**
     * 相当于
    test("sam should be a three letter name") {
    "sam".shouldHaveLength(3)
    }

    test("pam should be a three letter name") {
    "pam".shouldHaveLength(3)
    }

    test("tim should be a three letter name") {
    "tim".shouldHaveLength(3)
    }
     */
        listOf(
            "sam",
            "pam",
            "tim",
        ).forEach {
            test("$it should be a three letter name") {
                it.shouldHaveLength(3)
            }
        }


})