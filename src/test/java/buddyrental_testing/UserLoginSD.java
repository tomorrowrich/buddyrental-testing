package buddyrental_testing;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserLoginSD {
    private Response response;
    private Map<String, Object> requestBody = new HashMap<>();
    private String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    @Given("the login email and password is valid")
    public void validLoginCredentials() {
        requestBody.clear();
        requestBody.put("clientKey", "b481c464ec2556f0d7e6a1fc46c99a92");
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
        // System.out.println(response.jsonPath().getString("accessToken"));
        Assertions.assertTrue(response.getBody().asString().contains("accessToken"), "Response should contain accessToken");
    }
}
