Feature: Edit Personal Information
  As a user,
  I want to edit my personal information,
  So that I can update my details in the system when changes occur.

  Scenario: Successfully update personal information
    Given the user is logged in
    When the user submits the updated details
    Then ensure the information is validated and updated


  @UserProfilePicUpdate
  Scenario: User successfully updates profile picture
    Given the user is logged in
    When the user uploads a new profile picture
    Then ensure the picture is validated and updated