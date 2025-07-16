package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import endpoints.PetEndpoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetTests {

    //static Logger logger = LogManager.getLogger(PetTests.class);

    @Test
    public void createPet() throws JsonProcessingException {

        Response res = PetEndpoints.createPet();
        res.then().log().all();
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));
        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertTrue(res.time() < 5000L);
        Assert.assertEquals(res.header("Content-Type"), "application/json");
        Assert.assertEquals(res.body().jsonPath().getInt("category.id"), 1);
    }
}
