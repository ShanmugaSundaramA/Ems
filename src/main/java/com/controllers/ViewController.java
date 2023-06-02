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
		
		for(int i=1;i<=7;i++) {
		model.addAttribute("image"+i, "/EMS/resources/images/sort.png");
		}
		
		String order;
		int orderValue =resquest.getParameter("orderValue")== null || resquest.getParameter("orderValue")== "" ? 2 : Integer.parseInt(resquest.getParameter("orderValue"));
		String field = resquest.getParameter("field") == null ? "id" : resquest.getParameter("field");
		int no=resquest.getParameter("sortNo") == null ? 1 :Integer.parseInt(resquest.getParameter("sortNo"));
		int pageNo =  resquest.getParameter("pageNo") == null || resquest.getParameter("pageNo") == "" ? 1 : Integer.parseInt(resquest.getParameter("pageNo")) ;
		int viewCount = resquest.getParameter("viewCount") == null || resquest.getParameter("viewCount") == "" ? 20 : Integer.parseInt(resquest.getParameter("viewCount")) ;
		
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
		
		model.addAttribute("image"+no, orderValue == 1 ? "/EMS/resources/images/asc.png" : "/EMS/resources/images/desc.png");
		model.addAttribute("no",no);
		model.addAttribute("field",field);
		model.addAttribute("orderValue"+no,orderValue);
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
			
			 if(empList.isEmpty()) { 
				 model.addAttribute("NoData","No Data Exist");
				 }
			 int existingRecordCount=employeeDTO.getExistingRecordCount();
			 int pages=Math.round(existingRecordCount/viewCount);
			 pages=existingRecordCount%viewCount != 0 || existingRecordCount==0 ? pages+1 : pages ;
			 
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
