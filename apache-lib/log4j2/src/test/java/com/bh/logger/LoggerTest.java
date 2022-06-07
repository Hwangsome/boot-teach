package com.bh.logger;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.Appender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test01(){
        log.info("aaa");
    }
}
