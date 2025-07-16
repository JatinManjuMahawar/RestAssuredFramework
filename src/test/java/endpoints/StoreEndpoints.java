package endpoints;

import com.aventstack.extentreports.gherkin.model.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.StorePojo;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class StoreEndpoints {

    public static Response getStoreInventory() {
        Response response =
                given()
                        .when()
                        .get(Routes.getStoreInventory);

        return response;
    }

    public static Response getStoreOrders(StorePojo storePojo) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(storePojo)
                .when()
                .post(Routes.getStoreOrders);

        return response;
    }

    public static Response getOrdersByID(int orderID) {
        Response response = given()
                .pathParam("orderid", orderID)
                .when()
                .get(Routes.getOrderByID);

        return response;

    }

    public static Response deleteOrderByID(int orderID) {
        Response response = given()
                .pathParam("orderid", orderID)
                .when()
                .delete(Routes.deleteOrderByID);

        return response;
    }
}
