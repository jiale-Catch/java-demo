package com.jiale.aopjdkproxy.controller;

import com.jiale.aopjdkproxy.annotation.IgnoreAccess;
import com.jiale.aopjdkproxy.annotation.RateLimit;
import com.jiale.aopjdkproxy.domain.resp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class TestLimitController {
    @RateLimit
    @GetMapping("/limit")
    @IgnoreAccess
    public ResponseResult<String> limit() {
        log.info("limit");
        return ResponseResult.success();
    }

    @RateLimit(limit = 2)
    @GetMapping("/limit1")
    @IgnoreAccess
    public ResponseResult<String> limit1() {
        log.info("limit1");
        return ResponseResult.success();
    }

    @GetMapping("/nolimit")
    public ResponseResult<String> noRateLimiter() {
        log.info("no limit");
        return ResponseResult.success();
    }
}
