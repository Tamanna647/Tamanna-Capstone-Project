package Project1_Telecom;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;




import static io.restassured.RestAssured.*;


public class getUserProfile_2Get {

	@Test(dependsOnMethods = "Project1_Telecom.addUser_1Post.addUser")
    public void getUserProfile() {
    	System.out.println("Token being used: " + AuthTokenStore.token);
       
    	Response response = given()
    	        .header("Authorization", "Bearer " + AuthTokenStore.token)
    	    .when()
    	        .get("https://thinking-tester-contact-list.herokuapp.com/users/me");

    	    int actualStatusCode = response.statusCode();
    	    Assert.assertEquals(actualStatusCode, 200, "Expected status code 200 but got: " + actualStatusCode);
        
           
    }
}
