package com.bh.spring.transaction.service;

import com.bh.spring.transaction.mapper.UserMapper;
import com.bh.spring.transaction.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 隔离属性
 * 传播属性
 * 只读属性
 * 超时属性
 * 异常属性
 */
@Service
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,timeout = -1,readOnly =false)
public class UserService {

    @Autowired
    private UserMapper usermapper;

    public void save(User user) {
        usermapper.insert(user);
        throw new RuntimeException("测试回滚数据");
    }
}
