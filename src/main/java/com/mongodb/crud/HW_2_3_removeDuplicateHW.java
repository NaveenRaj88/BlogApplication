package com.mongodb.crud;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.m101j.util.Helpers;

public class HW_2_3_removeDuplicateHW {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("students");
		MongoCollection<Document> coll = db.getCollection("grades");
		
		Integer highestStudentId = coll.find().sort(Sorts.descending("student_id")).first().getInteger("student_id");
		
		Integer lowestStudentId = coll.find().sort(Sorts.ascending("student_id")).first().getInteger("student_id");
		
		for (int i = lowestStudentId; i <= highestStudentId; i++) {
			List<Document> studentGradeList = coll.find(Filters.and(Filters.eq("student_id", i), Filters.eq("type", "homework"))).into(new ArrayList<Document>());
			ObjectId lowestValue_id = null;
			double lowestScore = 0.0;
			for (Document document : studentGradeList) {
				if(lowestValue_id == null){
					lowestValue_id = document.getObjectId("_id");
					lowestScore = document.getDouble("score");
				}else{
					Double currentScore;
					if((currentScore = document.getDouble("score"))< lowestScore){
						lowestScore = document.getDouble("score");
						lowestValue_id = document.getObjectId("_id");
					}
				}
			}
			if(lowestValue_id != null){
				coll.deleteOne(Filters.eq("_id", lowestValue_id));
			}
		}
	}
}
