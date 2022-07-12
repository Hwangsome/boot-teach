package com.bh.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "statsd")
@Configuration
public class StatsDConfig {
    private String prefix;
    private String host;
    private Integer port;
    private Boolean systemMetricsEnabled;
}