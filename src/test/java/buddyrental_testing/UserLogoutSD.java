package buddyrental_testing;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserLogoutSD {
    private Response response;
    // private String baseUrl = "https://buddyrental-backend-dev.onrender.com";
    private String baseUrl = "http://localhost:55500";
    private String accessToken;

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"clientKey\": \"b481c464ec2556f0d7e6a1fc46c99a92\", \"email\": \"john.doe@example.com\", \"password\": \"Password123!\"}");

        Response loginResponse = request.post(baseUrl + "/api/auth/login");

        Assertions.assertEquals(201, loginResponse.getStatusCode(), "Expected status code 201 for login");

        accessToken = loginResponse.jsonPath().getString("accessToken");
        // System.out.println("accessToken:" + accessToken);
        Assertions.assertNotNull(accessToken, "Login response should contain an accessToken");
    }

    @And("the user has a valid JWT token")
    public void userHasValidJWTToken() {
        Assertions.assertNotNull(accessToken, "User should have a valid JWT token");
    }

    @When("the user logs out by removing the token")
    public void theUserLogsOutByRemovingToken() {
        // จำลองการลบ token จาก frontend
        RestAssured.given().cookie("token", "");
    }

    @Then("the JWT token should be invalidated")
    public void jwtTokenShouldBeInvalidated() {
        Response testAccess = RestAssured.given()
                .when()
                .get(baseUrl + "/api/auth/me");  // ใช้ API อะไรก็ได้ที่ต้องมี Auth     

         Assertions.assertEquals(401, testAccess.getStatusCode(), "Token should be invalid after logout");
    }

    @Then("the session should be removed")
    public void sessionShouldBeRemoved() {
        // ตรวจสอบว่า token ถูกลบไปแล้วจริง
        Response response = RestAssured.given()
                .when()
                .get(baseUrl + "/api/auth/me");  // ใช้ API อะไรก็ได้ที่ต้องมี Auth
                
        String cookieValue = response.getCookie("token");
        Assertions.assertNull(cookieValue, "Token should be null after logout");
    }
}
