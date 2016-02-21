package com.mongospark;

import spark.Route;
import spark.Spark;

/**
 * Created by Naveenkumar on 2/21/16.
 */
public class SparkRoutes {

    public static void main(String[] args) {
        Spark.get("/", (req,res)-> "Hello World\n");

        Spark.get("/test", (request, response) -> "This is a test page\n");

        Spark.get("/echo/:thing", (request, response) -> request.params(":thing"));
    }
}
