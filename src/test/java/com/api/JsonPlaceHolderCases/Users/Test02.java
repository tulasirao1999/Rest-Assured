package com.api.JsonPlaceHolderCases.Users;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test02 extends ApiHelper{

    @Test
    public void testGetSingleUsers()  {


        String id ="1";
        Response response = ApiHelper.sendGetRequest("/users/"+id);
        String responseBody = response.getBody().asString();

        System.out.println(response.getBody().asString());


        Assert.assertFalse(responseBody.trim().isEmpty(), "The user list is empty");

        response.then().assertThat()
                .statusCode(200)
                .body("size()", org.hamcrest.Matchers.greaterThan(0))
                .body("name", org.hamcrest.Matchers.notNullValue())
                .body("email", org.hamcrest.Matchers.notNullValue())
                .body("address.street", org.hamcrest.Matchers.notNullValue())
                .body("address.city", org.hamcrest.Matchers.notNullValue())
                .body("address.zipcode", org.hamcrest.Matchers.notNullValue());


    }

    @Test
    public void testToValidateNonExistentUser()  {


        String id ="1345";
        Response response = ApiHelper.sendGetRequest("/users/"+id);
        String responseBody = response.getBody().asString();

        System.out.println(response.getBody().asString());

        // Assert that the response is not empty
        Assert.assertFalse(responseBody.trim().isEmpty(), "The user list is empty");


        response.then().assertThat()
                .statusCode(404);

    }



}
