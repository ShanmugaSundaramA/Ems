package com.controllers;

import java.sql.SQLException;
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
public class DeleteController {

	@Autowired
	@Qualifier("employeeService")
	EmployeeService employeeService;
	@Autowired
	@Qualifier("employeeVo")
	EmployeeVO employeeVo;
	@Autowired
	@Qualifier("employeeDTO")
	EmployeeDTO employeeDTO;
	
	@RequestMapping("/delete")
	public String viewDetails() {
		return "Delete";
	}
	
	@RequestMapping("/getDetailsForDelete")
	public String getDetailsForEdit(HttpServletRequest resquest, Model model) {
		int id = Integer.parseInt(resquest.getParameter("employeeId"));
		System.out.println(id);
		try {
			employeeDTO = employeeService.getDetails(id);
			if(employeeDTO!=null) {
				model.addAttribute("responseToUser","Do you want to Delete your Data");
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
		return "Delete";
	}
	
	@RequestMapping("/DeleteDetails")
	public String editDetails(HttpServletRequest resquest, Model model) {

		int id = Integer.parseInt(resquest.getParameter("employeeId"));
		
		try {
			int result = employeeService.removeEmployee(id);
			if (result == 1) {
				model.addAttribute("responseToUser","Deleted Successfully.");
			} else {
				model.addAttribute("responseToUser", "Database Connection Down.Try Again");
				System.out.println("failed");
			}
		} catch (SQLException e) {
			model.addAttribute("responseToUser", "Failed to Edited. Try Again.");
			System.out.println("connection failed");
		}
		return "Delete";
	}

	
}
