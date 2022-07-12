package com.bh.spring.transaction.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String name;
    private Integer balance;
}
