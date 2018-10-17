package com.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SecurityApp {

    public static void main(String[] args){
        SpringApplication.run(SecurityApp.class,args);
    }


    @RequestMapping("/admin/test")
    public String adminUrl(){

        return "admin";
    }

    @RequestMapping("test")
    public String test(){
        return "testing";
    }

}
