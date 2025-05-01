package com.mycom.myapp.user.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    // #1
    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }

    // #2
    @PostMapping("/param1")
    public void param1 (
        @RequestParam("id") Integer id,
        @RequestParam("name") String name
    ) {
        log.info("param1");
        log.info("id :" + id + " name :" + name);
    }
}
