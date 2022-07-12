package com.bh.jackson.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * @JsonIgnoreProperties Jackson注解用于指定要忽略的类的属性列表。
 * @JsonIgnoreProperties注解放置在类声明上方，而不是要忽略的各个属性（字段）上方。
 *
 * 在此示例中，属性firstName和lastName都将被忽略，因为它们的名称在类声明上方的@JsonIgnoreProperties注解声明内列出。
 */
@JsonIgnoreProperties({"last_name","first_name"})
public class Person {
    /**
     * @JsonSetter : json中的person_id值映射到id值中
     */
    @JsonSetter("person_id")
    private Integer id;
    private String last_name;
    private String first_name;

    /**
     * Jackson注解@JsonIgnore用于告诉Jackson忽略Java对象的某个属性（字段）。
     * 在将JSON读取到Java对象中以及将Java对象写入JSON时，都将忽略该属性。
     *
     * 不会从JSON读取或写入JSON属性jsonIgnore。
     */
    @JsonIgnore
    private String jsonIgnore;
}
