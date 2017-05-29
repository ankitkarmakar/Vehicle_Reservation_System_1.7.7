package com.vrs.constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ResourceClosing {
	public static final Logger LOG = Logger.getLogger("ResourceClosing");
	
	public static void closeResources(ResultSet result,Connection connection,PreparedStatement statement){
		if(result!=null){
			try {
				result.close();
				LOG.info("resultset closed");
			} catch (SQLException e) {
				
			}
		}
		
		if(statement!=null){
			try {
				statement.close();
				LOG.info("statement closed");
			} catch (SQLException e) {
				
			}
		}
		
		if(connection!=null){
			try {
				connection.close();
				LOG.info("Database connection closed.");
			} catch (SQLException e) {
				
			}
		}
				
		
	}

}
