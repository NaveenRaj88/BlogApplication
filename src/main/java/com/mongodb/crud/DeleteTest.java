package com.mongodb.crud;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.m101j.util.Helpers;

public class DeleteTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("test");

		coll.drop();

		for (int i = 0; i < 8; i++) {
			coll.insertOne(new Document("_id", i).append("x", i).append("y", true));
		}
		
		
		coll.deleteOne(Filters.gt("_id", 4));
		
		
		for (Document document : coll.find().into(new ArrayList<Document>())) {
			Helpers.printJson(document);
		}
	}
}
