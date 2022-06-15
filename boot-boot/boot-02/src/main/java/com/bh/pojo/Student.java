package com.bh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Component("Id")
 * 如果不设置id，直接使用@Component来创建bean,Spring会默认的为它生成ID,即将类名首字母变为小写。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
   private Integer age;
   private String name;
}