package com.bh.spring.transaction.controller;

import com.bh.spring.transaction.pojo.User;
import com.bh.spring.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/save")
    public ResponseEntity<User> save(){
        User user = User.builder().balance(100).id(new Random().nextInt()).name("bh").build();
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}
