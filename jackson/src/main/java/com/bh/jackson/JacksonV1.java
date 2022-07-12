package com.bh.jackson;

import com.bh.jackson.pojo.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * Jackson 最常用的 API 就是基于"对象绑定" 的 ObjectMapper：
 * <p>
 * ObjectMapper可以从字符串，流或文件中解析JSON，并创建表示已解析的JSON的Java对象。 将JSON解析为Java对象也称为从JSON反序列化Java对象。
 * ObjectMapper也可以从Java对象创建JSON。 从Java对象生成JSON也称为将Java对象序列化为JSON。
 * Object映射器可以将JSON解析为自定义的类的对象，也可以解析置JSON树模型的对象。
 * <p>
 * 之所以称为ObjectMapper是因为它将JSON映射到Java对象（反序列化），或者将Java对象映射到JSON（序列化）。
 *
 * jackson 的推荐配置：
 * ObjectMapper objectMapper = new ObjectMapper()
 *         // 自动加载 classpath 中所有 Jackson Module
 *         .findAndRegisterModules()
 *         // 时区序列化为 +08:00 形式
 *         .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false)
 *         // 日期、时间序列化为字符串
 *         .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
 *         // 持续时间序列化为字符串
 *         .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)
 *         // 当出现 Java 类中未知的属性时不报错，而是忽略此 JSON 字段
 *         .configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false)
 *         // 枚举类型调用 `toString` 方法进行序列化
 *         .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
 *         // 设置 java.util.Date 类型序列化格式
 *         .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
 *         // 设置 Jackson 使用的时区
 *         .setTimeZone(SimpleTimeZone.getTimeZone("GMT+8"));
 *
 *         springboot中的配置：
 *  spring:
 *   jackson:
 *     date-format: yyyy-MM-dd HH:mm:ss
 *     time-zone: GMT+8
 *     locale: zh_CN
 *     serialization:
 *       WRITE_DATES_WITH_ZONE_ID: false
 *       WRITE_DURATIONS_AS_TIMESTAMPS: false
 *       WRITE_DATES_AS_TIMESTAMPS: false
 *       FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS: false
 *       WRITE_ENUMS_USING_TO_STRING: true
 */
@Slf4j
public class JacksonV1 {
    public static void main(String[] args) throws JsonProcessingException {
        /**
         * ObjectMapper 通过 writeValue 系列方法将 java 对象序列化为 json，并将 json 存储成不同的格式，
         * String（writeValueAsString），Byte Array（writeValueAsString），Writer， File，OutStream 和 DataOutput。
         *
         * ObjectMapper 通过 readValue 系列方法从不同的数据源像 String ， Byte Array， Reader，File，URL，
         * InputStream 将 json 反序列化为 java 对象。
         */
        ObjectMapper mapper = new ObjectMapper();
        /**
         * 如果不添加这一行的话会报下面的错误，因为Java8增加了一套全新的日期时间类(LocalDate, LocalTime, LocalDateTime)，为支持这些类的序列化，需要先注册这些模块
         * Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.bh.jackson.pojo.Car["produceTime"])
         *
         * objectMapper.registerModule(new JavaTimeModule())
         *         // .registerModule(new ParameterNamesModule())
         *         .registerModule(new Jdk8Module());
         *         上面的几行代码可以使用：mapper.findAndRegisterModules(); 代替
         */
//        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();
        Car car = new Car();
        car.setBrand("bmw");
        car.setDoors(99);
        car.setProduceTime(LocalDate.now());
        String carJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
        log.info("序列化...");
        /**
         * {
         *   "brand" : "bmw",
         *   "doors" : 99,
         *   "produceTime" : [ 2022, 7, 11 ]
         * }
         */
        log.info(carJson);

        log.info("反序列化...");
        Car carDeserialized = mapper.readValue(carJson, Car.class);
        /**
         * Car(brand=bmw, doors=99, produceTime=2022-07-11)
         */
        log.info("carDeserialized:"+carDeserialized);
    }
}
