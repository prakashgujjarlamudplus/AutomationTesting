Feature: Forgot Password & login Functionality

  # Forgot Password Scenarios
  Scenario: Validate Forgot password functionality and verify Ideabridge logo
    Given I navigate to the URL "https://enterprise.ideabridge.co/login"
    When I check if the "We use cookies" text is displayed
    And I check if the buttons "I agree", "I decline", and "Change my preferences" are enabled or disabled
    Then I click the "Change my preferences" button
    And I verify that the "Your privacy is important to us" text is visible 
    Then I click the close button
    Then I click the "I agree" button
    Then I should see the "IdeabridgeLogo" logo displayed
    When I click the "Forgot Password?" link
    Then the "Forgot Your Password?" page should be displayed
    Given I am on the "Forgot Your Password?" page
    When I do not enter an email address in the email field
    Then check that the "Reset Password" button is disabled
    When I enter an invalid email as "prakash"
    Then check that the "Reset Password" button is disabled
    When I clear the email field 
    When I enter an invalid email id "level@mailinator.com"
    Then an error message "This email does not exist." should be displayed
    When I clear the email field 
    When I enter "abcdef" in the email field
    Then an error message "Email must be a valid email address" should be displayed
    When I clear the email field 
    When I enter a valid email "level1@mailinator.com"
    And I do not check the reCAPTCHA box
    Then check that the "Reset Password" button is disabled
   # When I check the reCAPTCHA box
    #Then the "Reset Password" button should be enabled
     And I click the " Back to sign in " button
    Then I should see the "IdeabridgeLogo" logo displayed
    