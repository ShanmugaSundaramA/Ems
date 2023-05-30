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
			employeeDTO = employeeService.getDetails(id);
			if(employeeDTO!=null) {
				model.addAttribute("responseToUser","Please Modify Your Data");
				model.addAttribute("employeeId", employeeDTO.getEmpId());
				model.addAttribute("employeeName", employeeDTO.getEmpName());
				model.addAttribute("employeeDOB", employeeDTO.getEmpDOB());
				model.addAttribute("employeeGender", employeeDTO.getEmpGender());
				model.addAttribute("employeeDesignation", employeeDTO.getEmpDesignation());
				model.addAttribute("employeeSalary", employeeDTO.getEmpSalary());
				model.addAttribute("employeeEmail", employeeDTO.getEmpEmail());	
			}else {
				model.addAttribute("responseToUser", "Data does not exist");
			}
		} catch (SQLException e) {
			System.out.println(e);
		    model.addAttribute("responseToUser","Server Connection Down.");  
		}
		return "Edit";
	}
	
	
	@RequestMapping("/editDetails")
	public String editDetails(HttpServletRequest request, Model model) {

		int id = Integer.parseInt(request.getParameter("employeeId"));
		String name = request.getParameter("employeeName");
		Date dob = Date.valueOf(request.getParameter("employeeDOB"));
		LocalDate dobLocal = LocalDate.parse(request.getParameter("employeeDOB"));
		LocalDate curDate = LocalDate.now();
		int age = Period.between(dobLocal, curDate).getYears();
		String gender = request.getParameter("employeeGender");
		String designation = request.getParameter("employeeDesignation");
		int salary = Integer.parseInt(request.getParameter("employeeSalary"));
		String email = request.getParameter("employeeEmail");

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
			int result = employeeService.modifyEmployee(employeeVo);
			if (result == 1) {
				model.addAttribute("responseToUser","Edited Successfully.");
			} else {
				model.addAttribute("responseToUser", "Database Connection Down.Try Again");
				System.out.println("failed");
			}
		} catch (SQLException e) {
			model.addAttribute("responseToUser", "Failed to Edited. Try Again.");
			System.out.println("connection failed");
		}
		return "Edit";
	}

}
