package com.xu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

public class Student {
	JdbcTemplate jdbcTemplate;
	String name;
	String phone;
	int age;
	public Student(){}
	public void setJDBC(JdbcTemplate j){
		this.jdbcTemplate=j;
	}
	public Student(String name,int age,String phone){
		this.age=age;
		this.name=name;
		this.phone=phone;
	}
	public  String getStudentName(){
		return this.name;
	}
	public  int getStudentAge(){
		return this.age;
	}
	public  String getStudentPhone(){
		return this.phone;
	}
	@Transactional
	public boolean addStudent(Student student)
	{
	 try
	 {
	 jdbcTemplate.update("insert into student values(null,?,?,?)", 
	  new Object[]{student.getStudentName(), student.getStudentAge(), student.getStudentPhone()},
	  new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
	  return true;
	 }
	 catch (Exception e)
	 {
	 return false;
	 }
	}
	// 根据Id删除单个学生信息
	public boolean deleteStudent(int id)
	{
	 try
	 {
	 jdbcTemplate.update("delete from student where studentId = ?", new Object[]{id}, new int[]{Types.INTEGER});
	 return true;
	 }
	 catch (Exception e)
	 {
	 return false;
	 }
	}
	// 根据Id更新指定学生信息
	public boolean updateStudent(int Id, Student student)
	{
	 try
	 {
	 jdbcTemplate.update("update student set studentName = ?, studentAge = ?, studentPhone = ? where studentId = ?", 
	  new Object[]{student.getStudentName(), student.getStudentAge(), student.getStudentPhone(), Id},
	  new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER});
	 return true;
	 }
	 catch (Exception e)
	 {
	 return false;
	 }
	}
	// 根据学生Id查询单个学生信息
	public Student getStudent(int id)
	{
	 try
	 {
	  return (Student)jdbcTemplate.queryForObject("select * from student where studentId = ?", new Object[]{id}, new int[]{Types.INTEGER}, 
			  new RowMapper<Student>(){
			  public Student mapRow(ResultSet rs, int arg1) throws SQLException{
		      Student student = new Student("zhangsan",18,"110");
		      return student;
	      }
		  });
	 }
	 // 根据Id查询学生信息抛异常, 不管什么原因, 认为查询不到该学生信息, 返回null
	 catch (DataAccessException e){
		 return null;
	 }
	}
	// 查询所有学生信息
	public List<Student> getStudents()
	{
	 List<Map<String, Object>> resultList = jdbcTemplate.queryForList("select * from student");
	 List<Student> studentList = null;
	 if (resultList != null && !resultList.isEmpty())
	 {
	 studentList = new ArrayList<Student>();
	 Map<String, Object> map = null;
	 for (int i = 0; i < resultList.size(); i++)
	 {
	  map = resultList.get(i);
	  Student student = new Student(
	  (String)map.get("name"),
	  (Integer)map.get("age"), (String)map.get("phone")
	  );
	  System.out.println(map.get("name"));
	  studentList.add(student);
	  }
	 }
	 return studentList;
	}
	
}
