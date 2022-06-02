package com.bh.kotlin.controller

import com.bh.kotlin.model.UserInfo
import com.bh.kotlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(private val userService: UserService) {

    @GetMapping("getUserInfo")
    fun getUserInfo(): ResponseEntity<UserInfo> {
        return userService.getUserInfo()
    }
}