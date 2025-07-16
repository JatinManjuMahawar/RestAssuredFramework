package tests;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import configuration.Utilities;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payloads.UserPojo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserTests {

    Faker faker;
    UserPojo userPayload;
public Logger logger;
    String username;
    @BeforeClass()
    public void getData(){
        faker = new Faker();
        userPayload = new UserPojo();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setPassword(faker.internet().password());
        userPayload.setUsername(faker.name().username());
        logger = LogManager.getLogger(this.getClass());

    }

    @DataProvider(name = "getTestData")
    public Object[][] getTestData() {
        File testExcelFile = new File("src/test/java/configuration/APItests.xlsx");
        Object[][] testData = Utilities.getTestData(testExcelFile, "API");
        return testData;
    }
    @Test(priority = 0)
    //public void createUser(String username, String firstname, String Lastname, int ID, int Phone, String Password, String Email){
    public void createUser(){

       /* userPayload = new UserPojo();
        userPayload.setId(ID);
        userPayload.setUsername(username);
        userPayload.setEmail(Email);
        userPayload.setFirstName(firstname);
        userPayload.setLastName(Lastname);
        userPayload.setPhone(String.valueOf(Phone));
        userPayload.setPassword(Password);
*/
        Response response = UserEndpoints.createUser(userPayload);
        System.out.println(response.asString());
        response.then().log().all();

        JSONObject jo = new JSONObject(response.toString());
        System.out.println(jo.toString());
        Assert.assertEquals(200, response.statusCode());
    }

    @Test(priority = 1)
    public void getUser(){
        Response response = UserEndpoints.getUser(userPayload.getUsername());
        System.out.println(response.body().prettyPrint());
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test(priority = 3)
    public void updateUser(){
        userPayload.setLastName("gupta");
        userPayload.setFirstName("jatin");
        userPayload.setEmail("jatingupta543@gmail.com");
        Response response = UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
        System.out.println(response.body().prettyPrint());

        Assert.assertEquals(response.statusCode(), 200);
    }
    @Test(priority = 4)
    public void getUserAgain(){
        Response response = UserEndpoints.getUser(this.userPayload.getUsername());
        System.out.println(response.body().prettyPrint());
        //System.out.println(response.jsonPath().getString("cookie"));
        Assert.assertEquals(response.body().jsonPath().getString("firstName"), "jatin");
    }

    @Test(priority = 5)
    public void deleteUser(){
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        System.out.println(response.body().prettyPrint());
        Assert.assertEquals(response.statusCode(), 200);
    }

}
