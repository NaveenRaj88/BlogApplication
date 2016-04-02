package com.mongodb;

import org.bson.BsonDocument;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Naveenkumar on 2/27/16.
 */
public class App {
    public static void main(String[] args) {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(10).build();
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017), options);
        MongoDatabase db = client.getDatabase("test").withReadPreference(ReadPreference.secondary());
        MongoCollection<BsonDocument> coll = db.getCollection("test", BsonDocument.class);

    }
}
