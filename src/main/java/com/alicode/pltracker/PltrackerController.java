package com.alicode.pltracker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PltrackerController {
    
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
