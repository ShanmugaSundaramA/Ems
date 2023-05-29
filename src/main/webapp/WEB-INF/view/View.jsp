<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Page</title>
<style type="text/css">
a {
	float: right;
	text-decoration: none;
}
</style>
</head>
<body>
	<%@include file="../utils/Header.html"%>
	<div align="center">
		<table border="2"
			style="text-align: center; border: medium; border-color: black; border-collapse: collapse;">
			<tr>
				<td>Id <a href="">&#x25B4;</a></td>
				<td>Name <a href="">&#9650;&#9660;</a></td>
				<td>Date Of Birth <a href="">&#9650;&#9660;</a></td>
				<td>Gender <a href="">&#9650;&#9660;</a></td>
				<td>Designation <a href="">&#9650;&#9660;</a></td>
				<td>Salary <a href="">&#9650;&#9660;</a></td>
				<td>Email <a href="">&#9650;&#9660;</a></td>
			</tr>
			<tr>
				<td><input type="search" name="searchId" onchange=""></td>
				<td><input type="search" name="searchName" onchange=""></td>
				<td><input type="search" name="searchDOB" onchange=""></td>
				<td><input type="search" name="searchGender" onchange=""></td>
				<td><input type="search" name="searchDesignation" onchange=""></td>
				<td><input type="search" name="searchSalary" onchange=""></td>
				<td><input type="search" name="searchEmail" onchange=""></td>
			</tr>
		</table>
	</div>
</body>
</html>