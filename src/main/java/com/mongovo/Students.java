package com.mongovo;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value="students")
public class Students {
	@Id
	public long id;
 public String name;
 
 public List<Score> scores = new ArrayList<>();
	
}
