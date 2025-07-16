package Project1_Telecom;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class update_8PUT {

    @Test(dependsOnMethods = "Project1_Telecom.contact_7GET.getSingleContact")
    public void updateFullContact() {

        String token = AuthTokenStore.token;
        String contactId = AuthTokenStore.contactId;

        // Optional: dynamic email to avoid duplicate email issues
        String updatedEmail = "aman" + System.currentTimeMillis() + "@gmail.com";

        String payload = "{\n" +
                "  \"firstName\": \"Aman\",\n" +
                "  \"lastName\": \"Miller\",\n" +
                "  \"birthdate\": \"1992-02-02\",\n" +
                "  \"email\": \"" + updatedEmail + "\",\n" +
                "  \"phone\": \"8005554242\",\n" +
                "  \"street1\": \"13 School St.\",\n" +
                "  \"street2\": \"Apt. 5\",\n" +
                "  \"city\": \"Washington\",\n" +
                "  \"stateProvince\": \"QC\",\n" +
                "  \"postalCode\": \"A1A1A1\",\n" +
                "  \"country\": \"Canada\"\n" +
                "}";

        given()
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .body(payload)
        .when()
            .put("https://thinking-tester-contact-list.herokuapp.com/contacts/" + contactId)
        .then()
            .statusCode(200);
    }
}
