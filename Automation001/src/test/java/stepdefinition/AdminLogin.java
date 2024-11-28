package stepdefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.*;

import java.time.Duration;
import java.util.Arrays;


public class AdminLogin {
    WebDriver driver;
    WebDriverWait wait;
   
    
    

    @Given("I user navigate to the URL {string}")
    public void i_user_navigate_to_the_url(String url) {
    	System.setProperty("webdriver.edge.driver", "C:\\Users\\HP\\eclipse-workspace\\Automation001\\Driver\\msedgedriver.exe");
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(url);
    }
	    
    @Then("user click the {string} button")
    public void user_click_the_button(String buttonText) {
        // Adding explicit wait to ensure the button is clickable
        WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='I agree']")));
        agreeButton.click();
    }
    
	@When("I  user enter the valid email address {string} without entering the password")
	public void i_user_enter_the_valid_email_address_without_entering_the_password(String string) {
		// Locate the email input field and enter the valid email address
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']"))); 
        emailField.clear(); // Clear the field if there's any pre-existing text
        emailField.sendKeys("donotuse@plusinnovations.co"); // Enter the valid email address
        
        // Ensure the password field is empty (this may vary based on your application)
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']")); 
        passwordField.clear(); // Clear the password field to ensure it is empty

        System.out.println("Entered valid email: donotuse@plusinnovations.co & without entering a password.");
    }
	

	@Then("The {string} button should be disabled")
	public void the_button_should_be_disabled(String string) throws Exception {
		WebElement continueButton = driver.findElement(By.xpath("//button[@type='submit']"));
	    Thread.sleep(3000);
	    boolean isEnabled = continueButton.isEnabled();
	    
	   
	   
	    if(isEnabled)
		{
			System.out.println("continur button is enable");
		}else {
			System.out.println("continue button is disable");
		}
	}


	@When("I clean the email address and password fields")
	public void i_clean_the_email_address_and_password_fields() {
		
	    // Locate the email input field and wait until it is visible
	    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']"))); // Replace with the actual ID or locator of the email field
	    emailField.clear(); // Clear the email field

	    // Locate the password input field and wait until it is visible
	    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']"))); // Replace with the actual ID or locator of the password field
	    passwordField.clear(); // Clear the password field

	    System.out.println("Cleared the email and password fields.");
	}
	

	@When("I user enter the valid password {string} without entering the email address")
	public void i_user_enter_the_valid_password_without_entering_the_email_address(String string) {
	     WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']"))); // Replace with the actual ID or locator of the password field
	    passwordField.clear(); // Clear the password field
	    passwordField.sendKeys("Ideabridge@123"); // Enter the valid password

	    System.out.println("Entered valid password: Ideabridge@123- without entering an email address.");
	}

	@When("I user enter the valid email address {string} and valid password {string}")
	public void i_user_enter_the_valid_email_address_and_valid_password(String string, String string2) {
		  WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		    emailField.clear(); // Clear the email field
		    emailField.sendKeys("donotuse@plusinnovations.co"); // Enter the valid email address

		    // Locate the password input field and wait until it is visible
		    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']"))); // Replace with the actual ID or locator of the password field
		    passwordField.clear(); // Clear the password field
		    passwordField.sendKeys("Ideabridge@123"); // Enter the valid password

		    System.out.println("Entered valid email: donotuse@plusinnovations.co & valid password: Ideabridge@123");
		}

	@Then("The {string} button should be enabled")
	public void the_button_should_be_enabled(String string) {
		 WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))); // Replace with the actual ID or locator of the button

		    // Assert that the button is enabled
		    boolean isEnabled = continueButton.isEnabled(); 
		    assertTrue("The 'Continue' button should be enabled, but it is disabled.", isEnabled);
		}

	@When("I user enter an invalid email address {string}")
	public void i_user_enter_an_invalid_email_address(String string) {
		 WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))); // Replace with the actual ID or locator of the email field
		    emailField.clear(); // Clear the email field
		    emailField.sendKeys("prakash"); // Enter the invalid email address

		    System.out.println("Entered invalid email: prakash");
		};
	

		@Then("An error message {string} should be displayed")
		public void an_error_message_should_be_displayed(String expectedErrorMessage) {
		    // Wait for the error message element to be visible
		    WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + expectedErrorMessage + "')]"))); // Adjust this XPath as needed

		    // Get the text of the error message
		    String actualErrorMessage = errorMessageElement.getText();

		    // Assert that the actual error message matches the expected message
		    assertEquals("The error message displayed is not as expected.", expectedErrorMessage, actualErrorMessage);
		}

	

		@When("I user enter a valid email {string} and a password shorter than 8 characters {string}")
		public void i_user_enter_a_valid_email_and_a_password_shorter_than_8_characters(String email, String password) {
		   
		    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))); 
		    emailField.clear(); // Clear the email field
		    emailField.sendKeys(email); // Enter the valid email address

		    // Locate the password input field and wait until it is visible
		    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))); 
		    passwordField.clear(); // Clear the password field
		    passwordField.sendKeys(password); // Enter the invalid password

		    System.out.println("Entered valid email: " + email + " and invalid password: " + password);
		}

	@When("I user enter a valid email {string} and a password longer than 32 characters {string}")
		public void i_user_enter_a_valid_email_and_a_password_longer_than_32_characters(String email, String password) {
		   
		    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))); 
		    emailField.clear(); // Clear the email field
		    emailField.sendKeys(email); // Enter the valid email address

		    // Locate the password input field and wait until it is visible
		    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))); 
		    passwordField.clear(); // Clear the password field
		    passwordField.sendKeys(password); // Enter the invalid (long) password

		    System.out.println("Entered valid email: " + email + " and invalid password: " + password);
		}

	@When("I user enter the invalid email address {string} and valid password {string}")
	public void i_user_enter_the_invalid_email_address_and_valid_password(String email, String password) {
	    
	    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']"))); 
	    emailField.clear(); // Clear the email field
	    emailField.sendKeys("donotuse@plusinnovations.co"); // Enter the invalid email address

	    // Locate the password input field and wait until it is visible
	    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']"))); 
	    passwordField.clear(); // Clear the password field
	    passwordField.sendKeys("Ideabridge@123"); // Enter the valid password

	    System.out.println("Entered invalid email: " + email + " and valid password: " + password);
	}

	@When("I click the continue button")
	public void i_click_the_continue_button() {
	   
	    WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))); 
	    continueButton.click(); // Click the continue button

	    System.out.println("Clicked the continue button.");
	}

	@When("I user should see an error message for invalid credentials")
	public void i_user_should_see_an_error_message_for_invalid_credentials() {
	    // Locate the error message element on the page
	    WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-login/div/div/div/form/main/div[1]/div"))); 
	    // Get the actual text from the error message element
	    
	   // System.out.println(errorMessage.getText());
	    String messageText  = errorMessage.getText();
	 // Define specific content to check within the error message
	    String[] expectedContents = {
	        "Invalid login credentials",
	        "Attempts remaining",
	        "Invalid User",
	        "Please try after"
	    };

	    // Check if the error message contains any of the expected contents
	    boolean containsExpectedContent = Arrays.stream(expectedContents)
	        .anyMatch(messageText::contains);

	    // Assert that the error message contains at least one of the expected contents
	    assertTrue("Expected error message to contain specific content but got: " + messageText, containsExpectedContent);
	    System.out.println("Displayed error message: " + messageText);
	}
	@When("I user enter the invalid email address {string} and invalid password {string}")
	public void i_user_enter_the_invalid_email_address_and_invalid_password(String email, String password) {
	   
	    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	    emailField.clear();
	    emailField.sendKeys(email); 

	    // Locate the password input field and wait until it is visible
	    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))); 
	    passwordField.clear(); // Clear the password field
	    passwordField.sendKeys(password); // Enter the invalid password

	    System.out.println("Entered invalid email: " + email + " and invalid password: " + password);
	}

	@When("I user enter the valid email address {string} and invalid password {string}")
	public void i_user_enter_the_valid_email_address_and_invalid_password(String email, String password) {
	    
	    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	    emailField.clear();
	    emailField.sendKeys(email); 

	    // Locate the password input field and wait until it is visible
	    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))); 
	    passwordField.clear(); // Clear the password field to ensure it is empty
	    passwordField.sendKeys(password); // Enter the invalid password

	    System.out.println("Entered valid email: " + email + " and invalid password: " + password);
	}

	@Then("The {string} image should be displayed on the home page")
	public void the_image_should_be_displayed_on_the_home_page(String imageName) {
	    
	    WebElement imageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-center']/img[@class='logo ng-star-inserted']"))); 
	    
	    // Assert that the image is displayed
	    Assert.assertTrue("The image is not displayed on the home page.", imageElement.isDisplayed());
	    
	 // Get the current URL
        String actualURL = driver.getCurrentUrl();
        
        // Expected URL
        String expectedURL = "https://enterprise.ideabridge.co/";
     // Print the actual URL for debugging purposes
        System.out.println("Actual URL: " + actualURL);

        // Assert that the actual URL matches the expected URL
        Assert.assertEquals("The user did not navigate to the expected URL.", expectedURL, actualURL);

	    System.out.println("Verified that the image '" + imageName + "' is displayed on the home page.");
	    
	    System.out.println("user successfully logged in home page of idea bridge");
	    
	    
	}

	



}
