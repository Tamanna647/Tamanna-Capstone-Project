package Project1_Telecom;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class addContact_5POST {

    @Test(dependsOnMethods = "Project1_Telecom.login_4POST.loginUser")
    public void addContact() {

        // Use token stored from login test
        String token = AuthTokenStore.token;

        // Optional: generate unique email for contact to avoid conflicts
        String uniqueEmail = "aishre" + System.currentTimeMillis() + "@gmail.com";

        String payload = "{\n" +
                "  \"firstName\": \"Aish\",\n" +
                "  \"lastName\": \"Roe\",\n" +
                "  \"birthdate\": \"1970-01-01\",\n" +
                "  \"email\": \"" + uniqueEmail + "\",\n" +
                "  \"phone\": \"8005555555\",\n" +
                "  \"street1\": \"1 Main St.\",\n" +
                "  \"street2\": \"Apartment A\",\n" +
                "  \"city\": \"Anytown\",\n" +
                "  \"stateProvince\": \"KS\",\n" +
                "  \"postalCode\": \"12345\",\n" +
                "  \"country\": \"USA\"\n" +
                "}";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
            .when()
                .post("https://thinking-tester-contact-list.herokuapp.com/contacts")
            .then()
                .statusCode(201)
                .extract().response();
        

        String contactId = response.jsonPath().getString("_id");
        System.out.println("Contact ID: " + contactId);
        AuthTokenStore.contactId = contactId;
       
    }
}
