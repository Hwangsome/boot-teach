package com.bh.kafka.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author bhuang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People implements java.io.Serializable {
    private String name;
    private int age;
    private String address;
}
