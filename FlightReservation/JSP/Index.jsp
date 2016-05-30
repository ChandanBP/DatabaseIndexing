<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*" %>
    <%@ page import="javax.servlet.http.*, javax.servlet.*" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/Airlines" user="root"  password="root"/>
<sql:query dataSource="${snapshot}" var="result">select Airport_code from AIRPORT;</sql:query>
<form action="MainServlet" method="post">
   <table>
   <tr>
	    <th>Flight Number:</th>
	    <td><input type="text" name="FlightNumber"/></td>
	</tr>
	<tr>
	    <th>Passenger Name:</th>
	    <td><input type="text" name="Name"/></td>
	</tr>
	<tr>
	    <th>Date: </th>
	    <td><input type="date" name="traveldate"/></td>
	</tr>
	<tr>
	    <th>Departure Airport Code: </th>
	    <td><input type="text" name="dcode"/></td>
	</tr>
	<tr>
	    <th>Arrival Airport Code: </th>
	    <td><input type="text" name="acode"/></td>
	</tr>
	<tr>
	    <th>Phone: </th>
	    <td><input type="text" name="phone"/></td>
	</tr>
	<tr>
	    <th>Num Connections: </th>
	    <td>
	    	<select name="options">
	    	<option value="0">Direct</option>
	    	<option value="1">1</option>
	    	<option value="2">2</option>
	    	<option value="3">3</option>
	    	</select>
	    </td>
	</tr>
	<tr><td><br></td></tr>
	<tr>
	<td></td>
	<td><input type="submit" name="action" value="FareInfo"/>
	<input type="submit" name="action" value="SeatsAvailable"/></td>
	<td><input type="submit" name="action" value="Passengers"/></td>
	<td><input type="submit" name="action" value="FlInstances"/></td>
	<td><input type="submit" name="action" value="Connections"/></td>
	<td><input type="submit" name="action" value="ReserveSeats"/></td>
    </tr>
   </table>
   
   
</form>


</body>
</html>