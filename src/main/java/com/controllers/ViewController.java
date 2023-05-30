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
	public String viewDetails(HttpServletRequest resquest,Model model) {
		String field="id";
		int orderValue=2;
		int pageNo=1;
		int viewCount=15;
		int id=0;
		Date dob = null;
		int salary=0;
		
		if (resquest.getParameter("employeeId")!=null) {
		   id =Integer.parseInt(resquest.getParameter("employeeId"));	
		}
		String name = resquest.getParameter("employeeName");
		if(resquest.getParameter("employeeDOB")!=null) {
		   dob = Date.valueOf(resquest.getParameter("employeeDOB"));			
		}
		String gender = resquest.getParameter("employeeGender");
		String designation = resquest.getParameter("employeeDesignation");
		if (resquest.getParameter("employeeSalary")!=null) {
		   salary = Integer.parseInt(resquest.getParameter("employeeSalary"));
		}
		String email = resquest.getParameter("employeeEmail");

		employeeVo.setEmpId(id);
		employeeVo.setEmpName(name);
		employeeVo.setEmpDOB(dob);
		employeeVo.setEmpGender(gender);
		employeeVo.setEmpDesignation(designation);
		employeeVo.setEmpSalary(salary);
		employeeVo.setEmpEmail(email);
		
		try {
		List<EmployeeDTO> empList=employeeService.showDetails(employeeVo, field, orderValue, pageNo, viewCount);
		
		if(empList.isEmpty()) {
			System.out.println("NoData");
		}else {
			for (EmployeeDTO employeeDTO : empList) {
				System.out.println(employeeDTO.getEmpId());
				System.out.println(employeeDTO.getEmpName());
				System.out.println(employeeDTO.getEmpDOB());
				System.out.println(employeeDTO.getEmpGender());
				System.out.println(employeeDTO.getEmpDesignation());
				System.out.println(employeeDTO.getEmpSalary());
				System.out.println(employeeDTO.getEmpEmail());
			}
		}
		model.addAttribute("empList",empList);
		} catch (SQLException e) {
			
		}
		
		return "View";
	}
}
