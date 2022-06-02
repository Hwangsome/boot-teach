//package com.bh.kafka.config;
//
//import com.google.gson.Gson;
//import org.apache.kafka.common.serialization.Serializer;
//import org.springframework.beans.BeanUtils;
//
//
///**
// * @author bhuang
// */
//public class ObjectSerializer implements Serializer<Object> {
//
//    @Override
//    public byte[] serialize(String topic, Object data) {
//        if (data instanceof byte[]) {
//            return (byte[]) data;
//        }
//        // gson 序列化data
//        Gson gson = new Gson();
//        String json = gson.toJson(data);
//        return json.getBytes();
//    }
//}
