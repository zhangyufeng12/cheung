package com.example.didi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    @RequestMapping(value = "page/JiedanbaoDel")
    public String JiedanbaoDel() {
        return "page/JiedanbaoDel";
    }
    @RequestMapping(value = "page/PolicyQuery")
    public String PolicyQuery() {
        return "page/PolicyQuery";
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
    @RequestMapping(value = "page/table")
    public String table() {
        return "page/table";
    }

    @RequestMapping(value = "page/table_1")
    public String table_1() {
        return "page/table_1";
    }

    @RequestMapping(value = "page/myloginfo")
    public String myloginfo() {
        return "page/myloginfo";
    }

    @RequestMapping(value = "404")
    public String error() {
        return "404";
    }

    @RequestMapping(value = "page/linksList")
    public String linksList() {
        return "page/linksList";
    }

    @RequestMapping(value = "page/linksAdd")
    public String linksAdd() {
        return "page/linksAdd";
    }
}
