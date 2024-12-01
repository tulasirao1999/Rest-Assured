package com.api.JsonPlaceHolderCases.Posts;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class Test04 extends ApiHelper {

    @Test
    public void testupdatepost(){

        JSONObject req = new JSONObject();

        req.put("title", "sunt aut facere repellat provident occaecati excepturi optio");

        Response response = ApiHelper.sendPutRequest("/posts/"+1, String.valueOf(req));

        String responseBody = response.getBody().asString();

        System.out.println(responseBody);

        response.then().assertThat()
                .statusCode(200)
                .body("title", Matchers.equalTo("sunt aut facere repellat provident occaecati excepturi optio"));
    }
}
