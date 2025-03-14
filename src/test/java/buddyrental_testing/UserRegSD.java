package buddyrental_testing;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.time.Instant;

public class UserRegSD {
    private Response response;
    private final String baseUrl = "http://localhost:55500";
    private Map<String, Object> requestBody = new HashMap<>();

    @Given("the information is valid and not linked to an existing account")
    public void validUserInformation() {

        String uniqueId = UUID.randomUUID().toString();
        String uniqueEmail = "john.doe+" + Instant.now().toEpochMilli() + "@example.com";

        requestBody.put("firstName", "John");
        requestBody.put("lastName", "Doe");
        requestBody.put("citizenId", uniqueId.substring(0, 9));
        requestBody.put("email", uniqueEmail);
        requestBody.put("phone", "+1234567890");
        requestBody.put("password", "Password123!");
        requestBody.put("nickname", "John");
        requestBody.put("gender", "MALE");
        requestBody.put("dateOfBirth", "2025-03-14T06:03:08.478Z");
        requestBody.put("address", "123 Main Street");
        requestBody.put("city", "Tennessee");
        requestBody.put("postalCode", "12345");
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
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    @And("ensure information is linked to this account")
    public void verifyInformationLinked() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        Assertions.assertFalse(responseBody.isEmpty(), "Response body should not be empty");
    }
}
