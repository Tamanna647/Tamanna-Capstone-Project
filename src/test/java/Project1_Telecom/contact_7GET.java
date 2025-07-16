package Project1_Telecom;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class contact_7GET {

    @Test(dependsOnMethods = "Project1_Telecom.addContact_5POST.addContact")
    public void getSingleContact() {

        String token = AuthTokenStore.token;

        
        String contactId = AuthTokenStore.contactId;

        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("https://thinking-tester-contact-list.herokuapp.com/contacts/" + contactId)
        .then()
            .statusCode(200);
    }
}
