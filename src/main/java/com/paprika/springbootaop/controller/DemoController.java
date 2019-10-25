package com.paprika.springbootaop.controller;

import com.paprika.springbootaop.annotation.WebVisitLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author adam
 * @date 2019/10/25
 */
@RestController
@Slf4j
@Api(tags = "Web日志测试相关接口")
public class DemoController {

    @GetMapping("/log")
    @ApiOperation("接口日志GET请求测试")
    @WebVisitLog(description = "GET请求测试接口", intoDatabase = true)
    public String hello(@RequestParam("name") String name){
        return name;
    }
}
