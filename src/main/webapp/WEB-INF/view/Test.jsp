<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test page</title>
<script type="text/javascript">
function Demo() {
	
	alert(document.getElementById("id").value);
	window.location.href='/EMS/add';
}
</script>
</head>
<body>
<table>
<tr>
<th onclick="Demo()"><input type="hidden" value=1 id="id" name="id">hiii</th>
</tr>
</table>
</body>
</html>