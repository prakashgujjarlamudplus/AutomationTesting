Feature: Admin Home Page

Scenario: User logs into the Ideabridge admin portal and verifies all tabs and widgets
  Given user enters the URL "https://admin.ideabridge.co/login"
  Then the Ideabridge logo should be displayed
  When user enters the valid email address "donotuse@plusinnovations.co"
  And user selects "enterprise.ideabridge.co" from the dropdown
  And user enters valid password "Ideabridge@123"
  And user checks the recaptcha checkbox
  And user clicks the continue button
  Then the Ideabridge logo should be displayed on the homepage 
  And user successfully logs into the homepage
  When user double clicks on "User Details"
Then User should be able to see the text "Users", "Suspended Users", "Administrator", "Administrator's Email" are displayed

  Then the "Operations" and "Configurations" tabs should be displayed on the homepage
  
  When user clicks on the "Configurations" tab
  Then the following widgets should be displayed:
    | Company Profile    |
    | Site Design        |
    | Billing            |
    | Currencies         |
    | SSO Settings       |
    | User Properties    |
    | Roles              |
    | User Import Mapping|
    | Application Settings|
  
 When user clicks on the "Operations" tab
  Then the following widgets should be displayed:
    | Users             |
    | Applications      |
    | Menu              |
    | Work Flow         |
    | Evaluation Matrix |
    | Notification      |
    | Import Export     |
    | Failed Submissions|
    | Pages             |

  