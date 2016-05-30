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
	   <th>Fare_code</th>
	   <!--<th>Amount</th> <th>Restrictions</th>-->
	   <th>Amount</th>
	   <th>Restrictions</th>
	   
	</tr>
	<c:forEach items="${result}" var="item">
	<tr>
	   <td><c:out value="${item.flightNo}"/></td>
	   <td><c:out value="${item.fareCode}"/></td>
	   <td><c:out value="${item.amount}"/></td>
	   <td><c:out value="${item.restrictions}"/></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>