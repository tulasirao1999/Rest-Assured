package com.api.JsonPlaceHolderCases.Posts;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.json.JSONObject;

public class Test03 extends ApiHelper {

    @Test
    public void testCreatepost(){

        JSONObject req = new JSONObject();
        req.put("userId", 101);
        req.put("title", "Creating a new Post");
        req.put("body", "New Post");

        Response response = ApiHelper.sendPostRequest("/posts", String.valueOf(req));

        String responseBody = response.getBody().asString();

        System.out.println(responseBody);

        response.then().assertThat()
                .statusCode(201)
                .body("userId", Matchers.equalTo( 101))
                .body("id", Matchers.notNullValue());

    }
}
