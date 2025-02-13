Feature: User Login
  As a user
  I want to log in to the system
  So that I can use the buddy rental system

  Scenario: Successful customer login
    Given the login email and password is valid
    And the customer is not logged in
    When the customer logs in to the system
    Then ensure the customer is logged in
