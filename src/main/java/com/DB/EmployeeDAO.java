package com.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	private final String selectQueryById = "select * from Employee where id=?";
	private final String updateQuery = "update Employee set name=?,dob=?,age=?,gender=?,designation=?,salary=?,email=? where id=? ";
	private final String deleteQuery="delete from Employee where id=?";
	private String selectQuery="select * from Employee";
	
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
		smt.setInt(1, empBO.getEmpId());
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

	public EmployeeDTO getDetailsForModifyAndDelete(int empid) throws SQLException {
		PreparedStatement smt = connection.prepareStatement(selectQueryById);
		smt.setInt(1, empid);
		ResultSet rs = smt.executeQuery();
		if (rs.next()) {
			employeeDTO.setEmpId(empid);
			employeeDTO.setEmpName(rs.getString(2));
			employeeDTO.setEmpDOB(rs.getDate(3));
			employeeDTO.setEmpGender(rs.getString(5));
			employeeDTO.setEmpDesignation(rs.getString(6));
			employeeDTO.setEmpSalary(rs.getInt(7));
			employeeDTO.setEmpEmail(rs.getString(8));
			return employeeDTO;
		} else {
			return null;
		}
	}

	public int updateEmpDetails(EmployeeBO empBO) throws SQLException {
		PreparedStatement smt = connection.prepareStatement(updateQuery);
		smt.setString(1, empBO.getEmpName());
		smt.setDate(2, empBO.getEmpDOB());
		smt.setInt(3, empBO.getEmpAge());
		smt.setString(4, empBO.getEmpGender());
		smt.setString(5, empBO.getEmpDesignation());
		smt.setInt(6, empBO.getEmpSalary());
		smt.setString(7, empBO.getEmpEmail());
		smt.setInt(8, empBO.getEmpId());
		int rs = smt.executeUpdate();
		return rs;
	}

	public int deleteEmpDetails(int empid) throws SQLException {
		PreparedStatement smt = connection.prepareStatement(deleteQuery);
		smt.setInt(1, empid);
		int rs=smt.executeUpdate();
		return rs;
	}
	
	public List<EmployeeDTO> selectEmpDetails(EmployeeBO employeeBO,String field,String order,int pageNo,int viewCount) throws SQLException{
		List<EmployeeDTO> empList=new ArrayList<EmployeeDTO>();
		int startingLimit=((pageNo-1)*viewCount);
		int endingLimit=viewCount;
		String defaultId;
		String defaultSalary=Integer.toString(employeeBO.getEmpSalary());
		if(employeeBO.getEmpId()==0) {
			 defaultId="";
		}else {
			 defaultId=Integer.toString(employeeBO.getEmpId());
		}
		
		selectQuery=selectQuery+" where id like '"+defaultId+"%' and name like '"+employeeBO.getEmpName()+
				"%' and DOB like '"+employeeBO.getEmpDOB()+"%' and gender like '"+employeeBO.getEmpGender()+
				"%' and designation like '"+employeeBO.getEmpDesignation()+"%' and salary like '"+defaultSalary+
				"%' and email like '"+employeeBO.getEmpEmail()+"%' order by "+field+" "+order+" limit "+startingLimit+","+endingLimit;
        System.out.println(selectQuery); 
        Statement smt=connection.createStatement();
        ResultSet rs=smt.executeQuery(selectQuery);
        if (rs.next()) {
			employeeDTO.setEmpId(rs.getInt(1));
			employeeDTO.setEmpName(rs.getString(2));
			employeeDTO.setEmpDOB(rs.getDate(3));
			employeeDTO.setEmpGender(rs.getString(5));
			employeeDTO.setEmpDesignation(rs.getString(6));
			employeeDTO.setEmpSalary(rs.getInt(7));
			employeeDTO.setEmpEmail(rs.getString(8));
			empList.add(employeeDTO);
		}
		return empList; 
	}
}
