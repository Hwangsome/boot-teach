package com.bh.kotlin.part1

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength

class DemoUnitTest: FunSpec({

    /**
     *Kotest 提供了几个回调，它们在测试生命周期的不同点被调用。这些回调对于重置状态、设置和拆除测试可能使用的资源等很有用。
     * 如前所述，Kotest 中的测试函数被标记为测试容器或测试用例，此外包含的类被标记为规范。我们可以注册在任何测试函数、容器、测试用例或规范本身之前或之后调用的回调。
     * 要注册回调，我们只需将一个函数传递给其中一个回调方法。
     *
     * 可以使用函数字面量在任何测试用例之前和之后添加回调
     */
    beforeEach {
        println("Hello from $it")
    }


    afterEach {
        println("Goodbye from $it")
    }



    test("my first test"){
        1+2 shouldBe 3
    }


})
//{
//    init {
//        test("this is my first test"){
//            val name="bhuang"
//            name.shouldHaveLength(6)
//        }
//    }
//
//
//}