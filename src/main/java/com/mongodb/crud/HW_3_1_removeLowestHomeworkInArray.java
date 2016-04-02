package com.mongodb.crud;

import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.MorphiaIterator;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.mongovo.Students;
import com.mongovo.Score;

public class HW_3_1_removeLowestHomeworkInArray {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.mongovo");
		Datastore ds = new Morphia().createDatastore(mongoClient, "school");
		ds.getCount(Students.class);
		Query<Students> query =  ds.createQuery(Students.class);
		Iterable<Students> fetch =query.fetch();
//		((MorphiaIterator<Students, Students>)fetch).close();
		Iterator<Students> iterator = fetch.iterator();
		while(iterator.hasNext()){
			Students student = iterator.next();
			Score lowestSubject = null;
			int homeWrdDocsCount=0;
			for(Score subject :student.scores){
				if(subject.type.equalsIgnoreCase("homework")){
					homeWrdDocsCount++;
					if(lowestSubject == null){
						lowestSubject = subject;
					}
					else if(lowestSubject.score > subject.score){
					lowestSubject = subject;
					lowestSubject = subject;
				}
			}
			}
			if(homeWrdDocsCount>1){
			student.scores.remove(lowestSubject);
			ds.save(student);
			}
		}
		mongoClient.close();
	}
}
