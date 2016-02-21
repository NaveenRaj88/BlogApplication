package com.mongospark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anandran on 2/21/16.
 */
public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = config.getTemplate("Hello.ftl");

                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "freemarker");
                    helloTemplate.process(helloMap, writer);
                    System.out.println(writer);
                } catch (Exception e) {
                    Spark.halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
//      Spark.get("/", (req, res) -> "Hello world");
    }
}
