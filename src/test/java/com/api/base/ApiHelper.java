package com.api.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ApiHelper {


    private static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ApiHelper.class);

    static{

        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);

            RestAssured.baseURI = properties.getProperty("base.uri"); // Set base URI
            RestAssured.basePath = "/";


        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to load properties file");
        }

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        logger.info("Base URI: " + RestAssured.baseURI);
    }

    // Common GET method
    public static Response sendGetRequest(String endpoint) {
        logger.info("Sending GET request to: " + RestAssured.baseURI + endpoint);
        return RestAssured.given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // Common POST method
    public static Response sendPostRequest(String endpoint, String jsonBody) {
        logger.info("Sending POST request to: " + RestAssured.baseURI + endpoint);
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    // Common PUT method
    public static Response sendPutRequest(String endpoint, String jsonBody) {
        logger.info("Sending PUT request to: " + RestAssured.baseURI + endpoint);
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    // Common DELETE method
    public static Response sendDeleteRequest(String endpoint) {
        logger.info("Sending DELETE request to: " + RestAssured.baseURI + endpoint);
        return RestAssured.given()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}