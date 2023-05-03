package com.q_dang.was;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/")
    public String test1() {
        return "test1";
    }

    @RequestMapping("/test")
    public String test2() {
        return "test2";
    }

}
