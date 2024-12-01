package com.api.JsonPlaceHolderCases.Users;

import com.api.base.ApiHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Test04 extends ApiHelper{



    @Test
    public void testUpdateUsers()  {
        String id="1";

        Response response = ApiHelper.sendDeleteRequest("/users/"+id);
        String responseBody = response.getBody().asString();

        System.out.println(responseBody);
        response.then()
                .statusCode(200) ;


        Response response2 = ApiHelper.sendGetRequest("/users/"+id);
        response.then()
                .statusCode(200);


    }
}