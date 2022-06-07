# log4j2 springboot
为了使用 YAML 配置 Spring Boot Log4J2，我们需要在我们的应用程序中有额外的包。
这些包是jackson-dataformat-yaml和jackson-databind。
```xml
<dependency> 
			<groupId> com.fasterxml.jackson.dataformat </groupId> 
			<artifactId> jackson-dataformat-yaml </artifactId> 
			<version> 2.11.3 </version> 
</dependency> 
<dependency> 
			<groupId> com .fasterxml.jackson.core </groupId> 
			<artifactId> jackson-databind </artifactId> 
			<version> 2.11.3 </version> 
</dependency>
```

## log4j2 yaml 
```yaml
Configutation:
  name: Default
  Properties:
    Property:
      name: log_pattern
      value: "%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${hostName} ====》 [%15.15t] %-40.40c{1.} : %m%n%ex"
  Appenders:
    Console:
      name: Console_Appender
      target: SYSTEM_OUT
      PatternLayout:
        pattern: ${log_pattern}
  Loggers:
    Logger:
      - name: com.bh
        level: debug
        additivity: false
        AppenderRef:
          - ref: Console_Appender
    Root:
      level: info
      AppenderRef:
        - ref: Console_Appender
```