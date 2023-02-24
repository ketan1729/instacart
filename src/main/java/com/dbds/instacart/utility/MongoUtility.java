package com.dbds.instacart.utility;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.github.vincentrussell.query.mongodb.sql.converter.MongoDBQueryHolder;
import com.github.vincentrussell.query.mongodb.sql.converter.ParseException;
import com.github.vincentrussell.query.mongodb.sql.converter.QueryConverter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class MongoUtility {
	
	private final String mongoDbName = "adni";

	public JSONObject executeSelectQuery(String strSql) throws ParseException {
		JSONObject jsonObject = new JSONObject();
		List<Object> res = new ArrayList<>();
		
		QueryConverter queryConverter = new QueryConverter.Builder().sqlString(strSql).build();
		MongoDBQueryHolder mongoDBQueryHolder = queryConverter.getMongoQuery();
		String collName = mongoDBQueryHolder.getCollection();
		Document query = mongoDBQueryHolder.getQuery();
		//Document projection = mongoDBQueryHolder.getProjection();
		//Document sort = mongoDBQueryHolder.getSort();
		
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase(mongoDbName);
		MongoCollection<Document> docColl = database.getCollection(collName);
		
		docColl.find(query).into(res);
		jsonObject.put("Output", res);
		return jsonObject;
	}

	public void execInsertMongoQuery(String strSql) throws ParseException {
		QueryConverter queryConverter = new QueryConverter.Builder().sqlString(strSql).build();
		MongoDBQueryHolder mongoDBQueryHolder = queryConverter.getMongoQuery();
		String collName = mongoDBQueryHolder.getCollection();
		Document query = mongoDBQueryHolder.getQuery();
		
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase(mongoDbName);
		MongoCollection<Document> docColl = database.getCollection(collName);
		
		docColl.insertOne(query);
	}
 }
