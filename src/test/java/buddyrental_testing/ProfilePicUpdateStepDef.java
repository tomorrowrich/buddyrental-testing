package buddyrental_testing;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.Map;
public class ProfilePicUpdateStepDef
{
    /*
    private final String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    private Response response;
    private String accessToken;
    private String userId;

    // Login credentials
    private final String clientKey = "MOCK_CLIENT_KEY";
    private final String validEmail = "john@doe.com";
    private final String validPassword = "password123";

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        // POST request to /api/auth/signin
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("clientKey", clientKey);
        loginRequest.put("email", validEmail);
        loginRequest.put("password", validPassword);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .post(baseUrl + "/api/auth/signin");

        Assertions.assertEquals(201, response.getStatusCode(), "Login failed.");
        accessToken = response.jsonPath().getString("accessToken");
        //userId = response.jsonPath().getString("userId");
        Assertions.assertNotNull(accessToken, "Access token should not be null after login");
        //Assertions.assertNotNull(userId, "User ID should not be null after login");
    }

    @When("the user uploads a new profile picture")
    public void the_user_uploads_a_new_profile_picture() {
        // Prepare updated profile picture
        Map<String, Object> profileUpdate = new HashMap<>();
        profileUpdate.put("profilePicture", "https://example.com/new-profile.jpg");

        // PATCH request to update profile picture
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(profileUpdate)
                .patch(baseUrl + "/api/users/" + userId);
    }

    @Then("ensure the picture is validated and updated")
    public void ensure_the_picture_is_validated_and_updated() {
        Assertions.assertEquals(200, response.getStatusCode(), "Profile picture update failed.");

        // Validate updated profile picture by GET request
        Response getUserResponse = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .get(baseUrl + "/api/users/" + userId);

        Assertions.assertEquals(200, getUserResponse.getStatusCode(), "Failed to retrieve updated user.");
        Assertions.assertEquals("https://example.com/new-profile.jpg",
                getUserResponse.jsonPath().getString("profilePicture"),
                "Profile picture did not update correctly.");
    }

     */
}
