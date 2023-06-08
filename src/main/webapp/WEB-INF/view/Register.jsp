<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
</head>
<body>
	<div align="center">
		<form action="/EMS/registerDataCheck">
			<table>
				<tr>
					<td><label for="userName"> User Name </label></td>
					<td><input type="text" id="userName" name="userName"></td>
				</tr>
				<tr>
					<td><label for="password"> Password </label></td>
					<td><input type="text" id="password" name="password"></td>
				</tr>
				<tr>
					<td><label for="re-password"> Password </label></td>
					<td><input type="text" id="re-password" name="re-password"></td>
				</tr>
			</table>
			<input type="submit" value="Submit">
		</form>
	</div>
</body>
</html>