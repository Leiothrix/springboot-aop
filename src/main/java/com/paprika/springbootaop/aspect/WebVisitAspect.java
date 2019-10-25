package com.paprika.springbootaop.aspect;

import com.alibaba.fastjson.JSON;
import com.paprika.springbootaop.annotation.WebVisitLog;
import com.paprika.springbootaop.dao.WebVisitRepository;
import com.paprika.springbootaop.domain.WebVisitInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adam
 * @date 2019/10/25
 */
@Slf4j
@Component
@Aspect
@Order(1)
public class WebVisitAspect {

    private WebVisitRepository webVisitRepository;

    @Autowired
    public WebVisitAspect(WebVisitRepository webVisitRepository){
        this.webVisitRepository = webVisitRepository;
    }

    private static final String START_TIME = "startTime";

    private static final String REQUEST_PARAMS = "requestParams";

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    @Pointcut("execution(* com.paprika.springbootaop.controller..*.*(..))")
    public void visitLog() {}

    @Before(value = "visitLog()&& @annotation(webVisitLog)")
    public void doBefore(JoinPoint joinPoint, WebVisitLog webVisitLog){
        // 开始时间。
        long startTime = System.currentTimeMillis();
        Map<String, Object> requestInfo = new HashMap<>();
        requestInfo.put(START_TIME, startTime);
        // 请求参数。
        StringBuilder requestStr = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                requestStr.append(arg.toString());
            }
        }
        requestInfo.put(REQUEST_PARAMS, requestStr.toString());
        threadLocal.set(requestInfo);
        log.info("{}接口开始调用:requestData={}", webVisitLog.description(), requestInfo.get(REQUEST_PARAMS));
    }

    @AfterReturning(value = "visitLog()&& @annotation(webVisitLog)", returning = "res")
    public void doAfterReturning(WebVisitLog webVisitLog, Object res){
        Map<String, Object> requestInfo = threadLocal.get();
        long takeTime = System.currentTimeMillis() - (long) requestInfo.getOrDefault(START_TIME, System.currentTimeMillis());
        if (webVisitLog.intoDatabase()) {
            WebVisitInfo webVisitInfo = new WebVisitInfo();
            webVisitInfo.setOperationName(webVisitLog.description());
            webVisitInfo.setRequest((String) requestInfo.getOrDefault(REQUEST_PARAMS, ""));
            webVisitInfo.setResponse(JSON.toJSONString(res));
            webVisitInfo.setError(false);
            webVisitInfo.setVisitTime(LocalDateTime.now());
            webVisitInfo.setTakeTime(takeTime);
            webVisitRepository.save(webVisitInfo);
        }
        threadLocal.remove();
        log.info("{}接口结束调用:耗时={}ms,result={}", webVisitLog.description(), takeTime, res);
    }

//    @AfterThrowing
//    public void doAfterThrowing(){}
//
//    @After(value = "")
//    public void doAfter(){}
//
//    @Around(value = "")
//    public void doAround(){}

}
