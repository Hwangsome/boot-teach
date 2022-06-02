package com.bh.kotlin.service

import com.bh.kotlin.model.UserInfo
import org.springframework.http.ResponseEntity

interface UserService {
    fun getUserInfo(): ResponseEntity<UserInfo>
}