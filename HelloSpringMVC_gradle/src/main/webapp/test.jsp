<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.xu.client.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
String wsurl="http://localhost:8080/HelloSpringMVC_gradle/services/hwWebService?wsdl";
String method="greeting";
String params="xuenhua";
String rtn=SoapUtilities.sendRequest(wsurl, method, params);
System.out.println("page:"+rtn);
out.write(rtn);
%>

</body>
</html>