package com.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.EmployeeDTO;
import com.model.EmployeeVO;
import com.service.EmployeeService;

@Controller
public class ViewController {

	@Autowired
	@Qualifier("employeeService")
	EmployeeService employeeService;
	@Autowired
	@Qualifier("employeeVo")
	EmployeeVO employeeVo;
	@Autowired
	@Qualifier("employeeDTO")
	EmployeeDTO employeeDTO;

	@RequestMapping("/view")
	public String viewDetails(HttpServletRequest resquest, Model model) {
		
		String order;
		//orderValue for ascending and descending.
		int orderValue =resquest.getParameter("orderValue")== null || resquest.getParameter("orderValue")== "" ? 2 : Integer.parseInt(resquest.getParameter("orderValue"));
		//field for which to sort.
		String field = resquest.getParameter("field") == null ? "id" : resquest.getParameter("field");
		//no append in orderValue for value in server page.
		int no=resquest.getParameter("sortNo") == null ? 1 :Integer.parseInt(resquest.getParameter("sortNo"));
		switch (orderValue) {
		case 1:
			order="desc";
			orderValue=orderValue+1;
			break;
		default :
			order="asc";
			orderValue=orderValue-1;
			break;
		}
		model.addAttribute("orderValue"+no,orderValue);
		
		int pageNo = 1;
		int viewCount = 15;
		
		int id = resquest.getParameter("employeeid") == null ||  resquest.getParameter("employeeid") ==  "" ? 0 : Integer.parseInt(resquest.getParameter("employeeid")) ;
		String name = resquest.getParameter("employeename");
		Date dob = resquest.getParameter("employeeDOB") == null || resquest.getParameter("employeeDOB") == ""? null : Date.valueOf(resquest.getParameter("employeeDOB"));
		String gender = resquest.getParameter("employeegender");
		String designation = resquest.getParameter("employeedesignation");
		int salary =resquest.getParameter("employeesalary") == null || resquest.getParameter("employeesalary") == "" ? 0 : Integer.parseInt(resquest.getParameter("employeesalary"));
		String email = resquest.getParameter("employeeemail");
		
		model.addAttribute("searchFieldValue"+no,id);
		model.addAttribute("searchFieldValue"+no,name);
		model.addAttribute("searchFieldValue"+no,dob);
		model.addAttribute("searchFieldValue"+no,gender);
		model.addAttribute("searchFieldValue"+no,designation);
		model.addAttribute("searchFieldValue"+no,salary);
		model.addAttribute("searchFieldValue"+no,email);
		
		employeeVo.setEmpId(id);
		employeeVo.setEmpName(name);
		employeeVo.setEmpDOB(dob);
		employeeVo.setEmpGender(gender);
		employeeVo.setEmpDesignation(designation);
		employeeVo.setEmpSalary(salary);
		employeeVo.setEmpEmail(email);

		try {
			List<EmployeeDTO> empList = employeeService.showDetails(employeeVo, field, order, pageNo, viewCount);
			/*
			 * if(empList.isEmpty()) { System.out.println("NoData"); }else { for
			 * (EmployeeDTO employeeDTO : empList) {
			 * 
			 * System.out.println(employeeDTO.getEmpId());
			 * System.out.println(employeeDTO.getEmpName());
			 * System.out.println(employeeDTO.getEmpDOB());
			 * System.out.println(employeeDTO.getEmpGender());
			 * System.out.println(employeeDTO.getEmpDesignation());
			 * System.out.println(employeeDTO.getEmpSalary());
			 * System.out.println(employeeDTO.getEmpEmail());
			 * 
			 * } }
			 */
			model.addAttribute("empList", empList);
		} catch (SQLException e) {

		}

		return "View";
	}
}
