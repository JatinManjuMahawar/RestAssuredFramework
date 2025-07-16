package endpoints;

import com.aventstack.extentreports.gherkin.model.Given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.UserPojo;

import static io.restassured.RestAssured.given;


public class UserEndpoints {
    //CRUD Operations

    public static Response createUser(UserPojo jsonPayload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(Routes.createUser);

        return response;

    }

    public static Response getUser(String username){
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(Routes.getUserGET);

        return response;

    }

    public static Response updateUser(String username, UserPojo jsonPayload){
        Response response = given()
                .pathParam("username", username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .put(Routes.updateUserPUT);

        return response;

    }

    public static Response deleteUser(String username){
        Response response = given()
                .pathParam("username", username)

                .when()
                .delete(Routes.deleteUserDELETE);

        return response;

    }

    
}
