package com.mongodb.crud;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.m101j.util.Helpers;

public class FindTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll= db.getCollection("findTest");
		
		coll.drop();
		
		// insert 10 docs
		for (int i = 0; i < 11; i++) {
			coll.insertOne(new Document("x",i));
		}
		
		System.out.println("Find One");
		Document first = coll.find().first();
		Helpers.printJson(first);
		
		
		System.out.println("Find all with into ");
		List<Document> all = coll.find().into(new ArrayList<Document>());
		for (Document document : all) {
			Helpers.printJson(document);
		}
		
		System.out.println("Find all with iteration ");
		MongoCursor<Document> cursor = coll.find().iterator();
		while(cursor.hasNext()){
			Helpers.printJson(cursor.next());
		}
		cursor.close();
		
		
		System.out.println("Count: ");
		long count = coll.count();
		System.out.println(count);
		
		client.close();
	}
}
