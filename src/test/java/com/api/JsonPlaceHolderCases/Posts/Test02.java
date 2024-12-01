package com.api.JsonPlaceHolderCases.Posts;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Test02 extends ApiHelper {

    @Test
    public void testgetsinglepost(){

        String id = "1" ;
        Response response = ApiHelper.sendGetRequest("/posts/" + id);

        String responseBody = response.getBody().asString();

        System.out.println(responseBody);

        response.then().assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(1));
    }
}
