package com.api.JsonPlaceHolderCases.comments;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Test01 extends ApiHelper {

    @Test
    public void testgetcomments(){


        Response response = ApiHelper.sendGetRequest("/posts/1/comments");

        String responsebody = response.getBody().asString();

        System.out.println(responsebody);

        response.then().assertThat()
                .statusCode(200)
                .body("[0].postId", Matchers.equalTo(1));
    }
}
