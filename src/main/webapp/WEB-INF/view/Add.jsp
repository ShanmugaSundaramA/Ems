<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Page</title>
</head>
<body>
	<%@include file="../utils/Header.html"%>
	<div class="responseTxtTag" align="center">
		<span>${responseToUser}</span>
	</div>
	<div class="mainFormTag" align="center">
		<form action="/EMS/addDetails">
			<table style="">
				<tr>
					<td><label for="employeeId">Employee Id</label></td>
					<td><input type="text" id="employeeId" name="employeeId"></td>
				</tr>
				<tr>
					<td><label for="employeeName">Employee Name</label></td>
					<td><input type="text" id="employeeName" name="employeeName" ></td>
				</tr>
				<tr>
					<td><label for="employeeDOB">Employee DateOfBirth</label></td>
					<td><input type="date" id="employeeDOB" name="employeeDOB"></td>
				</tr>
				<tr>
					<td><span>Employee Gender</span></td>
					<td>
					<label for="male">Male</label>
					<input type="radio"	id="male" name="employeeGender" value="male">
					<label for="female">Female</label>
					<input type="radio" id="female"	name="employeeGender" value="female">
					</td>
				</tr>
				<tr>
					<td><label for="employeeDesignation">EmployeeDesignation</label></td>
					<td><input type="text" id="employeeDesignation" name="employeeDesignation" ></td>
				</tr>
				<tr>
					<td><label for="employeeSalary">Employee Salary</label></td>
					<td><input type="text" id="employeeSalary"name="employeeSalary"></td>
				</tr>
				<tr>
					<td><label for="employeeEmail">Employee Email</label></td>
					<td><input type="email" id="employeeEmail" name="employeeEmail" ></td>
				</tr>
			</table>
			<input type="submit" value="Submit">
		</form>
	</div>
</body>
</html>