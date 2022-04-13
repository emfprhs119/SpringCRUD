package com.min.mutabledata;

import com.min.mutabledata.model.MockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/")
@Controller
public class WebController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MockService mockService;

    @GetMapping("/database")
    String database(Model model, @RequestParam(value="page", defaultValue="1") int page) {
        model.addAttribute("title","Database");
        model.addAttribute("appList",mockService.findAllPage(page-1,10));
        //model.addAttribute("appList",mockService.findAll());
        return "index";
    }

    @RequestMapping( method = RequestMethod.GET, path={"/home","/index"})
    String home(Model model) {
        model.addAttribute("title","Home");
        model.addAttribute("content_name","Main");
        return "index";
    }

    @RequestMapping("/test")
    String test(Model model) {
        model.addAttribute("test","이거슨 model로 가져온값이다!");
        return "test";
    }
}