package com.zmu.controller;

import com.zmu.service.loginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class loginServlet {
    @Resource
    private loginService service;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody Map<String, String> params) {
        String auth = service.login(params.get("username"), params.get("password"));

        if(auth == "stu")
            return "stu";
        else if(auth == "teacher")
            return "teacher";
        else if(auth == "admin")
            return "admin";
        else
            return null;
    }
}
