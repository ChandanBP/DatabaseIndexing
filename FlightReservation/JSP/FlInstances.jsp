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
	   <th>Flight Number</th>
	   <th>Travel Date</th>
	</tr>
	<c:forEach items="${result}" var="item">
	<tr>
	   <td><c:out value="${item.fnumber}"/></td>
	   <td><c:out value="${item.tdate}"/></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>