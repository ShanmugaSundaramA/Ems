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
	var pageNo=document.getElementById("pageNo").value;
	generatingUrl(orderValue,no,pageNo);
}
function search(no) {
	var orderValue=2;
	var pageNo=1;
	generatingUrl(orderValue,no,pageNo);
}
function viewCount(no){
	var orderValue=2;
	var pageNo=1;
	generatingUrl(orderValue,no,pageNo);
}
function paging(no,navigation){
	var orderValue=2;
	let pageNo=document.getElementById("pageNo").value;
    var pageEnd='${pageCount}';
	switch (navigation) {
	case 'First':
		pageNo=1;
	break;
	case 'Prev':
		pageNo = (1 != pageNo) ? pageNo-1 : 1;
	break;
	case 'Last':
		pageNo=pageEnd;
	break;
	case 'Next':
		pageNo = (pageNo != pageEnd ) ? Number(pageNo)+1  : pageEnd ;
	break;
	default:
		pageNo;
		break;
	}
	generatingUrl(orderValue,no,pageNo);	
}
function generatingUrl(orderValue,no,pageNo){
	var field=document.getElementById("field"+no).value;
	var viewCount=document.getElementById("viewCount").value;
    var url='/EMS/view?orderValue='+orderValue+'&field='+field+'&sortNo='+no+'&viewCount='+viewCount+'&pageNo='+pageNo;
    var count=1
    const parameter=["&employeeId=","&employeeName=","&employeeDOB=","&employeeGender=","&employeeDesignation=","&employeeSalary=","&employeeEmail="]
    parameter.forEach(creatingURL);
    function creatingURL(item) {
    	var itemValue=document.getElementById("search"+count).value;
		url=url+item+itemValue;
		count++;
	}
    alert(url);
    window.location.href=url;
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
					<img id="sortimage1" alt="error" src="/EMS/resources/images/asc.png" width="16px" height="16px">
					</td>
					<td onclick="sort(2)">
					<input type="hidden" value="${orderValue2 }" id="orderValue2" name="orderValue2">
					<input type="hidden" value="name" id="field2" name="field2" >Name 
					<img id="sortimage2" alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td> 
					<td onclick="sort(3)">
					<input type="hidden" value="${orderValue3 }" id="orderValue3" name="orderValue3">
					<input type="hidden" value="DOB" id="field3" name="field3" >Date Of Birth 
					<img id="sortimage3" alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
					<td onclick="sort(4)">
					<input type="hidden" value="${orderValue4 }" id="orderValue4" name="orderValue4">
					<input type="hidden" value="gender" id="field4" name="field4" >Gender
					<img id="sortimage4" alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
					<td onclick="sort(5)">
					<input type="hidden" value="${orderValue5 }" id="orderValue5" name="orderValue5">
					<input type="hidden" value="designation" id="field5" name="field5" >Designation
					<img id="sortimage5" alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
					<td onclick="sort(6)">
					<input type="hidden" value="${orderValue6 }" id="orderValue6" name="orderValue6">
					<input type="hidden" value="salary" id="field6" name="field6" >Salary
					<img id="sortimage6" alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
					<td  onclick="sort(7)">
					<input type="hidden" value="${orderValue7 }" id="orderValue7" name="orderValue7">
					<input type="hidden" value="email" id="field7" name="field7" >Email
					<img id="sortimage7" alt="error" src="/EMS/resources/images/sort.png" width="16px" height="16px">
					</td>
				</tr>
			</thead>
			<tr>
				<td><input type="text" value="${searchId }" id="search1" name="search1" onchange="search(1)"></td>
				<td><input type="text" value="${searchName }" id="search2" name="search2" onchange="search(2)"></td>
				<td><input type="text" placeholder=" YYYY-MM-DD" value="${searchDOB }" id="search3" name="search3" onchange="search(3)"></td>
				<td><input type="text" value="${searchGender }" id="search4" name="search4" onchange="search(4)"></td>
				<td><input type="text" value="${searchDesignation }" id="search5" name="search5" onchange="search(5)"></td>
				<td><input type="text" value="${searchSalary }" id="search6" name="search6" onchange="search(6)"></td>
				<td><input type="text" value="${searchEmail }" id="search7" name="search7" onchange="search(7)"></td>
			</tr>
					
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
		
		<span style="padding : 50px 0px 0px 145px ;float: left ;">No of Records : ${TotalRecords} </span>
		
		<table style="padding:50px 285px 5px 230px">		
			<tr>
			    <td onclick="paging(1,'First')">First</td>
			    <td onclick="paging(1,'Prev')">Prev</td>
		      	<td>
		      	   <select name="pageNo" id="pageNo" onchange="paging(1,'NormalOrder')">
		      	     <option>${pageNo}</option>
		      	     <c:forEach var="i" begin="1" end="${pageCount}">
		      	           <option value="${i}">${i}</option>
		      	     </c:forEach>
		      	   </select>
		      	</td>
			    <td onclick="paging(1,'Next')">Next</td>
			    <td onclick="paging(1,'Last')">Last</td>
		      </tr>
		  <span style="padding : 50px 175px 0px 0px ;float: right ;">
		  <select id="viewCount" name="viewCount" onchange="viewCount(1)">
		      <option >${viewCount}</option>
		      <option value="5">5</option>
		      <option value="10">10</option>
		      <option value="15">15</option>
		      <option value="20">20</option>
		  </select>
		  </span>   
         </table>
        
         <span style="padding:0px 0px 0px 235px"> Page </span>
         <span style="padding : 0px 160px 0px 0px ;float: right ;">View Count</span>
         
	</div>
</body>
</html>