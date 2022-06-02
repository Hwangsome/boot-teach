package com.bh.kotlin.part2

import io.kotest.core.spec.style.DescribeSpec

/**
 * DescribeSpec提供了一种对于那些有 Ruby 或 Javascript 背景的人来说很熟悉的风格，
 * 因为这种测试风格使用describe/it关键字。测试必须嵌套在一个或多个describe块中。
 */
class MyDescribeSpec: DescribeSpec({
    describe("score") {
        it("start as zero") {
            // test here
        }
        describe("with a strike") {
            it("adds ten") {
                // test here
            }
            it("carries strike to the next frame") {
                // test here
            }
        }

        describe("for the opposite team") {
            it("Should negate one score") {
                // test here
            }
        }
    }
})