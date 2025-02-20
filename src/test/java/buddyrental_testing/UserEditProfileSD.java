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
public class UserEditProfileSD {

    private Response response;
    private String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    private String accessToken;
    private Map<String, Object> requestBody = new HashMap<>();
    private String phone = String.valueOf(System.currentTimeMillis());
    private String pic = "https://example.com/" + System.currentTimeMillis() + ".com";

    @Given("the user is logged in for profile update")
    public void userIsLoggedIn() {
        Map<String, Object> loginRequest = new HashMap<>();
        loginRequest.put("clientKey", "b481c464ec2556f0d7e6a1fc46c99a92");
        loginRequest.put("email", "john.doe@example.com");
        loginRequest.put("password", "Password123!");

        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(loginRequest);
        response = request.post(baseUrl + "/api/auth/login");

        Assertions.assertEquals(201, response.getStatusCode(), "Expected status code 201");
        accessToken = response.jsonPath().getString("accessToken");
        System.out.println("accessToken:"+accessToken);
    }

    @When("the user submits the updated details")
    public void updateUserDetails() {
        requestBody.put("phoneNumber", phone);
        requestBody.put("profilePicture", pic);

        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody);
        response = request.patch(baseUrl + "/api/users/profile");
        System.out.println("response:"+response.getBody().asString());
    }

    @Then("ensure the information is validated and updated")
    public void verifyInformationUpdated() {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody);
        Response meResponse = request.get(baseUrl + "/api/auth/me");
        Assertions.assertEquals(200, meResponse.getStatusCode(), "Expected status code 200");
        Assertions.assertTrue(meResponse.getBody().asString().contains(phone), "Updated phone should be present");
        Assertions.assertTrue(meResponse.getBody().asString().contains(pic), "Updated profile picture should be present");
    }

}
