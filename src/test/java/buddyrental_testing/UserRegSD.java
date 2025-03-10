package buddyrental_testing;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserRegSD {
    private Response response, meResponse;
    // private final String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    private String baseUrl = "http://localhost:55500";
    private Map<String, Object> requestBody = new HashMap<>();

    @Given("the information is valid and not linked to an existing account")
    public void validUserInformation() {
        requestBody.put("firstName", "John");
        requestBody.put("lastName", "Wick");
        requestBody.put("citizenId", String.valueOf(System.currentTimeMillis()));
        requestBody.put("email", "john" + System.currentTimeMillis() + "@example.com");
        requestBody.put("phone", "+1234567890");
        requestBody.put("password", "Password123!");
        requestBody.put("nickname", "John");
        requestBody.put("gender", "MALE");
        requestBody.put("dateOfBirth", "2025-02-20T08:10:49.323Z");
        requestBody.put("address", "123 Main Street");
        requestBody.put("city", "Tennessee");
        requestBody.put("postalCode", "12345");
        requestBody.put("profilePicture", "string");
    }

    @When("the customer registers to the system for UserReg")
    public void registerCustomer() {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody);
        response = request.post(baseUrl + "/api/auth/register");

        // ✅ Debug Response Body
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("ensure an user account is created")
    public void verifyAccountCreation() {
        Assertions.assertEquals(201, response.getStatusCode(), "Expected status code 201");
    }

    // @And("ensure information is linked to this account for UserReg")
    // public void verifyInformationLinked() {
    //     // ✅ ตรวจสอบว่ามี userId กลับมา
    //     RequestSpecification request = RestAssured.given()
    //             .header("Content-Type", "application/json");
    //     meResponse = request.get(baseUrl + "/api/auth/me");
    //     System.out.println("Response: " + response.asString());
    //     Assertions.assertEquals(200, meResponse.getStatusCode(), "Expected status code 200");
    // }
}
