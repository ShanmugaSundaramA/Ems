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
		
		int pageNo =  resquest.getParameter("pageNo") == null || resquest.getParameter("pageNo") == "" ? 1 : Integer.parseInt(resquest.getParameter("pageNo")) ;
		int viewCount = resquest.getParameter("viewCount") == null || resquest.getParameter("viewCount") == "" ? 15 : Integer.parseInt(resquest.getParameter("viewCount")) ;
		
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("viewCount",viewCount);
		
		int id = resquest.getParameter("employeeId") == null ||  resquest.getParameter("employeeId") ==  "" ? 0 : Integer.parseInt(resquest.getParameter("employeeId")) ;
		String name = resquest.getParameter("employeeName");
		Date dob = resquest.getParameter("employeeDOB") == null || resquest.getParameter("employeeDOB") == ""? null : Date.valueOf(resquest.getParameter("employeeDOB"));
		String gender = resquest.getParameter("employeeGender");
		String designation = resquest.getParameter("employeeDesignation");
		int salary =resquest.getParameter("employeeSalary") == null || resquest.getParameter("employeeSalary") == "" ? 0 : Integer.parseInt(resquest.getParameter("employeeSalary"));
		String email = resquest.getParameter("employeeEmail");
	
		model.addAttribute("searchId",resquest.getParameter("employeeId"));
		model.addAttribute("searchName",name);
		model.addAttribute("searchDOB",dob);
		model.addAttribute("searchGender",gender);
		model.addAttribute("searchDesignation",designation);
		model.addAttribute("searchSalary",resquest.getParameter("employeeSalary"));
		model.addAttribute("searchEmail",email);
		
		employeeVo.setEmpId(id);
		employeeVo.setEmpName(name);
		employeeVo.setEmpDOB(dob);
		employeeVo.setEmpGender(gender);
		employeeVo.setEmpDesignation(designation);
		employeeVo.setEmpSalary(salary);
		employeeVo.setEmpEmail(email);

		try {
			List<EmployeeDTO> empList = employeeService.showDetails(employeeVo, field, order, pageNo, viewCount);
			employeeDTO= employeeService.getCounts(employeeVo);
			/*
			 * if(empList.isEmpty()) { System.out.println("NoData"); }else { 
			 * }
			 */
			 int existingRecordCount=employeeDTO.getExistingRecordCount();
			 //System.out.println(existingRecordCount);
			 int pages=Math.round(existingRecordCount/viewCount);
			 //System.out.println(pages);
			 pages=existingRecordCount%viewCount != 0 ? pages+1 : pages ;
			 //System.out.println(pages);
			 int totalRecord=employeeDTO.getTotalRecordCount();
			 
			model.addAttribute("pageCount",pages);
			model.addAttribute("TotalRecords",totalRecord);
			model.addAttribute("empList", empList);
			
		} catch (SQLException e) {
             System.out.println(e);
		}

		return "View";
	}
}
