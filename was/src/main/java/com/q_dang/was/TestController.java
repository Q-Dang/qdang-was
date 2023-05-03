package com.q_dang.was;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public String test2() {
        return "test2";
    }
}
