<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Page</title>
<style type="text/css">
img {
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
			<thead>
			<tr>
				<td>Id <a href=""><img alt="error" src="/EMS/resources/images/asc.png" width="16px" height="16px"></a></td>
				<td>Name <a href=""><img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px"></a></td>
				<td>Date Of Birth <a href=""><img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px"></a></td>
				<td>Gender <a href=""><img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px"></a></td>
				<td>Designation <a href=""><img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px"></a></td>
				<td>Salary <a href=""><img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px"></a></td>
				<td>Email <a href=""><img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px"></a></td>
			</tr>
			</thead>
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