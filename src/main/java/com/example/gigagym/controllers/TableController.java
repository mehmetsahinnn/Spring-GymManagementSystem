package com.example.gigagym.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TableController {

    @RequestMapping("/table")
    public String table() {
        return "table";
    }

    @RequestMapping("/table-1")
    public String table1() {
        return "table-1";
    }

    @RequestMapping("/table-2")
    public String table2() {
        return "table-2";
    }
}

