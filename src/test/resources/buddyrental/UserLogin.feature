@UserLogin
Feature: User Login
  As a user
  I want to log in to the system
  So that I can use the buddy rental system

  # ✅ Scenario สำหรับการ login ที่สำเร็จ
  @Positive @UserLogin
  Scenario: Successful customer login
    Given I visit the login page
    When I input username "john.doe@example.com" and password "Password123!"
    And I click on the login button
    Then I should navigate to "/app"

  # ✅ Scenario สำหรับการ login ที่ไม่สำเร็จ
  @Negative
  Scenario Outline: Unsuccessful login attempts
    Given I visit the login page
    When I input username "<email>" and password "<password>"
    And I click on the login button
    Then the login should fail with "<error_message>"
    Examples:
      | email                      | password      | error_message                    |
      | invalid@example.com        | Password123! | Invalid email or password         |
      | john.doe@example.com       | WrongPass!   | Invalid email or password         |