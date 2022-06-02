package com.bh.kotlin.service.impl

import com.bh.kotlin.model.UserInfo
import com.bh.kotlin.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class UserServiceImpl:UserService {

    override fun getUserInfo(): ResponseEntity<UserInfo> {

  return RestTemplate().getForEntity("https://mock.apipost.cn/app/mock/project/f3945492-7818-41ea-a896-04aff3d8025c/getUserInfo", UserInfo::class.java)

    }
}