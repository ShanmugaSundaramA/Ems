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
import com.model.EmployeeVO;
import com.service.EmployeeService;

@Controller
public class AddDetailController {

	@Autowired
	@Qualifier("employeeService")
	EmployeeService employeeService;
	@Autowired
	@Qualifier("employeeVo")
	EmployeeVO employeeVo;

	@RequestMapping("/add")
	public String add() {
		return "Add";
	}

	@RequestMapping("/addDetails")
	public String addDetails(HttpServletRequest resquest, Model model) {

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
				model.addAttribute("responseToUser", "Details Added Successfully.");
				System.out.println("successfully");
				return "Add";
			} else {
				model.addAttribute("responseToUser", "Database Connection Down.Try Again");
				System.out.println("failed");
				return "Add";
			}
		} catch (SQLException e) {
			model.addAttribute("responseToUser", "Failed to Added. Try Again.");
			System.out.println("connection failed");
			// System.out.println(e);
			return "Add";
		}
	}

}
