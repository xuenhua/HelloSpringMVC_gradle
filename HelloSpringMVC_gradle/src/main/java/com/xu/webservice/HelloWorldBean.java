package com.xu.webservice;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xu.dao.Student;
import com.xu.dao.StudentManager;
import com.xu.springmvc.TestBean;

public class HelloWorldBean implements HelloWorld {
	//@Transactional
    public String greeting(String name) {
    	System.out.println("=======OK======");
    	WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        TestBean sm2=(TestBean)wac.getBean("test");
		sm2.doprint();
		StudentManager sm=(StudentManager) wac.getBean("studentManager");
		JdbcTemplate j=	sm.getJdbcTemplate(); 
		 
		Student s=new Student("ok",18,"999");
		s.setJDBC(j);
		s.addStudent(s);
		s.getStudents();
        return "你好 "+name;
    }

    public String print() {
    	System.out.println("=======OK=2=====");
        return "我叫xuenhua";
    }
}
