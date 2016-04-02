package com.mongodb.crud;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.m101j.util.Helpers;

public class UpdateTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("test");

		coll.drop();

		for (int i = 0; i < 8; i++) {
			coll.insertOne(new Document("_id", i).append("x", i).append("y", true));
		}
		
		//replace
//		coll.replaceOne(Filters.eq("x", 5), new Document("x", 20).append("updated", true));
		
		// update operator
//		coll.updateOne(Filters.eq("x", 5), new Document("$set", new Document("x",20).append("updated", true)));
		
		//updates builder
//		coll.updateOne(Filters.eq("x", 5), Updates.set("x", 20));
		
		//updates combining
		coll.updateOne(Filters.eq("x", 5), Updates.combine(Updates.set("x", 20), Updates.set("update", true)));
		
		
		// update with upsert
		coll.updateOne(Filters.eq("x", 9), Updates.combine(Updates.set("x", 20), Updates.set("update", true)), 
				new UpdateOptions().upsert(true));
		
		for (Document document : coll.find().into(new ArrayList<Document>())) {
			Helpers.printJson(document);
		}
		
		client.close();
	}
}
