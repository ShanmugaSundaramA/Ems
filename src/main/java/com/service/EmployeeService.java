package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.DB.EmployeeDAO;
import com.model.EmployeeBO;
import com.model.EmployeeDTO;
import com.model.EmployeeVO;

@Component("employeeService")
public class EmployeeService {

	@Autowired
	@Qualifier("employeeBO")
	EmployeeBO employeeBO;
	@Autowired
	@Qualifier("employeeDAO")
	EmployeeDAO employeeDAO;
	
	public int saveEmployee(EmployeeVO employeeVo) throws SQLException {
		
		employeeBO.setEmpId(employeeVo.getEmpId());
		employeeBO.setEmpName(employeeVo.getEmpName());
		employeeBO.setEmpDOB(employeeVo.getEmpDOB());
		employeeBO.setEmpAge(employeeVo.getEmpAge());
		employeeBO.setEmpGender(employeeVo.getEmpGender());
		employeeBO.setEmpDesignation(employeeVo.getEmpDesignation());
		employeeBO.setEmpSalary(employeeVo.getEmpSalary());
		employeeBO.setEmpEmail(employeeVo.getEmpEmail());

		return employeeDAO.insertEmpDetails(employeeBO);
		
	}

	public EmployeeDTO getDetails(int empid) throws SQLException {
		return employeeDAO.getDetailsForModifyAndDelete(empid);
	}

	public int modifyEmployee(EmployeeVO employeeVo) throws SQLException {
		employeeBO.setEmpId(employeeVo.getEmpId());
		employeeBO.setEmpName(employeeVo.getEmpName());
		employeeBO.setEmpDOB(employeeVo.getEmpDOB());
		employeeBO.setEmpAge(employeeVo.getEmpAge());
		employeeBO.setEmpGender(employeeVo.getEmpGender());
		employeeBO.setEmpDesignation(employeeVo.getEmpDesignation());
		employeeBO.setEmpSalary(employeeVo.getEmpSalary());
		employeeBO.setEmpEmail(employeeVo.getEmpEmail());
		return employeeDAO.updateEmpDetails(employeeBO);
	}

	public int removeEmployee(int empid) throws SQLException {
		return employeeDAO.deleteEmpDetails(empid);
	}
	
	public List<EmployeeDTO> showDetails(EmployeeVO employeeVo,String field,int orderValue,int pageNo,int viewCount) throws SQLException {
		String order;
		switch (orderValue) {
		case 1:
			order="desc";
			orderValue=orderValue+1;
			break;
		case 2:
			order="asc";
			orderValue=orderValue-1;
			break;	
		default:
			order="asc";
			orderValue=orderValue-2;
			break;
		}
		
		employeeBO.setEmpId(employeeVo.getEmpId());
		employeeBO.setEmpName(employeeVo.getEmpName());
		employeeBO.setEmpDOB(employeeVo.getEmpDOB());
		employeeBO.setEmpAge(employeeVo.getEmpAge());
		employeeBO.setEmpGender(employeeVo.getEmpGender());
		employeeBO.setEmpDesignation(employeeVo.getEmpDesignation());
		employeeBO.setEmpSalary(employeeVo.getEmpSalary());
		employeeBO.setEmpEmail(employeeVo.getEmpEmail());
		
		return employeeDAO.selectEmpDetails(employeeBO, field, order, pageNo, viewCount);
		
	}

}
