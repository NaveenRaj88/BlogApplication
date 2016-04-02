package com.mongodb.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.m101j.util.Helpers;

public class FindWithProjectionTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("projections");

		// insert 10 docs
		for (int i = 0; i < 10; i++) {
			coll.insertOne(new Document("x", new Random().nextInt(2)).append("y", new Random().nextInt(100)).append("i", i));
		}

		Bson filter = Filters.and(Filters.eq("x", 0), Filters.gt("y", 10), Filters.lt("y", 90));
		// using document
		// Bson projection = new Document("x",0).append("_id", 0);

		// using projections class to exclude
		// Bson projection = Projections.exclude("x", "_id");

		// projections class to include
		// Bson projection = Projections.include("y","i");

		// combining include and exclude
		Bson projection = Projections.fields(Projections.include("y", "i"), Projections.excludeId());

		List<Document> all = coll.find(filter).projection(projection).into(new ArrayList<Document>());
		for (Document document : all) {
			// Helpers.printJson(document);
		}

		// Sorting
//		Bson sort = new Document("i", -1).append("y", -1);
		
		// using builders
//		Bson sort = Sorts.ascending("i");
		
		Bson sort = Sorts.ascending("i", "y");
		
		// combining sorting builders
//		Bson sort = Sorts.orderBy(Sorts.ascending("i"), Sorts.descending("y"));
		
		
		all = coll.find().projection(projection).sort(sort).skip(5).limit(2).into(new ArrayList<Document>());
		for (Document document : all) {
			 Helpers.printJson(document);
		}

		client.close();
	}
}
