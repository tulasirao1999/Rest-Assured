package com.api.JsonPlaceHolderCases.Users;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test01  extends ApiHelper {

    @Test
    public void testGetUsers()  {

        Response response = ApiHelper.sendGetRequest("/users");
        String responseBody = response.getBody().asString();

        System.out.println(response.getBody().asString());

        // Assert that the response is not empty
        Assert.assertFalse(responseBody.trim().isEmpty(), "The user list is empty");

        // Assert that the response contains the expected fields
        // Validate the JSON structure - for example, the first user
        response.then().assertThat()
                .statusCode(200)
                .body("size()", org.hamcrest.Matchers.greaterThan(0))
                .body("[0].name", org.hamcrest.Matchers.notNullValue())
                .body("[0].email", org.hamcrest.Matchers.notNullValue())
                .body("[0].address.street", org.hamcrest.Matchers.notNullValue())
                .body("[0].address.city", org.hamcrest.Matchers.notNullValue())
                .body("[0].address.zipcode", org.hamcrest.Matchers.notNullValue());


    }
}