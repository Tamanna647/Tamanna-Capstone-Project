package Project1_Telecom;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class logoutuser_10POST {

    @Test(dependsOnMethods = "Project1_Telecom.update_9PATCH.updatePartialContact")
    public void logoutUser() {

        String token = AuthTokenStore.token;

        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .post("https://thinking-tester-contact-list.herokuapp.com/users/logout")
        .then()
            .statusCode(200);

        System.out.println("✅ User logged out successfully.");
    }
}
