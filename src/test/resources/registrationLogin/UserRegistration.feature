@RegistrationLogin
Feature: Register as a Customer
  As a user
  I want to register as a customer
  So that I can rent a buddy

  @Positive @UserRegistration
  Scenario: Successful customer registration
    Given the information is valid and not linked to an existing account
    When the customer registers to the system
    Then ensure an account is created
    And ensure information is linked to this account

  @Negative @UserRegistration
  Scenario Outline: Unsuccessful registration attempts
    Given In case <error_condition>
    When the customer registers to the system
    Then ensure registration fails with "<error_message>"

    Examples:
      | error_condition                          | error_message                                    |
      | the information is already linked to an existing account | Email is already registered       |
      | the email format is invalid              | Invalid email format                             |
      | the password is too weak                 | Password does not meet security requirements     |