package com.example.didi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    @zhangyufeng
    2018.1
*  thymeleaf类  控制页面展示
* */
@Controller
@RequestMapping(value = "/")
public class DemoController {

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "page/login")
    public String login() {
        return "page/login";
    }

    @RequestMapping(value = "page/personInfo")
    public String personInfo() {
        return "page/personInfo";
    }

    @RequestMapping(value = "page/changepwd")
    public String changepwd() {
        return "page/changepwd";
    }

    @RequestMapping(value = "404")
    public String error() {
        return "404";
    }

    @RequestMapping(value = "page/JiedanbaoDel")
    public String JiedanbaoDel() {
        return "page/JiedanbaoDel";
    }

    @RequestMapping(value = "page/PolicyQuery")
    public String PolicyQuery() {
        return "page/PolicyQuery";
    }

    @RequestMapping(value = "page/redisreboot")
    public String redisreboot() {
        return "page/redisreboot";
    }

    @RequestMapping(value = "page/addNewRedis")
    public String addNewRedis() {
        return "page/addNewRedis";
    }

    @RequestMapping(value = "page/RedisGD")
    public String RedisGD() {
        return "page/RedisGD";
    }

    @RequestMapping(value = "page/ApprovalQuery")
    public String ApprovalQuery() {
        return "page/ApprovalQuery";
    }

    @RequestMapping(value = "page/ApprovalUpdate")
    public String ApprovalUpdate() {
        return "page/ApprovalUpdate";
    }

    @RequestMapping(value = "page/TradeSearch")
    public String TradeSearch() {
        return "page/TradeSearch";
    }

    @RequestMapping(value = "page/TradeInsert")
    public String TradeInsert() {
        return "page/TradeInsert";
    }
}
