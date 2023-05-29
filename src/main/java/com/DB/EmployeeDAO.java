package com.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.model.EmployeeBO;
import com.model.EmployeeDTO;

@Component("employeeDAO")
public class EmployeeDAO {

	private final String insertQuery = "insert into Employee values(?,?,?,?,?,?,?,?)";
	private final String selectQueryforEdit="select * from Employee where id=?";
	static Connection connection = null;
	
	@Autowired
	@Qualifier("employeeDTO")
	EmployeeDTO employeeDTO;

	@PostConstruct
	public static void getConnection() throws ClassNotFoundException, SQLException {
		connection = DBConnection.dbconnectionStart();
	}

	public int insertEmpDetails(EmployeeBO empBO) throws SQLException {
		PreparedStatement smt = connection.prepareStatement(insertQuery);
		smt.setInt(1, empBO.getEmpId() );
		smt.setString(2, empBO.getEmpName());
		smt.setDate(3, empBO.getEmpDOB());
		smt.setInt(4, empBO.getEmpAge());
		smt.setString(5, empBO.getEmpGender());
		smt.setString(6, empBO.getEmpDesignation());
		smt.setInt(7, empBO.getEmpSalary());
		smt.setString(8, empBO.getEmpEmail());
		int rs = smt.executeUpdate();
		return rs;
	}

	@PreDestroy
	public static void closeConnection() throws SQLException {
		DBConnection.dbconnectionEnd();
	}

	public EmployeeDTO getDetailsForModify(int empid) throws SQLException {
		PreparedStatement smt = connection.prepareStatement(selectQueryforEdit);
		smt.setInt(1, empid);
		ResultSet rs=smt.executeQuery();
	    if(rs.next()) {
		employeeDTO.setEmpId(empid);
		employeeDTO.setEmpName(rs.getString(2));
		employeeDTO.setEmpDOB(rs.getDate(3));
		employeeDTO.setEmpGender(rs.getString(5));
		employeeDTO.setEmpDesignation(rs.getString(6));
		employeeDTO.setEmpSalary(rs.getInt(7));
		employeeDTO.setEmpEmail(rs.getString(8));
		return employeeDTO;
	    }else {
	    	return null;
	    }
	    
		
	}

}
