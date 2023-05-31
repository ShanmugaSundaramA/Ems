<%@page import="com.model.EmployeeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript">
function sort(no) {
	var orderValue=document.getElementById("orderValue"+no).value;
	var field=document.getElementById("field"+no).value;
	var searchFieldValue =document.getElementById("search"+no).value;
	var searchingField="employee"+field;
	window.location.href='/EMS/view?orderValue='+orderValue+'&field='+field+'&sortNo='+no+'&'+searchingField+'='+searchFieldValue;
}
</script>
</head>
<body>
	<%@include file="../utils/Header.html"%>

	<div align="center">
		<table border="2"
			style="text-align: center; border: medium; border-color: black; border-collapse: collapse;">
			<thead>
				<tr>
					<td onclick="sort(1)">
					<input type="hidden" value="${orderValue1 }" id="orderValue1" name="orderValue1">
					<input type="hidden" value="id" id="field1" >Id
					<img alt="error" src="/EMS/resources/images/asc.png" width="16px" height="16px">
					</td>
					<td onclick="sort(2)">
					<input type="hidden" value="${orderValue2 }" id="orderValue2" name="orderValue2">
					<input type="hidden" value="name" id="field2" name="field2" >Name 
					<img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td> 
					<td onclick="sort(3)">
					<input type="hidden" value="${orderValue3 }" id="orderValue3" name="orderValue3">
					<input type="hidden" value="DOB" id="field3" name="field3" >Date Of Birth 
					<img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
					<td onclick="sort(4)">
					<input type="hidden" value="${orderValue4 }" id="orderValue4" name="orderValue4">
					<input type="hidden" value="gender" id="field4" name="field4" >Gender
					<img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
					<td onclick="sort(5)">
					<input type="hidden" value="${orderValue5 }" id="orderValue5" name="orderValue5">
					<input type="hidden" value="designation" id="field5" name="field5" >Designation
					<img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
					<td onclick="sort(6)">
					<input type="hidden" value="${orderValue6 }" id="orderValue6" name="orderValue6">
					<input type="hidden" value="salary" id="field6" name="field6" >Salary
					<img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
					<td  onclick="sort(7)">
					<input type="hidden" value="${orderValue7 }" id="orderValue7" name="orderValue7">
					<input type="hidden" value="email" id="field7" name="field7" >Email
					<img alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
				</tr>
			</thead>
			<tr>
				<td><input type="text" value="${searchFieldValue1 }" id="search1" name="search1" onchange="sort(1)"></td>
				<td><input type="text" value="${searchFieldValue2 }" id="search2" name="search2" onchange="sort(2)"></td>
				<td><input type="text" value="${searchFieldValue3 }" id="search3" name="search3" onchange="sort(3)"></td>
				<td><input type="text" value="${searchFieldValue4 }" id="search4" name="search4" onchange="sort(4)"></td>
				<td><input type="text" value="${searchFieldValue5 }" id="search5" name="search5" onchange="sort(5)"></td>
				<td><input type="text" value="${searchFieldValue6 }" id="search6" name="search6" onchange="sort(6)"></td>
				<td><input type="text" value="${searchFieldValue7 }" id="search7" name="search7" onchange="sort(7)"></td>
			</tr>
			<%-- 			
			<tr>
				<td><input type="text" value="${searchid }" id="searchid" name="searchid" onchange="search(1)"></td>
				<td><input type="text" value="${searchName }" id="searchName" name="searchName" onchange="search(2)"></td>
				<td><input type="text" value="${searchDOB }" id="searchDOB" name="searchDOB" onchange="search(3)"></td>
				<td><input type="text" value="${searchGender }" id="searchGender" name="searchGender" onchange="search(4)"></td>
				<td><input type="text" value="${searchDesignation }" id="searchDesignation" name="searchDesignation" onchange="search(5)"></td>
				<td><input type="text" value="${searchSalary }" id="searchSalary" name="searchSalary" onchange="search(6)"></td>
				<td><input type="text" value="${searchEmail }" id="searchEmail" name="searchEmail" onchange="search(7)"></td>
			</tr>
			 --%>
	  
      <c:forEach items="${empList}" var="emp">
          <tr>
          <td>${emp.getEmpId()}</td>
          <td>${emp.getEmpName()}</td>
          <td>${emp.getEmpDOB()}</td>
          <td>${emp.getEmpGender()}</td>
          <td>${emp.getEmpDesignation()}</td>
          <td>${emp.getEmpSalary()}</td>
          <td>${emp.getEmpEmail()}</td>
          </tr>
      </c:forEach>
		</table>
	</div>
</body>
</html>