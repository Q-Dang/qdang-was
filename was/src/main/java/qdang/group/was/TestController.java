package qdang.group.was;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public String test1() {
        return "hello";
    }

    @ResponseBody
    @RequestMapping()
    public String test2() {
        return "hello";
    }
}
