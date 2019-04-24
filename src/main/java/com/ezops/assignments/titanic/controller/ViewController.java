package com.ezops.assignments.titanic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author yao.chen
 */

@Controller
public class ViewController {

//    @CrossOrigin(origins = "*")
    @RequestMapping("/")
    public String titanic(){
        return "titanic";
    }

}
