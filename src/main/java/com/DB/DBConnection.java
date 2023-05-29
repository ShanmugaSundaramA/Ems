package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/sundar";
	static final String userName = "root";
	static final String password = "Sundar0129Sowmiya";
	static Connection connection;

	public static Connection dbconnectionStart() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, userName, password);
		return connection;
	}

	public static void dbconnectionEnd() throws SQLException {
		connection.close();
	}

}
