package com.dbds.instacart.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.dbds.instacart.beans.SelectQueryBean;
import com.dbds.instacart.constants.RedshiftConstants;

@Repository
public class RedshiftUtility {
	public SelectQueryBean executeSelectQuery(String sqlQuery) throws SQLException {
		long startTime = System.currentTimeMillis();
		connect();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sqlQuery);
		
		SelectQueryBean selectQueryBean = new SelectQueryBean();
		List<String> columnNames = new ArrayList<>();
		
		List<Object> lr = new ArrayList<>();
		ResultSetMetaData meta = rs.getMetaData();
		int cols = meta.getColumnCount();
		for (int i = 0; i < cols; i++)
			columnNames.add(meta.getColumnName(i+1));
		while(rs.next()) {

	        Object[] result = new Object[cols];

	        for (int i = 0; i < cols; i++) {
	            result[i] = rs.getObject(i + 1);
	        }
	        lr.add(result);
		}
		connection.close();
		selectQueryBean.setRsObjList(lr);
		selectQueryBean.setColumnNames(columnNames);
		long endTime = System.currentTimeMillis();
		selectQueryBean.setTimeTaken(endTime-startTime);
        return selectQueryBean;
	}
	
	public int executeUpdateQuery(String sqlQuery) throws SQLException {
		connect();
		PreparedStatement ps = connection.prepareStatement(sqlQuery);
		int rowsAffected = ps.executeUpdate();
		connection.close();
		return rowsAffected;
	}
	
	private Connection connection = null;
	 
    public void connect() {
        // Dynamically load driver at runtime.
        try {
            Class.forName(RedshiftConstants.CLASS_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver class could not loaded");
            System.out.println(e.getMessage());
        }
        Properties properties = getPropertiesForDriverManager();
        try {
            System.out.println("Connecting to the database...");
            this.connection = DriverManager.getConnection(RedshiftConstants.JDBC_URL, properties);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	private Properties getPropertiesForDriverManager() {
        Properties props = new Properties();
        props.setProperty("user", RedshiftConstants.USERNAME);
        props.setProperty("password", RedshiftConstants.PASSWORD);
        return props;
    }
}
