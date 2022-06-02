package com.bh.kotlin.part2

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/**
 * StringSpec将语法减少到绝对最小值。只需使用您的测试代码编写一个字符串，然后是一个 lambda 表达式。
 */
class MyStringSpec: StringSpec({
    "strings.length should return size of string" {
        "hello".length shouldBe 5
    }
})