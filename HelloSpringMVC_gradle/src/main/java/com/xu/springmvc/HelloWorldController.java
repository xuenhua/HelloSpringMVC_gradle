package com.xu.springmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xu.dao.Student;
import com.xu.dao.StudentManager;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
@Controller
public class HelloWorldController {
	@RequestMapping("/hello")
    public String hello(Model model) {
         
        model.addAttribute("greeting", "Hello Spring MVC");
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        TestBean sm2=(TestBean)wac.getBean("test");
		sm2.doprint();
		StudentManager sm=(StudentManager) wac.getBean("studentManager");
		JdbcTemplate j=	sm.getJdbcTemplate(); 
		 
		Student s=new Student("ok",18,"999");
		s.setJDBC(j);
		s.addStudent(s);
		s.getStudents();
		
        return"helloworld";
         
    }
}
