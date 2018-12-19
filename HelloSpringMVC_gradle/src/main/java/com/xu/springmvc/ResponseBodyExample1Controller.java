package com.xu.springmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ResponseBodyExample1Controller {

	// Simple example, method returns String.
    @RequestMapping(value = "/saveResult")
    @ResponseBody
    public String authorInfo(Model model) {
        return "saved";
    }
}
