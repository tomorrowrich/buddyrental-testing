// package buddyrental_testing;

// import io.cucumber.java.en.*;
// import io.restassured.RestAssured;
// import io.restassured.response.Response;
// import java.util.List;
// import static org.junit.jupiter.api.Assertions.*;

// public class userAPITest {

//     private Response response;
//     private List<Object> userList;

//     @When("the client requests the list of users")
//     public void clientRequestsUserList() {
//         response = RestAssured.given()
//                 .get("https://buddyrental-backend-dev.onrender.com/api/users");

//         userList = response.jsonPath().getList(""); // ดึงข้อมูลเป็น List
//     }


//     @Then("the response should not be empty")
//     public void responseShouldNotBeEmpty() {
//         assertNotNull(userList, "User list should not be null");
//         assertFalse(userList.isEmpty(), "User list should not be empty");
//     }

//     @Then("each user should have a userId, displayName, firstName, lastName, and age")
//     public void validateBasicUserFields() {
//         for (Object user : userList) {
//             assertInstanceOf(java.util.Map.class, user, "Each user should be a Map object");
//             var userMap = (java.util.Map<String, Object>) user;

//             assertNotNull(userMap.get("userId"), "User ID should not be null");
//             assertNotNull(userMap.get("displayName"), "Display Name should not be null");
//             assertNotNull(userMap.get("firstName"), "First Name should not be null");
//             assertNotNull(userMap.get("lastName"), "Last Name should not be null");
//             assertNotNull(userMap.get("age"), "Age should not be null");
//         }
//     }

//     @Then("each user should have a gender, phoneNumber, and address")
//     public void validateAdditionalUserFields() {
//         for (Object user : userList) {
//             var userMap = (java.util.Map<String, Object>) user;

//             assertNotNull(userMap.get("gender"), "Gender should not be null");
//             assertNotNull(userMap.get("phoneNumber"), "Phone Number should not be null");
//             assertNotNull(userMap.get("address"), "Address should not be null");
//         }
//     }
// }
