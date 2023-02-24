package com.dbds.instacart.utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.dbds.instacart.beans.SelectQueryBean;

@Repository
public class MySQLUtility {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public SelectQueryBean executeSelectQuery(String sqlQuery) {
		long startTime = System.currentTimeMillis();
		SelectQueryBean selectQueryBean = new SelectQueryBean();
		List<String> columnNames = new ArrayList<>();
		List<Object> res = jdbcTemplate.query(sqlQuery, new ResultSetExtractor<List<Object>>() {
			public List<Object> extractData(ResultSet rs) throws SQLException {
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
		        return lr;
			}
		});
		selectQueryBean.setRsObjList(res);
		selectQueryBean.setColumnNames(columnNames);
		long endTime = System.currentTimeMillis();
		selectQueryBean.setTimeTaken(endTime-startTime);
		return selectQueryBean;
	}
	
	public int executeUpdateQuery(String sqlQuery) {
		int rowsAffected = jdbcTemplate.update(sqlQuery);
		return rowsAffected;
	}
}
