Feature: Operation Module - Creating Application

   Background:
   Given the user is logged into the Aapli demo79

  Scenario: Creating an application setting
 
  #  Then the user should successfully navigate to the home page

  #  When the user clicks on the Application tab
  # Then the "Application" page should open

  #  When the user clicks on the "+Add" icon
  #  And the user fills in the application settings

 #  Verify the components and drag and drop the components
  #  Then the user clicks on the Next button on the application settings page
 #  Then the user verifies the components of the form module
 #  And the user drags and drops all components one by one

 #  Additional verification step
 #  When the user click save and next on the form selection and naviagte to response work flow page
 #   Then the Basic and Advanced settings should be editable with the text "ABC"
 # Then a success message should be displayed confirming the form changes were saved 
 
  


  Scenario: form module 
    When the user clicks on the Application tab
    And the user is on the existing application page
    Then the user clicks the Form tab
    And User verifies and changes the settings of the Single Line Input 
  #  And User verifies and changes the settings of the Multi Line Input 
 #  And user verifies and changes the settings of the Number
  And user verifies and changes the settings of the Date
  And user verifies and changes the settings of the Date Range
  #  And user verifies and changes the settings of the Currency 
  #  And user verifies and changes the settings of the Fileuploades
  #  And user verifies and changes the settings of the Banner image
   # And user verifies and changes the settings of the Timeline
   # And user verifies and changes the settings of the Url
   And user verifies and changes the settings of the Dropdown
  #  And the user click the Single line input and verify the rightside setting or components
  #  And verifies that the Single Line Input component is reflected on the User page
  #  Then the user select the singline input and  should able to give Field label, Placeholder text, Help text, Tooltip and verify though should be appears in frontend of single line input as expected
  #  And saves the Application on form module
  
  