package com.bh.jackson.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private String brand = null;
    private int doors = 0;
    private LocalDate produceTime = null;
}