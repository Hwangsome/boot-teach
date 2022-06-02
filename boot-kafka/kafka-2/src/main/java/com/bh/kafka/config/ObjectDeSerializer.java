package com.bh.kafka.config;



import com.bh.kafka.pojo.People;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author bhuang
 */
public class ObjectDeSerializer implements Deserializer<Object> {
    public static void main(String[] args) {
        ObjectDeSerializer objectDeSerializer = new ObjectDeSerializer();
        final byte[] bytes = new People("12", 122,"1").toString().getBytes(StandardCharsets.UTF_8);
//        final Object deserialize = objectDeSerializer.deserialize("",bytes);
//        System.out.println("deserialize = " + deserialize);
//        bytes2Object(bytes);
//        bytes2Object(new byte[] {1,2});
        Gson gson = new Gson();
        final String people = new String(bytes, StandardCharsets.UTF_8);



        System.out.println("people = " + people);

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        Gson gson = new Gson();
//        final People people = gson.fromJson(bytes2Object(data), Object.class);
//        System.out.println("people = " + people);
        return null;
    }

    public static Object bytes2Object(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }


}
