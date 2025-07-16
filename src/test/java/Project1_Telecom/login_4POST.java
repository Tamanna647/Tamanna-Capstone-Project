package Project1_Telecom;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class login_4POST {

    @Test(dependsOnMethods = "Project1_Telecom.update_3Patch.updateUser")
    public void loginUser() {

        String email = AuthTokenStore.email;
        String password = "new12345";  

        String payload = "{\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
            .when()
                .post("https://thinking-tester-contact-list.herokuapp.com/users/login")
            .then()
                .statusCode(200)
                .extract()
                .response();

        String token1 = response.jsonPath().getString("token");
        AuthTokenStore.token = token1;

        System.out.println("Logged in successfully. Token: " + token1);
    }
}
