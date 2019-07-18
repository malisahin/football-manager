package com.works.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mali.sahin
 * @since 2019-07-18.
 */
@RestController
@RequestMapping("/api/private")
public class PrivateController {


    @GetMapping
    public  String getMessage(){
        return "Hello from PRIVATE API";
    }
}
