package com.mongodb.crud;

import org.bson.Document;

public class DocumentTest {

	
	public static void main(String[] args) {
		Document document = new Document().append("str", "MongoDB Hello");
		
		String str = document.getString("str");
	}
}
