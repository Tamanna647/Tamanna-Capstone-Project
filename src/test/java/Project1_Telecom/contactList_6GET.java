package Project1_Telecom;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class contactList_6GET {

    @Test(dependsOnMethods = "Project1_Telecom.login_4POST.loginUser")
    public void getContactList() {

        String token = AuthTokenStore.token;

        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("https://thinking-tester-contact-list.herokuapp.com/contacts")
        .then()
            .statusCode(200);
    }
}
