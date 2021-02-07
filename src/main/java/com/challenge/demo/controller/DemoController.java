package com.challenge.demo.controller;

import com.challenge.demo.model.Fleet;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
public class DemoController {

    private static Fleet fleet = new Fleet();

    @ApiOperation(value = "Get the position and the help message", tags = "XWing")
    @GetMapping(value = "/api/v1//hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
