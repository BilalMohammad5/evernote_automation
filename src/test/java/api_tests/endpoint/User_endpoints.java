package api_tests.endpoint;

import api_tests.payload.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//this class created for performing CRUD operations on user module
public class User_endpoints {

    public static Response create_user(User payload) {
        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
                .when().post(Routes.create_user);
        return response;


    }

    public static Response read_user(String userName) {
        Response response = given().pathParam("username", userName)
                .when().get(Routes.get_user);
        return response;


    }
    public static Response update_user(String userName,User payload) {
        Response response = given().accept("application/json").contentType("application/json")
                .body(payload).pathParam("username",userName)
                .when().put(Routes.get_user);
        return response;


    }
}
