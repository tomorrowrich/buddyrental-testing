@RegistrationLogin
Feature: User Login
  As a user
  I want to log in to the system
  So that I can use the buddy rental system

  # ✅ Ensure registration happens before login
  @Positive @UserRegistration
  Scenario: Successful customer registration
    Given the information is valid and not linked to an existing account
    When the customer registers to the system
    Then ensure an account is created

  # ✅ Login scenario now depends on successful registration
  @Positive @UserLogin
  Scenario: Successful customer login
    Given the login email and password is from a registered user
    And the customer is not logged in
    When the customer logs in to the system
    Then ensure the customer is logged in

  @Negative
  Scenario Outline: Unsuccessful login attempts
    Given the login email is "<email>"
    And the login password is "<password>"
    When the customer attempts to log in
    Then the login should fail with "<error_message>"

    Examples:
      | email                      | password      | error_message                      |
      | invalid@example.com        | Password123! | Invalid email or password         |
      | john.doe@example.com       | WrongPass!   | Invalid email or password         |
      |                            | Password123! | Email is required                 |
      | john.doe@example.com       |              | Password is required              |
      | notregistered@example.com  | Password123! | User does not exist               |
      | inactive@example.com       | Password123! | Account is not activated          |

  @EdgeCase
  Scenario Outline: Edge case login attempts
    Given the login email is "<email>"
    And the login password is "<password>"
    When the customer attempts to log in
    Then the login should fail with "<error_message>"

    Examples:
      | email                      | password           | error_message                      |
      | test@@example.com          | Password123!       | Invalid email format               |
      | john.doe@example.com       | short              | Password must be at least 8 chars  |
      | john.doe@example.com       | 12345678           | Password must contain letters      |
      | john.doe@example.com       | PASSWORD123!       | Password must contain lowercase    |
      | JOHN.DOE@EXAMPLE.COM       | Password123!       | Invalid email or password         |