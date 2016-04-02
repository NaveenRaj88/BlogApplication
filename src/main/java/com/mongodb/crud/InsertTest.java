package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.m101j.util.Helpers;
import org.bson.Document;

import java.util.Arrays;

/**
 * Created by Naveenkumar on 2/28/16.
 */
public class InsertTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTest");

        coll.drop();

        Document doc = new Document("name", "smith").append("age",30).append("profession","programmer");

        Helpers.printJson(doc);
        coll.insertOne(doc);
        Helpers.printJson(doc);

        Document doc1 = new Document("name", "jones").append("age",28).append("profession","dancer");

        Document doc2 = new Document("name", "alice").append("age",25).append("profession","singer");

        coll.insertMany(Arrays.asList(doc1,doc2));

        Helpers.printJson(doc1);

        Helpers.printJson(doc2);

        System.out.println(coll.count());
    }
}
