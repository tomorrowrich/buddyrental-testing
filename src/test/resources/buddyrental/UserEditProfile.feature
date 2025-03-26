@edit_profile
Feature: Edit Personal Information
  As a user,
  I want to edit my personal information,
  So that I can update my details in the system when changes occur.

  Scenario: Successfully update personal information
    Given I am logged in
    When I navigate to the profile edit page
    And I update my first name to "<first_name>"
    And I update my last name to "<last_name>"
    And I update my nickname to "<nickname>"
    And I update my phone number to "<phone_number>"
    And I update my address to "<address>"
    And I update my city to "<city>"
    And I update my zip code to "<zip_code>"
    Then I should see my updated name as "<name>"
      Examples:
        | first_name | last_name | nickname | phone_number | address              | city       | zip_code | name |
        | Jane       | Doe       | JD       | 1234567890 | 123 Main St, Apt 4B  | New York   | 10001    | Jane Doe |
        | Mary       | Smith     | M         | 9876543210 | 456 Elm St, Apt 12A  | Los Angeles| 90001    | Mary Smith |
        | Emma       | Johnson   | Em        | 5551234567 | 789 Oak St, Apt 8B   | Chicago    | 60601    | Emma Johnson |
    
  Scenario: Fail to update personal information with invalid input
    Given I am logged in
    When I navigate to the profile edit page
    And I update my first name to "<first_name>"
    And I update my last name to "<last_name>"
    And I update my nickname to "<nickname>"
    And I update my phone number to "<phone_number>"
    And I update my address to "<address>"
    And I update my city to "<city>"
    And I update my zip code to "<zip_code>"
    Then I should see an "<error_message>" for the invalid input
      Examples:
        | first_name | last_name | nickname    | phone_number   | address              | city       | zip_code | error_message                                             |
        | 1234       | Doe       | JD123       | abcdefg        | 123 Main St, Apt 4B  | New York   | 10001    | First Name is required and can only contain letters.      |
        | Mary       | Smith@123 | M!         | 9876543210     | 456 Elm St, Apt 12A  | Los Angeles| 90001    | Last Name is required and can only contain letters. |
        | Emma       | Johnson   | Em@#       | 5551234567     | 789 Oak St, Apt 8B   | Chicago    | 60601    | Display Name is required and can only contain letters and numbers, no special characters allowed. |
        | John       | Doe       | JD         | 123abc4567     | 1234 Oak St          | San Francisco | abc12 | Phone Number is required and can only contain numbers.    |
        | Alice      | Cooper    | Al         | 9876543210     | 123 Main St, Apt 5   | Boston     | xyz456   | Postal Code is required and can only contain numbers.    |