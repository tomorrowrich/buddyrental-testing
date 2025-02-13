package buddyrental_testing;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class UserRegSD {
    private Response response;
    private final String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    private Map<String, Object> requestBody = new HashMap<>();

    @Given("the information is valid and not linked to an existing account")
    public void validUserInformation() {
        requestBody.put("firstName", "John");
        requestBody.put("lastName", "Wick");
        requestBody.put("idCard", "123456789");
        requestBody.put("email", "unique.johnwick@example.com");
        requestBody.put("phone", "+1234567890");
        requestBody.put("password", "johnwick");
        requestBody.put("nickname", "Xx_Mist3rJohn_xX");
        requestBody.put("gender", "M");
        requestBody.put("dateOfBirth", "1990-01-01");
        requestBody.put("address", "123 Main Street");
        requestBody.put("city", "Tennessee");
        requestBody.put("zipcode", "12345");
        requestBody.put("profilePicture", "string");
    }

    @When("the customer registers to the system")
    public void registerCustomer() {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody);
        response = request.post(baseUrl + "/api/auth/register");
    }

    @Then("ensure an account is created")
    public void verifyAccountCreation() {
        Assertions.assertEquals(201, response.getStatusCode(), "Expected status code 201");
    }

    @And("ensure information is linked to this account")
    public void verifyInformationLinked() {
        String userId = response.getBody().asString();
        Assertions.assertFalse(userId.isEmpty(), "User ID should not be empty");
    }
}
