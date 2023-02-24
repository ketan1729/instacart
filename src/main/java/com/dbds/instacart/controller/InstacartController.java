package com.dbds.instacart.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbds.instacart.beans.SelectQueryBean;
import com.dbds.instacart.utility.MongoUtility;
import com.dbds.instacart.utility.MySQLUtility;
import com.dbds.instacart.utility.RedshiftUtility;

@RestController
@CrossOrigin
@RequestMapping("/instacart")
public class InstacartController {
	@Autowired
	private MySQLUtility mySQLUtility;
	
	@Autowired
	private RedshiftUtility redshiftUtility;
	
	@Autowired
	private MongoUtility mongoUtility;
	
	@RequestMapping(value = "/execSelectMySqlQuery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SelectQueryBean execSelectMySqlQuery(@RequestParam String strSql) throws Exception {
		return mySQLUtility.executeSelectQuery(strSql);
	}
	
	@RequestMapping(value = "/execUpdateMySqlQuery", method = RequestMethod.POST)
	public int execUpdateMySqlQuery(@RequestParam String strSql) throws Exception {
		return mySQLUtility.executeUpdateQuery(strSql);
	}
	
	@RequestMapping(value = "/execSelectRedshiftQuery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SelectQueryBean execSelectRedshiftQuery(@RequestParam String strSql) throws Exception {
		return redshiftUtility.executeSelectQuery(strSql);
	}
	
	@RequestMapping(value = "/execUpdateRedshiftQuery", method = RequestMethod.POST)
	public int execUpdateRedshiftQuery(@RequestParam String strSql) throws Exception {
		return redshiftUtility.executeUpdateQuery(strSql);
	}
	
	@RequestMapping(value = "/execSelectMongoQuery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject execSelectMongoQuery(@RequestParam String strSql) throws Exception {
		return mongoUtility.executeSelectQuery(strSql);
	}
	
	@RequestMapping(value = "/execInsertMongoQuery", method = RequestMethod.POST)
	public void execInsertMongoQuery(@RequestParam String strSql) throws Exception {
		mongoUtility.execInsertMongoQuery(strSql);
	}
}