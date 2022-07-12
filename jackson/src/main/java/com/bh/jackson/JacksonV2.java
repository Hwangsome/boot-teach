package com.bh.jackson;


import com.bh.jackson.pojo.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.SimpleTimeZone;

@Slf4j
public class JacksonV2 {
    public static void main(String[] args) throws JsonProcessingException {
        Car car = Car.builder()
                .brand("bmw")
                .doors(10)
                .produceTime(LocalDate.now())
                .build();

        /**
         * 配置jackson
         */
        ObjectMapper objectMapper = new ObjectMapper()
                // 自动加载 classpath 中所有 Jackson Module
                .findAndRegisterModules()
                // 时区序列化为 +08:00 形式
                .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false)
                // 日期、时间序列化为字符串
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                // 持续时间序列化为字符串
                .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)
                // 当出现 Java 类中未知的属性时不报错，而是忽略此 JSON 字段
                .configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false)
                // 枚举类型调用 `toString` 方法进行序列化
                .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
                // 设置 java.util.Date 类型序列化格式
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                // 设置 Jackson 使用的时区
                .setTimeZone(SimpleTimeZone.getTimeZone("GMT+8"));

        /**
         * 美化输出
         *
         * {
         *   "brand" : "bmw",
         *   "doors" : 10,
         *   "produceTime" : "2022-07-11"
         * }
         */
        String carJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
        log.info(carJson);
    }
}
