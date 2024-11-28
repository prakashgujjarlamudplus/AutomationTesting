  Feature: User Management
  As an admin user
  I want to add a new user and manage their roles and privileges
  So that I can successfully create and manage user accounts

  Background:
    Given the user is logged into the application

  Scenario: Add a new user with specific details and settings
 #    Given the user clicks on the "Users" tab
 #   When the user navigates to the Users page and verify the visible text

  #  When the user clicks the Add icon
   
  #  When the user enters "Prakash" in the "FirstName" field
  #  And the user enters "Gujjarlamudi" in the "Last Name" field
  #  And the user enters "prakash.gujjarlamudi@plusinnovations.co" in the "Email Address" field
  #  And the user enters "prakash" in the "User Name" field

   

  #  When the user selects the checkbox with id "mat-checkbox-24-input" as "Level 1 Approver"
  #  And the user selects "Male" from the gender dropdown
  #  And the user enters "Hyderabad" in the "City" field
  #  And the user selects "Software Development" from the "Department" dropdown
  #  And the user selects "Ideabridge" from the "Category" dropdown
  #  And the user selects "IT" from the "SubCategory" dropdown
    
  #   Then the total number of checkboxes should be printed
  #  And the label text of each checkbox should be displayed

   # When the user clicks the "Save Changes" button
  # Then the user should be successfully added
  Scenario: Application setting 
    Given user click on the Applications tab
    When the user navigates to the Applications page and the text " Applications " should be visible
    Then the user checks the enable and disable options, duplicates, and deletes the application
    # as per the test cases  clicked add+ icon and create the appliaction but the icone is disable so i can continue the creation flow of application for doing edit on the existing appliaction creating application
    And user click o the editicon on Best Practices appliaction
    
    Then the user checks the buttons on the right side down of the page
   Then the user clicks on the Cancel button and verifies whether the application is closed
   And user click o the editicon on Best Practices appliaction
    Then the  verify the all following tabs should be visible after clicking edit
    
    Then the following content should be visible on the right side of the page:
    | Preview                            |
    | Application Card                   |
    | Select Application Color           |
    | Or Choose Color                    |
    | Assign application icon            |
    | Select Application Color           |
    
    Then the user verifies that the application name, description, category, type, workflow, and response workflow text are visible on the right side of the application settings page.

    
    # In right side content of the application setting page
    # Verify selecting the "select eg..." icon and checking its visibility in the Application Settings page
    Given user clicks on the Assign application icon button
    When user selects the work icon from the list
    Then the work icon should be visible in the selected area
    Then user checks "work" icon should be reflected on application cards
    
    Then user click on the Select Application Color as blue
    Then user checks if the background color is the same as the selected blue is reflected on application cards
     
    # fill the required feilds on application settings 
    Given the user clears the existing text in the Application Name 
    And the mouse is placed on the tooltip of the Application Name field then the required text in the tooltip should be visible
    When the user enters more than 40 characters into the Application Name field it should be verified whether the text is accepted or not
    When the user enters the application name
    When the user clears the existing text in the Description feild and the mouse is placed on the tooltip of the description field then the required text in the tooltip should be visible
    When the user enters more than 500 characters into the Description field it should be verified whether the text is accepted or not
    Then the user enters the description as "employee onboarding"
    Then the mouse is placed on the tooltip of the Category Dropdown then the required text in the tooltip should be visible
    Then the mouse is placed on the tooltip of the Type dropdown then the required text in the tooltip should be visible 
    Then the mouse is placed on the tooltipof the workflow the the required text in the tooltip should be visible
    Then the user clicks on the Response has Workflow toggle then the user should see either "Yes" or "No" text visible and check the all the visible tabs
    Then user click the   Cancel , Preview , Save  and Next:
    
