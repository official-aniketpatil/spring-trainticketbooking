<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<!DOCTYPE html>
<html>
<head>
<style>
table {
	width:100%;
	text-align:left;
}
.button {
	margin: 30px;
	margin-left:50%;
	width: 100px;
    height: 25px;
}
</style>
</head>
<body>
<form method="post" action ="book">
<table border="1">
<tr>
 <th>Sr. No.</th>
 <th>Name</th>
 <th>Gender</th>
 <th>Mobile</th>
</tr>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="j"  begin="1" end= "${param.passengerCount}">
<tr>
<td>${j}</td>
<td><input type="text" placeholder ="Passenger Name" name="passengerName"></td>
<td>
<select name="gender">
<option>Choose Gender</option>
<option value="MALE">Male</option>
<option value="FEMALE">Female</option>
</select>
</td>
<td><input type="text" placeholder = "Mobile Number" name="mobile"></td>
</tr>
</c:forEach>
<tr><td colspan="4"><input class="button" type ="submit" value="Book"></td></tr>
</table>
<input type="hidden" name="passengerCount" value="${param.passengerCount}">
<input type="hidden" name="seatType" value="${param.seatType}">
<input type="hidden" name="trainId" value="${param.trainId}">
<input type="hidden" name ="date" value="${param.date}">
<input type="hidden" name ="source" value="${param.source}">
<input type="hidden" name ="destination" value="${param.destination}">
</form>
</body>
</html>