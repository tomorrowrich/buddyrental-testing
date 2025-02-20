Feature: User Logout

  Scenario: Successful logout
    Given the user is logged in
    And the user has a valid JWT token
    When the user logs out by removing the token
    And the JWT token should be invalidated
    And the session should be removed