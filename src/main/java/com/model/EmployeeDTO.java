package com.model;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component("employeeDTO")
public class EmployeeDTO {

	private int empId;
	private String empName;
	private Date empDOB;
	private int empAge;
	private String empGender;
	private String empDesignation;
	private int empSalary;
	private String empEmail;
	private int existingRecordCount;
	private int totalRecordCount;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getEmpDOB() {
		return empDOB;
	}

	public void setEmpDOB(Date dob) {
		this.empDOB = dob;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public int getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public int getExistingRecordCount() {
		return existingRecordCount;
	}

	public void setExistingRecordCount(int existingRecordCount) {
		this.existingRecordCount = existingRecordCount;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

}
