package com.xu.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class MyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new FileSystemXmlApplicationContext("file:/Users/xuenhua/Documents/workspace/hello_gradle/src/main/resources/jdbc.xml");
				  //new ClassPathXmlApplicationContext("jdbc.xml");
		//StudentManager sm= StudentManager.getInstance();
		
		StudentManager sm2=(StudentManager) ac.getBean("studentManager");
		
		JdbcTemplate j=	sm2.getJdbcTemplate();
				 
		Student s=new Student("lisi",28,"119");
		s.setJDBC(j);
		s.addStudent(s);
		s.getStudents();
	}

}
