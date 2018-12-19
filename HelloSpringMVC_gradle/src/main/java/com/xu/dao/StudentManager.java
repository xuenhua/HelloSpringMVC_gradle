package com.xu.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class StudentManager
{
 private JdbcTemplate jdbcTemplate;

 private static StudentManager instance = new StudentManager();

 public static StudentManager getInstance()
 {
 return instance;
 }

 public JdbcTemplate getJdbcTemplate()
 {
 return jdbcTemplate;
 }
 public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
 {
 this.jdbcTemplate = jdbcTemplate;
 }
}
