package com.vrs.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbUtil {


	private static Connection connection = null;

	public static Connection getConnection() throws ClassNotFoundException,
			IOException, SQLException {

		if(connection==null || connection.isClosed())
		{
			ResourceBundle prop=ResourceBundle.getBundle("dataBase");
			String driver = prop.getString("driver");
			String url = prop.getString("url");
			String user = prop.getString("username");
			String password = prop.getString("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		}
		return connection;
	}
}
