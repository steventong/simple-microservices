package me.itwl.apiservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Steven on 2019/10/27.
 */
@RestController
public class ApiController {

    @GetMapping("/hello")
    public String getRequest() {
        return "Hello World.";
    }
}
