package stepdefinition;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;


public class AdminHomeSteps {
	WebDriver driver;
    WebDriverWait wait;

 

     @Given("user enters the URL {string}")
    public void user_enters_the_URL(String url) throws TimeoutException {
       System.setProperty("webdriver.edge.driver", "C:\\Users\\HP\\eclipse-workspace\\Automation001\\Driver\\msedgedriver.exe");
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.get(url);
        // Navigating to the given URL
        driver.get(url);
        System.out.println("Navigated to: " + url);

     }


    @Then("the Ideabridge logo should be displayed")
     public void the_Ideabridge_logo_should_be_displayed() {
    	    
    	    try {
    	        // Wait for the Ideabridge logo to be visible
    	        WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='logo text-center']/child::img")));
    	        
    	        // Assert that the logo is displayed
    	        Assert.assertTrue("The Ideabridge logo is not displayed on the login page.", logoElement.isDisplayed());
    	        System.out.println("Ideabridge logo is displayed.");
    	    } catch (NoSuchElementException e) {
    	        // Fail the test if the logo is not found within the wait time
    	        Assert.fail("The Ideabridge logo did not appear within the expected time.");
    	    } catch (Exception e) {
    	        // Handle any other unexpected exceptions
    	        Assert.fail("An unexpected error occurred: " + e.getMessage());
    	    }
    	    System.out.println("user successfully open the login page");
     }

    @When("user enters the valid email address {string}")
    public void user_enters_the_valid_email_address(String email) {
        
        try {
            // Wait for the email input field to be visible
            WebElement emailInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            
            // Clear the email input field before entering the new email
            emailInputField.clear();
            
            // Enter the valid email address
            emailInputField.sendKeys(email);
            
            System.out.println("Entered the email address: " + email);
        } catch (Exception e) {
            // Handle exceptions (e.g., element not found)
            Assert.fail("Failed to enter email address: " + e.getMessage());
        }
    }

    @And("user selects {string} from the dropdown")
    public void user_selects_from_the_dropdown(String option) {
        try {
            // Wait for the dropdown element to be visible
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mat-select-value-1']")));

            // Click the dropdown to open it
            dropdown.click();
            
            // Locate the option within the dropdown and select it
            WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='enterprise.ideabridge.co']")));
            optionToSelect.click();
            
            System.out.println("Selected the option: enterprise.ideabridge.co");
        } catch (Exception e) {
            // Handle exceptions (e.g., element not found)
            Assert.fail("Failed to select option from dropdown: " + e.getMessage());
        }
    }


    @And("user enters valid password {string}")
    public void user_enters_valid_password(String password) {
        try {
            // Wait for the password input field to be visible
            WebElement passwordInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            
            // Clear the password input field before entering the new password
            passwordInputField.clear();
            
            // Enter the valid password
            passwordInputField.sendKeys(password);
            
            System.out.println("Entered the password: " + password);
        } catch (Exception e) {
            // Handle exceptions (e.g., element not found)
            Assert.fail("Failed to enter password: " + e.getMessage());
        }
    }


   @And("user checks the recaptcha checkbox")
    public void user_checks_the_recaptcha_checkbox() {
        try {
        	// go the iframes
        	WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@title='reCAPTCHA']")));
            driver.switchTo().frame(iframe);
            
            // Wait for the reCAPTCHA checkbox to be clickable
            WebElement recaptchaCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='recaptcha-anchor']")));
            // Click the reCAPTCHA checkbox
            recaptchaCheckbox.click();
            driver.switchTo().defaultContent();
            System.out.println("Checked the reCAPTCHA checkbox.");
        } catch (Exception e) {
            // Handle exceptions (e.g., element not found, not clickable)
            Assert.fail("Failed to check the reCAPTCHA checkbox: " + e.getMessage());
        }
    }


    @And("user clicks the continue button")
   public void user_clicks_the_continue_button() {
	    try {
	        // Wait for the continue button to be clickable
	        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
	        
	        // Click the continue button
	        continueButton.click();
	        
	        System.out.println("Clicked the continue button.");
	    } catch (Exception e) {
	        // Handle exceptions (e.g., element not found, unable to click)
	        Assert.fail("Failed to click the continue button: " + e.getMessage());
	    }
	}


   @Then("the Ideabridge logo should be displayed on the homepage")
    public void the_Ideabridge_logo_should_be_displayed_on_the_homepage() {
        try {
            // Wait for the Ideabridge logo to be visible
            WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='logo text-center']/child::img")));
            // Verify that the logo is displayed
            Assert.assertTrue("Ideabridge logo is not displayed on the homepage.", logoElement.isDisplayed());
            
            System.out.println("Ideabridge logo is displayed on the homepage.");
        } catch (Exception e) {
            // Handle exceptions (e.g., element not found)
            Assert.fail("Failed to verify Ideabridge logo on the homepage: " + e.getMessage());
        }
    }

   @And("user successfully logs into the homepage")
   public void user_successfully_logs_into_the_homepage() {
      
       
       System.out.println("user successfully login to homepage");
   }

    @When("user double clicks on {string}")
    public void user_double_clicks_on(String elementName) {
        try {
            // Locate the element to double-click on
            WebElement userDetailsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-panel-title[text()=' User Details ']")));
            
            // Create an instance of Actions class
            Actions actions = new Actions(driver);
            
            // Perform the double-click action
            actions.doubleClick(userDetailsElement).perform();
            
            System.out.println("User double clicked on: User Details " );
        } catch (Exception e) {
            // Handle exceptions (e.g., element not found)
            Assert.fail("Failed to double click on User Details: " + e.getMessage());
        }
    }

    @Then("User should be able to see the text {string}, {string}, {string}, {string} are displayed")
    public void user_should_be_able_to_see_the_text_are_displayed(String usersText, String suspendedUsersText, String administratorText, String adminEmailText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait and verify that the text "Users" is displayed
            WebElement users = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Users ']")));
            Assert.assertTrue("Users text is not displayed", users.isDisplayed());
            System.out.println("Users: is displayed.");

            // Wait and verify that the text "Suspended Users" is displayed
            WebElement suspendedUsers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Suspended Users']")));
            Assert.assertTrue("Suspended Users text is not displayed", suspendedUsers.isDisplayed());
            System.out.println("Suspended Users: is displayed.");

            // Wait and verify that the text "Administrator" is displayed
            WebElement administrator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Administrator']")));
            Assert.assertTrue("Administrator text is not displayed", administrator.isDisplayed());
            System.out.println("Administrator: is displayed.");

            // Wait and verify that the "Administrator's Email" text is displayed
            WebElement adminEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cdk-accordion-child-0']/div/div/div/div[4]/h3")));
            Assert.assertTrue("Administrator's Email text is not displayed", adminEmail.isDisplayed());
            System.out.println("Administrator's Email: is displayed.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

  


   
	@Then("the {string} tab should be highlighted after login")
    public void the_tab_should_be_highlighted_after_login(String string) {
        
    }

    @Then("the {string} and {string} tabs should be displayed on the homepage")
    public void theTabsShouldBeDisplayed(String tab1, String tab2) {
        // Create a list of expected tab names
        List<String> expectedTabs = Arrays.asList(tab1, tab2);

        // Check if each tab is displayed
        for (String tabName : expectedTabs) {
            try {
                // Wait until the tab is visible
                WebElement tab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + tabName + "')]")));

                // Assert that the tab is displayed
                assert tab.isDisplayed() : tabName + " tab is not displayed.";

                // Print success message
                System.out.println(tabName + " tab is displayed.");
            } catch (Exception e) {
                // Print failure message and the exception
                System.err.println("Error: " + e.getMessage());
            }
        }
    
    }
    @When("user clicks on the {string} tab")
    public void user_clicks_on_the_tab(String tabName) {
        try {
            // Wait until the tab is clickable
            WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), '" + tabName + "')]")));
            
            // Click on the tab
            tab.click();
            System.out.println(tabName + " tab is clicked.");
        } catch (Exception e) {
            System.err.println("Error: Could not click on the " + tabName + " tab. " + e.getMessage());
        }
    }

    @Then("the following widgets should be displayed:")
    public void the_following_widgets_should_be_displayed(List<String> widgets) {
        // Initialize WebDriverWait with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (String widgetName : widgets) {
            try {
                // Wait for the widget to be visible on the page
                WebElement widget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='" + widgetName + "']")));

                // Assert that the widget is displayed
                Assert.assertTrue(widgetName + " widget is not displayed.", widget.isDisplayed());
                System.out.println(widgetName + " widget is displayed.");
            } catch (Exception e) {
                // Print an error message if the widget is not found or not visible
                System.err.println("Error: Could not find or display the widget " + widgetName + ". " + e.getMessage());
            }
        }
    }

    @When("the user clicks on the {string} tab on operations")
    public void the_user_clicks_on_tab(String tabName) {
        // Click on the Users tab
        WebElement usersTab = driver.findElement(By.xpath("//a[text()='" + tabName + "']"));
        usersTab.click();

        // Add an explicit wait to ensure the Users page loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Users']")));
        System.out.println("Navigated to the 'Users' page.");
    }
    
    @Then("the user navigates to the {string} page")
	public void the_user_navigates_to_the_page(String pageName) {
	    // Assuming there's a method to get the URL of the page based on the page name
	    String url = getPageUrl("https://admin.ideabridge.co/");
	    // Navigating to the Users page using Selenium WebDriver
	    driver.get(url);
	}

	private String getPageUrl(String pageName) {
	    // You can use a switch-case or if-else to handle different pages
	    switch(pageName.toLowerCase()) {
	        case "users":
	            return "https://admin.ideabridge.co/admin/operations/users"; // Replace with the actual URL
	        // Add more cases for different pages if needed
	        default:
	            throw new IllegalArgumentException("Page not found: " + pageName);
	    }
	}

    
    
    
    
    
    
    
    
	}


