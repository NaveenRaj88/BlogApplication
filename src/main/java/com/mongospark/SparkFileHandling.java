package com.mongospark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Naveenkumar on 2/21/16.
 */
public class SparkFileHandling {

    public static void main(String[] args) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(SparkFileHandling.class, "/");
        Spark.get("/", (request, response) -> {
            Map<String, Object> fruitsMap = new HashMap<String, Object>();
            fruitsMap.put("fruits", Arrays.asList("apple","banana","peach","orange"));
            Template fruitTemplate = config.getTemplate("FruitTemplate.ftl");
            StringWriter writer = new StringWriter();
            fruitTemplate.process(fruitsMap, writer);
            return writer;
        });

        Spark.post("/favourite_fruit", (request, response) -> {
            String fruit = request.queryParams("fruit");
            if(fruit == null){
                return "Why dont you pick a fruit";
            }else {
                return "your favourite fruit is " + fruit;
            }
        });
    }
}
