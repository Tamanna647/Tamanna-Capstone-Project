package Project1_Telecom;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class update_9PATCH {

    @Test(dependsOnMethods = "Project1_Telecom.update_8PUT.updateFullContact")
    public void updatePartialContact() {

        String token = AuthTokenStore.token;
        String contactId = AuthTokenStore.contactId;

        String payload = "{\n" +
                "  \"firstName\": \"Ishu\"\n" +
                "}";

        given()
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .body(payload)
        .when()
            .patch("https://thinking-tester-contact-list.herokuapp.com/contacts/" + contactId)
        .then()
            .statusCode(200);

        System.out.println("Contact partially updated successfully.");
    }
}
