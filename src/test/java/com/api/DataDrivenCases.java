package com.api;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.Matchers.*;

public class DataDrivenCases {

    // Parameterized Test - Reading data from the CSV file
    @ParameterizedTest
    @CsvFileSource(resources = "/user_data.csv", numLinesToSkip = 1)  // Skip header row
    public void createUserTest(String name, String username, String email, String street, String suite, String city,
                               String zipcode, String lat, String lng, String phone, String website, String companyName,
                               String companyCatchPhrase, String companyBs) {

        // Prepare the request body as JSON
        JSONObject body = new JSONObject();
        body.put("name", name);
        body.put("username", username);
        body.put("email", email);

        JSONObject geo = new JSONObject();
        geo.put("lat", lat);
        geo.put("lng", lng);

        JSONObject address = new JSONObject();
        address.put("street", street);
        address.put("suite", suite);
        address.put("city", city);
        address.put("zipcode", zipcode);
        address.put("phone", phone);
        address.put("geo", geo);

        JSONObject company = new JSONObject();
        company.put("name", companyName);
        company.put("catchPhrase",companyCatchPhrase);
        company.put("bs", companyBs);

        body.put("address", address);
        body.put("company", company);

        // Send POST request to create user
        Response response = ApiHelper.sendPostRequest("/users", String.valueOf(body));


        // Assert the response
        response.then()
                .statusCode(201)  // Assert that the user is created
                .body("name", equalTo(name))  // Assert that the name is returned correctly
                .body("username", equalTo(username))  // Assert that the username is returned correctly
                .body("email", equalTo(email))  // Assert that the email is returned correctly
                .body("address.city", equalTo(city))  // Assert that the city is returned correctly
                .body("company.name", equalTo(companyName))  // Assert that the company name is returned correctly
                .body("id", notNullValue());  // Assert that the id is generated and returned
    }
}