package com.bh.kotlin.part4

import org.junit.jupiter.api.*

class SimpleUnitTest {

//    private var initNumber =0
//
//    @BeforeAll
//    fun init() {
//        initNumber =100
//    }

    //@AfterAll


    @Test
    fun `isEmpty should return true for empty lists`() {
        val list = listOf<String>()
        Assertions.assertTrue(list::isEmpty)
    }


    /**
     * 你不想测试哪一个方法，则使用@Disabled标记即可
     */
    @Test
//    @Disabled
    fun `3 is equal to 4`() {
        Assertions.assertEquals(3, 4) {
            "Three does not equal four"
        }
    }
}