package com.api.JsonPlaceHolderCases.Posts;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Test05 extends ApiHelper {

    @Test
    public void testDeletePost(){

        Response response = ApiHelper.sendDeleteRequest("/posts/1");

        String responsebody = response.getBody().asString();

        System.out.println(responsebody);

        response.then().assertThat()
                .statusCode(200);

    }
}
