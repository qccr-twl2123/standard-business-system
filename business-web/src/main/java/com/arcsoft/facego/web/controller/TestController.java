package com.arcsoft.facego.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping("main")
    public String main(){
        return "main";
    }

    @RequestMapping("demo")
    public String test(){
        System.out.println("---come demo---");
        return "demo";
    }

    @GetMapping("changeLanguage")
    public String changeLanguage(String lang, HttpSession session, HttpServletResponse response) {
        if ("zh".equals(lang)) {
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("zh", "CN"));
        } else if ("en".equals(lang)) {
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en", "US"));
        }
        return "ok";
    }




}
