package Project1_Telecom;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class update_3Patch {

    @Test(dependsOnMethods = "Project1_Telecom.addUser_1Post.addUser")  // Optional but recommended
    public void updateUser() {
        
        // Use email from AuthTokenStore to avoid conflicts
        String payload = "{\n" +
                "  \"firstName\": \"Tanu\",\n" +
                "  \"lastName\": \"Parma\",\n" +
                "  \"email\": \"" + AuthTokenStore.email + "\",\n" +
                "  \"password\": \"new12345\"\n" +
                "}";

        Response response = given()
            .header("Authorization", "Bearer " + AuthTokenStore.token)
            .header("Content-Type", "application/json")
            .body(payload)
        .when()
            .patch("https://thinking-tester-contact-list.herokuapp.com/users/me");

        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, 200, "Expected status code 200 but got: " + actualStatusCode);
        
        response.prettyPrint();  
    }
}
