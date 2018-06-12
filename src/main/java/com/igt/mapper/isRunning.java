package com.igt.mapper;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class isRunning {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public static @ResponseBody
    String isRunning() {
        return "Server is running";
    }

}