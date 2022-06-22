package com.bh.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * 怎么把application.yml或者application.properties配置文件的内容自动映射绑定到配置类的对应属性字段上，
 * 所以我们需要在application.yml文件中添加部分我们自定义的配置内容，如下所示：
 * author:
 *  name: bh
 *  age: 18
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "author")
@ConstructorBinding
public class AuthorProperties {
    private String name;
    private String age;
}
