package com.wusd.familyfinanceapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping
    public String test() {
        return "test";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @PostMapping(value = "/post", produces = MediaType.TEXT_PLAIN_VALUE)
    public String post(@RequestParam("a") String a,
                       @RequestParam("b") String b) {
        log.info("TestController.post...a->{},b->{}", a, b);
        return "post";
    }
}
