package com.ezops.assignments.titanic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ViewController {

//    @CrossOrigin(origins = "*")
    @RequestMapping("/")
    public String titanic(){
        return "titanic";
    }

//    @RestController
//    public class IndexController implements ErrorController {
//
//        private static final String PATH = "/error";
//
//        @RequestMapping(value = PATH)
//        public String error() {
//            return "Error handling";
//        }
//
//        @Override
//        public String getErrorPath() {
//            return PATH;
//        }
//    }







}
