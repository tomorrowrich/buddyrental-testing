package buddyrental

import net.datafaker.Faker
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach

class UserEditProfileStepDef {
    private lateinit var response: Response
    private val baseUrl = "http://localhost:55500"
    private lateinit var accessToken: String
    private val requestBody: MutableMap<String, Any> = mutableMapOf()
    private val faker = Faker() // Initialize Faker

    private val testEmail = faker.internet().emailAddress()
    private val testPassword = "Test@${faker.number().digits(5)}"
    private val testFirstName = faker.name().firstName()
    private val testLastName = faker.name().lastName()
    private val testPhone = faker.phoneNumber().cellPhone()
    private val testAddress = faker.address().streetAddress()
    private val testCity = faker.address().city()
    private val testZipCode = faker.address().zipCode()

    @BeforeEach
    @Given("the user is logged in")
    fun userIsLoggedIn() {
        RestAssured.baseURI = baseUrl

        val loginRequest = mapOf(
            "clientKey" to "DEFAULT_CLIENT_KEY",
            "email" to testEmail,
            "password" to testPassword
        )

        val request: RequestSpecification = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(loginRequest)

        response = request.post("/api/auth/login")

        // Validate login response
        assertEquals(201, response.statusCode, "Expected HTTP 201 Created on login")

        // Extract access token safely
        accessToken = response.jsonPath().getString("accessToken").orEmpty()
        assertTrue(accessToken.isNotEmpty(), "Access token should not be empty")

        println("Login successful! Email: $testEmail | Access token: $accessToken")
    }

    @When("the user submits the updated details")
    fun updateUserDetails() {
        requestBody.apply {
            put("firstName", testFirstName)
            put("lastName", testLastName)
            put("phone", testPhone)
            put("address", testAddress)
            put("city", testCity)
            put("zipcode", testZipCode)
        }

        val request: RequestSpecification = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer $accessToken")
            .body(requestBody)

        response = request.put("/api/user/update")

        // Validate response status
        assertEquals(200, response.statusCode, "Expected HTTP 200 OK on update")

        println("User details updated: $requestBody")
    }

    @Then("ensure the information is validated and updated")
    fun verifyInformationUpdated() {
        assertEquals(200, response.statusCode, "Expected status code 200 after update")

        val responseBody = response.body.asString()
        assertTrue(responseBody.contains(testFirstName), "Updated first name should be present in response")
        assertTrue(responseBody.contains(testLastName), "Updated last name should be present in response")
        assertTrue(responseBody.contains(testPhone), "Updated phone should be present in response")

        println("User profile successfully updated and verified!")
    }
}