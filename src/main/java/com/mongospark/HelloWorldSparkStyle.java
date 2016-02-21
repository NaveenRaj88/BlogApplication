package com.mongospark;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by anandran on 2/21/16.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World from spark";
            }
        });
//      Spark.get("/", (req, res) -> "Hello world");
    }
}
