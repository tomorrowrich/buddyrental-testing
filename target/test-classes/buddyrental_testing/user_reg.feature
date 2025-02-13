Feature: Register as a Customer
  As a user
  I want to register as a customer
  So that I can rent a buddy

  @UserReg
  Scenario: Successful customer registration
    Given the information is valid and not linked to an existing account
    When the customer registers to the system
    Then ensure an account is created
    And ensure information is linked to this account
