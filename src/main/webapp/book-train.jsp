<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<form action="add-passengers.jsp" method="post">
<table>
<tr>
<th>Train Id</th>
<th>Number of Passengers</th>
<th>Seat Type</th>
</tr>
<tr>
<td><input type="text" placeholder = "Enter Train Id" name="trainId"></td>
<td><input type="number" placeholder ="Enter Number of Passengers" name="passengerCount"></td>
<td><select name="seatType">
<option>Choose type of seat</option>
<option value="AC">AC</option>
<option value="SLEEPER">SLEEPER</option>
</select>
</td>
<td><input class="button" type="submit" value="Book"></td>
</tr>
</table>
<input type="hidden" name ="date" value="${param.date}">
<input type="hidden" name ="source" value="${param.source}">
<input type="hidden" name ="destination" value="${param.destination}">
</form>
</body>
</html>