package com.bh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class RetryService {

    /**
     * 默认情况下，会重试3次，间隔1秒
     * int maxAttempts() default 3;
     * Backoff类中：
     * long value() default 1000;
     * @throws IllegalAccessException
     *
     * 2022-06-22 ==> 15:46:34.820  INFO EXPC02Z8114LVCH ====》 [nio-9999-exec-1] o.s.w.s.DispatcherServlet                : Completed initialization in 1 ms
     * 2022-06-22 ==> 15:46:34.861  INFO EXPC02Z8114LVCH ====》 [nio-9999-exec-1] c.b.s.RetryService                       : do something...2022-06-22
     * 2022-06-22 ==> 15:46:35.863  INFO EXPC02Z8114LVCH ====》 [nio-9999-exec-1] c.b.s.RetryService                       : do something...2022-06-22
     * 2022-06-22 ==> 15:46:36.866  INFO EXPC02Z8114LVCH ====》 [nio-9999-exec-1] c.b.s.RetryService                       : do something...2022-06-22
     * 2022-06-22 ==> 15:46:36.877 ERROR EXPC02Z8114LVCH ====》 [nio-9999-exec-1] o.a.c.c.C.[.[.[.[dispatcherServlet]      : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.IllegalAccessException: access exception] with root cause
     * java.lang.IllegalAccessException: access exception
     * 重试三次之后抛出异常
     */
    @Retryable(value = IllegalAccessException.class,maxAttemptsExpression = "${retry.maxAttempts}")
    public void retryTest() throws IllegalAccessException {
        log.info("do something...{}", LocalDate.now());
        throw new IllegalAccessException("access exception");
    }
}
