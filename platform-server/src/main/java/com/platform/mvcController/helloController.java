package com.platform.mvcController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class helloController {
    @RequestMapping
    public String index(){
        return "index";
    }
}
