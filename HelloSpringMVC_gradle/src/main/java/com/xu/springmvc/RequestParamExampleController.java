package com.xu.springmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RequestParamExampleController {
	@RequestMapping("/user")
    public String userInfo(Model model,
            @RequestParam(value = "name", defaultValue = "Guest") String name) {
 
        model.addAttribute("name", name);
 
        if("admin".equals(name)) {
            model.addAttribute("email", "admin@yiibai.com");
        } else{
            model.addAttribute("email", "Not set");
        }
        return "userInfo";
    }
}
