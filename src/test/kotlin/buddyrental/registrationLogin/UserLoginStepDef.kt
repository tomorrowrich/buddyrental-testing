package buddyrental.registrationLogin

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import net.datafaker.Faker
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class UserLoginStepDef {
    private lateinit var response: Response
    private val requestBody: MutableMap<String, Any> = mutableMapOf()
    private val baseUrl = "http://localhost:55500" // Change if needed


    @Given("the login email is {string}")
    fun the_login_email_is(email: String) {
        requestBody["email"] = email.ifEmpty { "" }
    }

    @Given("the login email and password is from a registered user")
    fun the_login_email_and_password_is_from_registered_user() {
        requestBody.clear()
        requestBody["clientKey"] = "DEFAULT_CLIENT_KEY"
        requestBody["email"] = UserRegistrationStepDef.registeredEmail
        requestBody["password"] = UserRegistrationStepDef.registeredPassword
    }

    @And("the login password is {string}")
    fun the_login_password_is(password: String) {
        requestBody["password"] = password.ifEmpty { "" }
    }

    @And("the customer is not logged in")
    fun the_customer_is_not_logged_in() {
        // Assume user is not logged in before the test
    }

    @When("the customer logs in to the system")
    fun the_customer_logs_in_to_the_system() {
        RestAssured.baseURI = baseUrl

        val request: RequestSpecification = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(requestBody)

        response = request.post("/api/auth/login")

        println("Login request sent: $requestBody")
    }

    @When("the customer attempts to log in")
    fun the_customer_attempts_to_log_in() {
        RestAssured.baseURI = baseUrl

        val request: RequestSpecification = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(requestBody)

        response = request.post("/api/auth/login")

        println("Login attempt sent: $requestBody")
    }

    @Then("ensure the customer is logged in")
    fun verifyCustomerLoggedIn() {
        assertEquals(201, response.statusCode, "Expected HTTP 201 Created")

        val responseBody = response.body.asString()
        assertTrue(responseBody.contains("accessToken"), "Response should contain an accessToken")

        println("Login successful! Response: $responseBody")
    }

    @Then("the login should fail with {string}")
    fun the_login_should_fail_with(expectedErrorMessage: String) {
        assertEquals(400, response.statusCode, "Expected HTTP 400 Bad Request for login failure")

        println("Login failed as expected! Error: $expectedErrorMessage")
    }
}