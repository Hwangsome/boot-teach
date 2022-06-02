package com.bh

import com.bh.kotlin.vals.ValTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ValTesta @Autowired constructor(private val valTest: ValTest) {



    @Test
    fun test(){
        println(valTest.name)
    }
}