package com.bh.kotlin.part2

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

/**
 * ShouldSpec类似于 fun 规范，但使用关键字should而不是test.
 */
class MyShouldSpec:ShouldSpec({
    should("return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }
})