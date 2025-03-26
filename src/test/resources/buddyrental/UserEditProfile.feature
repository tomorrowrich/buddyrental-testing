@edit_profile
Feature: Edit Personal Information
  As a user,
  I want to edit my personal information,
  So that I can update my details in the system when changes occur.

  Scenario: Successfully update personal information
    Given the user logged in
    When the user navigate to the profile edit page
    And the user update my first name to "Jane"
    Then the user should see my updated name as "Jane Doe"
