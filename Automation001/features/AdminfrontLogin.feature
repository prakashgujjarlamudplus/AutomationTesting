Feature: Login Functionality

  
  Scenario:Verify the behavior of the "Continue" button with valid and invalid inputs
    Given I user navigate to the URL "https://enterprise.ideabridge.co/login"
    Then user click the "I agree" button
    When I  user enter the valid email address "donotuse@plusinnovations.co" without entering the password
    Then The "Continue" button should be disabled
    When I clean the email address and password fields

    When I user enter the valid password "Ideabridge@123" without entering the email address
    Then The "Continue" button should be disabled
    When I clean the email address and password fields

    When I user enter the valid email address "donotuse@plusinnovations.co" and valid password "Ideabridge@123"
    Then The "Continue" button should be enabled
     When I clean the email address and password fields

    When I user enter an invalid email address "prakash"
    Then An error message "Please enter a valid email" should be displayed
     When I clean the email address and password fields

    When I user enter a valid email "donotuse@plusinnovations.co" and a password shorter than 8 characters "abcdef"
    Then An error message "Password needs to be minimum 8 characters" should be displayed
   When I clean the email address and password fields

    When I user enter a valid email "donotuse@plusinnovations.co" and a password longer than 32 characters "verylongpassword1234567890verylongpassword"
    Then An error message "password needs to be maximum 32 characters" should be displayed
     When I clean the email address and password fields

    # Verify login attempts and error message

    When I user enter the invalid email address "donotuse@plusinnovations.co" and invalid password "Idebridge123"
    And I click the continue button
    When I user should see an error message for invalid credentials

     When I clean the email address and password fields

    When I user enter the valid email address "donotuse@plusinnovations.co" and invalid password "Ideabidge123"
    And I click the continue button
    When I user should see an error message for invalid credentials

     When I clean the email address and password fields

    When I user enter the valid email address "donotuse@plusinnovations.co" and invalid password "Ideabridg12@3"
    And I click the continue button
    When I user should see an error message for invalid credentials
   
    When I clean the email address and password fields

   When I user enter the valid email address "donotuse@plusinnovations.co" and invalid password "Ideabridge1@23"
   And I click the continue button
   When I user should see an error message for invalid credentials

   When I clean the email address and password fields

    When I user enter the valid email address "donotuse@plusinnovations.co" and invalid password "Ideabridge123"
    And I click the continue button
    When I user should see an error message for invalid credentials


    #Successful login to the Ideabridge home page
    When I user enter the valid email address "donotuse@plusinnovations.co" and valid password "Ideabridge@123"
    And I click the continue button
    Then The "ideabridge" image should be displayed on the home page
