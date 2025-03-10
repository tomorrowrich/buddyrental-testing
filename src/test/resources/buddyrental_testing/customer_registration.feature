#@CusReg
#Feature: Customer Registration

  #@ValidReg
  #Scenario: Registering with valid information
    #Given the information is valid
    #When the customer registers to the system for CusReg
    #Then ensure a customer account is created for CusReg
    #And ensure information is linked to this account
    #And the system requests the user to fill in personal information

  #@FetchUser
  #Scenario: Fetch registered user
    #Given a user is registered
    #When the client requests the user with ID
    #Then the system should return user details
    #And the response should have status 200
