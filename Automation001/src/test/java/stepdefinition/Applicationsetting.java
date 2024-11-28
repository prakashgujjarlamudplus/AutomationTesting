package stepdefinition;


import java.time.Duration;

import java.util.List;
import java.util.NoSuchElementException;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;

public class Applicationsetting {
	WebDriver driver;
	
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(200));
     
    @Given("the user is logged into the application")
	public void the_user_is_logged_into_the_application() throws Exception {
		
		// Set up WebDriver before running all scenarios
   	 System.setProperty("webdriver.edge.driver", "C:\\Users\\HP\\eclipse-workspace\\Automation001\\Driver\\msedgedriver.exe");
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.manage().window().maximize();
	    // Open the login page
	    driver.get("https://admin.ideabridge.co/login");

	    // Find and fill in the email field
	    WebElement emailField =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	    emailField.sendKeys("donotuse@plusinnovations.co"); 

	    WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mat-select-value-1']")));

        // Click the dropdown to open it
        dropdown.click();
        Thread.sleep(1000);
        // Locate the option within the dropdown and select it
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='enterprise.ideabridge.co']")));
        optionToSelect.click();
        
        System.out.println("Selected the option: enterprise.ideabridge.co");
        
	    // Find and fill in the password field
	    WebElement passwordField =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))); 
	    passwordField.sendKeys("Ideabridge@123"); 
        
	 // go the iframes
    	WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@title='reCAPTCHA']")));
        driver.switchTo().frame(iframe);
        
        // Wait for the reCAPTCHA checkbox to be clickable
        WebElement recaptchaCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='recaptcha-anchor']")));
        // Click the reCAPTCHA checkbox
        recaptchaCheckbox.click();
        driver.switchTo().defaultContent();
	    
	    
	    // Click the continue button
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        
        // Click the continue button
        continueButton.click();

	    // Wait for the home page to load and verify the user is logged in
        try {
        WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='logo text-center']/child::img")));
        // Verify that the logo is displayed
        Assert.assertTrue("Ideabridge logo is not displayed on the homepage.", logoElement.isDisplayed());
        
        System.out.println("Ideabridge logo is displayed on the homepage.");
    } catch (Exception e) {
        // Handle exceptions (e.g., element not found)
        Assert.fail("Failed to verify Ideabridge logo on the homepage: " + e.getMessage());
    }
          
        
	}


	// // Assuming driver is already initialized in the LoginStepDefinitions
  //  private WebDriver driver = LoginStepDefinitions.getDriver(); // or however you access it

    @When("the user clicks on the {string} tab")
    public void the_user_clicks_on_the_tab(String tabName) {
        // Use the tabName parameter to locate the appropriate tab
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Users']"))); 
        tab.click();
    }

    @When("the user navigates to the Users page and verify the visible text")
    public void the_user_navigates_to_the_users_page_and_text_should_be_visible() {
    	 // Check the URL to ensure it matches the expected Users page URL
        String expectedUrl = "https://admin.ideabridge.co/admin/operations/users";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("The URL of the Users page is not as expected.", expectedUrl, actualUrl);

        //  check if the text is visible on the page
        WebElement textElement = driver.findElement(By.xpath("//p[text()='Add users and manage their roles and privileges']"));
        assertTrue(textElement.isDisplayed(), "The expected text is not visible.");
    }

   

	@When("the user clicks the Add icon")
	public void the_user_clicks_the_icon() throws Exception {
		 try {
	            // Wait until the "Add" icon is clickable
	            WebElement addIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add_circle_outline']")));

	            // Use JavaScriptExecutor to click the "Add" icon
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].click();", addIcon);
	            System.out.println("");

	            // Wait for the result of the click action (example: new element visibility)
	            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='right-container overflow-hidden']//h2/text()"))); 
	            
	            // Assert the expected condition
	            String expectedText = " Add User "; 
	            String actualText = resultElement.getText();
	            if (actualText.equals(expectedText)) {
	                System.out.println("Assertion Passed: " + actualText);
	            } else {
	                System.out.println("Assertion Failed: Expected - " + expectedText + ", Actual - " + actualText);
	                System.out.println("user navigate to 'ADD USERS' page");
	            }
	        } catch (TimeoutException e) {
	            System.out.println("Element not found within the wait time: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("An unexpected error occurred: " + e.getMessage());
	}
	}


	


	 @When("the user enters {string} in the {string} field")
	    public void the_user_enters_in_the_field(String value, String fieldName) {
	        WebElement field;

	        // Normalize field names to lowercase to avoid case sensitivity issues
	        switch (fieldName.toLowerCase().trim()) {
            case "first name":
                field = driver.findElement(By.id("userFirstName")); 
                break;
            case "last name":
                field = driver.findElement(By.id("userLastName")); 
                break;
            case "email address":
                field = driver.findElement(By.id("userEmail")); 
                break;
            case "user name":
                field = driver.findElement(By.id("UserName")); 
                break;
            case "city":
                field = driver.findElement(By.id("prop-City")); 
                break;
            
            default:
                throw new IllegalArgumentException("Field not recognized: " + fieldName);
        }
	        // Clear the field before entering the value
	        field.clear();
	        field.sendKeys(value);
	    }


	@Then("the total number of checkboxes should be printed")
	public void the_total_number_of_checkboxes_should_be_printed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the label text of each checkbox should be displayed")
	public void the_label_text_of_each_checkbox_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects the checkbox with id {string} as {string}")
	public void the_user_selects_the_checkbox_with_id_as(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects {string} from the gender dropdown")
	public void the_user_selects_from_the_gender_dropdown(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user selects {string} from the {string} dropdown")
	public void the_user_selects_from_the_dropdown(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@And("the user clicks the {string} button")
	public void the_user_clicks_the_button(String string) {
	
	}
	@Then("the user should be successfully added")
	public void the_user_should_be_successfully_added() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
	
	// appliaction------
	
	@Given("user click on the Applications tab")
	public void user_click_on_the_tab() {
	try {
		// Use click the application
        WebElement application = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Applications']"))); 
       
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", application);
    //    jsExecutor.executeScript("arguments[0].click();", application);
        
        
	}catch(Exception e) {
		System.out.println(e);
	}
	}

	@When("the user navigates to the Applications page and the text {string} should be visible")
	public void the_user_navigates_to_the_applications_page_and_the_text_should_be_visible(String string) {
		 // Check the URL to ensure it matches the expected Users page URL
        String expectedUrl1 = "https://admin.ideabridge.co/admin/operations/application-management";
        String actualUrl1 = driver.getCurrentUrl();
        Assert.assertEquals("The URL of the Users page is not as expected.", expectedUrl1, actualUrl1);

        //  check if the text is visible on the page
        WebElement text = driver.findElement(By.xpath("//h2[text()='Applications']"));
        assertTrue(text.isDisplayed(), "The expected text is not visible.");
        System.out.println("....application page is successfully open......");
    }

	
	@Then("the user checks the enable and disable options, duplicates, and deletes the application")
	public void the_user_checks_the_enable_and_disable_options_duplicates_and_deletes_the_application() throws Exception {
	    try {
	        // Locate and interact with the 'DMAIC Project' element
	        WebElement dmaicProjectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='DMAIC Project']")));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(dmaicProjectElement).perform();

	        // Disable the application
	        try {
	            // Wait until the 'disable' element is clickable and click it
	            WebElement disableElement = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[text()='DMAIC Project']/following::img[@title='Click to disable']")));
	            disableElement.click();
	            System.out.println("Clicked on the 'Disable' icon for DMAIC Project.");

	            // Wait for the confirmation message to appear
	            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='content-container']/div[@class='message']")));

	            // Get the text of the confirmation message
	            String actualMessage = messageElement.getText();
	            System.out.println("Confirmation Message: " + actualMessage);

	            // Define the expected confirmation message
	            String expectedMessage = "DMAIC Project disabled successfully";

	            // Assert that the actual message matches the expected message
	            Assert.assertEquals("Disable action did not complete successfully.", expectedMessage, actualMessage);

	            // Optional: print a success message if assertion passes
	            System.out.println("DMAIC Project disabled successfully as per the confirmation message.");
	        } catch (Exception e) {
	            // Handle exceptions and log error details
	            System.err.println("An error occurred while trying to disable the DMAIC Project: " + e.getMessage());
	            e.printStackTrace();
	        }

Thread.sleep(3000);
	        // Re-enable the application
	        try {
	            // Wait until the 'enable' element is clickable and click it
	            WebElement enableElement = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[text()='DMAIC Project']/following::img[@title='Click to enable']")));
	            enableElement.click();
	            System.out.println("Clicked on the 'Enable' icon for DMAIC Project.");

	            // Wait for the confirmation message to appear
	            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='content-container']/div[@class='message']")));

	            // Get the text of the confirmation message
	            String actualMessage = messageElement.getText();
	            System.out.println("Confirmation Message: " + actualMessage);

	            // Define the expected confirmation message
	            String expectedMessage = "DMAIC Project enabled successfully";

	            // Assert that the actual message matches the expected message
	            Assert.assertEquals("Enable action did not complete successfully.", expectedMessage, actualMessage);

	            // Optional: print a success message if assertion passes
	            System.out.println("DMAIC Project enabled successfully as per the confirmation message.");
	        } catch (Exception e) {
	            // Handle exceptions and log error details
	            System.err.println("An error occurred while trying to enable the DMAIC Project: " + e.getMessage());
	            e.printStackTrace();
	        }

	        Thread.sleep(3000);
	        // Duplicate the application
	        try {
	            // Wait for the 'Duplicate' button to be clickable
	            WebElement duplicateButton = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[text()='DMAIC Project']/following::img[@title='Duplicate']")));
	            
	            // Click the 'Duplicate' button for DMAIC Project
	            duplicateButton.click();
	            System.out.println("Clicked on the 'Duplicate' icon for DMAIC Project.");

	            // Wait for the modal title to appear and check if it is displayed
	            WebElement modalTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h3[@class='modal-title my-0']/following::div[contains(@class,'def--modal-inputs mt')]//p[@class='d-inline-block']")));
	            String modalTitleText = modalTitleElement.getText();
	            System.out.println("Modal Title Text: " + modalTitleText);

	            // Wait for the submit button and click it
	            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[@class='mx-3 button-blue']")));
	            submitButton.click();
	            System.out.println("Clicked on the submit button for duplication.");

	            // Wait for the success message to appear
	            WebElement successMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='content-container']/div[@class='message']")));
	            String actualMessage = successMessageElement.getText();
	            System.out.println("Success Message: " + actualMessage);

	            // Define the expected success message
	            String expectedMessage = "DMAIC Project cloned successfully";

	            // Assert that the actual message matches the expected message
	            Assert.assertEquals("Duplication action did not complete successfully.", expectedMessage, actualMessage);
	            System.out.println("DMAIC Project cloned successfully as per the confirmation message.");

	        } catch (Exception e) {
	            // Handle exceptions and log error details
	            System.err.println("An error occurred while trying to duplicate the DMAIC Project: " + e.getMessage());
	            e.printStackTrace();
	        }

	        Thread.sleep(3000);
	        // Delete the application
	        try {
	            // Locate the "Copy-DMAIC Project" element and perform mouse hover
	            WebElement copyDmaicProjectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(text(),'Copy-DMAIC Project')]")));
	            Actions actions1 = new Actions(driver);
	            actions1.moveToElement(copyDmaicProjectElement).perform();
	            System.out.println("Mouse hovered over 'Copy-DMAIC Project' element.");

	            // Wait for the 'Delete' icon to be clickable and click it
	            WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[contains(text(),'Copy-DMAIC Project')]/following::img[@title='Delete']")));
	            deleteIcon.click();
	            System.out.println("Clicked on the 'Delete' icon for Copy-DMAIC Project.");

	            // Wait for the confirmation message to appear and check if it is displayed
	            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//p[@class='ng-star-inserted']")));
	            String confirmationMessageText = confirmationMessage.getText();
	            System.out.println("Confirmation Message: " + confirmationMessageText);

	            // Wait for the "Delete" button to be clickable and click it
	            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[@class='mx-3 button-blue']")));
	            deleteButton.click();
	            System.out.println("Clicked on the 'Delete' button.");

	            // Wait for the success message to appear
	            WebElement successMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='content-container']/div[@class='message']")));
	            String successMessageText = successMessageElement.getText();
	            System.out.println("Success Message: " + successMessageText);

	            // Verify if the success message contains keywords indicating success
	            if (successMessageText.contains("deleted successfully") || successMessageText.toLowerCase().contains("deleted")) {
	                System.out.println("Deletion was successful. Message: " + successMessageText);
	            } else {
	                System.out.println("Unexpected success message: " + successMessageText);
	            }

	        } catch (Exception e) {
	            // Handle exceptions and log error details
	            System.err.println("An error occurred while trying to delete the Copy-DMAIC Project: " + e.getMessage());
	            e.printStackTrace();
	        }

	    } catch (Exception e) {
	        System.err.println("An error occurred: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	

	
	
	
	
	@When("user click o the editicon on Best Practices appliaction")
	public void user_click_o_the_editicon_on_best_practices_appliaction() {
		try {
			Thread.sleep(2000);
		    // Define the element you want to hover over
		   WebElement elementToHover = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='DMAIC Project']/following::div[text()='Active'])[1]")));

		    // Create an Actions object for mouse movements
		    Actions actions = new Actions(driver);

		    // Hover over the element
		   actions.moveToElement(elementToHover).perform();
		   System.out.println("Hovered over DMAIC Project successfully!");

		    // Wait for the "Edit" icon to be visible and clickable after hovering
		    WebElement clickEditIcon = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("(//div[text()='DMAIC Project']/following::img[@title='Edit'])[1]")));
		    actions.moveToElement(clickEditIcon).perform();
		    // Use JavaScriptExecutor to click the element
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    jsExecutor.executeScript("arguments[0].click();", clickEditIcon);
		    System.out.println("Edit icon clicked successfully using JavaScript!");

		    // Verify that the page navigates to the expected URL
		    String expectedUrl1 = "https://admin.ideabridge.co/form-builder/8870fe28-7f68-41d7-b604-c4e31923f412";
		    String actualUrl1 = driver.getCurrentUrl();
		    Assert.assertEquals("The URL of the Users page is not as expected.", expectedUrl1, actualUrl1);
		    System.out.println("Successfully entered the application settings page.");

		} catch (Exception e) {
		    // Log the error with stack trace for debugging
		    System.out.println("Error: " + e.getMessage());
		    e.printStackTrace();
		}

   
	}
	
	@Then("the  verify the all following tabs should be visible after clicking edit")
	public void the_verify_the_all_following_tabs_should_be_visible_after_clicking_edit() {
		try {
           
            // Wait until elements are present
            List<WebElement> tabLabels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='mat-tab-label-content']")
            ));

            // Print the count of elements found
            System.out.println("Number of Tab Labels Found: " + tabLabels.size());

            // Loop through each element
            for (WebElement tab : tabLabels) {
                // Wait until each tab label is visible
                boolean isVisible = wait.until(ExpectedConditions.visibilityOf(tab)).isDisplayed();

                // Get and print the text of the tab
                String tabText = tab.getText();
                System.out.println("Tab Text: " + tabText);

                // Check visibility and assert
                System.out.println("Is Visible: " + isVisible);

                // Assertion to ensure tab is visible
                if (isVisible) {
                    System.out.println("Assertion Passed: Tab is visible.");
                } else {
                    System.out.println("Assertion Failed: Tab is not visible.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
	}
	}
	
	
	@Then("the following content should be visible on the right side of the page:")
	 public void verifyRightSideContent(List<String> contentList) {
		    for (String content : contentList) {
		     try {
		    	// Wait for the content to be visible on the page
		    
		        WebElement contentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='" + content + "'] | //h4[text()='" + content + "']")));
		        
		     // Assert that the content is displayed
		        
		        Assert.assertTrue(content + " text is not displayed.", contentElement.isDisplayed());
             System.out.println(content + " text is displayed.");
             
                
		     } catch (Exception e) {
	                // Print an error message if the widget is not found or not visible
	                System.err.println("Error: Could not find or display the tabs " + content + ". " + e.getMessage());
	            }
	        }
		        
		       
		}
	@Then("the user verifies that the application name, description, category, type, workflow, and response workflow text are visible on the right side of the application settings page.")
	public void verifyApplicationSettingsText() {
		System.out.println("the following content should be visible o the left side of the page");
	    // List of XPaths for the elements to verify
	    String[] xpaths = {
	        "//div[@class='required']", // Application Name
	        "//div[text()=' Description ']", // Description
	        "(//span[@class='required'])[1]", // Category
	        "(//span[@class='required'])[2]", // Type
	        "(//span[@class='required'])[3]", // Workflow
	        "//label[text()='Response has Workflow']" // Response has Workflow
	    };

	    // Loop through each XPath and print the text
	    for (String xpath : xpaths) {
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	        System.out.println(element.getText());
	    }
	}
	 //In right side content of the application setting page
	// Given: Click on the Assign application icon button
	 @Given("user clicks on the Assign application icon button")
	 public void user_clicks_on_the_assign_application_icon_button() throws Exception {
		 try {
		        // Create instance of JavascriptExecutor
		        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		        
		        // Wait for the assign icon button to be visible
		        WebElement assignIconButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='icon-selector']/child::span")));
		        
		        // Click the button using JavaScript
		        jsExecutor.executeScript("arguments[0].click();", assignIconButton);
		        
		        // Log successful click
		        System.out.println("Assign application icon Button clicked");
		        
		    } catch (Exception e) {
		        // Print stack trace for debugging
		        e.printStackTrace();
		        
		        // Throw a custom exception or rethrow the caught exception if necessary
		        throw new Exception("Failed to click on the Assign application icon button: " + e.getMessage());
		    }
	 }

	 // When: Select the 'work' icon from the list
	 @When("user selects the work icon from the list")
	 public void user_selects_the_icon_from_the_list() {
		  try {
	            // Wait for the element to be clickable
	            WebElement iconOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='work']")));
	            
	            // Print element's title for debugging
	            System.out.println("Icon Option found with title: " + iconOption.getAttribute("title"));
	            
	            // Click the icon option
	            iconOption.click();
	            System.out.println("Icon option with title 'work' clicked successfully.");

	            
	        } catch (Exception e) {
	            // Print stack trace for debugging
	            e.printStackTrace();
	            
	            // Throw a custom exception with a relevant message
	            throw new RuntimeException("Failed to click on the icon option: " + e.getMessage());
	        }
	 }

	 // Then: Verify if the 'work' icon is visible after selection
	 @Then("the work icon should be visible in the selected area")
	 public void the_icon_should_be_visible_in_the_selected_area() {
	     String iconName = "Work"; // Set the icon name, or pass it dynamically if needed
	     
	     try {
	         // Wait until the icon is visible on the page
	         WebElement selectedIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                 By.xpath("//div[@class='icon-selector']/child::span[@title='work' and contains(@class, 'material-icons-outlined')]")));

	         // Assertion to check if the icon is displayed
	         assert selectedIcon.isDisplayed() : iconName + " icon is not visible after selection!";

	         // Print confirmation that the icon is visible
	         System.out.println(iconName + " icon is visible after selection.");
	     } catch (AssertionError ae) {
	         // Catch and print AssertionError if the icon is not visible
	         System.out.println("Assertion failed: " + ae.getMessage());
	         // You can also re-throw the error or handle it as per your requirement
	         throw ae;
	     } catch (Exception e) {
	         // Catch and print any other exceptions
	         System.out.println("An error occurred while checking icon visibility: " + e.getMessage());
	         e.printStackTrace();
	     }
	 }


	 @Then("user checks {string} icon should be reflected on application cards")
	 public void verifyIconReflectedOnApplicationCards(String iconName) throws Exception {
	     try {
	         // Locate the selected icon in the application cards
	         WebElement selectedIconElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='" + iconName + "']")));
	         assert selectedIconElement.isDisplayed() : iconName + " icon is not visible on application cards!";

	         // Locate the original icon (the one selected from the list)
	         WebElement originalIconElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='" + iconName + "']")));

	         // Check if both icons are displayed
	         assert originalIconElement.isDisplayed() : iconName + " original icon is not visible!";

	         // Get the text of both icons
	         String selectedIconText = selectedIconElement.getText();
	         String originalIconText = originalIconElement.getText();

	         // Debugging: Print the texts for comparison
	         System.out.println("Selected Icon Text: " + selectedIconText);
	         System.out.println("Original Icon Text: " + originalIconText);

	         // Assert that both texts are the same
	         assert selectedIconText.equals(originalIconText) : iconName + " icon text is not the same on application cards, reflection unsuccessful.";
	         
	         System.out.println(iconName + " icon is successfully reflected on application cards.");
	     } catch (NoSuchElementException e) {
	         System.err.println("An error occurred: " + e.getMessage());
	     } catch (AssertionError e) {
	         System.err.println("Assertion failed: " + e.getMessage());
	     } catch (Exception e) {
	         System.err.println("An unexpected error occurred: " + e.getMessage());
	     }
	     Thread.sleep(1000);
	 }



	    
	 @Then("user click on the Select Application Color as blue")
	 public void user_click_on_the_select_application_color_as_blue() {
		 try {
		        // Wait until the blue color element is visible
		        WebElement blueColorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//div[@style='background-color: rgb(12, 0, 152);']")));

		        // Assertion to verify the blue color element is displayed before clicking
		        assert blueColorElement.isDisplayed() : "Blue application color element is not visible!";
		        
		        // Click the blue color element
		        blueColorElement.click();

		        // Print confirmation that the blue color was selected
		        System.out.println("Selected application color as blue.");
		        
		    } catch (AssertionError ae) {
		        // Catch and print AssertionError if the element is not visible
		        System.out.println("Assertion failed: " + ae.getMessage());
		        throw ae; // Re-throw the AssertionError if you want to fail the test
		    } catch (Exception e) {
		        // Catch and print any other exceptions
		        System.out.println("An error occurred while selecting the blue application color: " + e.getMessage());
		        e.printStackTrace();
		    }
	    }
	 @Then("user checks if the background color is the same as the selected blue is reflected on application cards")
	 public void user_checks_if_the_background_color_is_the_same_as_the_selected_blue_is_reflected_on_application_cards() {
	     try {
	         // Locate the element where the background color is applied
	         WebElement backgroundColorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                 By.xpath("//div[@style='background-color: rgb(12, 0, 152);']")
	         ));

	         // Use JavaScript to get the computed background color of the element
	         JavascriptExecutor js = (JavascriptExecutor) driver;
	         String actualBackgroundColor = (String) js.executeScript(
	                 "return window.getComputedStyle(arguments[0]).getPropertyValue('background-color');",
	                 backgroundColorElement
	         );

	         // The expected blue color in the same RGB format
	         String expectedBlueColor = "rgb(12, 0, 152)";  // Ensure this matches the selected color

	         // Assertion to verify the actual background color matches the expected blue color
	         assert actualBackgroundColor.equals(expectedBlueColor) : 
	             "Background color mismatch! Expected: " + expectedBlueColor + ", but found: " + actualBackgroundColor;

	         // Print confirmation that the background color is reflected
	         System.out.println("The background color is successfully reflected on application cards.");

	     } catch (AssertionError ae) {
	         // Catch and print AssertionError if the colors don't match
	         System.err.println("Assertion failed: " + ae.getMessage());
	         // You can rethrow the AssertionError if you want to fail the test
	         throw ae;
	     } catch (Exception e) {
	         // Catch and print any other exceptions that occur
	         System.err.println("An error occurred while checking the background color: " + e.getMessage());
	         e.printStackTrace();
	     }
	 }




	 
	 
	 
	 @Given("the user clears the existing text in the Application Name")
	 public void the_user_clears_the_existing_text_in_the_Application_feild() {
		    // Step 1: Locate the "Application Name" field
		    WebElement applicationNameField = driver.findElement(By.xpath("//div[text()=' Application Name ']/following::input[@name='Name']"));
		    
		    // Step 2: Clear the existing text
		    applicationNameField.clear();
		    
		   /* // Step 3: Wait for the error message element to be present
		    WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Application Name is required']")));
		    
		    // Step 4: Wait for the error message to be visible
		    wait.until(ExpectedConditions.visibilityOf(errorMessage));
		    
		    // Step 5: Assert that the error message is displayed
		    assert errorMessage.isDisplayed() : "Error message: Application Name is required is not visible.";
		    
		    // Print confirmation
		    System.out.println("Error message: Application Name is required is visible after clearing the Application Name field."); */
		}
	 

	 @Given("the mouse is placed on the tooltip of the Application Name field then the required text in the tooltip should be visible")
	 public void the_mouse_is_placed_on_the_tooltip_of_the_application_name_field_then_the_required_text_in_the_tooltip_should_be_visible() {
		  try {
		        // Step 1: Locate the tooltip element
		        WebElement tooltipElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//div[text()=' Application Name ']/following-sibling::div[@class='custom-tooltip-container']")
		        ));

		        // Step 2: Hover over the tooltip
		        Actions action = new Actions(driver);
		        action.moveToElement(tooltipElement).perform();
		        System.out.println("Hovered over the tooltip for Application Name.");

		        // Step 3: Locate the tooltip text
		        WebElement tooltipTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//div[text()=' Application Name ']/following-sibling::div/child::div/p[@class='mb-0']")
		        ));

		        // Print the visible tooltip text
		        String tooltipText = tooltipTextElement.getText();
		        System.out.println("Visible tooltip text: " + tooltipText);

		        // Step 4: Assert that the tooltip text is displayed
		        assert tooltipTextElement.isDisplayed() : "Required tooltip text is not visible.";

		        // Optional: Print confirmation that the tooltip text is visible
		        System.out.println("Required tooltip text is visible when hovered over the Application Name field.");

		   // mouse handover
		        action.moveToElement(tooltipElement).perform();
		        
		    } catch (Exception e) {
		       System.out.println(e);
		    }
	 }

	 @When("the user enters more than 40 characters into the Application Name field it should be verified whether the text is accepted or not")
	 public void the_user_enters_more_than_characters_into_the_application_name_field_it_should_be_verified_whether_the_text_is_accepted_or_not() {
		 try {
			    // Locate the Application Name field
			    WebElement applicationNameField = driver.findElement(By.xpath("//div[text()=' Application Name ']/following::input[@name='Name']"));

			    // Clear any existing text (optional)
			    applicationNameField.clear();

			    // Enter a string with more than 40 characters
			    String longName = "mnbvcxzasdfghjklpoiuytrewqzxcvbnmlkjhgfdas"; // 41 characters
			    applicationNameField.sendKeys(longName);

			    // Get the entered text
			    String enteredText = applicationNameField.getAttribute("value");

			    // Verify if the text is accepted (assuming it should accept the long name)
			    if (enteredText.length() > 40) {
			        System.out.println("The text is accepted, and it contains " + enteredText.length() + " characters.");
			    } else {
			        System.err.println("The text is not accepted; it should allow more than 40 characters.");
			    }
			
			} catch (Exception e) {
			    System.err.println("An unexpected error occurred: " + e.getMessage());
			}

		
	 }

	 
	 
	 @When("the user enters the application name")
	 public void the_user_enters_the_application_name_as() {
	     try {
	         // Locate the Application Name input field
	         WebElement applicationNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Name']")));

	         // Clear any existing text (optional)
	         applicationNameField.clear();

	         // Enter the application name provided in the step
	         applicationNameField.sendKeys("employee onboarding process");
	         
	       System.out.println("appliaction name :employee onboarding process");
	   	     } catch (Exception e) {
	         // Catch any other exceptions that may occur
	         System.out.println("Unexpected error: " + e.getMessage());
	         e.printStackTrace();
	     }
	 }


	 
	 @When("the user clears the existing text in the Description feild and the mouse is placed on the tooltip of the description field then the required text in the tooltip should be visible")
	 public void the_user_clears_the_existing_text_in_the_description_feild_and_the_mouse_is_placed_on_the_tooltip_of_the_description_field_then_the_required_text_in_the_tooltip_should_be_visible() throws Exception {
		 try {
			    // Step 1: Locate the tooltip element
			    WebElement tooltipElement1 = driver.findElement(By.xpath("//div[text()=' Description ']/parent::label/div[@class='custom-tooltip-container ms-1']/mat-icon"));

			    // Step 2: Hover over the tooltip using Aoutctions
			    Actions action = new Actions(driver);
			    action.moveToElement(tooltipElement1).perform();

			    // Wait for the tooltip text to become visible (instead of using Thread.sleep)
			    WebElement tooltipTextElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=' Description ']/parent::label//div[@class='custom-tooltip']/p")));

			    // Step 3: Print the visible tooltip text
			    System.out.println("Visible tooltip text for Description: " + tooltipTextElement1.getText());

			    // Step 4: Assert that the tooltip text is displayed
			    assert tooltipTextElement1.isDisplayed() : "Required tooltip text is not visible.";

		
			} catch (Exception e) {
			    // Catch any other unexpected exceptions
			    System.out.println("Unexpected error: " + e.getMessage());
			    e.printStackTrace();
			}


	 }

	 @When("the user enters more than {int} characters into the Description field it should be verified whether the text is accepted or not")
	 public void the_user_enters_more_than_characters_into_the_description_field_it_should_be_verified_whether_the_text_is_accepted_or_not(Integer maxLength) {
	     try {
	         // Create a string of exactly 600 characters
	         String longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ".repeat(12).substring(0, 600);

	         // Print the length of the text being sent
	         System.out.println("Length of text being sent: " + longText.length());

	         // Locate the input field for the Description
	         WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='FormDescription']")));

	         // Clear any existing text (optional)
	         inputField.clear();

	         // Enter the more than 500 characters
	         inputField.sendKeys(longText);

	         // Get the actual text from the Description field after sending keys
	         String enteredText = inputField.getAttribute("value");

	         // Print the actual text entered in the field
	         System.out.println("Actual text entered in the Description field: " + enteredText);

	         // Assert that the length of the entered text is less than or equal to the maxLength
	         if (enteredText.length() > maxLength) {
	             System.out.println("The input exceeds the maximum allowed characters of " + maxLength);
	         } else {
	             System.out.println("The input is within the allowed limit of " + maxLength);
	         }

	         // Perform the assertion
	         assert enteredText.length() <= maxLength : "Input exceeds the maximum allowed characters of " + maxLength;

	     
	     } catch (Exception e) {
	         // Catch any other unexpected exceptions
	         System.out.println("Unexpected error: " + e.getMessage());
	         e.printStackTrace();
	     }
	 }

	 
	 @Then("the user enters the description as {string}")
	 public void the_user_enters_the_description_as(String string) {
		 try {
		 // Locate the Description Name field
		    WebElement description_Field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=' Description ']/following::textarea[@name='FormDescription']")));

		    // Clear any existing text (optional)
		    description_Field.clear();

		    // Enter the application name provided in the step
		    description_Field.sendKeys("employee onboarding ");
		 }catch(Exception e) {
			 System.out.println(e);
		 }
		    
	 }

	 @Then("the mouse is placed on the tooltip of the Category Dropdown then the required text in the tooltip should be visible")
	 public void the_mouse_is_placed_on_the_tooltip_of_the_category_dropdown_then_the_required_text_in_the_tooltip_should_be_visible() throws Exception {
		    
		// Step 1: Locate the tooltip element
		 try {
		     WebElement tooltipElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Category ']/following-sibling::span[@class='custom-tooltip-container']")));

		     // Step 2: Hover over the tooltip
		     Actions action = new Actions(driver);
		     action.moveToElement(tooltipElement2).perform();

		     // Step 3: Locate the tooltip text
		     WebElement tooltipTextElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Category ']/following::div[@class='custom-tooltip']/p")));
		     System.out.println("print the visible tool tip of the category : " + tooltipTextElement2.getText());

		     // Step 4: Assert that the tooltip text is displayed
		     assert tooltipTextElement2.isDisplayed() : "Required tooltip text is not visible.";

		     // Optional: Print confirmation
		     System.out.println("Required tooltip text is visible when hovered over the Category field.");

		
		 } catch (Exception e) {
		     System.err.println("Unexpected error while handling the tooltip. " + e.getMessage());
		 }

		 Thread.sleep(2000);

		 
		// Step 1: Click on the "add_circle" icon
		 try {
		     WebElement addIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='add_circle']")));
		     addIcon.click();
		     System.out.println("Clicked on the add_circle icon.");
		 } catch (Exception e) {
		     System.out.println("Failed to click on the add_circle icon: " + e.getMessage());
		 }

		 // Step 2: Verify modal title
		 try {
		     WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
		         By.xpath("//h3[@class='modal-title my-0']/span[@class='elipse']")));
		     String modalTitleText = modalTitle.getText();
		     System.out.println("Modal Title visible: " + modalTitleText);
		     Assert.assertTrue("Expected modal title not visible.", modalTitleText.contains("Add Category"));
		 } catch (AssertionError ae) {
		     System.out.println("Assertion failed for modal title: " + ae.getMessage());
		 } catch (Exception e) {
		     System.out.println("Failed to verify modal title: " + e.getMessage());
		 }

		 // Step 3: Enter text in the Name input field
		 try {
		     WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
		         By.xpath("//label[@for='Name']/following-sibling::input[@id='Name']")));
		     nameInput.sendKeys("level2");
		     System.out.println("Sent keys 'level2' to the Name input field.");
		 } catch (Exception e) {
		     System.out.println("Failed to send keys to the Name input field: " + e.getMessage());
		 }

		 // Step 4: Click the submit button
		 try {
		     WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//label[@for='Name']/following::div[@type='submit']")));
		     submitButton.click();
		     System.out.println("Clicked on the submit button.");
		 } catch (Exception e) {
		     System.out.println("Failed to click on the submit button: " + e.getMessage());
		 }

		 // Step 5: Wait for confirmation message
		 try {
		     WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
		         By.xpath("//div[@class='content-container']/div[@class='message']")));
		     Assert.assertTrue("Submission not successful.", confirmationMessage.isDisplayed());
		     System.out.println("Submission successful: " + confirmationMessage.getText());
		 } catch (AssertionError ae) {
		     System.out.println("Assertion failed for confirmation message: " + ae.getMessage());
		 } catch (Exception e) {
		     System.out.println("Failed to verify confirmation message: " + e.getMessage());
		 }

		 
		 
		 
		 
		
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 try {
		     // Step 1: Locate and click the dropdown to expand options
		     WebElement categoryDropdown = driver.findElement(By.xpath("//span[text()='Implementation']"));
		     try {
		         categoryDropdown.click();
		     } catch (Exception e) {
		         JavascriptExecutor js = (JavascriptExecutor) driver;
		         js.executeScript("arguments[0].click();", categoryDropdown);
		     }

		     // Step 2: Get all the options that appear in the dropdown panel
		     List<WebElement> allOptions = driver.findElements(By.xpath("//mat-option")); // For Material dropdown options

		     // Step 3: Print the text of each option
		     System.out.println("Dropdown options:");
		     for (WebElement option : allOptions) {
		         System.out.println(option.getText());
		     }
		 
		 } catch (Exception e) {
		     System.err.println("Unexpected error while interacting with the category dropdown. " + e.getMessage());
		 }

		 Thread.sleep(3000);

		 try {
		     // Step 4: Locate and open the second dropdown to select the default option
		     WebElement categoryDropdown1 = driver.findElement(By.xpath("//span[text()='Implementation']"));
		     try {
		         categoryDropdown1.click();
		     } catch (Exception e) {
		         JavascriptExecutor js1 = (JavascriptExecutor) driver;
		         js1.executeScript("arguments[0].click();", categoryDropdown1);
		     }

		     // Step 5: Wait for the default option to be clickable and select it
		     WebElement defaultOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Challenge ']")));
		     defaultOption.click();

		     System.out.println("Default option is selected");
		 }catch (Exception e) {
		     System.err.println("Unexpected error while interacting with the second dropdown. " + e.getMessage());
		 }

	 }    
		    
		   @Then("the mouse is placed on the tooltip of the Type dropdown then the required text in the tooltip should be visible")
                public void the_mouse_is_placed_on_the_tooltip_of_the_type_dropdown_then_the_required_text_in_the_tooltip_should_be_visible() throws Exception {
			  
			   try {
			   // Step 1: Locate the tooltip element
			    WebElement tooltipElement1 = driver.findElement(By.xpath("//span[text()=' Type ']/following-sibling::span[@class='custom-tooltip-container']"));

			    // Step 2: Hover over the tooltip
			    Actions action = new Actions(driver);
			    action.moveToElement(tooltipElement1).perform();
			    

			    // Step 3: Locate the tooltip text
			    WebElement tooltipTextElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Type ']/following-sibling::span/div[@class='custom-tooltip type']")));

			    // Use JavaScript Executor to scroll to the tooltip text
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			    js.executeScript("arguments[0].scrollIntoView(true);", tooltipTextElement1);

			    // Print the tooltip text using getText()
			    String tooltipText = tooltipTextElement1.getText();
			    System.out.println("Tooltip text: " + tooltipText);

			    // Verify that the tooltip text is visible
			    assert tooltipText != null && !tooltipText.isEmpty() : "Tooltip text is not visible or is empty.";
			   }catch(Exception e) 
			   {
				   System.out.println(e);
			   }
			    
			   
			   
			   
			   try {
			   // Step 1: Locate the 'Type' element and wait until it is visible
			    WebElement typeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Type ']")));

			    // Step 2: Create an Actions object for mouse hover
			    Actions action = new Actions(driver);

			    // Step 3: Perform mouse hover over the element
			    action.moveToElement(typeElement).perform();
			   }catch(Exception e) {
				   
			   }
			   
		            try {
		                // Step 1: Locate and click the first dropdown arrow
		                WebElement arrowElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Response only']")));
		                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		                jsExecutor.executeScript("arguments[0].click();", arrowElement);
		                System.out.println("Clicked on the dropdown arrow.");

		                // Step 2: Select the "Request and Response" option
		                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Request and Response ']")));
		               jsExecutor.executeScript("arguments[0].click();", option);
		                System.out.println("Selected 'Request and Response' option.");

		                // Step 3: Check if the modal title text is visible
		                try {
		                    WebElement modalText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(@class,'modal-title my')]/span[@class='elipse']")));
		                    System.out.println("Modal text is visible: " + modalText.getText());
		                } catch (TimeoutException e) {
		                    System.err.println("Modal title text is not visible: " + e.getMessage());
		                }

		                // Step 4: Click on the 'Change' button
		                WebElement changeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=' Change ']")));
		                changeButton.click();
		                System.out.println("'Change' button clicked.");

		                // Step 5: Check if 'Request has Workflow' text is visible
		                try {
		                    WebElement workflowText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Request has Workflow']")));
		                    System.out.println("'Request has Workflow' text is visible: " + workflowText.getText());
		                } catch (TimeoutException e) {
		                    System.err.println("'Request has Workflow' text is not visible: " + e.getMessage());
		                }

		                // Step 6: Click the arrow again to open the dropdown
		                jsExecutor.executeScript("arguments[0].click();", arrowElement);
		                System.out.println("Clicked on the dropdown arrow again.");

		                // Step 7: Select the "Response only" option
		                WebElement responseOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Response only ']")));
		                responseOption.click();
		                System.out.println("Selected 'Response only' option.");
		                
		              

		                // Step 8: Check if the modal title text is visible after selecting "Response only"
		                try {
		                    WebElement modalTextAfterResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(@class,'modal-title my')]/span[@class='elipse']")));
		                    System.out.println("Modal text after selecting 'Response only' is visible: " + modalTextAfterResponse.getText());
		                } catch (TimeoutException e) {
		                    System.err.println("Modal title text after selecting 'Response only' is not visible: " + e.getMessage());
		                }

		                // Step 9: Click on the 'Change' button
		                WebElement changeButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=' Change ']")));
		                jsExecutor.executeScript("arguments[0].click();", changeButton1);
		                System.out.println("'Change' button clicked.");
		                
		                
		               

		           
		            } catch (Exception e) {
		                System.err.println("Unexpected error: " + e.getMessage());
		            }
		        }
			   
			   
			   
			   
			   
			   
			   
			   
			   
			    
	 

           @Then("the mouse is placed on the tooltipof the workflow the the required text in the tooltip should be visible")
            public void the_mouse_is_placed_on_the_tooltipof_the_workflow_the_the_required_text_in_the_tooltip_should_be_visible() throws Exception {
        	 
        	   try {
        	   Actions action = new Actions(driver);
        	  // mouse placed on the workflow
        	   WebElement workflow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Workflow ']")));

			    // Step 2: Hover over the tooltip
			   
			    action.moveToElement(workflow).perform();
              
        	 
        	   
        	   // Step 1: Locate the tooltip element
			    WebElement tooltipElement3 = driver.findElement(By.xpath("(//span[@class='custom-tooltip-container']/child::mat-icon)[3]"));

			    // Step 2: Hover over the tooltip
			    action.moveToElement(tooltipElement3).perform();
               
			    // Step 3: Locate the tooltip text
			    WebElement tooltipTextElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Workflow ']/following-sibling::span/div[@class='custom-tooltip type']")));
			    // Use JavaScript Executor to scroll to the tooltip text
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			    js.executeScript("arguments[0].scrollIntoView(true);", tooltipTextElement3);

			    // Print the tooltip text using getText()
			    String tooltipText12 = tooltipTextElement3.getText();
			    System.out.println("Tooltip workflow: " + tooltipText12);

			    // Verify that the tooltip text is visible
			    assert tooltipText12 != null && !tooltipText12.isEmpty() : "Tooltip text is not visible or is empty.";
			    
			    
			    action.moveToElement(workflow).perform();
			    
			    
			    
        	   }catch(Exception e) {
        		System.out.println(e);   
        	   }
	 }

           @Then("the user clicks on the Response has Workflow toggle then the user should see either {string} or {string} text visible and check the all the visible tabs")
           public void the_user_clicks_on_the_response_has_workflow_toggle_then_the_user_should_see_either_or_text_visible_and_check_all_the_visible_tabs(String string, String string2) {
               try {
            	// Perform the operation two times
                   for (int iteration = 1; iteration <= 2; iteration++) {
                       System.out.println("Operation Iteration: " + iteration);

                       // Locate and click the toggle switch
                       WebElement toggleSwitch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='mat-slide-toggle-thumb-container']")));
                       toggleSwitch.click();

                       // Wait for the 'Yes' or 'No' text to appear
                       WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                           By.xpath("//span[text()='Yes'] | //span[text()='No']")
                       ));
                       String toggleText = textElement.getText();
                       System.out.println("Toggle Text after click: " + toggleText);

                       // Print elements based on the toggle state
                       List<WebElement> elements;
                       if (toggleText.equals("Yes")) {
                           // When the toggle is "Yes"
                           elements = driver.findElements(By.xpath("//span[text()='Application Settings'] | //span[text()='Form'] | //span[text()='Response Workflow'] | //span[text()='Tasks'] | //span[text()='Properties'] | //span[text()='Dashboard']"));
                           System.out.println("Visible Text when 'Yes' is displayed:");
                       } else {
                           // When the toggle is "No"
                           elements = driver.findElements(By.xpath("//span[text()='Application Settings'] | //span[text()='Form'] | //span[text()='Properties'] | //span[text()='Dashboard']"));
                           System.out.println("Visible Text when 'No' is displayed:");
                       }

                       // Print all elements' text
                       for (WebElement element : elements) {
                           System.out.println(element.getText());
                       }

                       // Assertion to verify the toggle text is either "Yes" or "No"
                       if (toggleText.equals("Yes") || toggleText.equals("No")) {
                           System.out.println("Assertion Passed: Toggle text is '" + toggleText + "'.");
                       } else {
                           System.out.println("Assertion Failed: Unexpected toggle text - " + toggleText);
                       }

                       // Adding a brief pause for better visibility in execution (optional)
                       Thread.sleep(1000);
                   }

               } catch (Exception e) {
                   e.printStackTrace();
               } 
           }


           @Then("the user checks the buttons on the right side down of the page")
           public void the_user_checks_the_buttons_on_the_right_side_down_of_the_page() {
          
        	   try {
                  

                   // Locate all elements matching the specified XPath
                   List<WebElement> footerButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'secondary-button footer-button')]")));

                   // Print details of each element
                   System.out.println("Footer Buttons Found: " + footerButtons.size());
                   for (WebElement button : footerButtons) {
                       String buttonText = button.getText();
                       boolean isVisible = button.isDisplayed();

                       System.out.println("Button Text: " + buttonText);
                       System.out.println("Is Visible: " + isVisible);
                   }

               } catch (Exception e) {
                   e.printStackTrace();
               }
        	   
           }
           
           @Then("the user clicks on the Cancel button and verifies whether the application is closed")
           public void the_user_clicks_on_the_cancel_button_and_verifies_whether_the_application_is_closed() {
               
        	   
        	   WebElement clicks_cancelbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='secondary-button footer-button ml-auto']")));
        	   clicks_cancelbutton.click();
        	  
        	   //check the url
        	   String expectedurl="https://admin.ideabridge.co/admin/operations/application-management";
        	   String actualUrl1 = driver.getCurrentUrl();
               Assert.assertEquals("The URL of the Users page is not as expected.", expectedurl, actualUrl1);
        	   // check the visibilty text on application page after clicks the cancel button
               
               WebElement text = driver.findElement(By.xpath("//h2[text()='Applications']"));
               assertTrue(text.isDisplayed(), "The expected text is not visible.");
               System.out.println("....application page is successfully open after CLICKS THE CANCEL BUTTON.....");
               
           }

           
           
           @Then("user click the   Cancel , Preview , Save  and Next:")
          public void user_click_the_cancel_preview_save_and_next() {
          // clcik and verify the preview button 
        	   WebElement click_preview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='preview']")));
        	  
        	   JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
               jsExecutor.executeScript("arguments[0].click();", click_preview);
        	//  check if the text is visible on the page
               WebElement text = driver.findElement(By.xpath("//span[text()='Overview']"));
               assertTrue(text.isDisplayed(), "The expected text is not visible.");
               System.out.println("the visible text: "+text.getText()+" :page open successfully");  
               
             // close the page  preview page
               WebElement closepreview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='close']")));
               closepreview.click();
               
         /*   // Click and verify the Save button
               try {
                   WebElement click_save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='save']")));
                   click_save.click();
                   System.out.println("Save button clicked successfully.");
                   
                   // Verify confirmation message with "Yes" or "No" options is displayed
                   WebElement confirmationText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='d-inline-block mb-0']")));
                   assertTrue(confirmationText.isDisplayed(), "The expected confirmation text is not visible.");
                   System.out.println("Visible confirmation text: " + confirmationText.getText() + " : page opened successfully.");
                   
                   // Click Yes
                   WebElement click_yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='primary-button ng-star-inserted']")));
                   click_yes.click();
                   System.out.println("'Yes' option clicked in confirmation message.");
                   
                   // Verify that the confirmation message with "Application updated" is displayed
                   WebElement appUpdatedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Application updated.']")));
                   assertTrue(appUpdatedMessage.isDisplayed(), "Application updated message is not displayed.");
                   System.out.println("Confirmation: " + appUpdatedMessage.getText() + " : Save action was successful.");

                   // Click and verify the Next button
                   WebElement click_next = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='arrow_circle_right']")));
                   click_next.click();
                   System.out.println("Next button clicked successfully.");

                   // Verify that the Form page has opened by checking for a unique element on the Form page
                   WebElement formPageIndicator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='ng-star-inserted']")));
                   assertTrue(formPageIndicator.isDisplayed(), "The Form page did not open as expected.");
                   System.out.println("Form page opened successfully.");

               } catch (TimeoutException e) {
                   System.err.println("An element was not found in time: " + e.getMessage());
               } catch (NoSuchElementException e) {
                   System.err.println("An element was not found on the page: " + e.getMessage());
               }
                   
	 
	 
	 
           }
           
           */
           
           }


		private void assertTrue(boolean displayed, String string) {
			// TODO Auto-generated method stub
			
		}
}
	 
	 

	


