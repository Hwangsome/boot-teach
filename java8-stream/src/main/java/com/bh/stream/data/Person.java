package com.bh.stream.data;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
public class Person {
    private Integer id;
    private String name;
    private Integer age;
    private Integer score;

    public Person setId(Integer id) {
        this.id = id;
        return this;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Person setScore(Integer score) {
        this.score = score;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getScore() {
        return score;
    }
}
