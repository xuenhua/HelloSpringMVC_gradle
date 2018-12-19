package com.xu.springmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class RedirectExampleController {
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String authorInfo(Model model) {
 
       // Do somethong here
 
        return "redirect:/hello";
    }
}
