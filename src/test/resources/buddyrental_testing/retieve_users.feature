#@UserAPI
#Feature: Retrieve Users from API
#
#  @GetUsers
#  Scenario: Get all users successfully
#    When the client requests the list of users
#    Then the response should not be empty
#
#  @ValidStructure
#  Scenario: Validate response structure
#    When the client requests the list of users
#    Then each user should have a userId, displayName, firstName, lastName, and age
#    And each user should have a gender, phoneNumber, and address
