package endpoints;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.Category;
import payloads.PetPojo;
import payloads.PhotoURL;
import payloads.Tags;
import static io.restassured.RestAssured.given;

public class PetEndpoints {

        public static Response createPet() throws JsonProcessingException {
        String[] photoURLArr = {"jj"};
        Category category = new Category(1, "New Category");
        PhotoURL photoURL = new PhotoURL(photoURLArr);
        Tags[] tags = new Tags[1];
        tags[0] = new Tags(1, "NewTag");
        PetPojo petPojo = new PetPojo(category, tags);
        petPojo.setId(1);
        petPojo.setStatus("not available");
        petPojo.setName("Doggie");
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(petPojo);
        //System.out.println(payload);

        Response res =
        given()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post(Routes.createPetByID);


        //System.out.println(res.body().prettyPrint());

        return res;

    }

}
