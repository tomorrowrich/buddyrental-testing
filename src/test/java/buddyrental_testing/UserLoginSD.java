package buddyrental_testing;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class UserLoginSD {
    private Response response;
    private Map<String, Object> requestBody = new HashMap<>();
    private String baseUrl = "http://localhost:55500";
    @Given("the login email and password is valid")
    public void validLoginCredentials() {
        requestBody.clear();
        requestBody.put("clientKey", "DEFAULT_CLIENT_KEY");
        requestBody.put("email", "john.doe@example.com");
        requestBody.put("password", "Password123!");
    }

    @And("the customer is not logged in")
    public void customerNotLoggedIn() {
        // Assume user is not logged in before the test
    }

    @When("the customer logs in to the system")
    public void loginUser() {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody);
        response = request.post(baseUrl + "/api/auth/login");
    }

    @Then("ensure the customer is logged in")
    public void verifyCustomerLoggedIn() {
        Assertions.assertEquals(201, response.getStatusCode(), "Expected status code 201");
        Assertions.assertTrue(response.getBody().asString().contains("accessToken"), "Response should contain accessToken");
    }
}
