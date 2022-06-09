package com.bh.jackson;

import com.bh.jackson.pojo.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Jackson 最常用的 API 就是基于"对象绑定" 的 ObjectMapper：
 * <p>
 * ObjectMapper可以从字符串，流或文件中解析JSON，并创建表示已解析的JSON的Java对象。 将JSON解析为Java对象也称为从JSON反序列化Java对象。
 * ObjectMapper也可以从Java对象创建JSON。 从Java对象生成JSON也称为将Java对象序列化为JSON。
 * Object映射器可以将JSON解析为自定义的类的对象，也可以解析置JSON树模型的对象。
 * <p>
 * 之所以称为ObjectMapper是因为它将JSON映射到Java对象（反序列化），或者将Java对象映射到JSON（序列化）。
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
        Car car = new Car();
        car.setBrand("bmw");
        car.setDoors(99);
        String carJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
        log.info("序列化...");
        log.info(carJson);

        log.info("反序列化...");
        Car carDeserialized = mapper.readValue(carJson, Car.class);
        log.info("carDeserialized:"+carDeserialized);
    }
}
