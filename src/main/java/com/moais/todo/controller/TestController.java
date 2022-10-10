package com.moais.todo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "테스트", description = "테스트")
@RestController
public class TestController {

    @ApiOperation(value = "테스트", notes = "테스트", httpMethod = "POST")
    @PostMapping("/test")
    public String test() {
        return "test";
    }
}
