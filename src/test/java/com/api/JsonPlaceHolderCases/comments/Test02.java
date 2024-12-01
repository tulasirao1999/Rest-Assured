package com.api.JsonPlaceHolderCases.comments;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class Test02 extends ApiHelper {

    @Test
    public void testpostcomments(){

        JSONObject req = new JSONObject();

        req.put("name","asdfghjkl");
        req.put("email","qwert@gmail.com");
        req.put("body", "qazwsxedcrfv");

   System.out.println(req.toString());

        Response response = ApiHelper.sendPostRequest("/posts/2/comments", String.valueOf(req));

        String responsebody = response.getBody().asString();

        System.out.println(responsebody);

        response.then().assertThat()
                .statusCode(201)
                .body("postId", Matchers.notNullValue());
    }

}
