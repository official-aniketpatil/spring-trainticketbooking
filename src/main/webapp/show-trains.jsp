<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Available Trains</title>
<style>
.book-train{
margin-top:20px;
}
hr {
	margin: 50px;
}
</style>
</head>
<body>  
<jsp:include page="index.jsp"></jsp:include>

<table border = "1">
<tr>
<td>Train Id</td>
<td>Source</td>
<td>Destination </td>
<td>AC available seats</td>
<td>Sleeper available seats</td>
</tr>
<c:forEach  var="i" begin="0" end="${requestScope.trainList.size() - 1}"> 
  <tr>
    <td>${requestScope.trainList.get(i).getId()}</td>
    <td>${requestScope.trainList.get(i).getSource()}</td>
    <td>${requestScope.trainList.get(i).getDestination()}</td>
    <td>${requestScope.availabilities.get(i).getAcSeats()}</td>
    <td>${requestScope.availabilities.get(i).getSleeperSeats()} </td>
  </tr>
</c:forEach>
</table>
<hr>
<div class="book-train">
<jsp:include page="book-train.jsp"></jsp:include>
</div>
</body>
</html>