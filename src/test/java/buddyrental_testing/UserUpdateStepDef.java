package buddyrental_testing;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserUpdateStepDef {
    /*
    private final String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    private Response response;
    private String accessToken;
    private String userId;

    // Login credentials
    private final String clientKey = "b481c464ec2556f0d7e6a1fc46c99a92";
    private final String validEmail = "john.doe@example.com";
    private final String validPassword = "Password123!";
    private final String phone = String.valueOf(System.currentTimeMillis());

    @Given("the login email and password are valid")
    public void the_user_is_logged_in() {
        // POST request to /api/auth/signin
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("clientKey", clientKey);
        loginRequest.put("email", validEmail);
        loginRequest.put("password", validPassword);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .post(baseUrl + "/api/auth/login");

        Assertions.assertEquals(201, response.getStatusCode(), "Login failed.");
        accessToken = response.jsonPath().getString("accessToken");
        //userId = response.jsonPath().getString("userId");  // Ensure correct extraction
        Assertions.assertNotNull(accessToken, "Access token should not be null after login");
        //Assertions.assertNotNull(userId, "User ID should not be null after login");
    }

    @When("the user submits the updated details")
    public void the_user_submits_the_updated_details() {
        // Prepare updated user details for PATCH request
        Map<String, Object> updatedDetails = new HashMap<>();
        updatedDetails.put("nickname", "John Doe");
        updatedDetails.put("description", "I love coding and coffee.");
        updatedDetails.put("profilePicture", "https://example.com/profile.jpg");
        updatedDetails.put("address", "123 Main Street");
        updatedDetails.put("phone", phone);

        // PATCH request to update user details
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(updatedDetails)
                .patch(baseUrl + "/api/users/" + userId);
    }

    @Then("ensure the information is validated and updated")
    public void ensure_the_information_is_validated_and_updated() {
        Assertions.assertEquals(200, response.getStatusCode(), "Update failed.");

        // Validate updated details by GET request
        Response getUserResponse = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .get(baseUrl + "/api/auth/me");

        Assertions.assertEquals(200, getUserResponse.getStatusCode(), "Failed to retrieve updated user.");
        Assertions.assertEquals("John Doe", getUserResponse.jsonPath().getString("displayName"));
        Assertions.assertEquals("I love coding and coffee.", getUserResponse.jsonPath().getString("description"));
        Assertions.assertEquals("https://example.com/profile.jpg", getUserResponse.jsonPath().getString("profilePicture"));
        Assertions.assertEquals("123 Main Street", getUserResponse.jsonPath().getString("address"));
        Assertions.assertEquals(phone, getUserResponse.jsonPath().getString("phoneNumber"));
    }
*/
}
