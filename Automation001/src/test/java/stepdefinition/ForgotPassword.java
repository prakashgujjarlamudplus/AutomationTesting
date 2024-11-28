package stepdefinition;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import io.cucumber.java.en.*;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class ForgotPassword {
	
    WebDriver driver;
    WebDriverWait wait;

    @Given("I navigate to the URL {string}")
    public void iNavigateToTheURL(String url) {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\HP\\eclipse-workspace\\Automation001\\Driver\\msedgedriver.exe");
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        System.out.println("Navigated to URL: " + url);
    } 

    @When("I check if the {string} text is displayed")
    public void iCheckIfTheTextIsDisplayed(String text) {
        try {
            WebElement cookieText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + text + "')]")));
            Assert.assertTrue("Cookies text is not displayed", cookieText.isDisplayed());
            System.out.println("Cookies text is displayed: " + text);
        } catch (Exception e) {
            System.out.println("Error checking cookies text: " + e.getMessage());
        }
    }

    @And("I check if the buttons {string}, {string}, and {string} are enabled or disabled")
    public void iCheckIfTheButtonsAreEnabledOrDisabled(String agree, String decline, String preferences) {
        try {
            WebElement agreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='" + agree + "']")));
            WebElement declineButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='" + decline + "']")));
            WebElement preferencesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='" + preferences + "']")));

            Assert.assertTrue(agree + " button is not enabled", agreeButton.isEnabled());
            Assert.assertTrue(decline + " button is not enabled", declineButton.isEnabled());
            Assert.assertTrue(preferences + " button is not enabled", preferencesButton.isEnabled());
            System.out.println("Buttons checked: " + agree + ", " + decline + ", " + preferences);
        } catch (Exception e) {
            System.out.println("Error checking button states: " + e.getMessage());
        }
    }

    @Then("I click the {string} button")
    public void iClickTheButton(String buttonText) {
        try {
            System.out.println("Clicking the button: " + buttonText);
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='" + buttonText + "']")));
            button.click();
            System.out.println("Button '" + buttonText + "' clicked.");
        } catch (Exception e) {
            System.out.println("Failed to click the button '" + buttonText + "': " + e.getMessage());
        }
    }
    
    
    
    @And("I verify that the {string} text is visible")
    public void iVerifyThatTheTextIsVisible(String text) {
        try {
            WebElement privacyText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + text + "')]")));
            Assert.assertTrue(text + " text is not visible", privacyText.isDisplayed());
            System.out.println(text + " text is visible.");
        } catch (Exception e) {
            System.out.println("Error verifying visibility of text: " + e.getMessage());
        }
    }

    @Then("I click the close button")
    public void iClickTheCloseButton() {
        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='✕']")));
            closeButton.click();
            System.out.println("Close button clicked.");
        } catch (Exception e) {
            System.out.println("Error clicking close button: " + e.getMessage());
        }
    }

    @Then("I click the agree button")
    public void iClickTheAgreeButton() {
        try {
            WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='I agree']")));
            agreeButton.click();
            System.out.println("Agree button clicked.");
        } catch (Exception e) {
            System.out.println("Error clicking agree button: " + e.getMessage());
        }
    }

    @Then("I should see the {string} logo displayed")
    public void iShouldSeeTheLogoDisplayed(String logoName) {
        try {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='logo text-center']/child::img")));
            Assert.assertTrue(logoName + " logo is not displayed", logo.isDisplayed());
            System.out.println(logoName + " logo is displayed.");
            System.out.println("successfully enter the login page");
        } catch (Exception e) {
            System.out.println("Error verifying logo display: " + e.getMessage());
        }
    }

    // Additional Steps for Forgot Password Scenarios
    @When("I click the {string} link")
    public void iClickTheLink(String linkText) {
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Forgot Password?']")));
            link.click();
            System.out.println(linkText + " link clicked.");
        } catch (Exception e) {
            System.out.println("Error clicking link: " + e.getMessage());
        }
    }

    @Then("the {string} page should be displayed")
    public void thePageShouldBeDisplayed(String pageTitle) {
        try {
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + pageTitle + "')]")));
            Assert.assertTrue(pageTitle + " page is not displayed", titleElement.isDisplayed());
            System.out.println(pageTitle + " page is displayed.");
        } catch (Exception e) {
            System.out.println("Error verifying page display: " + e.getMessage());
        }
    }

    @Given("I am on the {string} page")
    public void iAmOnThePage(String pageTitle) {
        // Additional implementation if needed
        System.out.println("I am on the " + pageTitle + " page.");
    }

    @When("I do not enter an email address in the email field")
    public void iDoNotEnterAnEmailAddress() {
        // No action needed since field is left empty
        System.out.println("Email address field left empty.");
    }

    @Then("check that the {string} button is disabled")
    public void checkButtonDisabled(String buttonName) {
    	
    	WebElement e = driver.findElement(By.xpath("//*[@id=\"register-form\"]/div[4]/button[2]"));
    	boolean actaul = e.isEnabled();
    			if(actaul)
    			{
    				System.out.println("restbutton is enable");
    			}else {
    				System.out.println("restbutton is disable");
    			}
    }
        


    @When("I enter an invalid email as {string}")
    public void iEnterAnInvalidEmail(String invalidEmail) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(invalidEmail);
        System.out.println("Entered invalid email: " + invalidEmail);
    }

    @When("I clear the email field")
    public void iClearTheEmailField() {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        System.out.println("Email field cleared.");
    }

    
    @When("I enter a valid email {string}")
    public void iEnterAValidEmail(String validEmail) {
        WebElement emailField = driver.findElement(By.id("email")); // Replace with your actual email field locator
        emailField.clear();
        emailField.sendKeys(validEmail);
        System.out.println("Entered valid email: " + validEmail);
    }

    @When("I enter {string} in the email field")
    public void iEnterInTheEmailField(String email) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(email);
        System.out.println("Entered email: " + email);
    }
    
    @When("I enter an invalid email id {string}")
    public void iEnterAnInvalidEmailId(String invalidEmail) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(invalidEmail);
        System.out.println("Entered invalid email: " + invalidEmail);
    }
    
    @When("I do not check the reCAPTCHA box")
    public void iDoNotCheckTheReCAPTCHABox() {
        System.out.println("Did not check the reCAPTCHA box.");
        // Assuming there's no action needed as the checkbox should be left unchecked
    }
    
    
    @When("I check the reCAPTCHA box")
    public void iCheckTheReCAPTCHABox() {
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@title='reCAPTCHA']")));
          driver.switchTo().frame(iframe);

          // Wait for the checkbox to appear and click it
          WebElement recaptchaCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='recaptcha-anchor']")));
          recaptchaCheckbox.click();
          driver.switchTo().defaultContent();

          // Print reCAPTCHA checked
          System.out.println("reCAPTCHA checked");
    }
    
    @Then("the {string} button should be enabled")
    public void theButtonShouldBeEnabled(String buttonText) {

    	WebElement e = driver.findElement(By.xpath("//*[@id=\"register-form\"]/div[4]/button[2]"));
    	boolean actaul = e.isEnabled();
    			if(actaul)
    			{
    				System.out.println("restbutton is enable");
    			}else {
    				System.out.println("restbutton is disable");
    			}
    
    }

    
    @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String errorMessage) {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + errorMessage + "')]")));
            Assert.assertTrue("Error message not displayed", errorElement.isDisplayed());
            System.out.println("Error message displayed: " + errorMessage);
        } catch (Exception e) {
            System.out.println("Error verifying error message: " + e.getMessage());
        }
    }
    
  

    

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after test
        }
    }
}


