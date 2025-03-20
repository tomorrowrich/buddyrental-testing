package buddyrental

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.*

class RetreiveUsersStepDef {
    private lateinit var response: Response
    private lateinit var userList: List<Map<String, Any>> // Adjust type as per API response

    @When("the client requests the list of users")
    fun clientRequestsUserList() {
        val request = RestAssured.given()

        response = request.get("https://buddyrental-backend-dev.onrender.com/api/users")

        // Validate HTTP response status
        assertEquals(200, response.statusCode, "Expected HTTP 200 OK")

        // Extract user list safely
        userList = response.jsonPath().getList<Map<String, Any>>("") ?: emptyList()

        // Ensure list is not empty
        assert(userList.isNotEmpty()) { "User list should not be empty" }

        println("Successfully retrieved ${userList.size} users.")
    }


    @Then("the response should not be empty")
    fun responseShouldNotBeEmpty() {
        assertNotNull(userList, "User list should not be null")
        assertFalse(userList!!.isEmpty(), "User list should not be empty")
    }

    @Then("each user should have a userId, displayName, firstName, lastName, and age")
    fun validateBasicUserFields() {
        for (user in userList!!) {
            assertInstanceOf(MutableMap::class.java, user, "Each user should be a Map object")
            val userMap = user as Map<*, *>

            assertNotNull(userMap["userId"], "User ID should not be null")
            assertNotNull(userMap["displayName"], "Display Name should not be null")
            assertNotNull(userMap["firstName"], "First Name should not be null")
            assertNotNull(userMap["lastName"], "Last Name should not be null")
            assertNotNull(userMap["age"], "Age should not be null")
        }
    }

    @Then("each user should have a gender, phoneNumber, and address")
    fun validateAdditionalUserFields() {
        for (user in userList!!) {
            val userMap = user as Map<*, *>

            assertNotNull(userMap["gender"], "Gender should not be null")
            assertNotNull(userMap["phoneNumber"], "Phone Number should not be null")
            assertNotNull(userMap["address"], "Address should not be null")
        }
    }
}
