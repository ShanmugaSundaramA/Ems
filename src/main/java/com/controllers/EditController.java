package com.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

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
public class EditController {

	@Autowired
	@Qualifier("employeeService")
	EmployeeService employeeService;
	@Autowired
	@Qualifier("employeeVo")
	EmployeeVO employeeVo;
	@Autowired
	@Qualifier("employeeDTO")
	EmployeeDTO employeeDTO;
	
	@RequestMapping("/edit")
	public String edit() {
		return "Edit";
	}
	
	@RequestMapping("/getDetailsForEdit")
	public String getDetailsForEdit(HttpServletRequest resquest, Model model) {
		int id = Integer.parseInt(resquest.getParameter("employeeId"));
		System.out.println(id);
		try {
			employeeDTO = employeeService.getDetailforEdit(id);
			if(employeeDTO!=null) {
				model.addAttribute("responseToUser","Please Modify Your Data");
				model.addAttribute("employeeId", employeeDTO.getEmpId());
				model.addAttribute("employeeName", employeeDTO.getEmpName());
				model.addAttribute("employeeDOB", employeeDTO.getEmpDOB());
				model.addAttribute("employeeGender", employeeDTO.getEmpGender());
				model.addAttribute("employeeDesignation", employeeDTO.getEmpDesignation());
				model.addAttribute("employeeSalary", employeeDTO.getEmpSalary());
				model.addAttribute("employeeEmail", employeeDTO.getEmpEmail());
				return "Edit";		
			}else {
				model.addAttribute("responseToUser", "Data does not exist");
				return "Edit";
			}
		} catch (SQLException e) {
			System.out.println(e);
		   model.addAttribute("responseToUser","Server Connection Down.");
		   return "Edit";  
		}
	}
	
	
	@RequestMapping("/editDetails")
	public String editDetails(HttpServletRequest resquest, Model model) {

		int id = Integer.parseInt(resquest.getParameter("employeeId"));
		String name = resquest.getParameter("employeeName");
		Date dob = Date.valueOf(resquest.getParameter("employeeDOB"));
		LocalDate dobLocal = LocalDate.parse(resquest.getParameter("employeeDOB"));
		LocalDate curDate = LocalDate.now();
		int age = Period.between(dobLocal, curDate).getYears();
		String gender = resquest.getParameter("employeeGender");
		String designation = resquest.getParameter("employeeDesignation");
		int salary = Integer.parseInt(resquest.getParameter("employeeSalary"));
		String email = resquest.getParameter("employeeEmail");

		/*
		 * System.out.println(id + " " + name + " " + dob + " "+age+" "+ gender + " " +
		 * designation + " " + salary + " " + email);
		 */

		employeeVo.setEmpId(id);
		employeeVo.setEmpName(name);
		employeeVo.setEmpDOB(dob);
		employeeVo.setEmpAge(age);
		employeeVo.setEmpGender(gender);
		employeeVo.setEmpDesignation(designation);
		employeeVo.setEmpSalary(salary);
		employeeVo.setEmpEmail(email);

		try {
			int result = employeeService.saveEmployee(employeeVo);
			if (result == 1) {
				model.addAttribute("responseToUser","");
				return "";
			} else {
				model.addAttribute("responseToUser", "Database Connection Down.Try Again");
				System.out.println("failed");
				return "";
			}
		} catch (SQLException e) {
			model.addAttribute("responseToUser", "Failed to Edited. Try Again.");
			System.out.println("connection failed");
			// System.out.println(e);
			return "";
		}
	}

}
