package com.api.JsonPlaceHolderCases.Posts;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test01 extends ApiHelper {

    @Test
    public void testgetpost() {

        Response response = ApiHelper.sendGetRequest("/posts/");
        String responseBody = response.getBody().asString();

        System.out.println(responseBody);

        Assert.assertFalse(responseBody.trim().isEmpty(), "The user list is empty");

        response.then().assertThat()
                .statusCode(200)
                .body("[0].id", Matchers.equalTo(1));
    }


}
