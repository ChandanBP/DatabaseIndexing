<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
    <%@ page import="javax.servlet.http.*, javax.servlet.*" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
	   <th>Flight_number</th>
	   <th>Weekdays</th>
	</tr>
	
	<c:forEach var="innerList" items="${result}">
	 <c:forEach var="obj" items="${innerList}">
		<tr>
		   <td><c:out value="${obj.fno}"/></td>
		   <td><c:out value="${obj.weekdays}"/></td>
		</tr>
	</c:forEach>
	</c:forEach>
</table>
</body>
</html>