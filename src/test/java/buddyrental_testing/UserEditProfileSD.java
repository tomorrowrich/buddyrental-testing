package buddyrental_testing;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;
public class UserEditProfileSD {

    private Response response;
    private String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    private String accessToken;
    private Map<String, Object> requestBody = new HashMap<>();

    @Given("the user is logged in")
    public void userIsLoggedIn() {
        Map<String, Object> loginRequest = new HashMap<>();
        loginRequest.put("clientKey", "MOCK_CLIENT_KEY");
        loginRequest.put("email", "unique.johnwick@example.com");
        loginRequest.put("password", "johnwick");

        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(loginRequest);
        response = request.post(baseUrl + "/api/auth/signin");

        Assertions.assertEquals(201, response.getStatusCode(), "Expected status code 201");
        accessToken = response.jsonPath().getString("accessToken");
    }

    @When("the user submits the updated details")
    public void updateUserDetails() {
        requestBody.put("firstName", "Jonathan");
        requestBody.put("lastName", "Doe");
        requestBody.put("phone", "+9876543210");
        requestBody.put("address", "456 New Street");
        requestBody.put("city", "New York");
        requestBody.put("zipcode", "54321");

        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody);
        response = request.put(baseUrl + "/api/user/update");
    }

    @Then("ensure the information is validated and updated")
    public void verifyInformationUpdated() {
        Assertions.assertEquals(200, response.getStatusCode(), "Expected status code 200");
        Assertions.assertTrue(response.getBody().asString().contains("Jonathan"), "Updated first name should be present");
    }

}
