package buddyrental_testing;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CusRegStepDef {
    /*
    private String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    private String userId;
    private Response response;
    Map<String, String> requestBody = new HashMap<>();

    @Given("the information is valid")
    public void the_information_is_valid() {
        // Preparing valid user registration data
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

    @When("the customer registers to the system for CusReg")
    public void the_customer_registers_to_the_system() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody) // Uses the requestBody set in @Given step
                .post(baseUrl + "/api/auth/register");

        if (response.getStatusCode() == 201) {
            userId = response.jsonPath().getString("userId");
        }
    }

    @Then("ensure a customer account is created for CusReg")
    public void an_account_should_be_created() {
        // System.out.println("Response Status Code: " + response.getStatusCode());
        // System.out.println("Response Body: " + response.getBody().asString());
        // System.out.println("Request Body: " + requestBody);
        
        Assertions.assertEquals(201, response.getStatusCode());
    }


    @Then("ensure information is linked to this account")
    public void ensureInformationIsLinkedToThisAccount() {
        Assertions.assertNotNull(userId, "User ID should not be null after registration.");
    }

    @Then("the system requests the user to fill in personal information")
    public void systemRequestsUserToFillInPersonalInformation() {
        // This assumes the profile needs additional information after registration
        response = RestAssured.given()
                .get(baseUrl + "/api/users/" + userId); // Assuming this endpoint shows incomplete profile details

        // Example validation: check if 'firstName', 'lastName' or other fields are missing
        String firstName = response.jsonPath().getString("firstName");
        String lastName = response.jsonPath().getString("lastName");

        // If the system expects the user to fill out personal information,
        // those fields would likely be empty or null
        Assertions.assertNull(firstName, "First name should be null or empty before completion.");
        Assertions.assertNull(lastName, "Last name should be null or empty before completion.");
    }




    @Given("a user is registered")
    public void a_user_is_registered() {
        // Pre-register a user for fetch testing
        Map<String, Object> user = new HashMap<>();
        user.put("email", "john@doe.com");
        user.put("password", "password123");

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(baseUrl + "/api/auth/register");

        Assertions.assertEquals(201, response.getStatusCode());
        userId = response.jsonPath().getString("userId");
    }

    @When("the client requests the user with ID")
    public void clientRequestsUserWithID() {
        response = RestAssured.given()
                .get(baseUrl + "/api/users/" + userId);
    }

    @Then("the system should return user details")
    public void systemReturnsUserDetails() {
        Assertions.assertEquals(200, response.getStatusCode());

        // Validate user data
        Assertions.assertEquals("John GG", response.jsonPath().getString("displayName"));
        Assertions.assertEquals("I love coding and coffee.", response.jsonPath().getString("description"));
        Assertions.assertEquals("ttt", response.jsonPath().getString("firstName"));
        Assertions.assertEquals("ggg", response.jsonPath().getString("lastName"));
        Assertions.assertEquals(12, response.jsonPath().getInt("age"));
        Assertions.assertEquals("MALE", response.jsonPath().getString("gender"));
        Assertions.assertEquals("0123456789000000", response.jsonPath().getString("phoneNumber"));
        Assertions.assertEquals("123 Main Street", response.jsonPath().getString("address"));
        Assertions.assertEquals("1000000000", response.jsonPath().getString("citizenId"));
    }

    @And("the response should have status 200")
    public void responseShouldHaveStatus200() {
        Assertions.assertEquals(200, response.getStatusCode());
    }

     */
}
