package com.dbds.instacart.beans;

import java.util.List;

public class SelectQueryBean {
	private List<Object> rsObjList;
	
	private List<String> columnNames;
	
	private long timeTaken;

	public List<Object> getRsObjList() {
		return rsObjList;
	}

	public void setRsObjList(List<Object> rsObjList) {
		this.rsObjList = rsObjList;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
	
	public long getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}
	
	public SelectQueryBean() {
	}
}
