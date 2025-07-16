package Project1_Telecom;

import org.testng.annotations.Test;


import io.restassured.response.Response;

import static io.restassured.RestAssured.*;         
import static org.hamcrest.Matchers.*;                

public class addUser_1Post {
    @Test
    public void addUser() {
    	
    	String uniqueEmail = "tamana" + System.currentTimeMillis() + "@gmail.com";
    	String payload = "{\n" +
    		    "  \"firstName\": \"Tamanna\",\n" +
    		    "  \"lastName\": \"Sharma\",\n" +
    		    "  \"email\": \"" + uniqueEmail + "\",\n" +
    		    "  \"password\": \"dimo123\"\n" +
    		    "}";

        Response response = given()
                
                .header("Content-Type", "application/json")
                .body(payload)
            .when()
                .post("https://thinking-tester-contact-list.herokuapp.com/users")
            .then()
                .statusCode(201)
                .extract().response();
        int statusCode = response.statusCode();
        String token = response.jsonPath().getString("token");  
        System.out.println("Token: " + token);
        AuthTokenStore.token = token;
        AuthTokenStore.email = uniqueEmail;
        
        System.out.println("Status Code: " + statusCode);
        response.prettyPrint();

            
            
            
        }
    }