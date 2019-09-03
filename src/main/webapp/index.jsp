<html>
<head>
<title>Welcome to Train Booking App</title>
<style>
.button {
    width: 100px;
    height: 27px;
    border: none;
    background: darkblue;
    color:white;
}
h2 {
	color: darkslateblue;
}
body {
    background-color: cyan;
}
input {
	margin-bottom: 10px;
}
header {
    text-align: center;
}
table {
	border:1px solid;
	width:100%;
	text-align:left;
}
hr {
	margin:50px;
}
</style>
</head>
<body>
<header>
<h2>Train Ticket Booking</h2>
</header>
<form action="train" method="GET">
<table >
<tr>
<th>Source</th>
<th>Destination</th>
<th>Date</th>
</tr>
<tr>
<td><input type="text" placeholder="Enter Source Station" name="source"><br></td>
<td><input type="text" placeholder="Enter Destination Station" name="destination"><br></td>
<td><input type="text" placeholder="Enter Date i.e dd-MM-yyyy" name ="date"><br></td>
<td rowspan="2"><input class="button" type="submit" value="Search"></td>
</tr>
</table>
<hr>
</form>
</body>
</html>
