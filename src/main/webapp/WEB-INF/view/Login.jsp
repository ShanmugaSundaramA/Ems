<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<div align="center">
		<form action="/EMS/loginDataCheck">
			<table>
				<tr>
					<td><label for="userName">User Name </label></td>
					<td><input type="text" id="userName" name="userName"></td>
				</tr>
				<tr>
					<td><label for="password">Password </label></td>
					<td><input type="text" id="password" name="password"></td>
				</tr>
			</table>
			<input type="submit" value="Login">
		</form>
	</div>
</body>
</html>