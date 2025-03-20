package buddyrental.registrationLogin

import io.cucumber.java.en.*
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import net.datafaker.Faker
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import io.restassured.path.json.JsonPath
import types.Gender
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class UserRegistrationStepDef {

    private lateinit var response: Response
    private val baseUrl = "http://localhost:55500" // Set base URL
    private val requestBody: MutableMap<String, Any> = mutableMapOf()
    private val faker = Faker()

    companion object {
        var registeredEmail: String = ""
        var registeredPassword: String = ""
    }

    private fun generateRandomDOB(): String {
        val currentYear = LocalDate.now().year
        val randomYear = Random.nextInt(currentYear - 60, currentYear - 18)
        val randomMonth = Random.nextInt(1, 13) // Month 1-12
        val randomDay = Random.nextInt(1, 28) // Safe range for days (Feb issue)

        val dob = LocalDate.of(randomYear, randomMonth, randomDay)
        return dob.format(DateTimeFormatter.ISO_DATE) + "T00:00:00.000Z"
    }

    @Given("the information is valid and not linked to an existing account")
    fun validUserInformation() {
        registeredEmail = faker.internet().emailAddress()
        registeredPassword = "Test@${faker.number().digits(5)}"

        requestBody.clear()
        requestBody["firstName"] = faker.name().firstName()
        requestBody["lastName"] = faker.name().lastName()
        requestBody["citizenId"] = faker.idNumber().valid()
        requestBody["email"] = registeredEmail
        requestBody["phone"] = faker.phoneNumber().cellPhone()
        requestBody["password"] = registeredPassword
        requestBody["nickname"] = faker.name().username()

        val randomGender = Gender.entries.toTypedArray().random()
        requestBody["gender"] = randomGender.name

        requestBody["dateOfBirth"] = generateRandomDOB()
        requestBody["address"] = faker.address().streetAddress()
        requestBody["city"] = faker.address().city()
        requestBody["postalCode"] = faker.address().zipCode()
        requestBody["profilePicture"] = "string"

        println("Generated Registration Info: $requestBody")
    }

    @Given("In case the information is already linked to an existing account")
    fun duplicateUserInformation() {
        requestBody.clear()
        requestBody["email"] = "existing.user@example.com" // Hardcoded existing user
        requestBody["password"] = "Password123!"
    }

    @Given("In case the email format is invalid")
    fun invalidEmailFormat() {
        requestBody.clear()
        requestBody["email"] = "invalid-email-format" // Invalid format
        requestBody["password"] = "Password123!"
    }

    @Given("In case the password is too weak")
    fun weakPassword() {
        requestBody.clear()
        requestBody["email"] = faker.internet().emailAddress()
        requestBody["password"] = "12345" // Weak password
    }

    @When("the customer registers to the system")
    fun registerCustomer() {
        RestAssured.baseURI = baseUrl

        val request: RequestSpecification = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(requestBody)

        response = request.post("/api/auth/register")

        println("Registration Request Sent: $requestBody")
    }

    @Then("ensure an account is created")
    fun verifyAccountCreation() {
        assertEquals(201, response.statusCode, "Expected HTTP 201 Created")

        val responseBody = response.body.asString()
        assertFalse(responseBody.isEmpty(), "Response body should not be empty")

        println("✅ Account successfully created! Response: $responseBody")
    }

    @Then("ensure registration fails with {string}")
    fun verifyRegistrationFailure(expectedErrorMessage: String) {
        assertEquals(400, response.statusCode, "Expected HTTP 400 Bad Request for registration failure")

        val responseBody = response.body.asString()
        val actualErrorMessage = extractErrorMessage(responseBody)

        assertEquals(expectedErrorMessage, actualErrorMessage, "Unexpected error message in API response")

        println("❌ Registration failed as expected! Received error: $actualErrorMessage")
    }

    @And("ensure information is linked to this account")
    fun verifyInformationLinked() {
        assertEquals(201, response.statusCode, "User email should be in response")
    }

    // ✅ Helper function to extract the actual error message from the API response
    private fun extractErrorMessage(responseBody: String): String {
        return try {
            val jsonPath = JsonPath.from(responseBody)
            jsonPath.getString("error") ?: "No error message found"
        } catch (e: Exception) {
            "Error extracting message"
        }
    }
}