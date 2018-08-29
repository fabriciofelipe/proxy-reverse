package com.proxy.reverse.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    @GetMapping("/test")
    public String helloWorld() {
        return "Hello World";
    }
}
