package stepdefinition;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.awt.event.KeyEvent;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeoutException;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.*;

public class Form_module {
	
  
	
	WebDriver driver;
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(100));


    
    
    @Given("the user is logged into the Aapli demo79")
    public void the_user_is_logged_into_the_aapli_demo79() {
    	  // Set the system property for Edge WebDriver
        System.setProperty("webdriver.edge.driver", "C:\\Users\\HP\\eclipse-workspace\\Automation001\\Driver\\msedgedriver.exe");

        // Set up EdgeOptions to customize Edge behavior
        EdgeOptions options = new EdgeOptions();
        
        // Example: Disable notifications for a smoother test
        options.addArguments("--disable-notifications");

        // Example: Disable popups or media stream UI
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");

        // Initialize the Edge driver with the options configured
        driver = new EdgeDriver(options);
        
        // Set an explicit wait to allow for element loading
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Maximize the window
        driver.manage().window().maximize();

        // Navigate to the URL (Aapli admin demo79 site)
        driver.get("https://admin-dev.aapli.app/");
        
        try {
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("demo79@mailinator.com");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("Ideabridge@1234");

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


            WebElement userDetailsText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-panel-title[text()=' User Details ']")));
            Assert.assertEquals(userDetailsText.getText(), "User Details", "Login page text does not match the expected value.");

            String expectedUrl = "https://admin-dev.aapli.app/";
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The URL of the Users page is not as expected.");
            System.out.println("Login to Aapli successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Then("the user should successfully navigate to the home page")
    public void theUserShouldSuccessfullyNavigateToTheHomePage() {
        try {
            WebElement userDetailsText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-panel-title[text()=' User Details ']")));
            Assert.assertEquals(userDetailsText.getText(), "User Details", "Login page text does not match the expected value.");

            String expectedUrl = "https://admin-dev.aapli.app/";
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The URL of the Users page is not as expected.");
            System.out.println("Login to Aapli successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("the user clicks on the Application tab")
    public void theUserClicksOnTheApplicationTab() throws Exception {
        WebElement application = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Applications']")));
        application.click();
        Thread.sleep(2000);
        }

    @Then("the {string} page should open")
    public void thePageShouldOpen(String string) {
        String expectedUrl = "https://admin-dev.aapli.app/admin/operations/application-management";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The URL of the Users page is not as expected.");

        WebElement text = driver.findElement(By.xpath("//h2[text()='Applications']"));
        Assert.assertTrue(text.isDisplayed(), "The expected text is not visible.");
        System.out.println("Application page is successfully open");
    }

    @When("the user clicks on the {string} icon")
    public void theUserClicksOnTheIcon(String string) {
    	WebElement addIcon = driver.findElement(By.xpath("//mat-icon[text()='add_circle_outline']"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", addIcon);


        String expectedUrl = "https://admin-dev.aapli.app/form-builder";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The URL of the form builder page is not as expected.");
    }

    @When("the user fills in the application settings")
    public void theUserFillsInTheApplicationSettings() throws InterruptedException {
        WebElement applicationNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Name']")));
        applicationNameField.clear();
        String applicationName = "Pluseinnovations.com";
        applicationNameField.sendKeys(applicationName);

        WebElement tooltipElement = driver.findElement(By.xpath("//div[@class='custom-tooltip-container']/child::mat-icon"));
        Actions action = new Actions(driver);
        action.moveToElement(tooltipElement).perform();

        WebElement tooltipTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='custom-tooltip-container']/div/p[@class='mb-0']")));
        Assert.assertTrue(tooltipTextElement.isDisplayed(), "Required tooltip text is not visible.");

        WebElement applicationCardsText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Name']")));
        Assert.assertEquals(applicationName, applicationCardsText.getAttribute("value"), "The Application Name field text and Application Cards text do not match.");

        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='FormDescription']")));
        descriptionField.clear();
        descriptionField.sendKeys("onboarding process of plus innovations");

        WebElement tooltipElement1 = driver.findElement(By.xpath("//div[@class='custom-tooltip-container ms-1']/child::mat-icon"));
       
        action.moveToElement(tooltipElement1).perform();
        Thread.sleep(3000);

        WebElement tooltipTextElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='custom-tooltip-container ms-1']/div/p[@class='mb-0']")));
        Assert.assertTrue(tooltipTextElement1.isDisplayed(),"Required tooltip text is not visible.");
        //.........add category and select options for category............
        
     // Step 1: Locate the tooltip Category Dropdown element
	    WebElement tooltipElement23 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='custom-tooltip-container']/child::mat-icon")));

	    // Step 2: Hover over the tooltip
	   
	    action.moveToElement(tooltipElement23).perform();

	    // Step 3: Locate the tooltip text
	    WebElement tooltipTextElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='custom-tooltip-container']//p[@class='mb-0'])[1]")));
	    System.out.println("print the visible tool tip of the category : "+tooltipTextElement2.getText());
	    // Step 4: Assert that the tooltip text is displayed
	    assert tooltipTextElement2.isDisplayed() : "Required tooltip text is not visible.";
	    
	    // Optional: Print confirmation
	    System.out.println("Required tooltip text is visible when hovered over the Category field.");
	    Thread.sleep(2000);
        
        
      // add category...
        
        WebElement addCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='add_circle']")));
        addCategory.click();

     /*   WebElement addField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Name']")));
        addField.clear();
        addField.sendKeys("Idea");

        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@type='submit']")));
        submit.click();

        WebElement addSuccessful = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message']")));
        if (addSuccessful.isDisplayed()) {
            System.out.println("Successfully added: " + addSuccessful.getText());
        } else {
            System.err.println("Success message is not visible.");
        }
        
        */
        
        WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button-blue cancel-button']")));
        cancel.click();
        
        
        Thread.sleep(2000);
     // Step 1: Locate and click the dropdown to expand options
        WebElement categoryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='mat-select-value-3']/child::span")));
       
        try {
            categoryDropdown.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", categoryDropdown);
        }

        // Step 2: Get all the options that appear in the dropdown panel
        List<WebElement> allOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option")));

        // Step 3: Print the text of each option
        System.out.println("Dropdown options:");
        for (WebElement option : allOptions) {
            System.out.println(option.getText());
        }

        // Adding a brief wait to observe the UI changes
        Thread.sleep(3000);

        // Step 4: Locate and open the dropdown to select the default option
        WebElement categoryDropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='mat-select-value-3']/child::span")));
        
        try {
            categoryDropdown1.click();
        } catch (Exception e) {
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].click();", categoryDropdown1);
        }
        

        // Step 5: Wait for the default option to be clickable and select it
        WebElement defaultOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Standalone  ']")));
       
        defaultOption.click();
        System.out.println("Standalone option is selected");
        
        Thread.sleep(3000);

        

   //..................TEXT DROPDOWN............
     // Step 1: Locate the tooltip element
	    WebElement tooltipElement_TEXT = driver.findElement(By.xpath("(//span[@class='custom-tooltip-container']/mat-icon[@class='mat-icon notranslate material-icons mat-ligature-font mat-icon-no-color'])[2]"));

	    // Step 2: Hover over the tooltip
	   
	    action.moveToElement(tooltipElement_TEXT).perform();
	    Thread.sleep(3000); 

	    // Step 3: Locate the tooltip text
	    WebElement tooltipTextElement123 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='custom-tooltip type'])[1]")));

	    // Use JavaScript Executor to scroll to the tooltip text
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", tooltipTextElement123);

	    // Print the tooltip text using getText()
	    String tooltipText123 = tooltipTextElement123.getText();
	    System.out.println("Tooltip text: " + tooltipText123);

	    // Verify that the tooltip text is visible
	    assert tooltipText123 != null && !tooltipText123.isEmpty() : "Tooltip text is not visible or is empty.";
	    
	    //handover THE TEXT
	    
	    WebElement handover_text = driver.findElement(By.xpath("(//span[@class='required'])[2]"));
	    action.moveToElement(handover_text).perform();
	    Thread.sleep(3000); 
	   // click the text dropdown and verify the response only & request and response
	    WebElement textDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Response only']")));
	       
        try {
        	textDropdown.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", textDropdown);
        }
	    

        // select a request and response
        WebElement Request_response = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Request and Response ']")));
       
        Request_response.click();
        System.out.println("Request and Response option is selected");
        
        //check the visible text on popup
       // String expected_text="Change application type";
        WebElement visible_text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Change application type ']")));
        String actual=visible_text.getText();
       // Assert.assertEquals("the user change the application type page.", expected_text, actual);
        System.out.println("Popup text is correctly displayed: " + actual);
      // click to change the application
        WebElement click_change = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mx-3 button-blue']")));
        click_change.click();
        
       // check the respone while click the request and response & request only
        
     // Step 2: Check if the "Request has Workflow" toggle is enabled
        WebElement requestWorkflowToggle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Request has Workflow']")));
        boolean isEnabled  = requestWorkflowToggle.isEnabled();
        System.out.println("Is 'Request has Workflow' toggle enabled? " + isEnabled );
        boolean expectedState = true;
        if (expectedState) {
            assertTrue("Element should be enabled", isEnabled);
        } else {
            assertFalse("Element should be disabled", isEnabled);
        }
        
        // click the text dropdown and verify the response only & request and response
	    WebElement textDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Request and Response']")));
	       
        try {
        	textDropdown2.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", textDropdown2);
        }
        
     // select a request and response
        WebElement Request_only = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Response only ']")));
       
        Request_only.click();
        System.out.println("Response only option is selected");
        
        //check the visible text on popup
       // String expectedText = " Change application type ";
        WebElement visibleTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='elipse']"))); 
        String actualText = visibleTextElement.getText();
        // Assertion to compare expected and actual text
       // Assert.assertEquals("The visible text on the popup does not match the expected text.", expectedText, actualText);

        // Optional: Print confirmation message
        System.out.println("Popup text is correctly displayed: " + actualText);
        // click to change the application
        WebElement click_change2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mx-3 button-blue']")));
        click_change2.click();
        
    /*   // Check if the "Request has Workflow" toggle is enabled
        WebElement requestWorkflowToggle1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Request has Workflow']")));
        boolean idisable1  = requestWorkflowToggle1.isEnabled();
        System.out.println("Is 'Request has Workflow' toggle disible? " + idisable1 );
        boolean expectedState1 = true;
        if (expectedState1) {
            assertTrue("Element should be disable", idisable1);
        } else {
            assertFalse("Element should be enable", idisable1);
        }
       */
        //.........verify the tool tip of the" work flow"......
        
        // mouse placed on the workflow
 	   WebElement workflow1 = driver.findElement(By.xpath("//span[text()=' Workflow ']"));
 	  action.moveToElement(workflow1).perform();
 	  
 	// Step 1: Locate the tooltip element
	    WebElement tooltipElement34 = driver.findElement(By.xpath("(//span[@class='custom-tooltip-container']/child::mat-icon)[3]"));

	    // Step 2: Hover over the tooltip
	    action.moveToElement(tooltipElement34).perform();
     
	    // Step 3: Locate the tooltip text
	    WebElement tooltipTextElement34 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='custom-tooltip type'])[2]")));
	    // Use JavaScript Executor to scroll to the tooltip text
	   
	    js.executeScript("arguments[0].scrollIntoView(true);", tooltipTextElement34);

	    // Print the tooltip text using getText()
	    String tooltipText1231 = tooltipTextElement34.getText();
	    System.out.println("Tooltip workflow: " + tooltipText1231);

	    // Verify that the tooltip text is visible
	    assert tooltipText1231 != null && !tooltipText1231.isEmpty() : "Tooltip text is not visible or is empty.";
        Thread.sleep(2000);
        
	    //click and select the Assign icon
	    
	    WebElement assignIconButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Click to select']")));
	     assignIconButton1.click();
	    //select the icon
	     WebElement iconOption1 =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='monetization_on']")));
	     js.executeScript("arguments[0].click();", iconOption1);
	     
	     System.out.println("monetization_on icone is selected");
	     Thread.sleep(1000);
	  // Then: Verify if the selected icon is visible after selection  
	     WebElement selectedIcon1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@title='monetization_on'])[1]")));
	     assert selectedIcon1.isDisplayed() :" monetization_on: icon is not visible after selection!";
	     System.out.println("monetization_on: icon is visible after selection.");
	     
	  // select the blue colour "select application colours"
	  // Locate and click the blue application color
	        WebElement blueColorElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='background-color: rgb(12, 0, 152);']")));
	        blueColorElement1.click();
	        System.out.println("Selected application color as blue.");
	        Thread.sleep(1000);
	  // click the save button and verify the respone
	        
	        WebElement click_save1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='save']")));
            click_save1.click();
         // Step 2: Wait for the success message to be visible
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div"))); 

            // Step 3: Assertion to check if the success message is displayed
            Assert.assertTrue(successMessage.isDisplayed(),"Success message not displayed.");

            // Optional: Print a success message to console
            System.out.println(successMessage.getText()+ ":message is displayed.");
            
            Thread.sleep(2000);
            
         
	   
}
    
  
    
    @Given("the user clicks on the Next button on the application settings page")
    public void user_clicked_on_next_button_on_application_setting() {
    	// Click and verify the Next button on "application setting "page
       try {
    	   WebElement click_next = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='arrow_circle_right']")));
           click_next.click();
           System.out.println("Next button clicked successfully.");

           // Verify that the Form page has opened by checking for a unique element on the Form page
           WebElement formPageIndicator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='ng-star-inserted']")));
           Assert.assertTrue(formPageIndicator.isDisplayed(), "The Form page did not open as expected.");
           System.out.println("Form page opened successfully.");
           Thread.sleep(2000);
	} catch (Exception e) {
		e.printStackTrace();
	}
        
    }

    @When("the user verifies the components of the form module")
    public void the_user_verifies_the_components_of_the_form_module() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	

    	    // Iterate over categories with i values: 1, 3, 5, 7, 9
    	    for (int i = 1; i <= 9; i += 2) {
    	        try {
    	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 ng-star-inserted']/child::div)[" + i + "]"));
    	            js.executeScript("arguments[0].scrollIntoView(true);", category);
    	            System.out.println("Category: " + category.getText());

    	            // Determine the starting point and end range for `j` based on `i`
    	            int jStart = 1;
    	            int jEnd = 5; // Default end value for j

    	            // Customize `j` ranges based on `i` value
    	            switch (i) {
    	                case 1:
    	                    jStart = 1;
    	                    jEnd = 5;
    	                    break;
    	                case 3:
    	                    jStart = 6;
    	                    jEnd = 9;
    	                    break;
    	                case 5:
    	                    jStart = 10;
    	                    jEnd = 11;
    	                    break;
    	                case 7:
    	                    jStart = 12;
    	                    jEnd = 12;
    	                    break;
    	                case 9:
    	                    jStart = 13;
    	                    jEnd = 20;
    	                    break;
    	            }

    	            // Iterate over components based on `jStart` and `jEnd` for each `i`
    	            for (int j = jStart; j <= jEnd; j++) {
    	                try {
    	                    WebElement component = driver.findElement(By.xpath("//*[contains(text(),'Basic')]//following::h5[" + j + "]"));
    	                    js.executeScript("arguments[0].scrollIntoView(true);", component);
    	                    System.out.println("Component: " + component.getText());
    	                } catch (Exception e) {
    	                    System.out.println("Component not found for j=" + j);
    	                    e.printStackTrace();
    	                    break;
    	                }
    	            }
    	        } catch (Exception e) {
    	            System.out.println("Category not found for i=" + i);
    	            e.printStackTrace();
    	        }
    	    }}
    

    @When("the user drags and drops all components one by one")
    public void the_user_drags_and_drops_all_components_one_by_one() throws TimeoutException, Exception {
    WebElement targetElement = driver.findElement(By.xpath("//label[text()='Description ']"));
 	    Actions actions = new Actions(driver);
 	   /*	
 	    for (int j = 1; j <= 20; j++) {
 	        try {
 	        	
 	        	{
 	            WebElement component = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[" + j + "]")));
 	           // Scroll the component into view
 	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", component);
 	            String componentText = component.getText();
 	            if (componentText.isEmpty()) componentText = "Unnamed Component";
 	            System.out.println("Dragging component: " + componentText);
 	            actions.dragAndDrop(component, targetElement).perform();
 	            System.out.println("Dropped component: " + componentText);
 	           Thread.sleep(3000); 
 	        	}
 	        } catch (Exception e) {
 	            System.out.println("Error with component at index " + j);
 	            e.printStackTrace();
 	        }
 	    }  */
 	    
    	
    	
 	   // List of XPaths for the components
 	    String[] componentXPaths = {
 	        "//*[contains(text(),'Basic')]//following::h5[1]",
 	        "//*[contains(text(),'Basic')]//following::h5[2]",
 	        "//*[contains(text(),'Basic')]//following::h5[3]",
 	        "//*[contains(text(),'Basic')]//following::h5[4]",
 	        "//*[contains(text(),'Basic')]//following::h5[5]",
 	        "//*[contains(text(),'Basic')]//following::h5[6]",
 	        "//*[contains(text(),'Basic')]//following::h5[7]",
 	        "//*[contains(text(),'Basic')]//following::h5[8]",
 	        "//*[contains(text(),'Basic')]//following::h5[9]",
 	        "//*[contains(text(),'Basic')]//following::h5[10]",
 	        "//*[contains(text(),'Basic')]//following::h5[11]",
 	        "//*[contains(text(),'Basic')]//following::h5[12]",
 	        "//*[contains(text(),'Basic')]//following::h5[13]",
 	        "//*[contains(text(),'Basic')]//following::h5[14]",
 	        "//*[contains(text(),'Basic')]//following::h5[15]",
 	        "//*[contains(text(),'Basic')]//following::h5[16]",
 	        "//*[contains(text(),'Basic')]//following::h5[17]",
 	        "//*[contains(text(),'Basic')]//following::h5[18]",
 	        "//*[contains(text(),'Basic')]//following::h5[19]",
 	        "//*[contains(text(),'Basic')]//following::h5[20]"
 	    };
    
 	   if (componentXPaths.length > 0) {

 	      try {
 	          WebElement component1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[1]")));
 	          dragAndDropComponent(actions, component1, targetElement, "Component 1");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 1");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[2]")));
 	          dragAndDropComponent(actions, component2, targetElement, "Component 2");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 2");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[3]")));
 	          dragAndDropComponent(actions, component3, targetElement, "Component 3");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 3");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[4]")));
 	          dragAndDropComponent(actions, component4, targetElement, "Component 4");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 4");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[5]")));
 	          dragAndDropComponent(actions, component5, targetElement, "Component 5");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 5");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[6]")));
 	          dragAndDropComponent(actions, component6, targetElement, "Component 6");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 6");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[7]")));
 	          dragAndDropComponent(actions, component7, targetElement, "Component 7");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 7");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[8]")));
 	          dragAndDropComponent(actions, component8, targetElement, "Component 8");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 8");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[9]")));
 	          dragAndDropComponent(actions, component9, targetElement, "Component 9");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 9");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[10]")));
 	          dragAndDropComponent(actions, component10, targetElement, "Component 10");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 10");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[11]")));
 	          dragAndDropComponent(actions, component11, targetElement, "Component 11");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 11");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[12]")));
 	          dragAndDropComponent(actions, component12, targetElement, "Component 12");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 12");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component13 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[13]")));
 	          dragAndDropComponent(actions, component13, targetElement, "Component 13");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 13");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component14 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[14]")));
 	          dragAndDropComponent(actions, component14, targetElement, "Component 14");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 14");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component15 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[15]")));
 	          dragAndDropComponent(actions, component15, targetElement, "Component 15");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 15");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component16 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[16]")));
 	          dragAndDropComponent(actions, component16, targetElement, "Component 16");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 16");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component17 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[17]")));
 	          dragAndDropComponent(actions, component17, targetElement, "Component 17");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 17");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component18 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[18]")));
 	          dragAndDropComponent(actions, component18, targetElement, "Component 18");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 18");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component19 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[19]")));
 	          dragAndDropComponent(actions, component19, targetElement, "Component 19");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 19");
 	          e.printStackTrace();
 	      }

 	      try {
 	          WebElement component20 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[20]")));
 	          dragAndDropComponent(actions, component20, targetElement, "Component 20");
 	      } catch (Exception e) {
 	          System.out.println("Error with Component 20");
 	          e.printStackTrace();
 	      }
 	  }
    }

 	  // Helper method for drag and drop
 	  private void dragAndDropComponent(Actions actions, WebElement component, WebElement targetElement, String componentName) {
 	      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", component);
 	      System.out.println("Dragging component: " + componentName);
 	      actions.dragAndDrop(component, targetElement).perform();
 	      System.out.println("Dropped component: " + componentName);
 	  }

    
    
    
    
    
    
    
    
     
    	

    
    
    	@Then("the user click save and next on the form selection and naviagte to response work flow page")
    	public void the_user_click_save_and_next_on_the_form_selection_and_naviagte_to_response_work_flow_page() {
    		
    		
    		
    		 // Save button and verify messages
    	    try {
    	    	Thread.sleep(4000);
    	        WebElement clickSave = driver.findElement(By.xpath("//mat-icon[text()='save']"));
    	        clickSave.click();
                Thread.sleep(4000);
    	        // Verify save confirmation message
    	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
    	        String actualMessage = appliSave.getText();
    	        if (!actualMessage.equals("Application updated.") && !actualMessage.equals("Application with same name already exists")) {
    	            Assert.fail("Unexpected message: " + actualMessage);
    	        }
    	        System.out.println("Application saved with message: " + actualMessage);

    	        // Next button and navigate to workflow selection
    	        WebElement clickNext = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='secondary-button footer-button ml-4 ng-star-inserted']/strong[@class='mr-2']")));
    	        clickNext.click();

    	        // Verify WORKFLOW DETAILS page
    	        WebElement textWorkflowPage = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	            By.xpath("//label[text()='WORKFLOW DETAILS']")
    	        ));
    	        Assert.assertEquals("WORKFLOW DETAILS", textWorkflowPage.getText());
    	        System.out.println("Workflow selection opened successfully");

    	    } catch (Exception e) {
    	        System.out.println("Error during save or next navigation");
    	        e.printStackTrace();
    	    }

    	    // Workflow dropdown selection
    	    try {
    	        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	            By.xpath("//mat-select[@formcontrolname='workflow']")
    	        ));
    	        dropdownElement.click();

    	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='1 Level Approval']")));
    	        option.click();

    	        // Confirm "1 Level Approval" is selected
    	        Assert.assertEquals("1 Level Approval", dropdownElement.getText().trim());
    	        System.out.println("Verified: '1 Level Approval' is selected.");

    	    } catch (Exception e) {
    	        System.out.println("Error selecting '1 Level Approval' from dropdown");
    	        e.printStackTrace();
    	    }
    	}
    		
    		
    		
    		
    	/*	
    		 try {
    		        // Wait for and click on the 'Title' field to ensure it's active
    		        WebElement titleLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Title ']")));
    		        titleLabel.click();
    		        
    		        // Wait for the description input field to be visible and clear any existing text
    		        WebElement editDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(
    		            By.xpath("//label[text()='Field label']//following-sibling::input")
    		        ));
    		        editDescription.clear();

    		        // Enter the new title/description text
    		        editDescription.sendKeys(title);

    		        // Capture and verify the entered text to ensure it matches the expected title
    		        String enteredText = editDescription.getAttribute("value").trim();
    		        Assert.assertEquals("Entered text does not match expected title!", title, enteredText);
    		        System.out.println("Verified Title/Description input successfully as: " + enteredText);

    		    } catch (Exception e) {
    		        System.out.println("Error while editing title or description - " + e.getMessage());
    		        e.printStackTrace();
    		    }
    		}*/
    	    
    	    
    	    
    	    
    	    
    	    
    	//    -----------------------------------------------
    	    
    	    @And("the user is on the existing application page")
        	public void the_user_is_on_the_existing_application_page() throws Exception {
    	    	try {
    	    	   

    	    	    // Wait for the "Application.com" element to be visible
    	    	    WebElement application1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	    	            By.xpath("//div[text()='Application.com']")
    	    	    ));

    	    	    // Hover over the application element
    	    	    Actions actions = new Actions(driver);
    	    	    actions.moveToElement(application1).perform();

    	    	    // Wait for the edit icon to appear after hovering
    	    	    WebElement clickEditIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	    	            By.xpath("(//div[text()='Application.com']/following::img[@title='Edit'])[1]")
    	    	    ));

    	    	    // Click on the edit icon
    	    	    clickEditIcon.click();

    	    	    // Validate the resulting URL
    	    	    String expectedUrl = "https://admin-dev.aapli.app/form-builder/fff9f0df-4a1b-41a4-8833-386bcbab911a";
    	    	    String actualUrl = driver.getCurrentUrl();
    	    	    Assert.assertEquals(actualUrl, expectedUrl, "The URL of the Users page is not as expected.");
    	    	} catch (Exception e) {
    	    	    System.out.println("An error occurred while trying to hover and click the edit icon.");
    	    	    e.printStackTrace();
    	    	}

    	    }

    	    
             @Then("the user clicks the Form tab")
             public void the_user_clicks_the_form_tabs() {
            	 
            WebElement Click_form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='fbtab-Form']")));
            Click_form.click();
             }
             
             
             
             @Then("User verifies and changes the settings of the Single Line Input")
             public void verifyAndChangeSingleLineInputSettings() throws Exception {
            	 JavascriptExecutor js = (JavascriptExecutor) driver;
            	    
            	    // Verify and change the settings of the Single Line Input
            	    WebElement singleLine = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[1]")));
            	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
            	    
            	    // Perform drag-and-drop action
            	    Actions actions = new Actions(driver);
            	    actions.dragAndDrop(singleLine, targetTabElement).perform();
            	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Single Line Input ']")));
            	    
            	    Assert.assertTrue(droppedElement.isDisplayed(), "Single Line Input component was not successfully dropped.");
            	    System.out.println("Successfully dragged and dropped the Single Line Input component to the Response tab.");
            	    
            	    // Verify right-side settings/components
            	    for (int i = 1; i <= 9; i += 2) {
            	        try {
            	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
            	            js.executeScript("arguments[0].scrollIntoView(true);", category);
            	            String categoryText = category.getText();
            	            System.out.println("Category: " + categoryText);

            	            if (categoryText.isEmpty()) {
            	                System.err.println("Warning: Category text is empty for element at index " + i);
            	            }
            	        } catch (NoSuchElementException e) {
            	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
            	        } catch (Exception e) {
            	            System.err.println("An unexpected error occurred: " + e.getMessage());
            	        }
            	    }

            	    // Save the application
            	    try {
            	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
            	        clickSave.click();
            	        System.out.println("Clicked on the save button.");

            	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
            	        String actualMessage = appliSave.getText();

            	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
            	        System.out.println("Application saved with message: " + actualMessage);
            	    } catch (Exception e) {
            	        System.out.println("An error occurred while saving the application.");
            	        e.printStackTrace();
            	    }

            	    // Frontend Verification
            	    ((JavascriptExecutor) driver).executeScript("window.open()");
            	    Set<String> windowHandles = driver.getWindowHandles();
            	    Iterator<String> iterator = windowHandles.iterator();
            	    String firstWindowHandle = iterator.next();
            	    String secondWindowHandle = iterator.next();
            	    
            	 // Switch to the front-end window
                    driver.switchTo().window(secondWindowHandle);
                    Thread.sleep(2000);
                    driver.navigate().refresh();
                    System.out.println("Switched to the front-end window. Page refreshed.");
    	            
    	            
    	            System.out.println("Switched to the front-end window.");
            	    driver.get("https://demo79.aapli.app/login");

            	    // Handle agreement pop-up if present
            	    try {
            	        WebElement clickAgree = wait.until(ExpectedConditions.elementToBeClickable(By.className("cc-nb-okagree")));
            	        clickAgree.click();
            	    } catch (Exception e) {
            	        System.out.println("Agree button not found or not clickable.");
            	    }

            	    try {
            	        // Log in to the application
            	        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            	        emailField.sendKeys("demo79@mailinator.com");

            	        WebElement passwordField = driver.findElement(By.id("password"));
            	        passwordField.sendKeys("Ideabridge@1234");

            	        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            	        continueButton.click();
            	        Thread.sleep(4000);

            	        String expectedURL1 = "https://demo79.aapli.app/";
            	        Assert.assertEquals(driver.getCurrentUrl(), expectedURL1, "The URL of the Users page is not as expected.");
            	        System.out.println("Successfully logged in to the Ideabridge website.");
            	    } catch (Exception e) {
            	        System.err.println("Error during login: " + e.getMessage());
            	    }

            	    try {
            	        // Interact with the "View All" and specific entries
            	        WebElement viewAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='view-all']")));
            	        viewAllButton.click();
            	        System.out.println("Clicked on the 'View All' button.");

            	        WebElement divA = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=' A ']")));
            	        divA.click();
            	        System.out.println("Clicked on the div with text 'A'.");
            	    } catch (Exception e) {
            	        System.err.println("Error while interacting with 'View All' and specific entries: " + e.getMessage());
            	    }

            	    try {
            	        // Verify and interact with the Application title
            	        String expectedTitle = "Application.com";
            	        WebElement titleElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=' Application.com ']")));
            	        String actualTitle = titleElement.getText();

            	        Assert.assertTrue(titleElement.isDisplayed(), "Title is not visible on the page!");
            	        Assert.assertEquals(actualTitle, expectedTitle, "Title verification failed! Expected: '" + expectedTitle + "', Found: '" + actualTitle + "'");
            	        System.out.println("Title is visible and verified as: " + actualTitle);

            	        titleElement.click();
            	        System.out.println("Clicked on 'Application.com'.");

            	        // Wait for a moment to ensure the next element is ready
            	        Thread.sleep(3000);

            	        // Click on the +ADD Icon
            	        WebElement clickAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='mat-mdc-menu-trigger add-item-button']/child::mat-icon")));
            	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            	        // Scroll into view in case the element is off-screen
            	        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", clickAdd);

            	        // Perform click using JavaScript
            	        jsExecutor.executeScript("arguments[0].click();", clickAdd);
            	        System.out.println("Clicked on the +ADD icon using JavaScript.");

            	    } catch (Exception e) {
            	        System.err.println("Error during title verification or while clicking on the +ADD icon: " + e.getMessage());
            	    }


            	    try {
            	        // Verify the application page opened on the frontend
            	        String expectedText1 = "Response";
            	        WebElement actualResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='py-2 ng-star-inserted nav-link-invalid']")));
            	        String actualText1 = actualResponse.getText();
            	        Assert.assertEquals(actualText1, expectedText1, "Text verification failed! Expected: '" + expectedText1 + "', Found: '" + actualText1 + "'");
            	        System.out.println("User successfully opened the application page on frontend.");
            	    } catch (Exception e) {
            	        System.err.println("Error while verifying the application page response text: " + e.getMessage());
            	    }

            	    try {
            	        // Verify that "Single Line Input" is displayed
            	        WebElement singleLineInputLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='Single Line Input']")));
            	        Assert.assertTrue(singleLineInputLabel.isDisplayed(), "'Single Line Input' is not visible on the page!");
            	        System.out.println("'Single Line Input' is visible on the page.");
            	    } catch (Exception e) {
            	        System.err.println("Error while verifying 'Single Line Input' visibility: " + e.getMessage());
            	    }

    	            
    	         

                    // Switch to the Admin Backend window
                   
                    
            	    
                    // Switch back to the original window
                    driver.switchTo().window(firstWindowHandle);
                    System.out.println("Switched back to the original window.");
                
                    
            
                    
                   
                 
                 

                    try {
                        // Set the Field Label
                        WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group ng-star-inserted']/child::input")));
                        fieldLabelInput.clear();
                        fieldLabelInput.sendKeys("Employee Name");
                        Assert.assertEquals(fieldLabelInput.getAttribute("value"), "Employee Name", "Field Label input text does not match expected value.");
                        System.out.println("Field Label set to 'Employee Name' successfully.");
                    } catch (Exception e) {
                        System.err.println("Failed to set the Field Label: " + e.getMessage());
                    }

                    try {
                        // Set the Placeholder Text
                        WebElement placeholderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Placeholder text']/following-sibling::input")));
                        placeholderInput.clear();
                        placeholderInput.sendKeys("Name");
                        Assert.assertEquals(placeholderInput.getAttribute("value"), "Name", "Placeholder text does not match expected value.");
                        System.out.println("Placeholder Text set to 'Name' successfully.");
                    } catch (Exception e) {
                        System.err.println("Failed to set the Placeholder Text: " + e.getMessage());
                    }

                    try {
                        // Set the Help Text
                        WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                        helpTextInput.clear();
                        helpTextInput.sendKeys("Enter valid name");
                        Assert.assertEquals(helpTextInput.getAttribute("value"), "Enter valid name", "Help Text does not match expected value.");
                        System.out.println("Help Text set to 'Enter valid name' successfully.");
                    } catch (Exception e) {
                        System.err.println("Failed to set the Help Text: " + e.getMessage());
                    }

                    try {
                        // Set the Tooltip
                        WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                        tooltipInput.clear();
                        tooltipInput.sendKeys("name as per aadhar");
                        Assert.assertEquals(tooltipInput.getAttribute("value"), "name as per aadhar", "Tooltip text does not match expected value.");
                        System.out.println("Tooltip set to 'name as per aadhar' successfully.");
                    } catch (Exception e) {
                        System.err.println("Failed to set the Tooltip: " + e.getMessage());
                    }
                    
                    
                    //select the enable duplicate 
                    
                   
                        try {
                            WebElement enableDuplicateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Enable Duplicate ']/preceding::input[@type='checkbox']")));
                            if (!enableDuplicateCheckbox.isSelected()) {
                            	 
                                 js.executeScript("arguments[0].click();", enableDuplicateCheckbox);
                                System.out.println("Checked: Enable Duplicate");
                                Assert.assertTrue(enableDuplicateCheckbox.isSelected(), enableDuplicateCheckbox + " checkbox is not selected after clicking!");
                                System.out.println("Enable Duplicate checkbox is already selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                        }

                     // Index Numbers Checkbox
                        try {
                            WebElement indexNumbersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Index Numbers ']/preceding::input[@type='checkbox'])[2]")));
                            if (!indexNumbersCheckbox.isSelected()) {
                            	 js.executeScript("arguments[0].click();", indexNumbersCheckbox);
                                System.out.println("Checked: Index Numbers");
                                // Verify if the checkbox is selected after clicking
                                Assert.assertTrue(indexNumbersCheckbox.isSelected(), indexNumbersCheckbox + " checkbox is not selected after clicking!");
                            } else {
                                System.out.println("Index Numbers checkbox is already selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Index Numbers' checkbox: " + e.getMessage());
                        }

                        // Repeat Heading Checkbox
                        try {
                            WebElement repeatHeadingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Repeat Heading ']/preceding::input[@type='checkbox'])[3]")));
                            if (!repeatHeadingCheckbox.isSelected()) {
                            	 js.executeScript("arguments[0].click();", repeatHeadingCheckbox);
                                System.out.println("Checked: Repeat Heading");
                                // Verify if the checkbox is selected after clicking
                                Assert.assertTrue(repeatHeadingCheckbox.isSelected(), repeatHeadingCheckbox + " checkbox is not selected after clicking!");
                            } else {
                                System.out.println("Repeat Heading checkbox is already selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Repeat Heading' checkbox: " + e.getMessage());
                        }

                        
                        
                     // Save the application
                	   
                	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
                	        clickSave1.click();
                	        System.out.println("Clicked on the save button.");

                	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
                	        String actualMessage1 = appliSave1.getText();

                	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
                	        System.out.println("Application saved with message: " + actualMessage1);
           
                        
                        
                        
                        
                        
                     // Step 1: Pause briefly to allow for any loading or processing
        	            Thread.sleep(2000);

        	            // Step 2: Switch to the front-end window
        	            driver.switchTo().window(secondWindowHandle);
        	            System.out.println("Switched to the front-end window.");

        	            // Step 3: Refresh the page to reload all elements
        	            driver.navigate().refresh();
        	            System.out.println("Page refreshed successfully.");


        	            
        	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
        	            
        	            
        	            try {
                            // Check if the feild label Name Reflected front end or not
                            WebElement employeeNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Employee Name']")));
                            Assert.assertTrue(employeeNameLabel.isDisplayed(), "Employee Name label is not visible.");
                            System.out.println(employeeNameLabel.getText()+" :label is visible on the page.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or display 'Employee Name' label: " + e.getMessage());
                        }

                        try {
                            // Check if the Help text is reflected front end or not
                            WebElement enterYourText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Employee Name']/ancestor::div[contains(@class, 'field-container')]//p[contains(@class, 'input-footer mb-2')]")));
                            Assert.assertTrue(enterYourText.isDisplayed(), "Help text is not visible.");
                            System.out.println( enterYourText.getText()+" :text is visible on the page");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or display help text: " + e.getMessage());
                        }

                        try {
                            // Check if the placeholder text is visible
                            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Employee Name']/ancestor::div[contains(@class, 'field-container')]//input[contains(@type, 'text')]")));
                            Assert.assertTrue(inputField.isDisplayed(), "Input field text is not visible.");
                            System.out.println("Name :text is visible on the page.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or display the input field text: " + e.getMessage());
                        }

                        try {
                            // Click on the tooltip icon near 'Employee Name'
                            WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Employee Name']/child::span/mat-icon")));
                            tooltipIcon.click();
                            System.out.println("Tooltip icon clicked successfully.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                        }

                        try {
                            // Verify that the tooltip content text is visible after clicking the tooltip icon
                            WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Employee Name']/child::span/div/p")));
                            Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                            System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                        }
                	    
                        
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
                        // verify the repeated duplicate headings with add index number ----in ADMIN FRONT-END
                        try {
                            // Step 1: Click the duplicate icon to add a new heading
                            WebElement duplication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='duplicate-icon-container ng-star-inserted']/mat-icon[@title='Add ']")));
                            Actions action = new Actions(driver);
                            // Hover over the element to make sure it's visible if necessary
                            action.moveToElement(duplication).perform();
                            duplication.click();
                            System.out.println("Added a duplicate heading.");;

                           
                        
                        // Step 2: Verify duplicate headings
                	    System.out.println("Step 2: Verifying duplicate headings.");
                	    List<WebElement> duplicateHeadings = driver.findElements(By.xpath("//label[contains(text(),'Employee Name')]"));
                	    Assert.assertTrue(duplicateHeadings.size() > 1, "No duplicate headings found. Verification failed.");

                	    System.out.println("Verification successful. Total occurrences of ' Employee Name ': " + duplicateHeadings.size());

                	    for (int i = 0; i < duplicateHeadings.size(); i++) {
                	        WebElement heading = duplicateHeadings.get(i);
                	        if (heading.isDisplayed()) {
                	            System.out.println("Duplicate " + (i + 1) + ": " + heading.getText());
                	        } else {
                	            System.out.println("Duplicate " + (i + 1) + " is not visible on the page.");
                	        }
                	    }

                	    // Step 3: Click the remove icon to delete a heading
                	    System.out.println("Step 3: Removing duplicate heading.");
                	    
                	    WebElement tooltipIcon12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' Employee Name 2 ')]//span/child::mat-icon")));
                	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon12);
                	    action.moveToElement(tooltipIcon12).perform();  
         
                	    WebElement clickRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' Employee Name 2 ')]/following::mat-icon[text()='remove_circle_outline']")));
                	    action.moveToElement(clickRemove).perform();
                	    clickRemove.click();
                	    System.out.println("Remove icon clicked. Heading should be removed.");

                	    // Step 4: Verify the heading is removed
                	    System.out.println("Step 4: Verifying removal of duplicate headings.");
                	    duplicateHeadings = driver.findElements(By.xpath("//label[contains(text(),'Employee Name')]"));
                	    Assert.assertEquals(duplicateHeadings.size(), 1, "Duplication removal failed. Multiple headings still found.");

                	    System.out.println("Duplication successfully removed. Only one occurrence of '  Employee Name ' found.");
                	} catch (Exception e) {
                	    e.printStackTrace();
                	    System.out.println("An error occurred: " + e.getMessage());
                	    Assert.fail("Test case failed due to unexpected error: " + e.getMessage());
                	}

                 
                 
                        
                        
                        
                         
                        
                        
                        
                        
                        // Switch back to the original window
                        driver.switchTo().window(firstWindowHandle);
                        System.out.println("Switched back to the original window.");
                         
                        
                        
                        
                        
                        // Allow Attachment Checkbox
                        try {
                            WebElement allowAttachmentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Allow attachment ']/preceding::input[@type='checkbox'])[4]")));
                            if (!allowAttachmentCheckbox.isSelected()) {
                            	js.executeScript("arguments[0].click();", allowAttachmentCheckbox);
                                System.out.println("Checked: Allow Attachment");
                                Assert.assertTrue(allowAttachmentCheckbox.isSelected(), allowAttachmentCheckbox + " checkbox is not selected after clicking!");
                            } else {
                                System.out.println("Allow Attachment checkbox is already selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Allow Attachment' checkbox: " + e.getMessage());
                        }

                        // Allow Audio Checkbox
                        try {
                            WebElement allowAudioCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Allow audio ']/preceding::input[@type='checkbox'])[5]")));
                            if (!allowAudioCheckbox.isSelected()) {
                            	js.executeScript("arguments[0].click();", allowAudioCheckbox);
                                System.out.println("Checked: Allow Audio");
                                Assert.assertTrue(allowAudioCheckbox.isSelected(), allowAudioCheckbox + " checkbox is not selected after clicking!");

                            } else {
                                System.out.println("Allow Audio checkbox is already selected.");
                            }
                        } catch (Exception e1) {
                            System.out.println("Error locating or clicking 'Allow Audio' checkbox: " + e1.getMessage());
                        }

                        
                        // click Filterable Checkbox
                        try {
                            WebElement allowAttachmentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Filterable ']/preceding::input[@type='checkbox'])[6]")));
                            if (!allowAttachmentCheckbox.isSelected()) {
                            	js.executeScript("arguments[0].click();", allowAttachmentCheckbox);
                                System.out.println("Checked: Allow Attachment");
                                Assert.assertTrue(allowAttachmentCheckbox.isSelected(), allowAttachmentCheckbox + " checkbox is not selected after clicking!");
                            } else {
                                System.out.println("Allow Attachment checkbox is already selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Allow Attachment' checkbox: " + e.getMessage());
                        }
                        
                        
                        // Final confirmation message
                        System.out.println("All specified checkboxes have been checked and verified.");

                    
                    
                        
                        // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                        try {
                        WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                        js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                        js.executeScript("arguments[0].click();", click_condition);
                        
                        System.out.println("user click the condition category");
                        
                    
                        
                            WebElement enable_Use_in_workflow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Use in workflow ']/preceding::input[@type='checkbox'])[7]")));
                            if (!enable_Use_in_workflow.isSelected()) {
                            	 
                                 js.executeScript("arguments[0].click();", enable_Use_in_workflow);
                                System.out.println("Checked: Use in workflow");
                                Assert.assertTrue(enable_Use_in_workflow.isSelected(), enable_Use_in_workflow + " checkbox is not selected after clicking!");
                                System.out.println("Use in workflow checkbox is  selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                        }
                      
                        
                        //step:2 verify the unique varible name input
                        
                        WebElement unique_varibleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group d-flex ng-star-inserted']/child::label")));
                      

                        if (unique_varibleInput.isDisplayed()) {
                            System.out.println(" unique varible name input : is displayed.");
                        } else {
                            System.out.println("The unique varible name input is not displayed.");
                        }
                        
                        //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                        
                        try {
                            // Step 1: Click on the edit icon
                            WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                            js.executeScript("arguments[0].click();", clickedit_icon);
                            System.out.println("User clicked the edit icon");

                            // Step 2: Verify the expected text on the edit page
                            String expected_text12 = "Rule Name:";
                            WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                            String actual_text12 = edit_rules.getText();
                            
                            // Assertion with custom message for failure
                            Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                            System.out.println("Edit rules for Employee Name: page opened successfully");

                            // Step 3: Close the page
                            WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                            js.executeScript("arguments[0].click();", close_page);
                            System.out.println("Edit page closed successfully");

                        } catch (Exception e) {
                            // Print the stack trace and a custom error message
                            e.printStackTrace();
                            System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                        }
                        
                        
                        
                        
                        // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                        
                        // click the appearence 
                        
                     // Click the Appearance category
                        try {
                            WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                            js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                            js.executeScript("arguments[0].click();", clickAppearance);

                            // Define the sizes to loop through
                            String[] sizes = {"large", "medium", "small"};


                            for (String size : sizes) {
                                try {
                                    // Click the size (medium, small, large)
                                    WebElement sizeElement = driver.findElement(By.xpath("//label[text()='" + size + "']"));
                                    js.executeScript("arguments[0].click();", sizeElement);
                                    clickSave1.click();

                                    // Switch to the front-end window
                                    driver.switchTo().window(secondWindowHandle);
                                    driver.navigate().refresh();
                                    System.out.println("User selected the " + size + " size, reflected on the front end.");

                                    // Verify field size
                                    WebElement SIZE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Employee Name']/ancestor::div[contains(@class, 'field-container')]//input[contains(@type, 'text')]")));
                                    Dimension fieldSize = SIZE.getSize();
                                    System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Width: " + fieldSize.getWidth());
                                    System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Height: " + fieldSize.getHeight());

                                    // Switch back to the original window
                                    driver.switchTo().window(firstWindowHandle);
                                    System.out.println("Switched back to the original window.");

                                } catch (Exception e) {
                                    System.out.println("An error occurred while handling size '" + size + "': " + e.getMessage());
                                }
                            }

                            // Click the Validation section
                            WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']")));
                            js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                            js.executeScript("arguments[0].click();", clickValidation);
                            System.out.println("User clicked the validation category.");

                            // Handle checkboxes: Required and Disable
                            try {
                                WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Required ']/preceding::input[@type='checkbox'])[8]")));
                                if (!requiredCheckbox.isSelected()) {
                                    js.executeScript("arguments[0].click();", requiredCheckbox);
                                    System.out.println("Checked: Required");
                                    Assert.assertTrue(requiredCheckbox.isSelected(), "Required checkbox is not selected after clicking!");
                                } else {
                                    System.out.println("Required checkbox is already selected.");
                                }

                                WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Disable ']/preceding::input[@type='checkbox'])[9]")));
                                if (!disableCheckbox.isSelected()) {
                                    js.executeScript("arguments[0].click();", disableCheckbox);
                                    System.out.println("Checked: Disable");
                                    Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                                } else {
                                    System.out.println("Disable checkbox is already selected.");
                                }
                               
                                
                                Thread.sleep(2000);
                                clickSave1.click();
                                Thread.sleep(2000);
                                // Switch to the front-end window
                                driver.switchTo().window(secondWindowHandle);
                                Thread.sleep(2000);
                                driver.navigate().refresh();
                                System.out.println("Switched to the front-end window. Page refreshed.");

                                // Check if the Disable text field is enabled
                                WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                                if (disableField.isEnabled()) {
                                    System.out.println("The text field is disabled.");
                                } else {
                                    System.out.println("The text field is enable.");
                                }
                                
                                Thread.sleep(2000);
                                // Switch back to the original window
                                driver.switchTo().window(firstWindowHandle);
                                
                                
                                
                                
                                //unchecked the Disible check box
                                
                             // Uncheck the "Disable" checkbox if it is selected
                                
                                
                                WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Disable ']/preceding::input[@type='checkbox'])[9]")));
                                if (disableCheckbox1.isSelected()) {
                                    // Use JavaScript to uncheck the checkbox
                                    js.executeScript("arguments[0].click();", disableCheckbox1);
                                    System.out.println("Unchecked: Disable checkbox.");
                                    
                                    // Verify the checkbox is now unchecked
                                    Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                                } else {
                                    System.out.println("Disable checkbox is already unchecked.");
                                }

                                Thread.sleep(2000);
                                clickSave1.click();
                                Thread.sleep(2000);

                                // Switch to the front-end window
                                driver.switchTo().window(secondWindowHandle);
                                Thread.sleep(2000);
                                driver.navigate().refresh();
                                System.out.println("Switched to the front-end window. Page refreshed.");

                                // Check if the "Disable" text field is enabled
                                WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, 'ng-star-inserted') and contains(text(), 'Employee')]")));
                                if (disableField1.isEnabled()) {
                                    System.out.println("The text field is enable.");
                                } else {
                                    System.out.println("The text field is Disable.");
                                }
                                Thread.sleep(2000);
                                // Switch back to the original window
                                driver.switchTo().window(firstWindowHandle);
                                System.out.println("switch to admin backend");
                                
                                
                                
                                
                                
                                
                            } catch (Exception e) {
                                System.out.println("Error handling validation checkboxes: " + e.getMessage());
                            }

                        } catch (Exception e) {
                            System.out.println("An error occurred in the appearance and validation process: " + e.getMessage());
                        }

                        
                        
                        
                        
                        
                        try {
                           
                        	  // 1. Interact with "Minimum Length" field
                            WebElement minLengthInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Minimum Length']/following-sibling::input")));
                            minLengthInput.clear();
                            minLengthInput.sendKeys("1");
                            System.out.println("Minimum Length input set to 1.");

                            // Assert value
                            String minValue = minLengthInput.getAttribute("value");
                            assert minValue.equals("1") : "Assertion failed: Minimum Length value is not 1!";
                            System.out.println("Assertion passed: Minimum Length value is 1.");

                            // 2. Interact with "Maximum Length" field
                            WebElement maxLengthInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//label[text()='Maximum Length']/following-sibling::input")));
                            maxLengthInput.clear();
                            maxLengthInput.sendKeys("50");
                            System.out.println("Maximum Length input set to 50.");

                            // Assert value
                            String maxValue = maxLengthInput.getAttribute("value");
                            assert maxValue.equals("50") : "Assertion failed: Maximum Length value is not 50!";
                            System.out.println("Assertion passed: Maximum Length value is 50.");

                          
                            
                            
                         // click the dropdown  "Maximum File Size"
                            WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Maximum File Size']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                            dropdown1.click();
                            System.out.println("Dropdown clicked.");

                            // Verify all options and print their text
                            List<WebElement> options = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
                            System.out.println("Verifying and printing all options:");
                            int i = 1;
                            for (WebElement option : options) {
                                if (option.isDisplayed()) {
                                    System.out.println("Option " + i + ": " + option.getText());
                                } else {
                                    System.out.println("Option " + i + " is not visible.");
                                }
                                i++;
                            }

                            // Click the 5th option
                            WebElement fifthOption = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//span[@class='mat-option-text'])[5]")));
                            fifthOption.click();
                            System.out.println("5th option clicked: " + fifthOption.getText()); 
                            
                            
                                            
                            
                            
                
                            try {
                                // Interact with "File Extension" dropdown
                                WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='File Extensions']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                                js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
                                js.executeScript("arguments[0].click();", dropdown);
                                System.out.println("Dropdown clicked.");

                                // Select checkboxes (1 to 10)
                                for (int j = 1; j <= 10; j++) {
                                    try {
                                        // Locate the checkbox element
                                        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@id,'mat-option')]/mat-pseudo-checkbox)[" + j + "]")));
                                        
                                        // Scroll to the checkbox
                                        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkbox);
                                        
                                        Thread.sleep(1000);
                                        // Click the checkbox
                                        js.executeScript("arguments[0].click();", checkbox);
                                        
                                        // Locate the corresponding text element within the mat-option
                                        WebElement textElement = driver.findElement(
                                            By.xpath("(//*[contains(@id,'mat-option')])[" + j + "]//span[@class='mat-option-text']"));
                                        
                                        // Retrieve and print the text
                                        String checkboxText = textElement.getText();
                                        System.out.println("Checked: " + checkboxText);
                                    } catch (Exception e) {
                                        System.err.println("Error handling checkbox " + j + ": " + e.getMessage());
                                    }
                                }

                                // Close the dropdown after selecting checkboxes
                                // Click the dropdown again to close it (or click outside the dropdown)
                                WebElement closeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='File Extensions']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                                js.executeScript("arguments[0].click();", closeDropdown);
                                System.out.println("Dropdown closed.");
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                            } 
                               
                        
                              
                         // Wait for the element you want to interact with
                            Thread.sleep(2000);
                            WebElement element = driver.findElement(By.xpath("//mat-icon[text()='save']"));

                            // Create an Actions object
                            Actions actions1 = new Actions(driver);

                            // Perform a double-click action on the element
                            actions1.doubleClick(element).perform();

                            System.out.println("Double-clicked on the save icon."); 

                          
                        } catch (Exception e) {
                            System.err.println("An error occurred: " + e.getMessage());
                            
                        }
                        
                        
                        
                     
                        
                     
                        
                        
                        //enter the Maximum Height-----Maximum Width
                        
                        WebElement Maximum_Height = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Maximum Height']/following-sibling::input")));
                        Maximum_Height.clear();
                        Maximum_Height.sendKeys("1");
                        System.out.println("Maximum Height input set to 1.");

                        // Assert value
                        String minValue2 = Maximum_Height.getAttribute("value");
                        assert minValue2.equals("1") : "Assertion failed: Maximum Height value is not 1!";
                        System.out.println("Assertion passed: Maximum Height value is 1.");

                        // 2. Interact with "Maximum Length" field
                        WebElement Maximum_Width = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Maximum Width']/following-sibling::input")));
                        Maximum_Width.clear();
                        Maximum_Width.sendKeys("50");
                        System.out.println("Maximum Width input set to 50.");

                        // Assert value
                        String maxValue1 = Maximum_Width.getAttribute("value");
                        assert maxValue1.equals("50") : "Assertion failed: Maximum Width value is not 50!";
                        System.out.println("Assertion passed: Maximum Width value is 50.");
                        
                        
                      
                        
                         // save it
                        
                        clickSave1.click();
                        
                            
                        // Switch to the front-end window
                        driver.switchTo().window(secondWindowHandle);
                        Thread.sleep(2000);
                        driver.navigate().refresh();
                        System.out.println("Switched to the front-end window. Page refreshed.");
                        
                        
                        
                        // Check if the attachment element is visible
                        try {
                            // First Upload: File 1
                            String filePath1 = "C:\\Users\\HP\\Downloads\\FRAME WORK.pdf";
                            WebElement attachment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[text()='Add Attachment']")));
                            if (attachment.isDisplayed()) {
                                attachment.click();
                                System.out.println("Attachment dialog opened for File 1.");

                                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);

                                Robot rb = new Robot();
                                rb.delay(2000);

                                // Simulate CTRL+V and ENTER for File 1
                                rb.keyPress(KeyEvent.VK_CONTROL);
                                rb.keyPress(KeyEvent.VK_V);
                                rb.keyRelease(KeyEvent.VK_V);
                                rb.keyRelease(KeyEvent.VK_CONTROL);
                                rb.keyPress(KeyEvent.VK_ENTER);
                                rb.keyRelease(KeyEvent.VK_ENTER);

                                System.out.println("File 1 uploaded successfully.");
                            } else {
                                System.out.println("Attachment button not visible for File 1.");
                            }
                        
                
                            // Verify File 1 Upload
                            WebElement uploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//label[text()=' Employee Name ']/ancestor::div[@class='row ng-star-inserted']//li[contains(@class,'uploded-files')]/span")));
                            Assert.assertTrue(uploadedFile.isDisplayed(),"Uploaded file 1 is not displayed.");
                            System.out.println("File 1 is displayed: " + uploadedFile.getText());

                            // Delete File 1
                            WebElement deleteFile = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//label[text()=' Employee Name ']/ancestor::div[@class='row ng-star-inserted']//li[contains(@class,'uploded-files')]/mat-icon[contains(text(),' delete')]")));
                            deleteFile.click();
                            System.out.println("File 1 deleted successfully.");

                            // Re-Upload: File 1
                            if (attachment.isDisplayed()) {
                                attachment.click();
                                System.out.println("Attachment dialog opened again for File 1.");

                                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);
                                Robot rb = new Robot();
                                rb.delay(2000);

                                // Simulate CTRL+V and ENTER for Re-Upload
                                rb.keyPress(KeyEvent.VK_CONTROL);
                                rb.keyPress(KeyEvent.VK_V);
                                rb.keyRelease(KeyEvent.VK_V);
                                rb.keyRelease(KeyEvent.VK_CONTROL);
                                rb.keyPress(KeyEvent.VK_ENTER);
                                rb.keyRelease(KeyEvent.VK_ENTER);

                                System.out.println("File 1 re-uploaded successfully.");
                            } else {
                                System.out.println("Attachment button not visible for re-upload.");
                            }

                           
                        } catch (Exception e) {
                            System.out.println("Error during file upload process: " + e.getMessage());
                        }

                                                
                          
                        try {  
                        // Check if the volume icon is visible and click it 
                        WebElement volumeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='volume_up']")));
                        if (volumeIcon.isDisplayed()) {
                            System.out.println("Volume icon is visible.");
                            // Click on the volume icon
                            volumeIcon.click();
                        } else {
                            System.out.println("Volume icon is not visible.");
                        }
                        
                       Thread.sleep(3000);
                       
                            // stop the recording and verify the file is dispalted or not
                           

                            // Locate and click the button
                            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()=' Employee Name ']/ancestor::div[@class='row ng-star-inserted']//button[@class='stop-button ng-star-inserted']/mat-icon")));
                            js.executeScript("arguments[0].scrollIntoView(true);", button); // Scroll to the button
                            button.click(); // Click the button
                            System.out.println("Button clicked.");

                            // Verify if the Audio file is displayed or not
                            WebElement Audiofile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()=' Employee Name ']/ancestor::div[@class='row ng-star-inserted']//li[@id='attachData.Name']/span")));
                            
                            boolean isDisplayed = Audiofile.isDisplayed();
                            Assert.assertTrue(isDisplayed,"The element is not displayed as expected."); 
                            
                            // Print the text of the element
                            String audiofileNameText = Audiofile.getText();
                            System.out.println("Audiofile name is: " + audiofileNameText);

                        } catch (Exception e) {
                            System.err.println("An error occurred: " + e.getMessage());
                        }

                       
                        
                   
                        Thread.sleep(2000);
                        // Switch back to the original window
                        driver.switchTo().window(firstWindowHandle);
                        System.out.println("switch to admin backend");
                        
                     
             
             
                        try {
                            // Locate the 'Single Line Input'
                            WebElement Employee_Name  = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//label[text()='Employee Name ']")));
                            Assert.assertNotNull(Employee_Name ,"Employee Name  is not found");

                            // Perform mouse hover action
                            actions.moveToElement(Employee_Name ).perform();
                            System.out.println("Hovered over 'Employee Name ' successfully.");

                            // Wait for the delete icon and click it
                            WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//label[contains(text(),'Employee Name ')]/ancestor::div[@class='row position-relative']//nb-icon[@icon='delete']")));
                            actions.moveToElement(deleteIcon).click().perform();
                            System.out.println("Clicked on the delete icon successfully.");

                            // Verify successful deletion (e.g., element is removed or confirmation message appears)
                            boolean isDeleted = wait.until(ExpectedConditions.invisibilityOf(Employee_Name ));
                            Assert.assertTrue(isDeleted,"Employee Name  was not deleted successfully.");

                        } catch (Exception e) {
                            Assert.fail("Test failed due to an exception: " + e.getMessage());
                        }
             
                        
             
             
             }   
             
             
             
             
             //-----------------------  MULTI LINE INPUT--------------------------------------------
             
             
             
             
             
             
             
             
             @Then("User verifies and changes the settings of the Multi Line Input")
             public void user_verifies_and_changes_the_settings_of_the_multi_line_input() throws Exception {
            	 
            	 
            	// Frontend Verification
          	    ((JavascriptExecutor) driver).executeScript("window.open()");
          	    Set<String> windowHandles = driver.getWindowHandles();
          	    Iterator<String> iterator = windowHandles.iterator();
          	    String firstWindowHandle = iterator.next();
          	    String secondWindowHandle = iterator.next();
          	    
          	    
          	
          	    
            	 JavascriptExecutor js = (JavascriptExecutor) driver;
         	    
         	    // Verify and change the settings of the Multi Line Input
         	    WebElement MultiLine = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[2]")));
         	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
         	    
         	    // Perform drag-and-drop action
         	    Actions actions = new Actions(driver);
         	    actions.dragAndDrop(MultiLine, targetTabElement).perform();
         	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Multi Line Input ']")));
         	    
         	    Assert.assertTrue(droppedElement.isDisplayed(), "multi Line Input component was not successfully dropped.");
         	    System.out.println("Successfully dragged and dropped the multi Line Input component to the Response tab.");
         	    
         	    // Verify right-side settings/components
         	    for (int i = 1; i <= 9; i += 2) {
         	        try {
         	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
         	            js.executeScript("arguments[0].scrollIntoView(true);", category);
         	            String categoryText = category.getText();
         	            System.out.println("Category: " + categoryText);

         	            if (categoryText.isEmpty()) {
         	                System.err.println("Warning: Category text is empty for element at index " + i);
         	            }
         	        } catch (NoSuchElementException e) {
         	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
         	        } catch (Exception e) {
         	            System.err.println("An unexpected error occurred: " + e.getMessage());
         	        }
         	    }

         	    // Save the application
         	    try {
         	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
         	        clickSave.click();
         	        System.out.println("Clicked on the save button.");

         	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
         	        String actualMessage = appliSave.getText();

         	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
         	        System.out.println("Application saved with message: " + actualMessage);
         	    } catch (Exception e) {
         	        System.out.println("An error occurred while saving the application.");
         	        e.printStackTrace();
         	    }

         	   
         	    // Step 2: Switch to the front-end window
 	            driver.switchTo().window(secondWindowHandle);
 	            
 	           driver.navigate().refresh();
	            System.out.println("Page refreshed successfully.");
 	            
 	           
         	    try {
         	        // Verify that "multi Line Input" is displayed
         	        WebElement singleLineInputLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='Multi Line Input']")));
         	        Assert.assertTrue(singleLineInputLabel.isDisplayed(), "'multi Line Input' is not visible on the page!");
         	        System.out.println("'multi Line Input' is visible on the page.");
         	    } catch (Exception e) {
         	        System.err.println("Error while verifying 'multi Line Input' visibility: " + e.getMessage());
         	    }

 	            
 	         
                 // Switch back to the original window
                 driver.switchTo().window(firstWindowHandle);
                 System.out.println("Switched back to the original window.");
                 
                 
                 
              
              

                 try {
                     // Set the Field Label
                     WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group ng-star-inserted']/child::input")));
                     fieldLabelInput.clear();
                     fieldLabelInput.sendKeys("multiple Employee Names");
                     Assert.assertEquals(fieldLabelInput.getAttribute("value"), "multiple Employee Names", "Field Label input text does not match expected value.");
                     System.out.println("Field Label set to 'multiple Employee Name' successfully.");
                 } catch (Exception e) {
                     System.err.println("Failed to set the Field Label: " + e.getMessage());
                 }

                 try {
                     // Set the Placeholder Text
                     WebElement placeholderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Placeholder text']/following-sibling::input")));
                     placeholderInput.clear();
                     placeholderInput.sendKeys("Names of employees");
                     Assert.assertEquals(placeholderInput.getAttribute("value"), "Names of employees", "Placeholder text does not match expected value.");
                     System.out.println("Placeholder Text set to 'Names of employees' successfully.");
                 } catch (Exception e) {
                     System.err.println("Failed to set the Placeholder Text: " + e.getMessage());
                 }

                 try {
                     // Set the Help Text
                     WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                     helpTextInput.clear();
                     helpTextInput.sendKeys("Enter valid names");
                     Assert.assertEquals(helpTextInput.getAttribute("value"), "Enter valid names", "Help Text does not match expected value.");
                     System.out.println("Help Text set to 'Enter valid names' successfully.");
                 } catch (Exception e) {
                     System.err.println("Failed to set the Help Text: " + e.getMessage());
                 }

                 try {
                     // Set the Tooltip
                     WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                     tooltipInput.clear();
                     tooltipInput.sendKeys("name as per valid");
                     Assert.assertEquals(tooltipInput.getAttribute("value"), "name as per valid", "Tooltip text does not match expected value.");
                     System.out.println("Tooltip set to 'name as per valid' successfully.");
                 } catch (Exception e) {
                     System.err.println("Failed to set the Tooltip: " + e.getMessage());
                 }
                 
                 
                 //select the enable duplicate 
                 
                
                     try {
                         WebElement enableDuplicateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Enable Duplicate ']/preceding::input[@type='checkbox']")));
                         if (!enableDuplicateCheckbox.isSelected()) {
                         	 
                              js.executeScript("arguments[0].click();", enableDuplicateCheckbox);
                             System.out.println("Checked: Enable Duplicate");
                             Assert.assertTrue(enableDuplicateCheckbox.isSelected(), enableDuplicateCheckbox + " checkbox is not selected after clicking!");
                             System.out.println("Enable Duplicate checkbox is already selected.");
                         }
                     } catch (Exception e) {
                         System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                     }

                  // Index Numbers Checkbox
                     try {
                         WebElement indexNumbersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Index Numbers ']/preceding::input[@type='checkbox'])[2]")));
                         if (!indexNumbersCheckbox.isSelected()) {
                         	 js.executeScript("arguments[0].click();", indexNumbersCheckbox);
                             System.out.println("Checked: Index Numbers");
                             // Verify if the checkbox is selected after clicking
                             Assert.assertTrue(indexNumbersCheckbox.isSelected(), indexNumbersCheckbox + " checkbox is not selected after clicking!");
                         } else {
                             System.out.println("Index Numbers checkbox is already selected.");
                         }
                     } catch (Exception e) {
                         System.out.println("Error locating or clicking 'Index Numbers' checkbox: " + e.getMessage());
                     }

                     // Repeat Heading Checkbox
                     try {
                         WebElement repeatHeadingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Repeat Heading ']/preceding::input[@type='checkbox'])[3]")));
                         if (!repeatHeadingCheckbox.isSelected()) {
                         	 js.executeScript("arguments[0].click();", repeatHeadingCheckbox);
                             System.out.println("Checked: Repeat Heading");
                             // Verify if the checkbox is selected after clicking
                             Assert.assertTrue(repeatHeadingCheckbox.isSelected(), repeatHeadingCheckbox + " checkbox is not selected after clicking!");
                         } else {
                             System.out.println("Repeat Heading checkbox is already selected.");
                         }
                     } catch (Exception e) {
                         System.out.println("Error locating or clicking 'Repeat Heading' checkbox: " + e.getMessage());
                     }

                     
                     
                  // Save the application
             	   
             	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
             	        clickSave1.click();
             	        System.out.println("Clicked on the save button.");

             	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
             	        String actualMessage1 = appliSave1.getText();

             	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
             	        System.out.println("Application saved with message: " + actualMessage1);
        
                     
                     
                     
                     
                     
                  // Step 1: Pause briefly to allow for any loading or processing
     	            Thread.sleep(2000);

     	            // Step 2: Switch to the front-end window
     	            driver.switchTo().window(secondWindowHandle);
     	            System.out.println("Switched to the front-end window.");

     	            // Step 3: Refresh the page to reload all elements
     	            driver.navigate().refresh();
     	            System.out.println("Page refreshed successfully.");


     	            
     	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
     	            
     	            
     	            try {
                         // Check if the feild label Name Reflected front end or not
                         WebElement employeeNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='multiple Employee Names']")));
                         Assert.assertTrue(employeeNameLabel.isDisplayed(), "multiple Employee Names label is not visible.");
                         System.out.println(employeeNameLabel.getText()+": label is visible on the page.");
                     } catch (Exception e) {
                         System.err.println("Failed to locate or display 'multiple Employee Names' label: " + e.getMessage());
                     }

                     try {
                         // Check if the Help text is reflected front end or not
                         WebElement enterYourText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@for='multiple Employee Names']/ancestor::div[contains(@class, 'field-container')]//p[text()='Enter valid names'])[1]")));
                         Assert.assertTrue(enterYourText.isDisplayed(), "Help text is not visible.");
                         System.out.println(enterYourText.getText()+" :text is visible on the page.");
                     } catch (Exception e) {
                         System.err.println("Failed to locate or display help text: " + e.getMessage());
                     }

                     try {
                         // Check if the placeholder text is visible
                         WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='multiple Employee Names']/ancestor::div[contains(@class, 'field-container')]//span[@class='angular-editor-placeholder']")));
                         Assert.assertTrue(inputField.isDisplayed(), "Input field text is not visible.");
                         System.out.println("Names of employees :Input field text is visible on the page.");
                     } catch (Exception e) {
                         System.err.println("Failed to locate or display the input field text: " + e.getMessage());
                     }

                     try {
                         // Click on the tooltip icon near 'multiple Employee Name '
                         WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='multiple Employee Names']/child::span/mat-icon")));
                         tooltipIcon.click();
                         System.out.println("Tooltip icon clicked successfully.");
                     } catch (Exception e) {
                         System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                     }

                     try {
                         // Verify that the tooltip content text is visible after clicking the tooltip icon
                         WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='multiple Employee Names']/child::span/div/p")));
                         Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                         System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                     } catch (Exception e) {
                         System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                     }
             	    
                     
     	            
     	            
     	            
     	            
     	            
     	            
     	            
     	            
     	            
     	            
     	            
     	            
     	            
                     // verify the repeated duplicate headings with add index number ----in ADMIN FRONT-END
                     try {
                    	    Actions action = new Actions(driver);
                    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

                    	    // Step 1: Hover over the tooltip icon and click the duplicate icon
                    	    System.out.println("Step 1: Hovering over tooltip icon and clicking duplicate icon.");
                    	    WebElement tooltipIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='multiple Employee Names']/child::span/mat-icon")));
                    	    action.moveToElement(tooltipIcon1).perform();
                    	    System.out.println("Tooltip hovered successfully.");

                    	    WebElement duplication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' multiple Employee Names ')]/following::mat-icon[text()='add_circle_outline']")));
                    	    duplication.click();
                    	    System.out.println("Duplicate icon clicked. New heading should be added.");
                    	    Thread.sleep(2000);

                    	    // Step 2: Verify duplicate headings
                    	    System.out.println("Step 2: Verifying duplicate headings.");
                    	    List<WebElement> duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' multiple Employee Names ')]"));
                    	    Assert.assertTrue(duplicateHeadings.size() > 1, "No duplicate headings found. Verification failed.");

                    	    System.out.println("Verification successful. Total occurrences of 'multiple Employee Names': " + duplicateHeadings.size());

                    	    for (int i = 0; i < duplicateHeadings.size(); i++) {
                    	        WebElement heading = duplicateHeadings.get(i);
                    	        if (heading.isDisplayed()) {
                    	            System.out.println("Duplicate " + (i + 1) + ": " + heading.getText());
                    	        } else {
                    	            System.out.println("Duplicate " + (i + 1) + " is not visible on the page.");
                    	        }
                    	    }

                    	    // Step 3: Click the remove icon to delete a heading
                    	    System.out.println("Step 3: Removing duplicate heading.");
                    	    
                    	    WebElement tooltipIcon12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'multiple Employee Names 2')]//span/child::mat-icon")));
                    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon12);
                    	    action.moveToElement(tooltipIcon12).perform();  
             
                    	    WebElement clickRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' multiple Employee Names 2 ')]/following::mat-icon[text()='remove_circle_outline']")));
                    	    action.moveToElement(clickRemove).perform();
                    	    clickRemove.click();
                    	    System.out.println("Remove icon clicked. Heading should be removed.");

                    	    // Step 4: Verify the heading is removed
                    	    System.out.println("Step 4: Verifying removal of duplicate headings.");
                    	    duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' multiple Employee Names')]"));
                    	    Assert.assertEquals(duplicateHeadings.size(), 1, "Duplication removal failed. Multiple headings still found.");

                    	    System.out.println("Duplication successfully removed. Only one occurrence of 'multiple Employee Names' found.");
                    	} catch (Exception e) {
                    	    e.printStackTrace();
                    	    System.out.println("An error occurred: " + e.getMessage());
                    	    Assert.fail("Test case failed due to unexpected error: " + e.getMessage());
                    	}

                     
                     
                     
                     // Switch back to the original window
                     driver.switchTo().window(firstWindowHandle);
                     System.out.println("Switched back to the original window.");
                      
                     
                     
                     
                     
                     // Allow Attachment Checkbox
                     try {
                         WebElement allowAttachmentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Allow attachment ']/preceding::input[@type='checkbox'])[4]")));
                         if (!allowAttachmentCheckbox.isSelected()) {
                         	js.executeScript("arguments[0].click();", allowAttachmentCheckbox);
                             System.out.println("Checked: Allow Attachment");
                             Assert.assertTrue(allowAttachmentCheckbox.isSelected(), allowAttachmentCheckbox + " checkbox is not selected after clicking!");
                         } else {
                             System.out.println("Allow Attachment checkbox is already selected.");
                         }
                     } catch (Exception e) {
                         System.out.println("Error locating or clicking 'Allow Attachment' checkbox: " + e.getMessage());
                     }

                     // Allow Audio Checkbox
                     try {
                         WebElement allowAudioCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Allow audio ']/preceding::input[@type='checkbox'])[5]")));
                         if (!allowAudioCheckbox.isSelected()) {
                         	js.executeScript("arguments[0].click();", allowAudioCheckbox);
                             System.out.println("Checked: Allow Audio");
                             Assert.assertTrue(allowAudioCheckbox.isSelected(), allowAudioCheckbox + " checkbox is not selected after clicking!");

                         } else {
                             System.out.println("Allow Audio checkbox is already selected.");
                         }
                     } catch (Exception e1) {
                         System.out.println("Error locating or clicking 'Allow Audio' checkbox: " + e1.getMessage());
                     }

                     
                     // click Filterable Checkbox
                     try {
                         WebElement allowAttachmentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Filterable ']/preceding::input[@type='checkbox'])[6]")));
                         if (!allowAttachmentCheckbox.isSelected()) {
                         	js.executeScript("arguments[0].click();", allowAttachmentCheckbox);
                             System.out.println("Checked: Allow Attachment");
                             Assert.assertTrue(allowAttachmentCheckbox.isSelected(), allowAttachmentCheckbox + " checkbox is not selected after clicking!");
                         } else {
                             System.out.println("Allow Attachment checkbox is already selected.");
                         }
                     } catch (Exception e) {
                         System.out.println("Error locating or clicking 'Allow Attachment' checkbox: " + e.getMessage());
                     }
                     
                     
                     // Final confirmation message
                     System.out.println("All specified checkboxes have been checked and verified.");

                 
                 
                     
                     // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                     try {
                     WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                     js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                     js.executeScript("arguments[0].click();", click_condition);
                     
                     System.out.println("user click the condition category");
                     
                 
                     
                         WebElement enable_Use_in_workflow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Use in workflow ']/preceding::input[@type='checkbox'])[7]")));
                         if (!enable_Use_in_workflow.isSelected()) {
                         	 
                              js.executeScript("arguments[0].click();", enable_Use_in_workflow);
                             System.out.println("Checked: Use in workflow");
                             Assert.assertTrue(enable_Use_in_workflow.isSelected(), enable_Use_in_workflow + " checkbox is not selected after clicking!");
                             System.out.println("Use in workflow checkbox is  selected.");
                         }
                     } catch (Exception e) {
                         System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                     }
                   
                     
                     //step:2 verify the unique varible name input
                     
                     WebElement unique_varibleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group d-flex ng-star-inserted']/child::label")));
                   

                     if (unique_varibleInput.isDisplayed()) {
                         System.out.println(" unique varible name input : is displayed.");
                     } else {
                         System.out.println("The unique varible name input is not displayed.");
                     }
                     
                     //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                     
                     try {
                         // Step 1: Click on the edit icon
                         WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                         js.executeScript("arguments[0].click();", clickedit_icon);
                         System.out.println("User clicked the edit icon");

                         // Step 2: Verify the expected text on the edit page
                         String expected_text12 = "Rule Name:";
                         WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                         String actual_text12 = edit_rules.getText();
                         
                         // Assertion with custom message for failure
                         Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                         System.out.println("Edit rules for Employee Name: page opened successfully");

                         // Step 3: Close the page
                         WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                         js.executeScript("arguments[0].click();", close_page);
                         System.out.println("Edit page closed successfully");

                     } catch (Exception e) {
                         // Print the stack trace and a custom error message
                         e.printStackTrace();
                         System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                     }
                     
                     
                     
                     
                     // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                     
                     // click the appearence 
                     
                  // Click the Appearance category
                     try {
                         WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                         js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                         js.executeScript("arguments[0].click();", clickAppearance);
                         
                         
                      
                         
                         
                         

                         // Define the sizes to loop through
                         String[] sizes = {"medium", "small", "large"};

                         for (String size : sizes) {
                             try {
                                 // Click the size (medium, small, large)
                                 WebElement sizeElement = driver.findElement(By.xpath("//label[text()='" + size + "']"));
                                 js.executeScript("arguments[0].click();", sizeElement);
                                 clickSave1.click();

                                 // Switch to the front-end window
                                 driver.switchTo().window(secondWindowHandle);
                                 driver.navigate().refresh();
                                 System.out.println("User selected the " + size + " size, reflected on the front end.");

                                 // Verify field size
                                 WebElement SIZE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='multiple Employee Names']/ancestor::div[contains(@class, 'field-container')]//div[@class='angular-editor-textarea']")));
                                 Dimension fieldSize = SIZE.getSize();
                                 System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Width: " + fieldSize.getWidth());
                                 System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Height: " + fieldSize.getHeight());
                                 
                                 
                                 
                                 
                                 // Switch back to the original window
                                 driver.switchTo().window(firstWindowHandle);
                                 System.out.println("Switched back to the original window.");
                                 
                                
                                 } catch (Exception e1) {
                                     System.out.println(e1);
                                 }
                         }
                         
                         
                         
                         
                             
                             driver.switchTo().window(secondWindowHandle);
                             System.out.println("switch to the front end   by verify the html editor");
                             
                             
                             
                          // Wait for the element to be present on front end   "HTML editor"
                             
                             System.out.println("Before checked the :Disable HTML editor");
                             WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='multiple Employee Names']/ancestor::div[contains(@class, 'field-container')]//div[@class='angular-editor-toolbar ng-star-inserted']")));
                             System.out.println("HTML editor  Element is present in the frontend.");

                             // Check if the element is enabled
                             boolean isEnabled = element.isEnabled();

                             // Print and assert based on enable/disable status
                             if (isEnabled) {
                                 System.out.println("The HTML editor is ENABLED.");
                                 Assert.assertTrue(isEnabled, "Assertion Passed: The element is ENABLED as expected.");
                             } else {
                                 System.out.println("The HTML editor is DISABLED.");
                                 Assert.assertFalse(isEnabled, "Assertion Passed: The element is DISABLED as expected.");
                             }

                             
                             
                             
                             
                          // Switch back to the original window
                             driver.switchTo().window(firstWindowHandle);
                             System.out.println("Switched back to the original window.");
                             
                             
                                 
                             // checked the Disable HTML editor
                             try {
                                 WebElement Disable_HTML_editor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Disable HTML editor')]/preceding::input[@type='checkbox'][1]")));
                                 if (!Disable_HTML_editor.isSelected()) {
                                 	js.executeScript("arguments[0].click();", Disable_HTML_editor);
                                     System.out.println("Checked: Disable HTML editor");
                                     Assert.assertTrue(Disable_HTML_editor.isSelected(), Disable_HTML_editor + " checkbox is not selected after clicking!");

                                 } else {
                                     System.out.println("Disable HTML editor checkbox is already selected.");
                                 }
                                 
                             } catch (Exception e) {
                                 System.out.println(e);
                             }
                              
                         
                       	       clickSave1.click();
                   	        System.out.println("Clicked on the save button.");
                   	        
                   	        
                   	     // Switch to the front-end window
                            driver.switchTo().window(secondWindowHandle);
                            System.out.println("switch to frontend");
                            driver.navigate().refresh();
                                 
                        // verify the after checked the disable html editor
                           
                                // Define the XPath locator
                                By locator = By.xpath("//label[@for='multiple Employee Names']/ancestor::div[contains(@class, 'field-container')]//div[@class='angular-editor-toolbar ng-star-inserted']");

                                // Check if the element is present
                                boolean isElementPresent = driver.findElements(locator).size() > 0;

                                if (isElementPresent) {
                                    // If the element is present, check if it's enabled
                                    WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                                    boolean isEnabled1 = element1.isEnabled();

                                    if (isEnabled1) {
                                        // If enabled, print and assert
                                        System.out.println("The HTML editor is ENABLED.");
                                        Assert.assertTrue(isEnabled1, "Assertion Passed: The element is ENABLED as expected.");
                                    } 
                                } else {
                                	 // If disabled, print and assert
                                    System.out.println("The HTML editor is DISABLED.");
                                   
                                }

                            
                            

                            
                            
                            Thread.sleep(2000);
                            
                      	     // Switch to the back-end window
                         // Switch back to the original window
                            driver.switchTo().window(firstWindowHandle);
                            System.out.println("Switched back to the backend.");

                            
                            
                            try {
                         // Click the Validation section
                            WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                            js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                            js.executeScript("arguments[0].click();", clickValidation);
                            System.out.println("User clicked the validation category.");

                            // Handle checkboxes: Required and Disable
                            
                                WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                if (!requiredCheckbox.isSelected()) {
                                    js.executeScript("arguments[0].click();", requiredCheckbox);
                                    System.out.println("Checked: Required");
                                    Assert.assertTrue(requiredCheckbox.isSelected(), "Required checkbox is not selected after clicking!");
                                } else {
                                    System.out.println("Required checkbox is already selected.");
                                }

                                WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                if (!disableCheckbox.isSelected()) {
                                    js.executeScript("arguments[0].click();", disableCheckbox);
                                    System.out.println("Checked: Disable");
                                    Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                                } else {
                                    System.out.println("Disable checkbox is already selected.");
                                }
                               
                                
                                Thread.sleep(2000);
                                clickSave1.click();
                                Thread.sleep(2000);
                                // Switch to the front-end window
                                driver.switchTo().window(secondWindowHandle);
                                Thread.sleep(2000);
                                driver.navigate().refresh();
                                System.out.println("Switched to the front-end window. Page refreshed.");

                                // Check if the Disable text field is enabled
                                WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                                if (disableField.isEnabled()) {
                                    System.out.println("The text field is disabled.");
                                } else {
                                    System.out.println("The text field is enable.");
                                }
                                
                                Thread.sleep(2000);
                                // Switch back to the original window
                                driver.switchTo().window(firstWindowHandle);
                                
                                
                                
                                
                                //unchecked the Disible check box
                                
                             // Uncheck the "Disable" checkbox if it is selected
                                WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                                if (disableCheckbox1.isSelected()) {
                                    // Use JavaScript to uncheck the checkbox
                                    js.executeScript("arguments[0].click();", disableCheckbox1);
                                    System.out.println("Unchecked: Disable checkbox.");
                                    
                                    // Verify the checkbox is now unchecked
                                    Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                                } else {
                                    System.out.println("Disable checkbox is already unchecked.");
                                }

                                Thread.sleep(2000);
                                clickSave1.click();
                                Thread.sleep(2000);

                                // Switch to the front-end window
                                driver.switchTo().window(secondWindowHandle);
                                Thread.sleep(2000);
                                driver.navigate().refresh();
                                System.out.println("Switched to the front-end window. Page refreshed.");

                                // Check if the "Disable" text field is enabled
                                WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, 'ng-star-inserted') and contains(text(), 'Employee')]")));
                                if (disableField1.isEnabled()) {
                                    System.out.println("The text field is enable.");
                                } else {
                                    System.out.println("The text field is Disable.");
                                }
                                Thread.sleep(2000);
                                // Switch back to the original window
                                driver.switchTo().window(firstWindowHandle);
                                System.out.println("switch to admin backend");
                                
                                
                                
                                
                                
                                
                            } catch (Exception e) {
                                System.out.println("Error handling validation checkboxes: " + e.getMessage());
                            }

                        } catch (Exception e) {
                            System.out.println("An error occurred in the appearance and validation process: " + e.getMessage());
                        }

                        
                     
                     
                     
                     try {
                         
                   	  // 1. Interact with "Minimum Length" field
                       WebElement minLengthInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Minimum Length']/following-sibling::input")));
                       minLengthInput.clear();
                       minLengthInput.sendKeys("1");
                       System.out.println("Minimum Length input set to 1.");

                       // Assert value
                       String minValue = minLengthInput.getAttribute("value");
                       assert minValue.equals("1") : "Assertion failed: Minimum Length value is not 1!";
                       System.out.println("Assertion passed: Minimum Length value is 1.");

                       // 2. Interact with "Maximum Length" field
                       WebElement maxLengthInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Maximum Length']/following-sibling::input")));
                       maxLengthInput.clear();
                       maxLengthInput.sendKeys("10");
                       System.out.println("Maximum Length input set to 10.");

                       // Assert value
                       String maxValue = maxLengthInput.getAttribute("value");
                       assert maxValue.equals("10") : "Assertion failed: Maximum Length value is not 10!";
                       System.out.println("Assertion passed: Maximum Length value is 10.");

                     
                       

               	       clickSave1.click();
           	        System.out.println("Clicked on the save button.");
           	        
           	        
           	     // Switch to the front-end window
                    driver.switchTo().window(secondWindowHandle);
                    System.out.println("switch to frontend");
                    driver.navigate().refresh();

            	     
        	        
        	        //verify the user enter the input feild more than 10 characters
                    
                    // Find the textarea element
                 // Test entering more than 10 characters in the target field
                 // Wait for the element to be visible using WebDriver's ExpectedConditions
                    try {
                    	

                             // Wait until the textarea is visible
                             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                             WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Names of employees']")));

                             // Step 1: Enter 10 characters into the textarea
                             textarea.clear();  // Clear any existing text
                             textarea.sendKeys("abcdefghij"); // Enter 10 characters

                             // Validate acceptance of input (assert that the value is exactly 10 characters)
                             String enteredText = textarea.getAttribute("value");
                             System.out.println("Entered Text: " + enteredText);
                             Assert.assertEquals( 10, enteredText.length(),"The input should be accepted as 10 characters.");

                             // Step 2: Clear the textarea
                             textarea.clear();

                             // Step 3: Enter more than 10 characters
                             textarea.sendKeys("abcdefghijk"); // Enter 11 characters

                             // Validate if the input is rejected (assert that the length is not greater than 10)
                             enteredText = textarea.getAttribute("value");
                             System.out.println("Entered Text after exceeding 10 characters: " + enteredText);
                             if (enteredText.length() > 10) {
                                 System.out.println("Rejected: The input is more than 10 characters.");
                                 Assert.assertTrue( enteredText.length() <= 10,"Input should not exceed 10 characters.");
                             } else {
                                 System.out.println("Accepted: The input is 10 characters or less.");
                             }

                         } catch (Exception e) {
                             // Handle any exceptions during the test execution
                             System.out.println("Exception occurred: " + e.getMessage());
                             e.printStackTrace();
                    }
        	        
        	        
        	     
                    Thread.sleep(2000);
                    // Switch back to the original window
                    driver.switchTo().window(firstWindowHandle);
                    System.out.println("switch to admin backend");  
                       
                       
                       
                       
                       
                    // click the dropdown  "Maximum File Size"
                       WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Maximum File Size']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                       dropdown1.click();
                       System.out.println("Dropdown clicked.");

                       // Verify all options and print their text
                       List<WebElement> options = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
                       System.out.println("Verifying and printing all options:");
                       int i = 1;
                       for (WebElement option : options) {
                           if (option.isDisplayed()) {
                               System.out.println("Option " + i + ": " + option.getText());
                           } else {
                               System.out.println("Option " + i + " is not visible.");
                           }
                           i++;
                       }

                       // Click the 5th option
                       WebElement fifthOption = wait.until(ExpectedConditions.elementToBeClickable(
                           By.xpath("(//span[@class='mat-option-text'])[5]")));
                       fifthOption.click();
                       System.out.println("5th option clicked: " + fifthOption.getText()); 
                       
                       
                                       
                       
                       
           
                       try {
                           // Interact with "File Extension" dropdown
                           WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='File Extensions']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                           js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
                           js.executeScript("arguments[0].click();", dropdown);
                           System.out.println("Dropdown clicked.");

                           // Select checkboxes (1 to 10)
                           for (int j = 1; j <= 10; j++) {
                               try {
                                   // Locate the checkbox element
                                   WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@id,'mat-option')]/mat-pseudo-checkbox)[" + j + "]")));
                                   
                                   // Scroll to the checkbox
                                   js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkbox);
                                   Thread.sleep(1000); // Allow DOM to stabilize
                                   
                                   // Click the checkbox
                                   js.executeScript("arguments[0].click();", checkbox);
                                   
                                   // Locate the corresponding text element within the mat-option
                                   WebElement textElement = driver.findElement(
                                       By.xpath("(//*[contains(@id,'mat-option')])[" + j + "]//span[@class='mat-option-text']"));
                                   
                                   // Retrieve and print the text
                                   String checkboxText = textElement.getText();
                                   System.out.println("Checked: " + checkboxText);
                               } catch (Exception e) {
                                   System.err.println("Error handling checkbox " + j + ": " + e.getMessage());
                               }
                           }

                           // Close the dropdown after selecting checkboxes
                           // Click the dropdown again to close it (or click outside the dropdown)
                           WebElement closeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='File Extensions']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                           js.executeScript("arguments[0].click();", closeDropdown);
                           System.out.println("Dropdown closed.");
                           
                       } catch (Exception e) {
                           e.printStackTrace();
                       } 
                          
                           
                         
                       

                     
                   } catch (Exception e) {
                       System.err.println("An error occurred: " + e.getMessage());
                       
                   }
                   
                   
                   
                
                   
                
                   
                   
                   //enter the Maximum Height-----Maximum Width
                   
                   WebElement Maximum_Height = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Maximum Height']/following-sibling::input")));
                   Maximum_Height.clear();
                   Maximum_Height.sendKeys("1");
                   System.out.println("Maximum Height input set to 1.");

                   // Assert value
                   String minValue2 = Maximum_Height.getAttribute("value");
                   assert minValue2.equals("1") : "Assertion failed: Maximum Height value is not 1!";
                   System.out.println("Assertion passed: Maximum Height value is 1.");

                   // 2. Interact with "Maximum Length" field
                   WebElement Maximum_Width = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Maximum Width']/following-sibling::input")));
                   Maximum_Width.clear();
                   Maximum_Width.sendKeys("50");
                   System.out.println("Maximum Width input set to 50.");

                   // Assert value
                   String maxValue1 = Maximum_Width.getAttribute("value");
                   assert maxValue1.equals("50") : "Assertion failed: Maximum Width value is not 50!";
                   System.out.println("Assertion passed: Maximum Width value is 50.");
                   
                   
                   Actions actions1 = new Actions(driver);
                   WebElement element = driver.findElement(By.xpath("//mat-icon[text()='save']"));
                   actions1.doubleClick(element).perform();
                   
                   Thread.sleep(2000);
                       
                   // Switch to the front-end window
                   driver.switchTo().window(secondWindowHandle);
                   Thread.sleep(2000);
                   driver.navigate().refresh();
                   System.out.println("Switched to the front-end window. Page refreshed.");
                   
                   
                   
                // Check if the attachment element is visible
                   // Check if the attachment element is visible
                   try {
                       // First Upload: File 1
                       String filePath1 = "C:\\Users\\HP\\Downloads\\FRAME WORK.pdf";
                       WebElement attachment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                           By.xpath("//span[text()='Add Attachment']")));
                       if (attachment.isDisplayed()) {
                           attachment.click();
                           System.out.println("Attachment dialog opened for File 1.");

                           Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);

                           Robot rb = new Robot();
                           rb.delay(2000);

                           // Simulate CTRL+V and ENTER for File 1
                           rb.keyPress(KeyEvent.VK_CONTROL);
                           rb.keyPress(KeyEvent.VK_V);
                           rb.keyRelease(KeyEvent.VK_V);
                           rb.keyRelease(KeyEvent.VK_CONTROL);
                           rb.keyPress(KeyEvent.VK_ENTER);
                           rb.keyRelease(KeyEvent.VK_ENTER);

                           System.out.println("File 1 uploaded successfully.");
                       } else {
                           System.out.println("Attachment button not visible for File 1.");
                       }
                   
           
                       // Verify File 1 Upload
                       WebElement uploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(
                           By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//li[contains(@class,'uploded-files')]/span")));
                       Assert.assertTrue(uploadedFile.isDisplayed(),"Uploaded file 1 is not displayed.");
                       System.out.println("File 1 is displayed: " + uploadedFile.getText());

                       // Delete File 1
                       WebElement deleteFile = wait.until(ExpectedConditions.elementToBeClickable(
                           By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//li[contains(@class,'uploded-files')]/mat-icon[contains(text(),' delete')]")));
                       deleteFile.click();
                       System.out.println("File 1 deleted successfully.");

                       // Re-Upload: File 1
                       if (attachment.isDisplayed()) {
                           attachment.click();
                           System.out.println("Attachment dialog opened again for File 1.");

                           Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);
                           Robot rb = new Robot();
                           rb.delay(2000);

                           // Simulate CTRL+V and ENTER for Re-Upload
                           rb.keyPress(KeyEvent.VK_CONTROL);
                           rb.keyPress(KeyEvent.VK_V);
                           rb.keyRelease(KeyEvent.VK_V);
                           rb.keyRelease(KeyEvent.VK_CONTROL);
                           rb.keyPress(KeyEvent.VK_ENTER);
                           rb.keyRelease(KeyEvent.VK_ENTER);

                           System.out.println("File 1 re-uploaded successfully.");
                       } else {
                           System.out.println("Attachment button not visible for re-upload.");
                       }

                      
                   } catch (Exception e) {
                       System.out.println("Error during file upload process: " + e.getMessage());
                   }

                                           
                     
                   try {  
                       // Check if the volume icon is visible and click it 
                       WebElement volumeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='volume_up']")));
                       if (volumeIcon.isDisplayed()) {
                           System.out.println("Volume icon is visible.");
                           // Click on the volume icon
                           volumeIcon.click();
                       } else {
                           System.out.println("Volume icon is not visible.");
                       }
                       
                      Thread.sleep(3000);
                      
                           // stop the recording and verify the file is dispalted or not
                          

                           // Locate and click the button
                           WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//button[@class='stop-button ng-star-inserted']/mat-icon")));
                           js.executeScript("arguments[0].scrollIntoView(true);", button); // Scroll to the button
                           button.click(); // Click the button
                           System.out.println("Button clicked.");

                           // Verify if the Audio file is displayed or not
                           WebElement Audiofile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//li[@id='attachData.Name']/span")));
                           
                           boolean isDisplayed = Audiofile.isDisplayed();
                           Assert.assertTrue(isDisplayed,"The element is not displayed as expected."); 
                           
                           // Print the text of the element
                           String audiofileNameText = Audiofile.getText();
                           System.out.println("Audiofile name is: " + audiofileNameText);

                       } catch (Exception e) {
                           System.err.println("An error occurred: " + e.getMessage());
                       }
             
                   
                   
                   
                   // Switch back to the original window
                   driver.switchTo().window(firstWindowHandle);
                   System.out.println("switch to admin backend"); 
                   
                   
                   
                   
                   try {
                       // Locate the 'Multi Line Input'
                       WebElement Employee_Name  = wait.until(ExpectedConditions.visibilityOfElementLocated(
                           By.xpath("//label[text()='multiple Employee Names ']")));
                       Assert.assertNotNull(Employee_Name ,"multiple Employee Names   is not found");

                       // Perform mouse hover action
                       actions.moveToElement(Employee_Name ).perform();
                       System.out.println("Hovered over 'multiple Employee ' successfully.");

                       // Wait for the delete icon and click it
                       WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
                           By.xpath("//label[contains(text(),'multiple Employee')]/ancestor::div[@class='row position-relative']//nb-icon[@icon='delete']")));
                       actions.moveToElement(deleteIcon).click().perform();
                       System.out.println("Clicked on the delete icon successfully.");

                       // Verify successful deletion (e.g., element is removed or confirmation message appears)
                       boolean isDeleted = wait.until(ExpectedConditions.invisibilityOf(Employee_Name ));
                       Assert.assertTrue(isDeleted,"multiple Employee  was not deleted successfully.");

                   } catch (Exception e) {
                       Assert.fail("Test failed due to an exception: " + e.getMessage());
                   }
                   
                   
                   
                   
                   
                   
                   
                   
                   
             }     
             
            
             
             
             
             
             
             
             */
             
             
             
             
             
             @Then("user verifies and changes the settings of the Number")
             public void user_verifies_and_changes_the_settings_of_the_Number() throws Exception {
            	 
            	 
             	// Frontend Verification
           	    ((JavascriptExecutor) driver).executeScript("window.open()");
           	    Set<String> windowHandles = driver.getWindowHandles();
           	    Iterator<String> iterator = windowHandles.iterator();
           	    String firstWindowHandle = iterator.next();
           	    String secondWindowHandle = iterator.next();
           	    
           	    
           	  Thread.sleep(2000);
               // Switch back to the original window
               driver.switchTo().window(firstWindowHandle);
               System.out.println("switch to admin backend");
           	    
             	 JavascriptExecutor js = (JavascriptExecutor) driver;
          	    
          	    // Verify and change the settings of the Number 
          	    WebElement Number = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[6]")));
          	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
          	    
          	    // Perform drag-and-drop action
          	    Actions actions = new Actions(driver);
          	    actions.dragAndDrop(Number, targetTabElement).perform();
          	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Number ']")));
          	    
          	    Assert.assertTrue(droppedElement.isDisplayed(), "Number  component was not successfully dropped.");
          	    System.out.println("Successfully dragged and dropped the Number  component to the Response tab.");
          	    
          	    // Verify right-side settings/components
          	    for (int i = 1; i <= 9; i += 2) {
          	        try {
          	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
          	            js.executeScript("arguments[0].scrollIntoView(true);", category);
          	            String categoryText = category.getText();
          	            System.out.println("Category: " + categoryText);

          	            if (categoryText.isEmpty()) {
          	                System.err.println("Warning: Category text is empty for element at index " + i);
          	            }
          	        } catch (NoSuchElementException e) {
          	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
          	        } catch (Exception e) {
          	            System.err.println("An unexpected error occurred: " + e.getMessage());
          	        }
          	    }

          	    // Save the application
          	    try {
          	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
          	        clickSave.click();
          	        System.out.println("Clicked on the save button.");

          	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
          	        String actualMessage = appliSave.getText();

          	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
          	        System.out.println("Application saved with message: " + actualMessage);
          	    } catch (Exception e) {
          	        System.out.println("An error occurred while saving the application.");
          	        e.printStackTrace();
          	    }

          	   
          	    // Step 2: Switch to the front-end window
  	            driver.switchTo().window(secondWindowHandle);
  	            
  	           driver.navigate().refresh();
 	            System.out.println("Page refreshed successfully.");
 	            System.out.println("switch to front-end");
  	            
  	           
          	    try {
          	        // Verify that "Number " is displayed
          	        WebElement singleLineInputLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='Number']")));
          	        Assert.assertTrue(singleLineInputLabel.isDisplayed(), "'Number ' is not visible on the page!");
          	        System.out.println("'Number ' is visible on the page.");
          	    } catch (Exception e) {
          	        System.err.println("Error while verifying 'Number ' visibility: " + e.getMessage());
          	    }

  	            
  	         
                  // Switch back to the original window
                  driver.switchTo().window(firstWindowHandle);
                  System.out.println("Switched back to the original window.");
                  
                  
                  
               
               

                  try {
                      // Set the Field Label
                      WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group ng-star-inserted']/child::input")));
                      fieldLabelInput.clear();
                      fieldLabelInput.sendKeys("Number of employees");
                      Assert.assertEquals(fieldLabelInput.getAttribute("value"), "Number of employees", "Field Label input text does not match expected value.");
                      System.out.println("Field Label set to 'Numbers' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Field Label: " + e.getMessage());
                  }

                  try {
                      // Set the Placeholder Text
                      WebElement placeholderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Placeholder text']/following-sibling::input")));
                      placeholderInput.clear();
                      placeholderInput.sendKeys("Enter employees");
                      Assert.assertEquals(placeholderInput.getAttribute("value"), "Enter employees", "Placeholder text does not match expected value.");
                      System.out.println("Placeholder Text set to 'Names of employees' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Placeholder Text: " + e.getMessage());
                  }

                  try {
                      // Set the Help Text
                      WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                      helpTextInput.clear();
                      helpTextInput.sendKeys("Numbers");
                      Assert.assertEquals(helpTextInput.getAttribute("value"), "Numbers", "Help Text does not match expected value.");
                      System.out.println("Help Text set to 'Enter valid names' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Help Text: " + e.getMessage());
                  }

                  try {
                      // Set the Tooltip
                      WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                      tooltipInput.clear();
                      tooltipInput.sendKeys("enter valid number of employees working");
                      Assert.assertEquals(tooltipInput.getAttribute("value"), "enter valid number of employees working", "Tooltip text does not match expected value.");
                      System.out.println("Tooltip set to 'name as per valid' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Tooltip: " + e.getMessage());
                  }
                  
                  
                  //select the enable duplicate 
                  
                 
                      try {
                          WebElement enableDuplicateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Enable Duplicate ']/preceding::input[@type='checkbox']")));
                          if (!enableDuplicateCheckbox.isSelected()) {
                          	 
                               js.executeScript("arguments[0].click();", enableDuplicateCheckbox);
                              System.out.println("Checked: Enable Duplicate");
                              Assert.assertTrue(enableDuplicateCheckbox.isSelected(), enableDuplicateCheckbox + " checkbox is not selected after clicking!");
                              System.out.println("Enable Duplicate checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                      }

                   // Index Numbers Checkbox
                      try {
                          WebElement indexNumbersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Index Numbers ']/preceding::input[@type='checkbox'])[2]")));
                          if (!indexNumbersCheckbox.isSelected()) {
                          	 js.executeScript("arguments[0].click();", indexNumbersCheckbox);
                              System.out.println("Checked: Index Numbers");
                              // Verify if the checkbox is selected after clicking
                              Assert.assertTrue(indexNumbersCheckbox.isSelected(), indexNumbersCheckbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Index Numbers checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Index Numbers' checkbox: " + e.getMessage());
                      }

                      // Repeat Heading Checkbox
                      try {
                          WebElement repeatHeadingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Repeat Heading ']/preceding::input[@type='checkbox'])[3]")));
                          if (!repeatHeadingCheckbox.isSelected()) {
                          	 js.executeScript("arguments[0].click();", repeatHeadingCheckbox);
                              System.out.println("Checked: Repeat Heading");
                              // Verify if the checkbox is selected after clicking
                              Assert.assertTrue(repeatHeadingCheckbox.isSelected(), repeatHeadingCheckbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Repeat Heading checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Repeat Heading' checkbox: " + e.getMessage());
                      }

                      
                      
                   // Save the application
              	   
              	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
              	        clickSave1.click();
              	        System.out.println("Clicked on the save button.");

              	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
              	        String actualMessage1 = appliSave1.getText();

              	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
              	        System.out.println("Application saved with message: " + actualMessage1);
         
                      
                      
                      
                      
                      
                   // Step 1: Pause briefly to allow for any loading or processing
      	            Thread.sleep(2000);

      	            // Step 2: Switch to the front-end window
      	            driver.switchTo().window(secondWindowHandle);
      	            System.out.println("Switched to the front-end window.");

      	            // Step 3: Refresh the page to reload all elements
      	            driver.navigate().refresh();
      	            System.out.println("Page refreshed successfully.");


      	            
      	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
      	            
      	            
      	            try {
                          // Check if the feild label Name Reflected front end or not
                          WebElement employeeNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Number of employees']")));
                          Assert.assertTrue(employeeNameLabel.isDisplayed(), "Number of employees label is not visible.");
                          System.out.println(employeeNameLabel.getText()+": label is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display 'Number of employees' label: " + e.getMessage());
                      }

                      try {
                          // Check if the Help text is reflected front end or not
                          WebElement enterYourText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Number of employees']/ancestor::div[contains(@class, 'field-container')]//p[@class='input-footer mb-2']")));
                          Assert.assertTrue(enterYourText.isDisplayed(), "Help text is not visible.");
                          System.out.println(enterYourText.getText()+" :text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display help text: " + e.getMessage());
                      }

                      try {
                          // Check if the placeholder text is visible
                          WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Number of employees']/ancestor::div[contains(@class, 'field-container')]//input")));
                          Assert.assertTrue(inputField.isDisplayed(), "Input field text is not visible.");
                          System.out.println("Enter employees :Input field text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display the input field text: " + e.getMessage());
                      }

                      try {
                          // Click on the tooltip icon near 'multiple Employee Name '
                          WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Number of employees']/child::span/mat-icon")));
                          tooltipIcon.click();
                          System.out.println("Tooltip icon clicked successfully.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                      }

                      try {
                          // Verify that the tooltip content text is visible after clicking the tooltip icon
                          WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Number of employees']/child::span/div/p")));
                          Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                          System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                      }
              	    
                      
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
                      // verify the repeated duplicate headings with add index number ----in ADMIN FRONT-END
                      try {
                     	    Actions action = new Actions(driver);
                     	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

                     	    // Step 1: Hover over the tooltip icon and click the duplicate icon
                     	    System.out.println("Step 1: Hovering over tooltip icon and clicking duplicate icon.");
                     	    WebElement tooltipIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Number of employees']/child::span/mat-icon")));
                     	    action.moveToElement(tooltipIcon1).perform();
                     	    System.out.println("Tooltip hovered successfully.");

                     	    WebElement duplication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' Number of employees ')]/following::mat-icon[text()='add_circle_outline']")));
                     	    duplication.click();
                     	    System.out.println("Duplicate icon clicked. New heading should be added.");
                     	    Thread.sleep(2000);

                     	    // Step 2: Verify duplicate headings
                     	    System.out.println("Step 2: Verifying duplicate headings.");
                     	    List<WebElement> duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' Number of employees ')]"));
                     	    Assert.assertTrue(duplicateHeadings.size() > 1, "No duplicate headings found. Verification failed.");

                     	    System.out.println("Verification successful. Total occurrences of ' Number of employees ': " + duplicateHeadings.size());

                     	    for (int i = 0; i < duplicateHeadings.size(); i++) {
                     	        WebElement heading = duplicateHeadings.get(i);
                     	        if (heading.isDisplayed()) {
                     	            System.out.println("Duplicate " + (i + 1) + ": " + heading.getText());
                     	        } else {
                     	            System.out.println("Duplicate " + (i + 1) + " is not visible on the page.");
                     	        }
                     	    }

                     	    // Step 3: Click the remove icon to delete a heading
                     	    System.out.println("Step 3: Removing duplicate heading.");
                     	    
                     	    WebElement tooltipIcon12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' Number of employees 2 ')]//span/child::mat-icon")));
                     	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon12);
                     	    action.moveToElement(tooltipIcon12).perform();  
              
                     	    WebElement clickRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' Number of employees 2 ')]/following::mat-icon[text()='remove_circle_outline']")));
                     	    action.moveToElement(clickRemove).perform();
                     	    clickRemove.click();
                     	    System.out.println("Remove icon clicked. Heading should be removed.");

                     	    // Step 4: Verify the heading is removed
                     	    System.out.println("Step 4: Verifying removal of duplicate headings.");
                     	    duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' Number of employees ')]"));
                     	    Assert.assertEquals(duplicateHeadings.size(), 1, "Duplication removal failed. Multiple headings still found.");

                     	    System.out.println("Duplication successfully removed. Only one occurrence of ' Number of employees ' found.");
                     	} catch (Exception e) {
                     	    e.printStackTrace();
                     	    System.out.println("An error occurred: " + e.getMessage());
                     	    Assert.fail("Test case failed due to unexpected error: " + e.getMessage());
                     	}

                      
                      
                      
                      // Switch back to the original window
                      driver.switchTo().window(firstWindowHandle);
                      System.out.println("Switched back to the original window.");
                       
                      
                      
                      
                      
                     

                      
                      // click Filterable Checkbox
                      try {
                          WebElement Filter_Checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Filterable ']/preceding::input[@type='checkbox'])[4]")));
                          if (!Filter_Checkbox.isSelected()) {
                          	js.executeScript("arguments[0].click();", Filter_Checkbox);
                              System.out.println("Checked: Filter_Checkbox");
                              Assert.assertTrue(Filter_Checkbox.isSelected(), Filter_Checkbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Filter Checkboxt checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Filter Checkbox' checkbox: " + e.getMessage());
                      }
                      
                      
                      // Final confirmation message
                      System.out.println("All specified checkboxes have been checked and verified.");

                  
                  
                      
                      // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                      try {
                      WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                      js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                      js.executeScript("arguments[0].click();", click_condition);
                      
                      System.out.println("user click the condition category");
                      
                  
                      
                          WebElement enable_Use_in_workflow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Use in workflow')]/ancestor::mat-checkbox//input[@type='checkbox']")));
                          if (!enable_Use_in_workflow.isSelected()) {
                          	 
                               js.executeScript("arguments[0].click();", enable_Use_in_workflow);
                              System.out.println("Checked: Use in workflow");
                              Assert.assertTrue(enable_Use_in_workflow.isSelected(), enable_Use_in_workflow + " checkbox is not selected after clicking!");
                              System.out.println("Use in workflow checkbox is  selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                      }
                    
                      
                      //step:2 verify the unique varible name input
                      
                      WebElement unique_varibleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group d-flex ng-star-inserted']/child::label")));
                    

                      if (unique_varibleInput.isDisplayed()) {
                          System.out.println(" unique varible name input : is displayed.");
                      } else {
                          System.out.println("The unique varible name input is not displayed.");
                      }
                      
                      //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                      
                      try {
                          // Step 1: Click on the edit icon
                          WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                          js.executeScript("arguments[0].click();", clickedit_icon);
                          System.out.println("User clicked the edit icon");

                          // Step 2: Verify the expected text on the edit page
                          String expected_text12 = "Rule Name:";
                          WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                          String actual_text12 = edit_rules.getText();
                          
                          // Assertion with custom message for failure
                          Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                          System.out.println("Edit rules for Employee Name: page opened successfully");

                          // Step 3: Close the page
                          WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                          js.executeScript("arguments[0].click();", close_page);
                          System.out.println("Edit page closed successfully");

                      } catch (Exception e) {
                          // Print the stack trace and a custom error message
                          e.printStackTrace();
                          System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                      }
                      
                      
                      
                      
                      // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                      
                      // click the appearence 
                      
                   // Click the Appearance category
                      try {
                          WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                          js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                          js.executeScript("arguments[0].click();", clickAppearance);
                          
                          // Enter the "Number of decimal places" input
                      
                          WebElement decimalPlacesInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Number of decimal places']/parent::div/input")));

                          // Print statement for debugging
                          System.out.println("Decimal places input field is visible.");

                          // Clear the input field and set a value
                          decimalPlacesInput.clear();  // Clear any existing value
                          decimalPlacesInput.sendKeys("2");  // Enter the value '2'
                          System.out.println("Entered '2' in the 'Number of decimal places' input field.");
                          // Save the selection and navigate to the front-end window
                          
                          
                      
                      }catch(Exception e) {
                    	  System.out.println(e);
                      }
                          
                      clickSave1.click();
                          

                          // Define the sizes to loop through
                      // Define the sizes to loop through
                      String[] sizes = {"small", "medium", "large"};

                      for (String size : sizes) {
                          try {
                              // Click the size (medium, small, large)
                              WebElement sizeElement = driver.findElement(By.xpath("//label[@for='" + size + "']"));
                              js.executeScript("arguments[0].click();", sizeElement);
                              clickSave1.click();

                              // Switch to the front-end window
                              driver.switchTo().window(secondWindowHandle);
                              driver.navigate().refresh();
                              System.out.println("User selected the " + size + " size, reflected on the front end.");

                              // Verify field size
                              WebElement SIZE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Number of employees']/ancestor::div[contains(@class, 'field-container')]//div[@class='ng-star-inserted']/input")));
                              Dimension fieldSize = SIZE.getSize();
                              System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Width: " + fieldSize.getWidth());
                              System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Height: " + fieldSize.getHeight());
                              
                              
                              
                              
                              // Switch back to the original window
                              driver.switchTo().window(firstWindowHandle);
                              System.out.println("Switched back to the original window.");
                              
                             
                              } catch (Exception e1) {
                                  System.out.println(e1);
                              }
                      }


                      
                             
                      // Click the Validation section 
                             try {
                          
                             WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                             js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                             js.executeScript("arguments[0].click();", clickValidation);
                             System.out.println("User clicked the validation category.");

                             // Handle checkboxes: Required and Disable
                             
                                 WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                 if (!requiredCheckbox.isSelected()) {
                                     js.executeScript("arguments[0].click();", requiredCheckbox);
                                     System.out.println("Checked: Required");
                                     Assert.assertTrue(requiredCheckbox.isSelected(), "Required checkbox is not selected after clicking!");
                                 } else {
                                     System.out.println("Required checkbox is already selected.");
                                 }

                                 WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                 if (!disableCheckbox.isSelected()) {
                                     js.executeScript("arguments[0].click();", disableCheckbox);
                                     System.out.println("Checked: Disable");
                                     Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                                 } else {
                                     System.out.println("Disable checkbox is already selected.");
                                 }
                                
                                 
                                 Thread.sleep(2000);
                                 clickSave1.click();
                                 Thread.sleep(2000);
                                 // Switch to the front-end window
                                 driver.switchTo().window(secondWindowHandle);
                                 System.out.println(" Switch to the front-end window");
                                 Thread.sleep(2000);
                                 driver.navigate().refresh();
                                 System.out.println("Switched to the front-end window. Page refreshed.");
                                 
                                 
                                 
                                 // verify  the "required" * icone is visible or not
                                 
                                 WebElement Required_icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Number of employees']/child::span")));
                                 if (Required_icon.isEnabled()) {
                                     System.out.println("The 'Required icon' is enable.");
                                 } else {
                                     System.out.println("The 'Required icon' is disable.");
                                 }
                                 
                                 

                                 //checked the disable checkbox and verify the frontend and backend
                                 WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                                 if (disableField.isEnabled()) {
                                     System.out.println("The text field is disabled.");
                                 } else {
                                     System.out.println("The text field is enable.");
                                 }
                                 
                                 Thread.sleep(2000);
                                 // Switch back to the original window
                                 driver.switchTo().window(firstWindowHandle);
                                 System.out.println(" Switch to the back-end window");
                                 
                                 
                                 
                               // un check the disable checkbox and verify the frontend and backend
                                 
                              // Uncheck the "Disable" checkbox if it is selected
                                 WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                                 if (disableCheckbox1.isSelected()) {
                                     // Use JavaScript to uncheck the checkbox
                                     js.executeScript("arguments[0].click();", disableCheckbox1);
                                     System.out.println("Unchecked: Disable checkbox.");
                                     
                                     // Verify the checkbox is now unchecked
                                     Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                                 } else {
                                     System.out.println("Disable checkbox is already unchecked.");
                                 }

                                 Thread.sleep(2000);
                                 clickSave1.click();
                                 Thread.sleep(2000);

                                 // Switch to the front-end window
                                 driver.switchTo().window(secondWindowHandle);
                                 Thread.sleep(2000);
                                 driver.navigate().refresh();
                                 System.out.println("Switched to the front-end window. Page refreshed.");

                                 // Check if the "Disable" text field is enabled
                                 WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, ' ng-star-inserted') and contains(text(), ' Number of employees ')]")));
                                 if (disableField1.isEnabled()) {
                                     System.out.println("The text field is enable.");
                                 } else {
                                     System.out.println("The text field is Disable.");
                                 }
                                 Thread.sleep(2000);
                                 // Switch back to the original window
                                 driver.switchTo().window(firstWindowHandle);
                                 System.out.println("switch to admin backend");
                                 
                                 
                                 
                                 
                                 
                                 
                             } catch (Exception e) {
                                 System.out.println("Error handling validation checkboxes: " + e.getMessage());
                             }

                         

                             try {
                                 // Locate the 'Multi Line Input'
                                 WebElement Numberemployees   = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                     By.xpath("//label[text()='Number of employees ']")));
                                 Assert.assertNotNull(Numberemployees ,"Number of employees    is not found");

                                 // Perform mouse hover action
                                 actions.moveToElement(Numberemployees ).perform();
                                 System.out.println("Hovered over 'Number of employees ' successfully.");

                                 // Wait for the delete icon and click it
                                 WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
                                     By.xpath("//label[contains(text(),'Number of employees ')]/ancestor::div[@class='row position-relative']//nb-icon[@icon='delete']")));
                                 actions.moveToElement(deleteIcon).click().perform();
                                 System.out.println("Clicked on the delete icon successfully.");

                                 // Verify successful deletion (e.g., element is removed or confirmation message appears)
                                 boolean isDeleted = wait.until(ExpectedConditions.invisibilityOf(Numberemployees ));
                                 Assert.assertTrue(isDeleted,"Number of employees   was not deleted successfully.");

                             } catch (Exception e) {
                                 Assert.fail("Test failed due to an exception: " + e.getMessage());
                             }
                    
                      
                      
                     
              }     
             
             
             
             
             
            
            
          
           
            	 
            
             
            
             
             @And("user verifies and changes the settings of the Date")
             public void user_verifies_and_changes_the_settings_of_the_Date() throws Exception {
            	 

              	// Frontend Verification
            	    ((JavascriptExecutor) driver).executeScript("window.open()");
            	    Set<String> windowHandles = driver.getWindowHandles();
            	    Iterator<String> iterator = windowHandles.iterator();
            	    String firstWindowHandle = iterator.next();
            	    String secondWindowHandle = iterator.next();
            	    
            	    
            	  Thread.sleep(2000);
                // Switch back to the original window
                driver.switchTo().window(firstWindowHandle);
                System.out.println("Switched to the back-end window");
            	    
              	 JavascriptExecutor js = (JavascriptExecutor) driver;
           	    
           	    // Verify and change the settings of the Number 
           	    WebElement MultiLine = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[7]")));
           	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
           	    
           	    // Perform drag-and-drop action
           	    Actions actions = new Actions(driver);
           	    actions.dragAndDrop(MultiLine, targetTabElement).perform();
           	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Date ']")));
           	    
           	    Assert.assertTrue(droppedElement.isDisplayed(), "Date  component was not successfully dropped.");
           	    System.out.println("Successfully dragged and dropped the Date  component to the Response tab.");
           	    
           	    // Verify right-side settings/components
           	    for (int i = 1; i <= 9; i += 2) {
           	        try {
           	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
           	            js.executeScript("arguments[0].scrollIntoView(true);", category);
           	            String categoryText = category.getText();
           	            System.out.println("Category: " + categoryText);

           	            if (categoryText.isEmpty()) {
           	                System.err.println("Warning: Category text is empty for element at index " + i);
           	            }
           	        } catch (NoSuchElementException e) {
           	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
           	        } catch (Exception e) {
           	            System.err.println("An unexpected error occurred: " + e.getMessage());
           	        }
           	    }

           	    // Save the application
           	    try {
           	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
           	        clickSave.click();
           	        System.out.println("Clicked on the save button.");

           	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
           	        String actualMessage = appliSave.getText();

           	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
           	        System.out.println("Application saved with message: " + actualMessage);
           	    } catch (Exception e) {
           	        System.out.println("An error occurred while saving the application.");
           	        e.printStackTrace();
           	    }

           	   
           	    // Step 2: Switch to the front-end window
   	            driver.switchTo().window(secondWindowHandle);
   	            
   	           driver.navigate().refresh();
   	        System.out.println("Switched to the front-end window. Page refreshed.");
   	            
   	           
           	    try {
           	        // Verify that "Date " is displayed
           	        WebElement singleLineInputLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[(@for= 'Date')]")));
           	        Assert.assertTrue(singleLineInputLabel.isDisplayed(), "'date ' is not visible on the page!");
           	        System.out.println("'date ' is visible on the page.");
           	    } catch (Exception e) {
           	        System.err.println("Error while verifying 'Number ' visibility: " + e.getMessage());
           	    }

   	            
   	         
                   // Switch back to the original window
                   driver.switchTo().window(firstWindowHandle);
                   System.out.println("Switched to the Back-end window.");
                   
                   
                   
                
                

                   try {
                       // Set the Field Label
                       WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group ng-star-inserted']/child::input")));
                       fieldLabelInput.clear();
                       fieldLabelInput.sendKeys("Date of joining");
                       Assert.assertEquals(fieldLabelInput.getAttribute("value"), "Date of joining", "Field Label input text does not match expected value.");
                       System.out.println("Field Label set to 'Date of joining' successfully.");
                   } catch (Exception e) {
                       System.err.println("Failed to set the Field Label: " + e.getMessage());
                   }

                   try {
                       // Set the Placeholder Text
                       WebElement placeholderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Placeholder text']/following-sibling::input")));
                       placeholderInput.clear();
                       placeholderInput.sendKeys("date");
                       Assert.assertEquals(placeholderInput.getAttribute("value"), "date", "Placeholder text does not match expected value.");
                       System.out.println("Placeholder Text set to 'date' successfully.");
                   } catch (Exception e) {
                       System.err.println("Failed to set the Placeholder Text: " + e.getMessage());
                   }

                   try {
                       // Set the Help Text
                       WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                       helpTextInput.clear();
                       helpTextInput.sendKeys("Joing date");
                       Assert.assertEquals(helpTextInput.getAttribute("value"), "Joing date", "Help Text does not match expected value.");
                       System.out.println("Help Text set to 'Joing date' successfully.");
                   } catch (Exception e) {
                       System.err.println("Failed to set the Help Text: " + e.getMessage());
                   }

                   try {
                       // Set the Tooltip
                       WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                       tooltipInput.clear();
                       tooltipInput.sendKeys("joing dates");
                       Assert.assertEquals(tooltipInput.getAttribute("value"), "joing dates", "Tooltip text does not match expected value.");
                       System.out.println("Tooltip set to 'joing dates' successfully.");
                   } catch (Exception e) {
                       System.err.println("Failed to set the Tooltip: " + e.getMessage());
                   }
                   
                   
                   //select the enable duplicate 
                   
                  
                       try {
                           WebElement enableDuplicateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Enable Duplicate ']/preceding::input[@type='checkbox']")));
                           if (!enableDuplicateCheckbox.isSelected()) {
                           	 
                                js.executeScript("arguments[0].click();", enableDuplicateCheckbox);
                               System.out.println("Checked: Enable Duplicate");
                               Assert.assertTrue(enableDuplicateCheckbox.isSelected(), enableDuplicateCheckbox + " checkbox is not selected after clicking!");
                               System.out.println("Enable Duplicate checkbox is already selected.");
                           }
                       } catch (Exception e) {
                           System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                       }

                    // Index Numbers Checkbox
                       try {
                           WebElement indexNumbersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Index Numbers ']/preceding::input[@type='checkbox'])[2]")));
                           if (!indexNumbersCheckbox.isSelected()) {
                           	 js.executeScript("arguments[0].click();", indexNumbersCheckbox);
                               System.out.println("Checked: Index Numbers");
                               // Verify if the checkbox is selected after clicking
                               Assert.assertTrue(indexNumbersCheckbox.isSelected(), indexNumbersCheckbox + " checkbox is not selected after clicking!");
                           } else {
                               System.out.println("Index Numbers checkbox is already selected.");
                           }
                       } catch (Exception e) {
                           System.out.println("Error locating or clicking 'Index Numbers' checkbox: " + e.getMessage());
                       }

                       // Repeat Heading Checkbox
                       try {
                           WebElement repeatHeadingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Repeat Heading ']/preceding::input[@type='checkbox'])[3]")));
                           if (!repeatHeadingCheckbox.isSelected()) {
                           	 js.executeScript("arguments[0].click();", repeatHeadingCheckbox);
                               System.out.println("Checked: Repeat Heading");
                               // Verify if the checkbox is selected after clicking
                               Assert.assertTrue(repeatHeadingCheckbox.isSelected(), repeatHeadingCheckbox + " checkbox is not selected after clicking!");
                           } else {
                               System.out.println("Repeat Heading checkbox is already selected.");
                           }
                       } catch (Exception e) {
                           System.out.println("Error locating or clicking 'Repeat Heading' checkbox: " + e.getMessage());
                       }

                       
                       
                    // Save the application
               	   
               	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
               	        clickSave1.click();
               	        System.out.println("Clicked on the save button.");

               	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
               	        String actualMessage1 = appliSave1.getText();

               	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
               	        System.out.println("Application saved with message: " + actualMessage1);
          
                       
                       
                       
                       
                       
                    // Step 1: Pause briefly to allow for any loading or processing
       	            Thread.sleep(2000);

       	            // Step 2: Switch to the front-end window
       	            driver.switchTo().window(secondWindowHandle);
       	           

       	            // Step 3: Refresh the page to reload all elements
       	            driver.navigate().refresh();
       	         System.out.println("Switched to the front-end window. Page refreshed.");




       	            
       	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
       	            
       	            
       	            try {
                           // Check if the feild label Name Reflected front end or not
                           WebElement Date_of_joiningLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date of joining']")));
                           Assert.assertTrue(Date_of_joiningLabel.isDisplayed(), "Date of joining label is not visible.");
                           System.out.println(Date_of_joiningLabel.getText()+": label is visible on the page.");
                       } catch (Exception e) {
                           System.err.println("Failed to locate or display 'Date of joining' label: " + e.getMessage());
                       }

                       try {
                           // Check if the Help text is reflected front end or not
                           WebElement enterYourText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date of joining']/ancestor::div[contains(@class, 'field-container')]//p[@class='input-footer mb-2']")));
                           Assert.assertTrue(enterYourText.isDisplayed(), "Help text is not visible.");
                           System.out.println(enterYourText.getText()+" :text is visible on the page.");
                       } catch (Exception e) {
                           System.err.println("Failed to locate or display help text: " + e.getMessage());
                       }

                       try {
                           // Check if the placeholder text is visible
                           WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date of joining']/ancestor::div[contains(@class, 'field-container')]//input")));
                           Assert.assertTrue(inputField.isDisplayed(), "Input field text is not visible.");
                           System.out.println("Enter employees :Input field text is visible on the page.");
                       } catch (Exception e) {
                           System.err.println("Failed to locate or display the input field text: " + e.getMessage());
                       }

                       try {
                           // Click on the tooltip icon near 'multiple Employee Name '
                           WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Date of joining']/child::span/mat-icon")));
                           tooltipIcon.click();
                           System.out.println("Tooltip icon clicked successfully.");
                       } catch (Exception e) {
                           System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                       }

                       try {
                           // Verify that the tooltip content text is visible after clicking the tooltip icon
                           WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date of joining']/child::span/div/p")));
                           Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                           System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                       } catch (Exception e) {
                           System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                       }
               	    
                       
       	            
       	            
       	            
       	            
       	            
       	            
       	            
                      
                       // verify the repeated duplicate headings with add index number ----in ADMIN FRONT-END
                       try {
                      	    Actions action = new Actions(driver);
                      	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

                      	    // Step 1: Hover over the tooltip icon and click the duplicate icon
                      	    System.out.println("Step 1: Hovering over tooltip icon and clicking duplicate icon.");
                      	    WebElement tooltipIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Date of joining']/child::span/mat-icon")));
                      	    action.moveToElement(tooltipIcon1).perform();
                      	    System.out.println("Tooltip hovered successfully.");

                      	    WebElement duplication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' Date of joining ')]/following::mat-icon[text()='add_circle_outline']")));
                      	    duplication.click();
                      	    System.out.println("Duplicate icon clicked. New heading should be added.");
                      	    Thread.sleep(2000);

                      	    // Step 2: Verify duplicate headings
                      	    System.out.println("Step 2: Verifying duplicate headings.");
                      	    List<WebElement> duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' Date of joining ')]"));
                      	    Assert.assertTrue(duplicateHeadings.size() > 1, "No duplicate headings found. Verification failed.");

                      	    System.out.println("Verification successful. Total occurrences of ' Date of joining ': " + duplicateHeadings.size());

                      	    for (int i = 0; i < duplicateHeadings.size(); i++) {
                      	        WebElement heading = duplicateHeadings.get(i);
                      	        if (heading.isDisplayed()) {
                      	            System.out.println("Duplicate " + (i + 1) + ": " + heading.getText());
                      	        } else {
                      	            System.out.println("Duplicate " + (i + 1) + " is not visible on the page.");
                      	        }
                      	    }

                      	    // Step 3: Click the remove icon to delete a heading
                      	    System.out.println("Step 3: Removing duplicate heading.");
                      	    
                      	    WebElement tooltipIcon12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' Date of joining 2 ')]//span/child::mat-icon")));
                      	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon12);
                      	    action.moveToElement(tooltipIcon12).perform();  
               
                      	    WebElement clickRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' Date of joining 2 ')]/following::mat-icon[text()='remove_circle_outline']")));
                      	    action.moveToElement(clickRemove).perform();
                      	    clickRemove.click();
                      	    System.out.println("Remove icon clicked. Heading should be removed.");

                      	    // Step 4: Verify the heading is removed
                      	    System.out.println("Step 4: Verifying removal of duplicate headings.");
                      	    duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),'Date of joining')]"));
                      	    Assert.assertEquals(duplicateHeadings.size(), 1, "Duplication removal failed. Multiple headings still found.");

                      	    System.out.println("Duplication successfully removed. Only one occurrence of Date of joining' found.");
                      	} catch (Exception e) {
                      	    e.printStackTrace();
                      	    System.out.println("An error occurred: " + e.getMessage());
                      	    Assert.fail("Test case failed due to unexpected error: " + e.getMessage());
                      	}

                       
                       
                       
                       // Switch back to the original window
                       driver.switchTo().window(firstWindowHandle);
                       System.out.println(" Switch to the back-end window");
                        
                       
                       
                       
                      

                       
                       
                   
                   
                       
                       // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                       
                       
                       try {
                       WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                       js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                       js.executeScript("arguments[0].click();", click_condition);
                       
                       System.out.println("user click the condition category");
                       
                   
                       
                           WebElement enable_Use_in_workflow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Use in workflow ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                           if (!enable_Use_in_workflow.isSelected()) {
                           	 
                                js.executeScript("arguments[0].click();", enable_Use_in_workflow);
                               System.out.println("Checked: Use in workflow");
                               Assert.assertTrue(enable_Use_in_workflow.isSelected(), enable_Use_in_workflow + " checkbox is not selected after clicking!");
                               System.out.println("Use in workflow checkbox is  selected.");
                           }
                       } catch (Exception e) {
                           System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                       }
                     
                       
                       //step:2 verify the unique varible name input
                       
                       WebElement unique_varibleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group d-flex ng-star-inserted']/child::label")));
                     

                       if (unique_varibleInput.isDisplayed()) {
                           System.out.println(" unique varible name input : is displayed.");
                       } else {
                           System.out.println("The unique varible name input is not displayed.");
                       }
                       
                       //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                       
                       try {
                           // Step 1: Click on the edit icon
                           WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                           js.executeScript("arguments[0].click();", clickedit_icon);
                           System.out.println("User clicked the edit icon");

                           // Step 2: Verify the expected text on the edit page
                           String expected_text12 = "Rule Name:";
                           WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                           String actual_text12 = edit_rules.getText();
                           
                           // Assertion with custom message for failure
                           Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                           System.out.println("Edit rules for Employee Name: page opened successfully");

                           // Step 3: Close the page
                           WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                           js.executeScript("arguments[0].click();", close_page);
                           System.out.println("Edit page closed successfully");

                       } catch (Exception e) {
                           // Print the stack trace and a custom error message
                           e.printStackTrace();
                           System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                       }
                       
                       
                       
                       
                       // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                       
                       // click the appearence 
                       
                    // Click the Appearance category
                       try {
                           WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                           js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                           js.executeScript("arguments[0].click();", clickAppearance);
                           
                         
                           
                       }catch(Exception e) {
                     	  System.out.println(e);
                       }
                           
                           
                           

                           // Define the sizes to loop through
                    // Define the sizes to loop through
                       String[] sizes = {"small", "medium", "large"};

                       for (String size : sizes) {
                           try {
                               // Locate and click the size label (small, medium, large)
                               WebElement sizeElement = driver.findElement(By.xpath("//label[text()='" + size + "']"));
                               js.executeScript("arguments[0].click();", sizeElement);
                              
                               clickSave1.click();

                               // Switch to the front-end window
                               driver.switchTo().window(secondWindowHandle);
                               driver.navigate().refresh();
                               System.out.println("User selected the " + size + " size, reflected on the front end.");
                                Thread.sleep(4000);
                               // Wait for the field to be visible and interact with it
                               WebElement SIZE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date of joining']/ancestor::div[contains(@class,'field-container position')]//div[@class='position-relative']/input")));
                               Dimension fieldSize = SIZE.getSize();
                               Thread.sleep(4000);
                               System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Width: " + fieldSize.getWidth());
                               System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Height: " + fieldSize.getHeight());

                               // Switch back to the original window
                               driver.switchTo().window(firstWindowHandle);
                               System.out.println("Switched back to the original window.");
                           } catch (Exception e) {
                               System.out.println("An error occurred while handling size '" + size + "': " + e.getMessage());
                           }
                       }


                              
                              
                              try {
                           // Click the Validation section
                              WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                              js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                              js.executeScript("arguments[0].click();", clickValidation);
                              System.out.println("User clicked the validation category.");

                              // Handle checkboxes: Required and Disable
                              
                                  WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                  if (!requiredCheckbox.isSelected()) {
                                      js.executeScript("arguments[0].click();", requiredCheckbox);
                                      System.out.println("Checked: Required");
                                      Assert.assertTrue(requiredCheckbox.isSelected(), "Required checkbox is not selected after clicking!");
                                  } else {
                                      System.out.println("Required checkbox is already selected.");
                                  }

                                  WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                  if (!disableCheckbox.isSelected()) {
                                      js.executeScript("arguments[0].click();", disableCheckbox);
                                      System.out.println("Checked: Disable");
                                      Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                                  } else {
                                      System.out.println("Disable checkbox is already selected.");
                                  }
                                 
                                  
                                  Thread.sleep(2000);
                                  clickSave1.click();
                                  Thread.sleep(2000);
                                  // Switch to the front-end window
                                  driver.switchTo().window(secondWindowHandle);
                                  Thread.sleep(2000);
                                  driver.navigate().refresh();
                                  System.out.println("Switched to the front-end window. Page refreshed.");
                                  
                                  
                                  
                                  // verify  the "required" * icone is visible or not
                                  
                                  WebElement Required_icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date of joining']/span[@class='impfieldstyle error ng-star-inserted']")));
                                  if (Required_icon.isEnabled()) {
                                      System.out.println("The 'Required icon' is enable.");
                                  } else {
                                      System.out.println("The 'Required icon' is disable.");
                                  }
                                  
                                  

                                  // Check if the Disable text field is enabled or not in FRONT-END
                                  WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                                  if (disableField.isEnabled()) {
                                      System.out.println("The text field is disabled.");
                                  } else {
                                      System.out.println("The text field is enable.");
                                  }
                                  
                                  Thread.sleep(2000);
                                  // Switch back to the original window
                                  driver.switchTo().window(firstWindowHandle);
                                  System.out.println("Switched to the backend-end window");
                                  
                                  
                                  
                                  //unchecked the Disible check box -BACK-END
                                  
                               // Uncheck the "Disable" checkbox if it is selected
                                  WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                                  if (disableCheckbox1.isSelected()) {
                                      // Use JavaScript to uncheck the checkbox
                                      js.executeScript("arguments[0].click();", disableCheckbox1);
                                      System.out.println("Unchecked: Disable");
                                      
                                      // Verify the checkbox is now unchecked
                                      Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                                  } else {
                                      System.out.println("Disable checkbox is already unchecked.");
                                  }

                                  Thread.sleep(2000);
                                  clickSave1.click();
                                  Thread.sleep(2000);

                                  // Switch to the front-end window
                                  driver.switchTo().window(secondWindowHandle);
                                  Thread.sleep(2000);
                                  driver.navigate().refresh();
                                  System.out.println("Switched to the front-end window. Page refreshed.");

                                  // Check if the "Disable" text field is enabled or not --FRONT-END
                                  
                                  WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, ' ng-star-inserted') and contains(text(), ' Date of joining ')]")));
                                  if (disableField1.isEnabled()) {
                                      System.out.println("The text field is enable.");
                                  } else {
                                      System.out.println("The text field is Disable.");
                                  }
                                  Thread.sleep(2000);
                                  // Switch back to the original window
                                  driver.switchTo().window(firstWindowHandle);
                                  System.out.println("Switched to the backend-end window");
                                  
                                   
                              } catch (Exception e) {
                                  System.out.println("Error handling validation checkboxes: " + e.getMessage());
                              }

                              
                          try {    
                           // Click on the "Date Mode" dropdown
                              WebElement dateModeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Date Mode']/parent::div//div[contains(@class,'mat-select-value ng-tns')]")));
                              js.executeScript("arguments[0].scrollIntoView(true);", dateModeDropdown);
                              js.executeScript("arguments[0].click();", dateModeDropdown);
                              
                              
                              // Select "mm/yyyy" option from the dropdown
                              WebElement mmYYYYOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='mm/yyyy']")));
                              js.executeScript("arguments[0].scrollIntoView(true);", mmYYYYOption);
                              js.executeScript("arguments[0].click();", mmYYYYOption);
                              
                              

                              // Fill the "From Date" field
                              WebElement fromDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='From Date']/following-sibling::input")));
                              js.executeScript("arguments[0].scrollIntoView(true);", fromDateField);
                              fromDateField.sendKeys("11/20/2024");

                              // Fill the "To Date" field
                              WebElement toDateField = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//label[text()='To Date']/following-sibling::input")));
                              js.executeScript("arguments[0].scrollIntoView(true);", toDateField);
                              toDateField.sendKeys("11/25/2024");

                              // Click on "Is Minimum Date Today" checkbox
                              WebElement minDateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Is Minimum Date Today ']/preceding-sibling::span/input")));
                              js.executeScript("arguments[0].scrollIntoView(true);", minDateCheckbox);
                              js.executeScript("arguments[0].click();", minDateCheckbox);

                              // Click on "Is Max Date Today" checkbox
                              WebElement maxDateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Is Max Date Today ']/preceding-sibling::span/input")));
                              js.executeScript("arguments[0].scrollIntoView(true);", maxDateCheckbox);
                              js.executeScript("arguments[0].click();", maxDateCheckbox);

                              System.out.println("All actions completed successfully.");

                          } catch (Exception e) {
                              System.out.println("Error encountered: " + e.getMessage());
                              e.printStackTrace();
                          }
                              
                              
                              
                          Thread.sleep(2000);
                          clickSave1.click(); 
                          
                       // Switch to the front-end window
                          driver.switchTo().window(secondWindowHandle);
                          Thread.sleep(2000);
                          driver.navigate().refresh();
                          System.out.println("Switched to the front-end window. Page refreshed.");

                              
                              
                              
                       // Wait for the datepicker to be visible
                       // Wait for the datepicker to be visible and click it
                          WebElement click_datepicker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mat-mdc-button-touch-target']")));

                          // Scroll the datepicker element into view and click it using JavaScriptExecutor
                          js.executeScript("arguments[0].scrollIntoView(true);", click_datepicker);
                          js.executeScript("arguments[0].click();", click_datepicker);
                          System.out.println("Datepicker opened.");

                       // Wait for the current year to be visible and retrieve the current year
                          String aYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='2024']"))).getText();
                          System.out.println("Current Year: " + aYear);

                          // Loop until the desired year (2026) is found
                          while (!(aYear.equals("2025"))) {
                              // Click the "next" button to go to the next month
                              driver.findElement(By.xpath("//button[contains(@class,'mat-calendar-next-button')]")).click();
                              Thread.sleep(3000);
                              // Wait for and get the updated year
                              aYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='2025']"))).getText();
                              System.out.println("Navigating to: " + aYear);
                          }

                          // Assert that the year is 2025
                          Assert.assertEquals(aYear, "2025", "Year mismatch! Expected 2025, but got " + aYear);  // Corrected assertion
                          System.out.println("Year is now 2025.");

                          // Wait for the desired month (December) to be visible and click it
                          WebElement monthElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' DEC ']")));
                         
                          js.executeScript("arguments[0].click();", monthElement);
                          System.out.println("Clicked on December.");

                          // Wait for the expected date (Dec/2025) to be visible after selecting the month
                          WebElement expected_date = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dec/2025']")));
                          System.out.println("Expected Date is: " + expected_date.getText());

                          // Assertion to ensure the correct date is selected
                          Assert.assertEquals(expected_date.getText(), "Dec/2025", "Selected date is incorrect. Expected Dec/2025.");
                          System.out.println("Date selection confirmed: Dec/2025.");


                            
                          
                          Thread.sleep(2000);
                          // Switch back to the original window
                          driver.switchTo().window(firstWindowHandle);
                          System.out.println("Switched to the backend-end window");
                          
                          
                          
                          
                          
                          
                          
                          
                      try {    
                        //  Click on the "Date Mode" dropdown
                          WebElement dateModeDropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Date Mode']/parent::div//div[contains(@class,'mat-select-value ng-tns')]")));
                          js.executeScript("arguments[0].scrollIntoView(true);", dateModeDropdown1);
                          js.executeScript("arguments[0].click();", dateModeDropdown1);
                          
                          
                          // Select "dd/mm/yyyy" option from the dropdown
                        
						
						WebElement dd_mm_yyyyOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='dd/mm/yyyy']")));
                          js.executeScript("arguments[0].scrollIntoView(true);", dd_mm_yyyyOption);
                          js.executeScript("arguments[0].click();", dd_mm_yyyyOption);
                          System.out.println("select the dd/mm/yyyy option ");
                          
                          
                      }catch(Exception e)  {
                    	  System.out.println(e);
                      }  
                        
                      
                      
                      Thread.sleep(2000);
                      clickSave1.click(); 
                      
                   // Switch to the front-end window
                      driver.switchTo().window(secondWindowHandle);
                      Thread.sleep(2000);
                      driver.navigate().refresh();
                      System.out.println("Switched to the front-end window. Page refreshed.");
                      
                      
                      
                      // Wait for the datepicker to be visible and click it
                      WebElement click_datepicker1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mat-mdc-button-touch-target']")));

                      // Scroll the datepicker element into view and click it using JavaScriptExecutor
                      js.executeScript("arguments[0].scrollIntoView(true);", click_datepicker1);
                      js.executeScript("arguments[0].click();", click_datepicker1);
                      System.out.println("Datepicker opened.");

                   // Wait for the current year to be visible and retrieve the current year
                   // Wait for the current month and year (NOV 2024) to be visible
                      String monthYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='NOV 2024']"))).getText();
                      System.out.println("Initial Month and Year: " + monthYear);

                   /*  try {
                          // Loop until the desired month and year (DEC 2025) is visible
                          while (!monthYear.equals("DEC 2024")) {
                              // Click the "next" button to go to the next month
                              WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'mat-calendar-next-button')]")));
                              nextButton.click();
                              Thread.sleep(3000);
                              // Wait for the updated month and year to be visible and fetch it
                              monthYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='DEC 2024']"))).getText();
                              System.out.println("Navigating to: " + monthYear);
                          }

                          // Print confirmation when the desired month and year are reached
                          System.out.println("Final Month and Year: " + monthYear);
                      } catch (Exception e) {
                          // Handle any unexpected errors
                          System.err.println("An error occurred while navigating to DEC 2024: " + e.getMessage());
                      }


                      // Assert that the month and year
                      Assert.assertEquals(monthYear, "DEC 2024", "Year & month mismatch! Expected 2024, but got " + monthYear);  
                      System.out.println("Year is now DEC 2024.");

                      // Wait for the desired date to be visible and click it
                      WebElement DateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' 25 ']")));
                      DateElement.click(); 
                      System.out.println("Clicked on 25th date.");

                      // Wait for the expected date (Dec/2026) to be visible after selecting the month
                      WebElement expected_dates = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='mat-datepicker-input input-icon date date-mat-input large ng-touched ng-dirty ng-valid']")));
                      System.out.println("Expected Date is: " + expected_dates.getText());

                      // Assertion to ensure the correct date is selected
                      Assert.assertEquals(expected_dates.getText(), "25/12/2024", "Selected date is incorrect. Expected25/12/2024.");
                      System.out.println("Date selection confirmed: 25/12/2024.");

                      */
                      
                      String year = "2027";
                      String date = "25";
                      String month = "Dec";

                      while (true) {
                          // Get the current month and year displayed in the calendar
                          String month_year = driver.findElement(By.xpath("//span[contains(@class, 'calendar-header')]")).getText();
                          
                          // Split the month and year into separate variables
                          String[] parts = month_year.split(" ");
                          String displayedMonth = parts[0];
                          String displayedYear = parts[1];

                          // Check if the displayed month and year match the desired values
                          if (displayedMonth.equalsIgnoreCase(month) && displayedYear.equals(year)) 
                        	  break;
                        	  else
                              driver.findElement(By.xpath("//button[contains(@class,'mat-calendar-next-button ')]")).click();
                              System.out.println("Selected date: " + date + " " + month + " " + year);
                             
                          } 
                      //date selection
                      
                      List<WebElement> alldates = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//td"));
                      
                      for(WebElement ele:alldates) {
                    	  String dt= ele.getText();
                    	  
                    	  if(dt.equals(date)) {
                    		  ele.click(); 
                    		  break;
                    	  }  
                      
                      
                      
                      
                      
                      }
                      
                      
                      
                      
                      
                    
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                       
                      
                      
             }

             
                       
                       
                
             
             
             
             
             @And("user verifies and changes the settings of the Date Range")
             public void user_verifies_and_changes_the_settings_of_the_Date_Range() throws Exception {
                      

               	// Frontend Verification
             	    ((JavascriptExecutor) driver).executeScript("window.open()");
             	    Set<String> windowHandles = driver.getWindowHandles();
             	    Iterator<String> iterator = windowHandles.iterator();
             	    String firstWindowHandle = iterator.next();
             	    String secondWindowHandle = iterator.next();
             	    
             	    
             	  Thread.sleep(2000);
                 // Switch back to the original window
                 driver.switchTo().window(firstWindowHandle);
                 System.out.println("switch to admin backend");
             	    
               	 JavascriptExecutor js = (JavascriptExecutor) driver;
            	    
            	    // Verify and change the settings of the Number 
            	    WebElement DateRange = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[8]")));
            	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
            	    
            	    // Perform drag-and-drop action
            	    Actions actions = new Actions(driver);
            	    actions.dragAndDrop(DateRange, targetTabElement).perform();
            	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Date Range ']")));
            	    
            	    Assert.assertTrue(droppedElement.isDisplayed(), "Date range  component was not successfully dropped.");
            	    System.out.println("Successfully dragged and dropped the DateRange component to the Response tab.");
            	    
            	    // Verify right-side settings/components
            	    for (int i = 1; i <= 9; i += 2) {
            	        try {
            	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
            	            js.executeScript("arguments[0].scrollIntoView(true);", category);
            	            String categoryText = category.getText();
            	            System.out.println("Category: " + categoryText);

            	            if (categoryText.isEmpty()) {
            	                System.err.println("Warning: Category text is empty for element at index " + i);
            	            }
            	        } catch (NoSuchElementException e) {
            	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
            	        } catch (Exception e) {
            	            System.err.println("An unexpected error occurred: " + e.getMessage());
            	        }
            	    }

            	    // Save the application
            	    try {
            	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
            	        clickSave.click();
            	        System.out.println("Clicked on the save button.");

            	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
            	        String actualMessage = appliSave.getText();

            	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
            	        System.out.println("Application saved with message: " + actualMessage);
            	    } catch (Exception e) {
            	        System.out.println("An error occurred while saving the application.");
            	        e.printStackTrace();
            	    }

            	   
            	    // Step 2: Switch to the front-end window
    	            driver.switchTo().window(secondWindowHandle);
    	            
    	           driver.navigate().refresh();
    	           System.out.println("Switched to the front-end window. Page refreshed.");
    	            
    	           
            	    try {
            	        // Verify that "Date Range " is displayed
            	        WebElement Date_Range = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='Date Range']")));
            	        Assert.assertTrue(Date_Range.isDisplayed(), "'date Range ' is not visible on the page!");
            	        System.out.println("'date Range ' is visible on the page.");
            	    } catch (Exception e) {
            	        System.err.println("Error while verifying 'Number ' visibility: " + e.getMessage());
            	    }

    	            
    	         
                    // Switch back to the original window
                    driver.switchTo().window(firstWindowHandle);

                    System.out.println(" Switch to the back-end window");
                    
                    
                    
                 
                 

                    try {
                        // Set the Field Label
                        WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group ng-star-inserted']/child::input")));
                        fieldLabelInput.clear();
                        fieldLabelInput.sendKeys("Date Ranges");
                        Assert.assertEquals(fieldLabelInput.getAttribute("value"), "Date Ranges", "Field Label input text does not match expected value.");
                        System.out.println("Field Label set to 'Date Ranges' successfully.");
                    } catch (Exception e) {
                        System.err.println("Failed to set the Field Label: " + e.getMessage());
                    }

                    try {
                        // Set the Placeholder Text
                        WebElement placeholderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Placeholder text']/following-sibling::input")));
                        placeholderInput.clear();
                        placeholderInput.sendKeys("from date and to date");
                        Assert.assertEquals(placeholderInput.getAttribute("value"), "from date and to date", "Placeholder text does not match expected value.");
                        System.out.println("Placeholder Text set to 'date' successfully.");
                    } catch (Exception e) {
                        System.err.println("Failed to set the Placeholder Text: " + e.getMessage());
                    }

                    try {
                        // Set the Help Text
                        WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                        helpTextInput.clear();
                        helpTextInput.sendKeys("offer date");
                        Assert.assertEquals(helpTextInput.getAttribute("value"), "offer date", "Help Text does not match expected value.");
                        System.out.println("Help Text set to 'Joing date' successfully.");
                    } catch (Exception e) {
                        System.err.println("Failed to set the Help Text: " + e.getMessage());
                    }

                    try {
                        // Set the Tooltip
                        WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                        tooltipInput.clear();
                        tooltipInput.sendKeys("check the date before entering");
                        Assert.assertEquals(tooltipInput.getAttribute("value"), "check the date before entering", "Tooltip text does not match expected value.");
                        System.out.println("Tooltip set to 'joing dates' successfully.");
                    } catch (Exception e) {
                        System.err.println("Failed to set the Tooltip: " + e.getMessage());
                    }
                    
                    
                    //select the enable duplicate 
                    
                   
                        try {
                            WebElement enableDuplicateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Enable Duplicate ']/preceding::input[@type='checkbox']")));
                            if (!enableDuplicateCheckbox.isSelected()) {
                            	 
                                 js.executeScript("arguments[0].click();", enableDuplicateCheckbox);
                                System.out.println("Checked: Enable Duplicate");
                                Assert.assertTrue(enableDuplicateCheckbox.isSelected(), enableDuplicateCheckbox + " checkbox is not selected after clicking!");
                                System.out.println("Enable Duplicate checkbox is already selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                        }

                     // Index Numbers Checkbox
                        try {
                            WebElement indexNumbersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Index Numbers ']/preceding::input[@type='checkbox'])[2]")));
                            if (!indexNumbersCheckbox.isSelected()) {
                            	 js.executeScript("arguments[0].click();", indexNumbersCheckbox);
                                System.out.println("Checked: Index Numbers");
                                // Verify if the checkbox is selected after clicking
                                Assert.assertTrue(indexNumbersCheckbox.isSelected(), indexNumbersCheckbox + " checkbox is not selected after clicking!");
                            } else {
                                System.out.println("Index Numbers checkbox is already selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Index Numbers' checkbox: " + e.getMessage());
                        }

                        // Repeat Heading Checkbox
                        try {
                            WebElement repeatHeadingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Repeat Heading ']/preceding::input[@type='checkbox'])[3]")));
                            if (!repeatHeadingCheckbox.isSelected()) {
                            	 js.executeScript("arguments[0].click();", repeatHeadingCheckbox);
                                System.out.println("Checked: Repeat Heading");
                                // Verify if the checkbox is selected after clicking
                                Assert.assertTrue(repeatHeadingCheckbox.isSelected(), repeatHeadingCheckbox + " checkbox is not selected after clicking!");
                            } else {
                                System.out.println("Repeat Heading checkbox is already selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Repeat Heading' checkbox: " + e.getMessage());
                        }

                        
                        
                     // Save the application
                	   
                	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
                	        clickSave1.click();
                	        System.out.println("Clicked on the save button.");

                	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
                	        String actualMessage1 = appliSave1.getText();

                	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
                	        System.out.println("Application saved with message: " + actualMessage1);
           
                        
                        
                        
                        
                        
                     // Step 1: Pause briefly to allow for any loading or processing
        	            Thread.sleep(2000);

        	            // Step 2: Switch to the front-end window
        	            driver.switchTo().window(secondWindowHandle);
        	            

        	            // Step 3: Refresh the page to reload all elements
        	            driver.navigate().refresh();
        	            System.out.println("Switched to the front-end window. Page refreshed.");


        	            
        	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
        	            
        	            
        	            try {
                            // Check if the feild label Name Reflected front end or not
                            WebElement dateRangeslabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date Ranges']")));
                            Assert.assertTrue(dateRangeslabel.isDisplayed(), "Date Ranges label is not visible.");
                            System.out.println(dateRangeslabel.getText()+": label is visible on the page.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or display 'Date Ranges' label: " + e.getMessage());
                        }

                        try {
                            // Check if the Help text is reflected front end or not
                            WebElement helptextText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date Ranges']/ancestor::div[contains(@class, 'field-container')]//p[@class='input-footer mb-2']")));
                            Assert.assertTrue(helptextText.isDisplayed(), "Help text is not visible.");
                            System.out.println(helptextText.getText()+" :text is visible on the page.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or display help text: " + e.getMessage());
                        }

                        try {
                            // Check if the placeholder text is visible
                            WebElement placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date Ranges']/ancestor::div[contains(@class, 'field-container')]//input")));
                            Assert.assertTrue(placeholder.isDisplayed(), "Input field text is not visible.");
                            System.out.println("from date and to date :Input field text is visible on the page.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or display the input field text: " + e.getMessage());
                        }

                        try {
                            // Click on the tooltip icon near 'multiple Employee Name '
                            WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Date Ranges']/child::span/mat-icon")));
                            tooltipIcon.click();
                            System.out.println("Tooltip icon clicked successfully.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                        }

                        try {
                            // Verify that the tooltip content text is visible after clicking the tooltip icon
                            WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date Ranges']/child::span/div/p")));
                            Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                            System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                        } catch (Exception e) {
                            System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                        }
                	    
                        
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
                        // verify the repeated duplicate headings with add index number ----in ADMIN FRONT-END
                        try {
                       	    Actions action = new Actions(driver);
                       	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

                       	    // Step 1: Hover over the tooltip icon and click the duplicate icon
                       	    System.out.println("Step 1: Hovering over tooltip icon and clicking duplicate icon.");
                       	    WebElement tooltipIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Date Ranges']/child::span/mat-icon")));
                       	    action.moveToElement(tooltipIcon1).perform();
                       	    System.out.println("Tooltip hovered successfully.");

                       	    WebElement duplication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' Date Ranges ')]/following::mat-icon[text()='add_circle_outline']")));
                       	    duplication.click();
                       	    System.out.println("Duplicate icon clicked. New heading should be added.");
                       	    Thread.sleep(2000);

                       	    // Step 2: Verify duplicate headings
                       	    System.out.println("Step 2: Verifying duplicate headings.");
                       	    List<WebElement> duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' Date Ranges ')]"));
                       	    Assert.assertTrue(duplicateHeadings.size() > 1, "No duplicate headings found. Verification failed.");

                       	    System.out.println("Verification successful. Total occurrences of '  Date Ranges ': " + duplicateHeadings.size());

                       	    for (int i = 0; i < duplicateHeadings.size(); i++) {
                       	        WebElement heading = duplicateHeadings.get(i);
                       	        if (heading.isDisplayed()) {
                       	            System.out.println("Duplicate " + (i + 1) + ": " + heading.getText());
                       	        } else {
                       	            System.out.println("Duplicate " + (i + 1) + " is not visible on the page.");
                       	        }
                       	    }

                       	    // Step 3: Click the remove icon to delete a heading
                       	    System.out.println("Step 3: Removing duplicate heading.");
                       	    
                       	    WebElement tooltipIcon12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' Date Ranges 2 ')]//span/child::mat-icon")));
                       	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon12);
                       	    action.moveToElement(tooltipIcon12).perform();  
                
                       	    WebElement clickRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' Date Ranges 2 ')]/following::mat-icon[text()='remove_circle_outline']")));
                       	    action.moveToElement(clickRemove).perform();
                       	    clickRemove.click();
                       	    System.out.println("Remove icon clicked. Heading should be removed.");

                       	    // Step 4: Verify the heading is removed
                       	    System.out.println("Step 4: Verifying removal of duplicate headings.");
                       	    duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' Date Ranges ')]"));
                       	    Assert.assertEquals(duplicateHeadings.size(), 1, "Duplication removal failed. Multiple headings still found.");

                       	    System.out.println("Duplication successfully removed. Only one occurrence of ' Date Ranges ' found.");
                       	} catch (Exception e) {
                       	    e.printStackTrace();
                       	    System.out.println("An error occurred: " + e.getMessage());
                       	    Assert.fail("Test case failed due to unexpected error: " + e.getMessage());
                       	}

                        
                        
                        
                        // Switch back to the original window
                        driver.switchTo().window(firstWindowHandle);

                        System.out.println(" Switch to the back-end window");
                         
                        
                        
                        
                        
                       

                 
                    
                    
                        
                        // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                        try {
                        WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                        js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                        js.executeScript("arguments[0].click();", click_condition);
                        
                        System.out.println("user click the condition category");
                        
                    
                        
                            WebElement enable_Use_in_workflow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Use in workflow ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                            if (!enable_Use_in_workflow.isSelected()) {
                            	 
                                 js.executeScript("arguments[0].click();", enable_Use_in_workflow);
                                System.out.println("Checked: Use in workflow");
                                Assert.assertTrue(enable_Use_in_workflow.isSelected(), enable_Use_in_workflow + " checkbox is not selected after clicking!");
                                System.out.println("Use in workflow checkbox is  selected.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                        }
                      
                        
                        //step:2 verify the unique varible name input
                        
                        WebElement unique_varibleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group d-flex ng-star-inserted']/child::label")));
                      

                        if (unique_varibleInput.isDisplayed()) {
                            System.out.println(" unique varible name input : is displayed.");
                        } else {
                            System.out.println("The unique varible name input is not displayed.");
                        }
                        
                        //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                        
                        try {
                            // Step 1: Click on the edit icon
                            WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                            js.executeScript("arguments[0].click();", clickedit_icon);
                            js.executeScript("arguments[0].scrollIntoView(true);", clickedit_icon);
                            System.out.println("User clicked the edit icon");

                            // Step 2: Verify the expected text on the edit page
                            String expected_text12 = "Rule Name:";
                            WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                            String actual_text12 = edit_rules.getText();
                            
                            // Assertion with custom message for failure
                            Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                            System.out.println("Edit rules for Employee Name: page opened successfully");

                            // Step 3: Close the page
                            WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                            js.executeScript("arguments[0].click();", close_page);
                            System.out.println("Edit page closed successfully");

                        } catch (Exception e) {
                            // Print the stack trace and a custom error message
                            e.printStackTrace();
                            System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                        }
                        
                        
                        
                        
                        // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                        
                        // click the appearence 
                        
                     // Click the Appearance category
                        try {
                            WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                            js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                            js.executeScript("arguments[0].click();", clickAppearance);
                            
                            
                        }catch(Exception e) {
                      	  System.out.println(e);
                        }
                            
                            
                            
                     // Define the sizes to loop through
                        String[] sizes = {"medium", "small", "large"};

                        for (String size : sizes) {
                            try {
                                // Click the size (medium, small, large)
                                WebElement sizeElement = driver.findElement(By.xpath("//label[text()='" + size + "']"));
                                js.executeScript("arguments[0].click();", sizeElement);
                                clickSave1.click();

                                // Switch to the front-end window
                                driver.switchTo().window(secondWindowHandle);
                                driver.navigate().refresh();
                                System.out.println("User selected the " + size + " size, reflected on the front end.");

                                // Verify field size
                                WebElement SIZE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date Ranges']/ancestor::div[contains(@class,'field-container position')]//mat-datepicker-toggle")));
                                Dimension fieldSize = SIZE.getSize();
                                System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Width: " + fieldSize.getWidth());
                                System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Height: " + fieldSize.getHeight());
                                
                                
                                
                                
                                // Switch back to the original window
                                driver.switchTo().window(firstWindowHandle);
                                System.out.println("Switched back to the original window.");
                                
                               
                                } catch (Exception e1) {
                                    System.out.println(e1);
                                }
                        }
                        

                            
                            
                            
                            
                                
                               
                                
                                
                                
                            
                                
                                    
                                
                      	        
                      	    

                               
                               

                               
                               
                               Thread.sleep(2000);
                               
                         	     // Switch to the back-end window
                            // Switch back to the original window
                               driver.switchTo().window(firstWindowHandle);
                               System.out.println("Switched back to the backend.");

                               
                               
                               try {
                                   // Click the Validation section
                                      WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                                      js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                                      js.executeScript("arguments[0].click();", clickValidation);
                                      System.out.println("User clicked the validation category.");

                                      // Handle checkboxes: Required and Disable
                                      
                                          WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                          if (!requiredCheckbox.isSelected()) {
                                              js.executeScript("arguments[0].click();", requiredCheckbox);
                                              System.out.println("Checked: Required");
                                              Assert.assertTrue(requiredCheckbox.isSelected(), "Required checkbox is not selected after clicking!");
                                          } else {
                                              System.out.println("Required checkbox is already selected.");
                                          }

                                          WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                          if (!disableCheckbox.isSelected()) {
                                              js.executeScript("arguments[0].click();", disableCheckbox);
                                              System.out.println("Checked: Disable");
                                              Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                                          } else {
                                              System.out.println("Disable checkbox is already selected.");
                                          }
                                         
                                          
                                          Thread.sleep(2000);
                                          clickSave1.click();
                                          Thread.sleep(2000);
                                          // Switch to the front-end window
                                          driver.switchTo().window(secondWindowHandle);
                                          Thread.sleep(2000);
                                          driver.navigate().refresh();
                                          System.out.println("Switched to the front-end window. Page refreshed.");
                                          
                                          
                                          
                                          // verify  the "required" * icone is visible or not
                                          
                                          WebElement Required_icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date Ranges']/span[@class='impfieldstyle error ng-star-inserted']")));
                                          js.executeScript("arguments[0].scrollIntoView(true);", Required_icon);
                                          if (Required_icon.isEnabled()) {
                                              System.out.println("The 'Required icon' is enable.");
                                          } else {
                                              System.out.println("The 'Required icon' is disable.");
                                          }
                                          
                                          

                                          // Check if the Disable text field is enabled or not in FRONT-END
                                          WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                                          js.executeScript("arguments[0].scrollIntoView(true);", Required_icon);
                                          if (disableField.isEnabled()) {
                                              System.out.println("The text field is disabled.");
                                          } else {
                                              System.out.println("The text field is enable.");
                                          }
                                          
                                          Thread.sleep(2000);
                                          // Switch back to the original window
                                          driver.switchTo().window(firstWindowHandle);
                                          System.out.println("Switched to the backend-end window");
                                          
                                          
                                          
                                          //unchecked the Disible check box -BACK-END
                                          
                                       // Uncheck the "Disable" checkbox if it is selected
                                          WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                                          if (disableCheckbox1.isSelected()) {
                                              // Use JavaScript to uncheck the checkbox
                                        	  js.executeScript("arguments[0].scrollIntoView(true);", disableCheckbox1);
                                              js.executeScript("arguments[0].click();", disableCheckbox1);
                                              System.out.println("Unchecked: Disable");
                                              
                                              // Verify the checkbox is now unchecked
                                              Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                                          } else {
                                              System.out.println("Disable checkbox is already unchecked.");
                                          }

                                          Thread.sleep(2000);
                                          clickSave1.click();
                                          Thread.sleep(2000);

                                          // Switch to the front-end window
                                          driver.switchTo().window(secondWindowHandle);
                                          Thread.sleep(2000);
                                          driver.navigate().refresh();
                                          System.out.println("Switched to the front-end window. Page refreshed.");

                                          // Check if the "Disable" text field is enabled or not --FRONT-END
                                          
                                          WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, ' ng-star-inserted') and contains(text(), ' Date Ranges ')]")));
                                          js.executeScript("arguments[0].scrollIntoView(true);", disableField1);
                                          if (disableField1.isEnabled()) {
                                              System.out.println("The text field is enable.");
                                          } else {
                                              System.out.println("The text field is Disable.");
                                          }
                                          Thread.sleep(2000);
                                          // Switch back to the original window
                                          driver.switchTo().window(firstWindowHandle);
                                          System.out.println("Switched to the backend-end window");
                                          
                                           
                                      } catch (Exception e) {
                                          System.out.println("Error handling validation checkboxes: " + e.getMessage());
                                      }

                                      
                                  try {    
                                   
                                      

                                      // Fill the "From Date" field
                                      WebElement fromDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='From Date']/following-sibling::input")));
                                      js.executeScript("arguments[0].scrollIntoView(true);", fromDateField);
                                      fromDateField.sendKeys("11/20/2024");

                                      // Fill the "To Date" field
                                      WebElement toDateField = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//label[text()='To Date']/following-sibling::input")));
                                      js.executeScript("arguments[0].scrollIntoView(true);", toDateField);
                                      toDateField.sendKeys("11/25/2024");

                                      // Click on "Is Minimum Date Today" checkbox
                                      WebElement minDateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Is Minimum Date Today ']/preceding-sibling::span/input")));
                                      js.executeScript("arguments[0].scrollIntoView(true);", minDateCheckbox);
                                      js.executeScript("arguments[0].click();", minDateCheckbox);

                                      // Click on "Is Max Date Today" checkbox
                                      WebElement maxDateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Is Max Date Today ']/preceding-sibling::span/input")));
                                      js.executeScript("arguments[0].scrollIntoView(true);", maxDateCheckbox);
                                      js.executeScript("arguments[0].click();", maxDateCheckbox);

                                      System.out.println("All actions completed successfully.");

                                  } catch (Exception e) {
                                      System.out.println("Error encountered: " + e.getMessage());
                                      e.printStackTrace();
                                  }
                       
             
                             Thread.sleep(2000);
                           clickSave1.click(); 
             
                           // Switch to the front-end window
                           driver.switchTo().window(secondWindowHandle);
                           Thread.sleep(2000);
                           driver.navigate().refresh();
                           System.out.println("Switched to the front-end window. Page refreshed.");
             
             try {
             
                           // Wait for the datepicker to be visible and click it
                           WebElement click_datepicker_DR = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mat-mdc-button-touch-target']")));

                           // Scroll the datepicker element into view and click it using JavaScriptExecutor
                           js.executeScript("arguments[0].scrollIntoView(true);", click_datepicker_DR);
                           js.executeScript("arguments[0].click();", click_datepicker_DR);
                           System.out.println("Datepicker opened.");

                        // Wait for the current year to be visible and retrieve the current year
                        // Wait for the current month and year (NOV 2024) to be visible
                           String monthYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='NOV 2024']"))).getText();
                           System.out.println("Initial Month and Year: " + monthYear);

                           try {
                               // Loop until the desired month and year (DEC 2024) is visible
                               while (!monthYear.equals("DEC 2024")) {
                                   // Click the "next" button to go to the next month
                                   WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'mat-calendar-next-button')]")));
                                   nextButton.click();
                                   Thread.sleep(3000);
                                   // Wait for the updated month and year to be visible and fetch it
                                   monthYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='DEC 2024']"))).getText();
                                   System.out.println("Navigating to: " + monthYear);
                               }

                               // Print confirmation when the desired month and year are reached
                               System.out.println("Final Month and Year: " + monthYear);
                           } catch (Exception e) {
                               // Handle any unexpected errors
                               System.err.println("An error occurred while navigating to DEC 2024: " + e.getMessage());
                           }


                           // Assert that the month and year
                           Assert.assertEquals(monthYear, "DEC 2024", "Year & month mismatch! Expected 2024, but got " + monthYear);  
                           System.out.println("Year is now DEC 2024.");

                           // Wait for the desired date to be visible and click it
                           WebElement Date1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' 25 ']")));
                           Date1.click(); 
                           WebElement Date2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' 30 ']")));
                           Date2.click(); 
                           
                           System.out.println("select date from 25/12/2024-30/12/2024");

                           // Wait for the expected date (Dec/2026) to be visible after selecting the month
                           WebElement expected_dates = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Date Ranges']/ancestor::div[contains(@class, 'field-container')]//mat-datepicker-toggle")));
                           System.out.println("Expected Date is: " + expected_dates.getText());

                          

                      }catch(Exception e) {
            	 
            	         System.out.println(e);
                     }
             
             
             
             // Check if the attachment element is visible
             try {
                 // First Upload: File 1
                 String filePath1 = "C:\\Users\\HP\\Downloads\\FRAME WORK.pdf";
                 WebElement attachment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//span[text()='Add Attachment']")));
                 if (attachment.isDisplayed()) {
                     attachment.click();
                     System.out.println("Attachment dialog opened for File 1.");

                     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);

                     Robot rb = new Robot();
                     rb.delay(2000);

                     // Simulate CTRL+V and ENTER for File 1
                     rb.keyPress(KeyEvent.VK_CONTROL);
                     rb.keyPress(KeyEvent.VK_V);
                     rb.keyRelease(KeyEvent.VK_V);
                     rb.keyRelease(KeyEvent.VK_CONTROL);
                     rb.keyPress(KeyEvent.VK_ENTER);
                     rb.keyRelease(KeyEvent.VK_ENTER);

                     System.out.println("File 1 uploaded successfully.");
                 } else {
                     System.out.println("Attachment button not visible for File 1.");
                 }

                 // Verify File 1 Upload
                 WebElement uploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//li[contains(@class,'uploded-files')]/span")));
                 Assert.assertTrue(uploadedFile.isDisplayed(),"Uploaded file 1 is not displayed.");
                 System.out.println("File 1 is displayed: " + uploadedFile.getText());

                 // Delete File 1
                 WebElement deleteFile = wait.until(ExpectedConditions.elementToBeClickable(
                     By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//li[contains(@class,'uploded-files')]/mat-icon[contains(text(),' delete')]")));
                 deleteFile.click();
                 System.out.println("File 1 deleted successfully.");

                 // Re-Upload: File 1
                 if (attachment.isDisplayed()) {
                     attachment.click();
                     System.out.println("Attachment dialog opened again for File 1.");

                     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);
                     Robot rb = new Robot();
                     rb.delay(2000);

                     // Simulate CTRL+V and ENTER for Re-Upload
                     rb.keyPress(KeyEvent.VK_CONTROL);
                     rb.keyPress(KeyEvent.VK_V);
                     rb.keyRelease(KeyEvent.VK_V);
                     rb.keyRelease(KeyEvent.VK_CONTROL);
                     rb.keyPress(KeyEvent.VK_ENTER);
                     rb.keyRelease(KeyEvent.VK_ENTER);

                     System.out.println("File 1 re-uploaded successfully.");
                 } else {
                     System.out.println("Attachment button not visible for re-upload.");
                 }

                 // Verify Re-Upload of File 1
                 WebElement reUploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//li[contains(@class,'uploded-files')]/span")));
                 Assert.assertTrue(reUploadedFile.isDisplayed(),"Re-uploaded file 1 is not displayed.");
                 System.out.println("File 1 is displayed after re-upload: " + reUploadedFile.getText());

             } catch (Exception e) {
                 System.out.println("Error during file upload process: " + e.getMessage());
             }


             
             try {  
             // Check if the volume icon is visible and click it 
             WebElement volumeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[text()='volume_up']")));
             if (volumeIcon.isDisplayed()) {
                 System.out.println("Volume icon is visible.");
                 // Click on the volume icon
                 volumeIcon.click();
             } else {
                 System.out.println("Volume icon is not visible.");
             }
             
            
            
                 // stop the recording and verify the file is dispalted or not
                

                 // Locate and click the button
                 WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//button[@class='stop-button ng-star-inserted']/mat-icon")));
                 js.executeScript("arguments[0].scrollIntoView(true);", button); // Scroll to the button
                 button.click(); // Click the button
                 System.out.println("Button clicked.");

                 // Verify if the Audio file is displayed or not
                 WebElement Audiofile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()=' multiple Employee Names ']/ancestor::div[@class='row ng-star-inserted']//li[@id='attachData.Name']/span")));
                 
                 boolean isDisplayed = Audiofile.isDisplayed();
                 Assert.assertTrue(isDisplayed,"The element is not displayed as expected."); 
                 
                 // Print the text of the element
                 String audiofileNameText = Audiofile.getText();
                 System.out.println("Audiofile name is: " + audiofileNameText);

             } catch (Exception e) {
                 System.err.println("An error occurred: " + e.getMessage());
             }

            
             
        
             
          
  
  
             try {
                 // Locate the 'multi Line Input'
                 WebElement Employee_Name  = wait.until(ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//label[text()='multiple Employee Names ']")));
                 Assert.assertNotNull(Employee_Name ,"multiple Employee Names   is not found");

                 // Perform mouse hover action
                 actions.moveToElement(Employee_Name ).perform();
                 System.out.println("Hovered over 'multiple Employee Names ' successfully.");

                 // Wait for the delete icon and click it
                 WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(
                     By.xpath("//label[contains(text(),'multiple Employee Names ')]/ancestor::div[@class='row position-relative']//nb-icon[@icon='delete']")));
                 actions.moveToElement(deleteIcon).click().perform();
                 System.out.println("Clicked on the delete icon successfully.");

                 // Verify successful deletion (e.g., element is removed or confirmation message appears)
                 boolean isDeleted = wait.until(ExpectedConditions.invisibilityOf(Employee_Name ));
                 Assert.assertTrue(isDeleted,"multiple Employee Names  was not deleted successfully.");

             } catch (Exception e) {
                 Assert.fail("Test failed due to an exception: " + e.getMessage());
             }
  
  
  
              
             
             }
             
             
             
             
          
             
             
             
             @And("user verifies and changes the settings of the Currency")
             public void user_verifies_and_changes_the_settings_of_the_Currency() throws Exception {

                	// Frontend Verification
              	    ((JavascriptExecutor) driver).executeScript("window.open()");
              	    Set<String> windowHandles = driver.getWindowHandles();
              	    Iterator<String> iterator = windowHandles.iterator();
              	    String firstWindowHandle = iterator.next();
              	    String secondWindowHandle = iterator.next();
              	    
              	    
              	  Thread.sleep(2000);
                  // Switch back to the original window
                  driver.switchTo().window(firstWindowHandle);
                  System.out.println("switch to admin backend");
              	    
                	 JavascriptExecutor js = (JavascriptExecutor) driver;
             	    
             	    // Verify and change the settings of the Number 
             	    WebElement Currency  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[9]")));
             	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
             	    
             	    // Perform drag-and-drop action
             	    Actions actions = new Actions(driver);
             	    actions.dragAndDrop(Currency, targetTabElement).perform();
             	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Currency ']")));
             	    
             	    Assert.assertTrue(droppedElement.isDisplayed(), "Currency   component was not successfully dropped.");
             	    System.out.println("Successfully dragged and dropped the Currency component to the Response tab.");
             	    
             	    // Verify right-side settings/components
             	    for (int i = 1; i <= 9; i += 2) {
             	        try {
             	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
             	            js.executeScript("arguments[0].scrollIntoView(true);", category);
             	            String categoryText = category.getText();
             	            System.out.println("Category: " + categoryText);

             	            if (categoryText.isEmpty()) {
             	                System.err.println("Warning: Category text is empty for element at index " + i);
             	            }
             	        } catch (NoSuchElementException e) {
             	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
             	        } catch (Exception e) {
             	            System.err.println("An unexpected error occurred: " + e.getMessage());
             	        }
             	    }

             	    // Save the application
             	    try {
             	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
             	        clickSave.click();
             	        System.out.println("Clicked on the save button.");

             	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
             	        String actualMessage = appliSave.getText();

             	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
             	        System.out.println("Application saved with message: " + actualMessage);
             	    } catch (Exception e) {
             	        System.out.println("An error occurred while saving the application.");
             	        e.printStackTrace();
             	    }

             	   
             	    // Step 2: Switch to the front-end window
     	            driver.switchTo().window(secondWindowHandle);
     	            
     	           driver.navigate().refresh();
     	           System.out.println("Switched to the front-end window. Page refreshed.");
     	            
     	           
             	    try {
             	        // Verify that "Currency " is displayed
             	        WebElement Date_Range = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='Currency']")));
             	        Assert.assertTrue(Date_Range.isDisplayed(), "'Currency ' is not visible on the page!");
             	        System.out.println("'Currency ' is visible on the page.");
             	    } catch (Exception e) {
             	        System.err.println("Error while verifying 'Currency' visibility: " + e.getMessage());
             	    }

     	            
     	         
                     // Switch back to the original window
                     driver.switchTo().window(firstWindowHandle);

                     System.out.println(" Switch to the back-end window");
                     
                     
                     
                  
                  

                     try {
                         // Set the Field Label
                         WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Field label']/following-sibling::input")));
                         js.executeScript("arguments[0].scrollIntoView(true);", fieldLabelInput);
                         fieldLabelInput.clear();
                         fieldLabelInput.sendKeys("Currency");
                         Assert.assertEquals(fieldLabelInput.getAttribute("value"), "Currency", "Field Label input text does not match expected value.");
                         System.out.println("Field Label set to 'Currency' successfully.");
                     } catch (Exception e) {
                         System.err.println("Failed to set the Field Label: " + e.getMessage());
                     }

                     try {
                         // Set the Placeholder Text
                         WebElement placeholderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Placeholder text']/following-sibling::input")));
                         placeholderInput.clear();
                         placeholderInput.sendKeys("Enter the amount");
                         Assert.assertEquals(placeholderInput.getAttribute("value"), "Enter the amount", "Placeholder text does not match expected value.");
                         System.out.println("Placeholder Text set to 'Enter the amount' successfully.");
                     } catch (Exception e) {
                         System.err.println("Failed to set the Placeholder Text: " + e.getMessage());
                     }

                     try {
                         // Set the Help Text
                         WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                         helpTextInput.clear();
                         helpTextInput.sendKeys("Enter the amount");
                         Assert.assertEquals(helpTextInput.getAttribute("value"), "Enter the amount", "Help Text does not match expected value.");
                         System.out.println("Help Text set to 'Enter the amount' successfully.");
                     } catch (Exception e) {
                         System.err.println("Failed to set the Help Text: " + e.getMessage());
                     }

                     try {
                         // Set the Tooltip
                         WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                         tooltipInput.clear();
                         tooltipInput.sendKeys("enter valid amount");
                         Assert.assertEquals(tooltipInput.getAttribute("value"), "enter valid amount", "Tooltip text does not match expected value.");
                         System.out.println("Tooltip set to 'enter valid amount' successfully.");
                     } catch (Exception e) {
                         System.err.println("Failed to set the Tooltip: " + e.getMessage());
                     }
                     
                     
                     //select the enable duplicate 
                     
                    
                         try {
                             WebElement enableDuplicateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Enable Duplicate ']/preceding::input[@type='checkbox']")));
                             if (!enableDuplicateCheckbox.isSelected()) {
                             	 
                                  js.executeScript("arguments[0].click();", enableDuplicateCheckbox);
                                 System.out.println("Checked: Enable Duplicate");
                                 Assert.assertTrue(enableDuplicateCheckbox.isSelected(), enableDuplicateCheckbox + " checkbox is not selected after clicking!");
                                 System.out.println("Enable Duplicate checkbox is already selected.");
                             }
                         } catch (Exception e) {
                             System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                         }

                      // Index Numbers Checkbox
                         try {
                             WebElement indexNumbersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Index Numbers ']/preceding::input[@type='checkbox'])[2]")));
                             if (!indexNumbersCheckbox.isSelected()) {
                             	 js.executeScript("arguments[0].click();", indexNumbersCheckbox);
                                 System.out.println("Checked: Index Numbers");
                                 // Verify if the checkbox is selected after clicking
                                 Assert.assertTrue(indexNumbersCheckbox.isSelected(), indexNumbersCheckbox + " checkbox is not selected after clicking!");
                             } else {
                                 System.out.println("Index Numbers checkbox is already selected.");
                             }
                         } catch (Exception e) {
                             System.out.println("Error locating or clicking 'Index Numbers' checkbox: " + e.getMessage());
                         }

                         // Repeat Heading Checkbox
                         try {
                             WebElement repeatHeadingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Repeat Heading ']/preceding::input[@type='checkbox'])[3]")));
                             if (!repeatHeadingCheckbox.isSelected()) {
                             	 js.executeScript("arguments[0].click();", repeatHeadingCheckbox);
                                 System.out.println("Checked: Repeat Heading");
                                 // Verify if the checkbox is selected after clicking
                                 Assert.assertTrue(repeatHeadingCheckbox.isSelected(), repeatHeadingCheckbox + " checkbox is not selected after clicking!");
                             } else {
                                 System.out.println("Repeat Heading checkbox is already selected.");
                             }
                         } catch (Exception e) {
                             System.out.println("Error locating or clicking 'Repeat Heading' checkbox: " + e.getMessage());
                         }

                         
                         
                     //verify the show conversation toggle    
                         
                      // Locate the toggle element
                         WebElement toggleElement = driver.findElement(By.xpath("//label[text()='Show conversion']/parent::div//i"));

                         // Capture the initial state of the toggle
                         String initialClass = toggleElement.getAttribute("class");
                         System.out.println("Initial toggle state: " + (initialClass.contains("fa-toggle-on") ? "ON" : "OFF"));

                         // Click the toggle to change its state
                         toggleElement.click();

                         // Wait for the state change (if needed)
                         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                         wait.until(ExpectedConditions.attributeToBe(toggleElement, "class", initialClass.contains("fa-toggle-on") 
                             ? "fa ml-2 blue fa-toggle-off" 
                             : "fa ml-2 blue fa-toggle-on"));

                         // Capture the updated state of the toggle
                         String updatedClass = toggleElement.getAttribute("class");
                         System.out.println("Updated toggle state: " + (updatedClass.contains("fa-toggle-on") ? "ON" : "OFF"));

                         // Assertions to validate the state change
                         if (initialClass.contains("fa-toggle-on")) {
                             Assert.assertTrue(updatedClass.contains("fa-toggle-off"));
                         } else {
                             Assert.assertTrue(updatedClass.contains("fa-toggle-on"));
                         }

                         
                         
                         
                      // click Filterable Checkbox
                         try {
                             WebElement allowAttachmentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Filterable ']/preceding::input[@type='checkbox'])[6]")));
                             if (!allowAttachmentCheckbox.isSelected()) {
                             	js.executeScript("arguments[0].click();", allowAttachmentCheckbox);
                                 System.out.println("Checked: Allow Attachment");
                                 Assert.assertTrue(allowAttachmentCheckbox.isSelected(), allowAttachmentCheckbox + " checkbox is not selected after clicking!");
                             } else {
                                 System.out.println("Allow Attachment checkbox is already selected.");
                             }
                         } catch (Exception e) {
                             System.out.println("Error locating or clicking 'Allow Attachment' checkbox: " + e.getMessage());
                         }
                         
                         
                         
                      // Save the application
                 	   
                 	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
                 	        clickSave1.click();
                 	        System.out.println("Clicked on the save button.");

                 	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
                 	        String actualMessage1 = appliSave1.getText();

                 	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
                 	        System.out.println("Application saved with message: " + actualMessage1);
            
                         
                         
                         
                         
                         
                      // Step 1: Pause briefly to allow for any loading or processing
         	            Thread.sleep(2000);

         	            // Step 2: Switch to the front-end window
         	            driver.switchTo().window(secondWindowHandle);
         	            

         	            // Step 3: Refresh the page to reload all elements
         	            driver.navigate().refresh();
         	            System.out.println("Switched to the front-end window. Page refreshed.");


         	            
         	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
         	            
         	            
         	            try {
                             // Check if the feild label Name Reflected front end or not
                             WebElement dateRangeslabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Currency']")));
                             Assert.assertTrue(dateRangeslabel.isDisplayed(), "Currency label is not visible.");
                             System.out.println(dateRangeslabel.getText()+": label is visible on the page.");
                         } catch (Exception e) {
                             System.err.println("Failed to locate or display 'Currency' label: " + e.getMessage());
                         }

                         try {
                             // Check if the Help text is reflected front end or not
                             WebElement helptextText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Currency']/ancestor::div[contains(@class, 'field-container')]//p[@class='input-footer mb-2']")));
                             Assert.assertTrue(helptextText.isDisplayed(), "Help text is not visible.");
                             System.out.println(helptextText.getText()+" :text is visible on the page.");
                         } catch (Exception e) {
                             System.err.println("Failed to locate or display help text: " + e.getMessage());
                         }

                         try {
                             // Check if the placeholder text is visible
                             WebElement placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Currency']/ancestor::div[contains(@class, 'field-container')]//input")));
                             Assert.assertTrue(placeholder.isDisplayed(), "Input field text is not visible.");
                             System.out.println("Enter the amount :Input field text is visible on the page.");
                         } catch (Exception e) {
                             System.err.println("Failed to locate or display the input field text: " + e.getMessage());
                         }

                         try {
                             // Click on the tooltip icon near 'multiple Employee Name '
                             WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Currency']/child::span/mat-icon")));
                             tooltipIcon.click();
                             System.out.println("Tooltip icon clicked successfully.");
                         } catch (Exception e) {
                             System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                         }

                         try {
                             // Verify that the tooltip content text is visible after clicking the tooltip icon
                             WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Currency']/child::span/div/p")));
                             Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                             System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                         } catch (Exception e) {
                             System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                         }
                 	    
                         
         	            
         	            
         	            
         	            
         	            
         	            
         	            
         	            
         	            
         	            
         	            
         	            
         	            
                         // verify the repeated duplicate headings with add index number ----in ADMIN FRONT-END
                         try {
                        	    Actions action = new Actions(driver);
                        	   

                        	    // Step 1: Hover over the tooltip icon and click the duplicate icon
                        	    System.out.println("Step 1: Hovering over tooltip icon and clicking duplicate icon.");
                        	    WebElement tooltipIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Currency']/child::span/mat-icon")));
                        	    action.moveToElement(tooltipIcon1).perform();
                        	    System.out.println("Tooltip hovered successfully.");

                        	    WebElement duplication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' Currency ')]/following::mat-icon[text()='add_circle_outline']")));
                        	    duplication.click();
                        	    System.out.println("Duplicate icon clicked. New heading should be added.");
                        	    Thread.sleep(2000);

                        	    // Step 2: Verify duplicate headings
                        	    System.out.println("Step 2: Verifying duplicate headings.");
                        	    List<WebElement> duplicateHeadings = driver.findElements(By.xpath("//label[contains(text(),' Currency ')]"));
                        	    Assert.assertTrue(duplicateHeadings.size() > 1, "No duplicate headings found. Verification failed.");

                        	    System.out.println("Verification successful. Total occurrences of ' Currency ': " + duplicateHeadings.size());

                        	    for (int i = 0; i < duplicateHeadings.size(); i++) {
                        	        WebElement heading = duplicateHeadings.get(i);
                        	        if (heading.isDisplayed()) {
                        	            System.out.println("Duplicate " + (i + 1) + ": " + heading.getText());
                        	        } else {
                        	            System.out.println("Duplicate " + (i + 1) + " is not visible on the page.");
                        	        }
                        	    }

                        	    // Step 3: Click the remove icon to delete a heading
                        	    System.out.println("Step 3: Removing duplicate heading.");
                        	    
                        	    WebElement tooltipIcon12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' Currency 2 ')]//span/child::mat-icon")));
                        	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon12);
                        	    action.moveToElement(tooltipIcon12).perform();  
                 
                        	    WebElement clickRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' Currency 2 ')]/following::mat-icon[text()='remove_circle_outline']")));
                        	    action.moveToElement(clickRemove).perform();
                        	    clickRemove.click();
                        	    System.out.println("Remove icon clicked. Heading should be removed.");

                        	    // Step 4: Verify the heading is removed
                        	    System.out.println("Step 4: Verifying removal of duplicate headings.");
                        	    duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' Currency ')]"));
                        	    Assert.assertEquals(duplicateHeadings.size(), 1, "Duplication removal failed. Multiple headings still found.");

                        	    System.out.println("Duplication successfully removed. Only one occurrence of '  Currency ' found.");
                        	} catch (Exception e) {
                        	    e.printStackTrace();
                        	    System.out.println("An error occurred: " + e.getMessage());
                        	    Assert.fail("Test case failed due to unexpected error: " + e.getMessage());
                        	}

                         
                         
                         
                         // Switch back to the original window
                         driver.switchTo().window(firstWindowHandle);

                         System.out.println(" Switch to the back-end window");
                          
                         
                         
                         
                         
                        

                  
                     
                     
                         
                         // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                         try {
                         WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                         js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                         js.executeScript("arguments[0].click();", click_condition);
                         
                         System.out.println("user click the condition category");
                         
                     
                         
                             WebElement enable_Use_in_workflow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Use in workflow ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                             if (!enable_Use_in_workflow.isSelected()) {
                             	 
                                  js.executeScript("arguments[0].click();", enable_Use_in_workflow);
                                 System.out.println("Checked: Use in workflow");
                                 Assert.assertTrue(enable_Use_in_workflow.isSelected(), enable_Use_in_workflow + " checkbox is not selected after clicking!");
                                 System.out.println("Use in workflow checkbox is  selected.");
                             }
                         } catch (Exception e) {
                             System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                         }
                       
                         
                         //step:2 verify the unique varible name input
                         
                         WebElement unique_varibleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group d-flex ng-star-inserted']/child::label")));
                       

                         if (unique_varibleInput.isDisplayed()) {
                             System.out.println(" unique varible name input : is displayed.");
                         } else {
                             System.out.println("The unique varible name input is not displayed.");
                         }
                         
                         //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                         
                         try {
                             // Step 1: Click on the edit icon
                             WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                             js.executeScript("arguments[0].click();", clickedit_icon);
                             js.executeScript("arguments[0].scrollIntoView(true);", clickedit_icon);
                             System.out.println("User clicked the edit icon");

                             // Step 2: Verify the expected text on the edit page
                             String expected_text12 = "Rule Name:";
                             WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                             String actual_text12 = edit_rules.getText();
                             
                             // Assertion with custom message for failure
                             Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                             System.out.println("Edit rules for Employee Name: page opened successfully");

                             // Step 3: Close the page
                             WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                             js.executeScript("arguments[0].click();", close_page);
                             System.out.println("Edit page closed successfully");

                         } catch (Exception e) {
                             // Print the stack trace and a custom error message
                             e.printStackTrace();
                             System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                         }
                         
                         
                         
                         
                         // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                         
                         // click the appearence 
                         
                      // Click the Appearance category
                         try {
                             WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                             js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                             js.executeScript("arguments[0].click();", clickAppearance);
                             
                             
                             
                             // Enter the "Number of decimal places" input
                             
                             WebElement decimalPlacesInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Number of decimal places']/parent::div/input")));

                             // Print statement for debugging
                             System.out.println("Decimal places input field is visible.");

                             // Clear the input field and set a value
                             decimalPlacesInput.clear();  // Clear any existing value
                             decimalPlacesInput.sendKeys("2");  // Enter the value '2'
                             System.out.println("Entered '2' in the 'Number of decimal places' input field.");
                             // Save the selection and navigate to the front-end window
                             
                             
                             
                             
                         }catch(Exception e) {
                       	  System.out.println(e);
                         }
                             
                             
                             
                      // Define the sizes to loop through
                         String[] sizes = {"medium", "small", "large"};

                         for (String size : sizes) {
                             try {
                                 // Click the size (medium, small, large)
                                 WebElement sizeElement = driver.findElement(By.xpath("//label[text()='" + size + "']"));
                                 js.executeScript("arguments[0].click();", sizeElement);
                                 clickSave1.click();

                                 // Switch to the front-end window
                                 driver.switchTo().window(secondWindowHandle);
                                 driver.navigate().refresh();
                                 System.out.println("User selected the " + size + " size, reflected on the front end.");

                                 // Verify field size
                                 WebElement SIZE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Currency']/ancestor::div[contains(@class,'field-container position')]//input")));
                                 Dimension fieldSize = SIZE.getSize();
                                 System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Width: " + fieldSize.getWidth());
                                 System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Height: " + fieldSize.getHeight());
                                 
                                 
                                 
                                 
                                 // Switch back to the original window
                                 driver.switchTo().window(firstWindowHandle);
                                 System.out.println("Switched back to the original window.");
                                 
                                
                                 } catch (Exception e1) {
                                     System.out.println(e1);
                                 }
                         }
                         

                             
                             
                             
                             
                                 
                                
                                 
                                 
                                 
                             
                                 
                                     
                                 
                       	        
                       	    

                                
                                

                                
                                
                                Thread.sleep(2000);
                                
                          	     // Switch to the back-end window
                             // Switch back to the original window
                                driver.switchTo().window(firstWindowHandle);
                                System.out.println("Switched back to the backend.");

                                
                                
                                try {
                                    // Click the Validation section
                                       WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                                       js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                                       js.executeScript("arguments[0].click();", clickValidation);
                                       System.out.println("User clicked the validation category.");

                                       // Handle checkboxes: Required and Disable
                                       
                                           WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                           if (!requiredCheckbox.isSelected()) {
                                               js.executeScript("arguments[0].click();", requiredCheckbox);
                                               System.out.println("Checked: Required");
                                               Assert.assertTrue(requiredCheckbox.isSelected(), "Required checkbox is not selected after clicking!");
                                           } else {
                                               System.out.println("Required checkbox is already selected.");
                                           }

                                           WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                           if (!disableCheckbox.isSelected()) {
                                               js.executeScript("arguments[0].click();", disableCheckbox);
                                               System.out.println("Checked: Disable");
                                               Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                                           } else {
                                               System.out.println("Disable checkbox is already selected.");
                                           }
                                          
                                           
                                           Thread.sleep(2000);
                                           clickSave1.click();
                                           Thread.sleep(2000);
                                           // Switch to the front-end window
                                           driver.switchTo().window(secondWindowHandle);
                                           Thread.sleep(2000);
                                           driver.navigate().refresh();
                                           System.out.println("Switched to the front-end window. Page refreshed.");
                                           
                                           
                                           
                                           // verify  the "required" * icone is visible or not
                                           
                                           WebElement Required_icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Currency']/span[@class='impfieldstyle error ng-star-inserted']")));
                                           js.executeScript("arguments[0].scrollIntoView(true);", Required_icon);
                                           if (Required_icon.isEnabled()) {
                                               System.out.println("The 'Required icon' is enable.");
                                           } else {
                                               System.out.println("The 'Required icon' is disable.");
                                           }
                                           
                                           

                                           // Check if the Disable text field is enabled or not in FRONT-END
                                           WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                                           js.executeScript("arguments[0].scrollIntoView(true);", Required_icon);
                                           if (disableField.isEnabled()) {
                                               System.out.println("The text field is disabled.");
                                           } else {
                                               System.out.println("The text field is enable.");
                                           }
                                           
                                           Thread.sleep(2000);
                                           // Switch back to the original window
                                           driver.switchTo().window(firstWindowHandle);
                                           System.out.println("Switched to the backend-end window");
                                           
                                           
                                           
                                           //unchecked the Disible check box -BACK-END
                                           
                                        // Uncheck the "Disable" checkbox if it is selected
                                           WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                                           if (disableCheckbox1.isSelected()) {
                                               // Use JavaScript to uncheck the checkbox
                                         	  js.executeScript("arguments[0].scrollIntoView(true);", disableCheckbox1);
                                               js.executeScript("arguments[0].click();", disableCheckbox1);
                                               System.out.println("Unchecked: Disable");
                                               
                                               // Verify the checkbox is now unchecked
                                               Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                                           } else {
                                               System.out.println("Disable checkbox is already unchecked.");
                                           }

                                           Thread.sleep(2000);
                                           clickSave1.click();
                                           Thread.sleep(2000);

                                           // Switch to the front-end window
                                           driver.switchTo().window(secondWindowHandle);
                                           Thread.sleep(2000);
                                           driver.navigate().refresh();
                                           System.out.println("Switched to the front-end window. Page refreshed.");

                                           // Check if the "Disable" text field is enabled or not --FRONT-END
                                           
                                           WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, ' ng-star-inserted') and contains(text(), ' Currency ')]")));
                                           js.executeScript("arguments[0].scrollIntoView(true);", disableField1);
                                           if (disableField1.isEnabled()) {
                                               System.out.println("The text field is enable.");
                                           } else {
                                               System.out.println("The text field is Disable.");
                                           }
                                           Thread.sleep(2000);
                                           // Switch back to the original window
                                           driver.switchTo().window(firstWindowHandle);
                                           System.out.println("Switched to the backend-end window");
                                           
                                            
                                       } catch (Exception e) {
                                           System.out.println("Error handling validation checkboxes: " + e.getMessage());
                                       }

                                
             
             }
             
             
             
             
             
             @Then("user verifies and changes the settings of the Fileuploades")
             public void user_verifies_and_changes_the_settings_of_the_fileuploades() throws Exception {
            	 
            	 
            	 
            	 
            	 
            	 
            	// Frontend Verification
           	    ((JavascriptExecutor) driver).executeScript("window.open()");
           	    Set<String> windowHandles = driver.getWindowHandles();
           	    Iterator<String> iterator = windowHandles.iterator();
           	    String firstWindowHandle = iterator.next();
           	    String secondWindowHandle = iterator.next();
           	    
           	    
           	  Thread.sleep(2000);
               // Switch back to the original window
               driver.switchTo().window(firstWindowHandle);
               System.out.println("switch to admin backend");
           	    
             	 JavascriptExecutor js = (JavascriptExecutor) driver;
          	    
          	    // Verify and change the settings of the Number 
          	    WebElement Currency  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[10]")));
          	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
          	    
          	    // Perform drag-and-drop action
          	    Actions actions = new Actions(driver);
          	    actions.dragAndDrop(Currency, targetTabElement).perform();
          	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='File Uploads ']")));
          	    
          	    Assert.assertTrue(droppedElement.isDisplayed(), "File Uploads    component was not successfully dropped.");
          	    System.out.println("Successfully dragged and dropped the File Uploads  component to the Response tab.");
          	    
          	    // Verify right-side settings/components
          	    for (int i = 1; i <= 9; i += 2) {
          	        try {
          	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
          	            js.executeScript("arguments[0].scrollIntoView(true);", category);
          	            String categoryText = category.getText();
          	            System.out.println("Category: " + categoryText);

          	            if (categoryText.isEmpty()) {
          	                System.err.println("Warning: Category text is empty for element at index " + i);
          	            }
          	        } catch (NoSuchElementException e) {
          	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
          	        } catch (Exception e) {
          	            System.err.println("An unexpected error occurred: " + e.getMessage());
          	        }
          	    }

          	    // Save the application
          	    try {
          	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
          	        clickSave.click();
          	        System.out.println("Clicked on the save button.");

          	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
          	        String actualMessage = appliSave.getText();

          	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
          	        System.out.println("Application saved with message: " + actualMessage);
          	    } catch (Exception e) {
          	        System.out.println("An error occurred while saving the application.");
          	        e.printStackTrace();
          	    }

          	   
          	    // Step 2: Switch to the front-end window
  	            driver.switchTo().window(secondWindowHandle);
  	            
  	           driver.navigate().refresh();
  	           System.out.println("Switched to the front-end window. Page refreshed.");
  	            
  	           
          	    try {
          	        // Verify that "File Uploads  " is displayed
          	        WebElement File_upload = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='File Uploads']")));
          	        Assert.assertTrue(File_upload.isDisplayed(), "'File Uploads ' is not visible on the page!");
          	        System.out.println("'File Uploads ' is visible on the page.");
          	    } catch (Exception e) {
          	        System.err.println("Error while verifying 'File Uploads' visibility: " + e.getMessage());
          	    }

  	            
  	         
                  // Switch back to the original window
                  driver.switchTo().window(firstWindowHandle);

                  System.out.println(" Switch to the back-end window");
                  Thread.sleep(3000);
                  
                  
               
               

                  try {
                      // Set the Field Label
                      WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Field label']/following-sibling::input[@type='text']"))); 
                      fieldLabelInput.clear();
                      fieldLabelInput.sendKeys("File Uploads");
                      Assert.assertEquals(fieldLabelInput.getAttribute("value"), "File Uploads", "Field Label input text does not match expected value.");
                      System.out.println("Field Label set to 'Currency' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Field Label: " + e.getMessage());
                  }

               

                  try {
                      // Set the Help Text
                      WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                      helpTextInput.clear();
                      helpTextInput.sendKeys("upload");
                      Assert.assertEquals(helpTextInput.getAttribute("value"), "upload", "Help Text does not match expected value.");
                      System.out.println("Help Text set to 'upload' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Help Text: " + e.getMessage());
                  }

                  try {
                      // Set the Tooltip
                      WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                      tooltipInput.clear();
                      tooltipInput.sendKeys("upload below formate");
                      Assert.assertEquals(tooltipInput.getAttribute("value"), "upload below formate", "Tooltip text does not match expected value.");
                      System.out.println("Tooltip set to 'upload below formate' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Tooltip: " + e.getMessage());
                  }
                  
                  
                  //select the enable duplicate 
                  
                 
                      try {
                          WebElement enableDuplicateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Enable Duplicate ']/preceding::input[@type='checkbox']")));
                          if (!enableDuplicateCheckbox.isSelected()) {
                          	 
                               js.executeScript("arguments[0].click();", enableDuplicateCheckbox);
                              System.out.println("Checked: Enable Duplicate");
                              Assert.assertTrue(enableDuplicateCheckbox.isSelected(), enableDuplicateCheckbox + " checkbox is not selected after clicking!");
                              System.out.println("Enable Duplicate checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                      }

                   // Index Numbers Checkbox
                      try {
                          WebElement indexNumbersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Index Numbers ']/preceding::input[@type='checkbox'])[2]")));
                          if (!indexNumbersCheckbox.isSelected()) {
                          	 js.executeScript("arguments[0].click();", indexNumbersCheckbox);
                              System.out.println("Checked: Index Numbers");
                              // Verify if the checkbox is selected after clicking
                              Assert.assertTrue(indexNumbersCheckbox.isSelected(), indexNumbersCheckbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Index Numbers checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Index Numbers' checkbox: " + e.getMessage());
                      }

                      // Repeat Heading Checkbox
                      try {
                          WebElement repeatHeadingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=' Repeat Heading ']/preceding::input[@type='checkbox'])[3]")));
                          if (!repeatHeadingCheckbox.isSelected()) {
                          	 js.executeScript("arguments[0].click();", repeatHeadingCheckbox);
                              System.out.println("Checked: Repeat Heading");
                              // Verify if the checkbox is selected after clicking
                              Assert.assertTrue(repeatHeadingCheckbox.isSelected(), repeatHeadingCheckbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Repeat Heading checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Repeat Heading' checkbox: " + e.getMessage());
                      }

                      
                      
                  
                      
                   // Save the application
              	   
              	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
              	        clickSave1.click();
              	        System.out.println("Clicked on the save button.");

              	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
              	        String actualMessage1 = appliSave1.getText();

              	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
              	        System.out.println("Application saved with message: " + actualMessage1);
         
                      
                      
                      
                      
                      
                   // Step 1: Pause briefly to allow for any loading or processing
      	            Thread.sleep(2000);

      	            // Step 2: Switch to the front-end window
      	            driver.switchTo().window(secondWindowHandle);
      	            

      	            // Step 3: Refresh the page to reload all elements
      	            driver.navigate().refresh();
      	            System.out.println("Switched to the front-end window. Page refreshed.");


      	            
      	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
      	            
      	            
      	            try {
                          // Check if the feild label Name Reflected front end or not
                          WebElement dateRangeslabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='File Uploads']")));
                          Assert.assertTrue(dateRangeslabel.isDisplayed(), "File Uploads label is not visible.");
                          System.out.println(dateRangeslabel.getText()+": label is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display 'File Uploads' label: " + e.getMessage());
                      }

      	          try {
                      // Check if the Help text is reflected front end or not
                      WebElement enterYourText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='File Uploads']/ancestor::div[contains(@class, 'field-container')]//p[@class='input-footer mb-2']")));
                      Assert.assertTrue(enterYourText.isDisplayed(), "Help text is not visible.");
                      System.out.println(enterYourText.getText()+" :text is visible on the page.");
                  } catch (Exception e) {
                      System.err.println("Failed to locate or display help text: " + e.getMessage());
                  }

                      

                      try {
                          // Click on the tooltip icon near 'file uploads '
                          WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='File Uploads']/child::span/mat-icon")));
                          tooltipIcon.click();
                          System.out.println("Tooltip icon clicked successfully.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                      }

                      try {
                          // Verify that the tooltip content text is visible after clicking the tooltip icon
                          WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='File Uploads']/child::span/div/p")));
                          Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                          System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                      }
              	    
                      
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
                      // verify the repeated duplicate headings with add index number ----in ADMIN FRONT-END
                      try {
                     	    Actions action = new Actions(driver);
                     	   

                     	    // Step 1: Hover over the tooltip icon and click the duplicate icon
                     	    System.out.println("Step 1: Hovering over tooltip icon and clicking duplicate icon.");
                     	    WebElement tooltipIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='File Uploads']/child::span/mat-icon")));
                     	   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon1);
                     	    action.moveToElement(tooltipIcon1).perform();
                     	    System.out.println("Tooltip hovered successfully.");

                     	    WebElement duplication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' File Uploads ')]/following::mat-icon[text()='add_circle_outline']")));
                     	    duplication.click();
                     	    System.out.println("Duplicate icon clicked. New heading should be added.");
                     	    Thread.sleep(2000);

                     	    // Step 2: Verify duplicate headings
                     	    System.out.println("Step 2: Verifying duplicate headings.");
                     	    List<WebElement> duplicateHeadings = driver.findElements(By.xpath("//label[contains(text(),' File Uploads ')]"));
                     	    Assert.assertTrue(duplicateHeadings.size() > 1, "No duplicate headings found. Verification failed.");

                     	    System.out.println("Verification successful. Total occurrences of ' File Uploads ': " + duplicateHeadings.size());

                     	    for (int i = 0; i < duplicateHeadings.size(); i++) {
                     	        WebElement heading = duplicateHeadings.get(i);
                     	        if (heading.isDisplayed()) {
                     	            System.out.println("Duplicate " + (i + 1) + ": " + heading.getText());
                     	        } else {
                     	            System.out.println("Duplicate " + (i + 1) + " is not visible on the page.");
                     	        }
                     	    }

                     	    // Step 3: Click the remove icon to delete a heading
                     	    System.out.println("Step 3: Removing duplicate heading.");
                     	    
                     	    WebElement tooltipIcon12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' File Uploads 2 ')]//span/child::mat-icon")));
                     	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon12);
                     	    action.moveToElement(tooltipIcon12).perform();  
              
                     	    WebElement clickRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' File Uploads 2 ')]/following::mat-icon[text()='remove_circle_outline']")));
                     	    action.moveToElement(clickRemove).perform();
                     	    clickRemove.click();
                     	    System.out.println("Remove icon clicked. Heading should be removed.");

                     	    // Step 4: Verify the heading is removed
                     	    System.out.println("Step 4: Verifying removal of duplicate headings.");
                     	    duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' File Uploads ')]"));
                     	    Assert.assertEquals(duplicateHeadings.size(), 1, "Duplication removal failed. Multiple headings still found.");

                     	    System.out.println("Duplication successfully removed. Only one occurrence of ' File Uploads ' found.");
                     	} catch (Exception e) {
                     	    e.printStackTrace();
                     	    System.out.println("An error occurred: " + e.getMessage());
                     	    Assert.fail("Test case failed due to unexpected error: " + e.getMessage());
                     	}

                      
                      
                      
                      // Switch back to the original window
                      driver.switchTo().window(firstWindowHandle);

                      System.out.println(" Switch to the back-end window");
                       
                      
                      
                      
                      
                     

               
                  
                  
                      
                      // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                      try {
                      WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                      js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                      js.executeScript("arguments[0].click();", click_condition);
                      
                      System.out.println("user click the condition category");
                      
                  
                      
                        
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                      }
                    
                      
                    
                      
                      //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                      
                      try {
                          // Step 1: Click on the edit icon
                          WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                          js.executeScript("arguments[0].click();", clickedit_icon);
                          js.executeScript("arguments[0].scrollIntoView(true);", clickedit_icon);
                          System.out.println("User clicked the edit icon");

                          // Step 2: Verify the expected text on the edit page
                          String expected_text12 = "Rule Name:";
                          WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                          String actual_text12 = edit_rules.getText();
                          
                          // Assertion with custom message for failure
                          Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                          System.out.println("Edit rules for Employee Name: page opened successfully");

                          // Step 3: Close the page
                          WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                          js.executeScript("arguments[0].click();", close_page);
                          System.out.println("Edit page closed successfully");

                      } catch (Exception e) {
                          // Print the stack trace and a custom error message
                          e.printStackTrace();
                          System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                      }
                      
                      
                      
                      
                      // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                      
                      // click the appearence 
                      
                   // Click the Appearance category
                      try {
                          WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                          js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                          js.executeScript("arguments[0].click();", clickAppearance);
                    
                          
                          
                          
                      }catch(Exception e) {
                    	  System.out.println(e);
                      }
                          
                          
                   // Locate and check the "Min Style" checkbox
                      try {
                      WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                          By.xpath("//span[text()=' Min Style ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//input")
                      ));

                      // Check if the checkbox is not already selected
                      if (!requiredCheckbox.isSelected()) {
                          js.executeScript("arguments[0].click();", requiredCheckbox); // Click using JavaScript
                          System.out.println("Checked: Min Style");
                          Assert.assertTrue(requiredCheckbox.isSelected(), "Min Style checkbox is not selected after clicking!");
                      } else {
                          System.out.println("Min Style checkbox is already selected.");
                      }
                      
                      Thread.sleep(3000);
                      clickSave1.click();

                      // Switch to the front-end window
                      driver.switchTo().window(secondWindowHandle);
                      driver.navigate().refresh();
                      System.out.println("User selected the 'Min Style', reflected on the front end.");
                      Thread.sleep(2000);

                      // Locate and verify the field size
                      WebElement mini_front = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='File Uploads']/ancestor::div[contains(@class,'field-container position')]//p[contains(text(),'Supported:')]")));
                      js.executeScript("arguments[0].scrollIntoView(true);", mini_front);
                      if (mini_front.isDisplayed()) {
                          System.out.println("The mini style is successfully  reflected frontend.");
                      } else {
                          System.out.println("The mini style is not  reflected frontend..");
                      }


                     
                      }catch (Exception e){
                    	  System.out.println(e);
                      }
                          
                          
               
                             
                             
                             Thread.sleep(2000);
                             
                       	     // Switch to the back-end window
                          // Switch back to the original window
                             driver.switchTo().window(firstWindowHandle);
                             System.out.println("Switched back to the backend.");

                             
                             
                             try {
                                 // Click the Validation section
                                    WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                                    js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                                    js.executeScript("arguments[0].click();", clickValidation);
                                    System.out.println("User clicked the validation category.");

                                    // Handle checkboxes: Required and Disable
                                    
                                        WebElement requiredCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                        if (!requiredCheckbox1.isSelected()) {
                                            js.executeScript("arguments[0].click();", requiredCheckbox1);
                                            System.out.println("Checked: Required");
                                            Assert.assertTrue(requiredCheckbox1.isSelected(), "Required checkbox is not selected after clicking!");
                                        } else {
                                            System.out.println("Required checkbox is already selected.");
                                        }

                                        WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                        if (!disableCheckbox.isSelected()) {
                                            js.executeScript("arguments[0].click();", disableCheckbox);
                                            System.out.println("Checked: Disable");
                                            Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                                        } else {
                                            System.out.println("Disable checkbox is already selected.");
                                        }
                                       
                                        
                                        Thread.sleep(2000);
                                        clickSave1.click();
                                        Thread.sleep(2000);
                                        // Switch to the front-end window
                                        driver.switchTo().window(secondWindowHandle);
                                        Thread.sleep(2000);
                                        driver.navigate().refresh();
                                        System.out.println("Switched to the front-end window. Page refreshed.");
                                        
                                        
                                        
                                        // verify  the "required" * icone is visible or not
                                        
                                        WebElement Required_icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='File Uploads']/span[@class='impfieldstyle error ng-star-inserted']")));
                                        js.executeScript("arguments[0].scrollIntoView(true);", Required_icon);
                                        if (Required_icon.isEnabled()) {
                                            System.out.println("The 'Required icon' is enable.");
                                        } else {
                                            System.out.println("The 'Required icon' is disable.");
                                        }
                                        
                                        

                                        // Check if the Disable text field is enabled or not in FRONT-END
                                        WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                                        js.executeScript("arguments[0].scrollIntoView(true);", Required_icon);
                                        if (disableField.isEnabled()) {
                                            System.out.println("The text field is disabled.");
                                        } else {
                                            System.out.println("The text field is enable.");
                                        }
                                        
                                        Thread.sleep(2000);
                                        // Switch back to the original window
                                        driver.switchTo().window(firstWindowHandle);
                                        System.out.println("Switched to the backend-end window");
                                        
                                        
                                        
                                        //unchecked the Disible check box -BACK-END
                                        
                                     // Uncheck the "Disable" checkbox if it is selected
                                        WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                                        if (disableCheckbox1.isSelected()) {
                                            // Use JavaScript to uncheck the checkbox
                                      	  js.executeScript("arguments[0].scrollIntoView(true);", disableCheckbox1);
                                            js.executeScript("arguments[0].click();", disableCheckbox1);
                                            System.out.println("Unchecked: Disable");
                                            
                                            // Verify the checkbox is now unchecked
                                            Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                                        } else {
                                            System.out.println("Disable checkbox is already unchecked.");
                                        }

                                        Thread.sleep(2000);
                                        clickSave1.click();
                                        Thread.sleep(2000);

                                        // Switch to the front-end window
                                        driver.switchTo().window(secondWindowHandle);
                                        Thread.sleep(2000);
                                        driver.navigate().refresh();
                                        System.out.println("Switched to the front-end window. Page refreshed.");

                                        // Check if the "Disable" text field is enabled or not --FRONT-END
                                        
                                        WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, ' ng-star-inserted') and contains(text(), ' File Uploads ')]")));
                                        js.executeScript("arguments[0].scrollIntoView(true);", disableField1);
                                        if (disableField1.isEnabled()) {
                                            System.out.println("The text field is enable.");
                                        } else {
                                            System.out.println("The text field is Disable.");
                                        }
                                        Thread.sleep(2000);
                                        // Switch back to the original window
                                        driver.switchTo().window(firstWindowHandle);
                                        System.out.println("Switched to the backend-end window");
                                        
                                         
                                    } catch (Exception e) {
                                        System.out.println("Error handling validation checkboxes: " + e.getMessage());
                                    }

                             
                             // check the  File Extension
                             
                             try {
                            	    // Interact with "File Extension" dropdown
                            	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='File Extensions']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                            	    js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
                            	    js.executeScript("arguments[0].click();", dropdown);
                            	    System.out.println("Dropdown clicked.");

                            	    // Select checkboxes (1 to 10)
                            	    for (int j = 1; j <= 10; j++) {
                            	        try {
                            	            // Locate the checkbox element
                            	            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@id,'mat-option')]/mat-pseudo-checkbox)[" + j + "]")));

                            	            // Locate the corresponding text element within the mat-option
                            	            WebElement textElement = driver.findElement(
                            	                By.xpath("(//*[contains(@id,'mat-option')])[" + j + "]//span[@class='mat-option-text']"));
                            	            
                            	            // Retrieve the checkbox text
                            	            String checkboxText = textElement.getText();

                            	            // Check if the checkbox is already selected using isSelected() method
                            	            if (checkbox.isSelected()) {
                            	                System.out.println("Checkbox " + j + " (" + checkboxText + ") is already selected. Skipping.");
                            	                continue; // Skip to the next checkbox
                            	            }

                            	            // Scroll to the checkbox
                            	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkbox);
                            	            wait.until(ExpectedConditions.elementToBeClickable(checkbox)); // Wait for the checkbox to be clickable

                            	            // Click the checkbox
                            	            js.executeScript("arguments[0].click();", checkbox);

                            	            // Print the name of the selected checkbox
                            	            System.out.println("Checked: " + checkboxText);
                            	        } catch (Exception e) {
                            	            System.err.println("Error handling checkbox " + j + ": " + e.getMessage());
                            	        }
                            	    }

                            	    // Close the dropdown after selecting checkboxes
                            	    WebElement closeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='File Extensions']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                            	    js.executeScript("arguments[0].click();", closeDropdown);
                            	    System.out.println("Dropdown closed.");
                            	} catch (Exception e) {
                            	    System.err.println("Error in dropdown handling: " + e.getMessage());
                            	}

                            	    Thread.sleep(2000);
                            	    WebElement element = driver.findElement(By.xpath("//mat-icon[text()='save']"));
                                    element.click();
                            	    
                            	    
                                    // click the dropdown  "Maximum File Size"
                                       WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Maximum File Size']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                                       dropdown1.click();
                                       System.out.println("Dropdown clicked.");

                                       // Verify all options and print their text
                                       List<WebElement> options = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
                                       System.out.println("Verifying and printing all options:");
                                       int i = 1;
                                       for (WebElement option : options) {
                                           if (option.isDisplayed()) {
                                               System.out.println("Option " + i + ": " + option.getText());
                                           } else {
                                               System.out.println("Option " + i + " is not visible.");
                                           }
                                           i++;
                                       }

                                       // Click the 6mb option
                                       WebElement Option = wait.until(ExpectedConditions.elementToBeClickable(
                                           By.xpath("(//span[@class='mat-option-text'])[6]")));
                                       Option.click();
                                       System.out.println("6th option clicked: " + Option.getText()); 
                                       
                            	    
                            	   
                            	    
                            	

                             
                           
                             
                              // save it
                             
                             clickSave1.click();
                                        
                             
                             
                             // Switch to the front-end window
                             driver.switchTo().window(secondWindowHandle);
                             Thread.sleep(2000);
                             driver.navigate().refresh();
                             System.out.println("Switched to the front-end window. Page refreshed.");  
                             
                             
                             
                           
                             
                             
                             
                             
                             try {
                                 // First Upload: File 1
                                 String filePath1 = "C:\\Users\\HP\\Downloads\\FRAME WORK.pdf";
                                 WebElement attachment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                     By.xpath("//label[@for='File Uploads']/ancestor::div[contains(@class,'field-container position')]//input[@type='text']")));
                                 if (attachment.isDisplayed()) {
                                     attachment.click();
                                     System.out.println("Attachment dialog opened for File 1.");

                                     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);

                                     Robot rb = new Robot();
                                     rb.delay(2000);

                                     // Simulate CTRL+V and ENTER for File 1
                                     rb.keyPress(KeyEvent.VK_CONTROL);
                                     rb.keyPress(KeyEvent.VK_V);
                                     rb.keyRelease(KeyEvent.VK_V);
                                     rb.keyRelease(KeyEvent.VK_CONTROL);
                                     rb.keyPress(KeyEvent.VK_ENTER);
                                     rb.keyRelease(KeyEvent.VK_ENTER);

                                     System.out.println("File 1 uploaded successfully.");
                                 } else {
                                     System.out.println("Attachment button not visible for File 1.");
                                 }
                             
                     
                                 // Verify File 1 Upload
                                 WebElement uploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                     By.xpath("//label[@for='File Uploads']/ancestor::div[contains(@class,'field-container position')]//li/span")));
                                 Assert.assertTrue(uploadedFile.isDisplayed(),"Uploaded file 1 is not displayed.");
                                 System.out.println("File 1 is displayed: " + uploadedFile.getText());

                                 // Delete File 1
                                 WebElement deleteFile = wait.until(ExpectedConditions.elementToBeClickable(
                                     By.xpath("//label[@for='File Uploads']/ancestor::div[contains(@class,'field-container position')]//mat-icon[text()=' delete_outline']")));
                                 deleteFile.click();
                                 System.out.println("File 1 deleted successfully.");

                                 // Re-Upload: File 1
                                 if (attachment.isDisplayed()) {
                                     attachment.click();
                                     System.out.println("Attachment dialog opened again for File 1.");

                                     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);
                                     Robot rb = new Robot();
                                     rb.delay(2000);

                                     // Simulate CTRL+V and ENTER for Re-Upload
                                     rb.keyPress(KeyEvent.VK_CONTROL);
                                     rb.keyPress(KeyEvent.VK_V);
                                     rb.keyRelease(KeyEvent.VK_V);
                                     rb.keyRelease(KeyEvent.VK_CONTROL);
                                     rb.keyPress(KeyEvent.VK_ENTER);
                                     rb.keyRelease(KeyEvent.VK_ENTER);

                                     System.out.println("File 1 re-uploaded successfully.");
                                 } else {
                                     System.out.println("Attachment button not visible for re-upload.");
                                 }

                                
                             } catch (Exception e) {
                                 System.out.println("Error during file upload process: " + e.getMessage());
                             }
                             
                             
                             
                             
                             
                             
                             
                             
             }
             
             
             
             @And("user verifies and changes the settings of the Banner image")
             public void user_verifies_and_changes_the_settings_of_the_Banner_image() throws Exception {

                 // Open a new tab and get window handles
                 ((JavascriptExecutor) driver).executeScript("window.open()");
                 Set<String> windowHandles = driver.getWindowHandles();
                 Iterator<String> iterator = windowHandles.iterator();
                 String firstWindowHandle = iterator.next();
                 String secondWindowHandle = iterator.next();

                 Thread.sleep(2000);

                 // Switch back to the original window
                 driver.switchTo().window(firstWindowHandle);
                 System.out.println("Switched to admin backend");

                 JavascriptExecutor js = (JavascriptExecutor) driver;

                 // Drag-and-drop "Currency" to "TargetTabElement"
                 WebElement Currency = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[11]")));
                 WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
                 new Actions(driver).dragAndDrop(Currency, targetTabElement).perform();

                 WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Banner Image ']")));
                 Assert.assertTrue(droppedElement.isDisplayed(), "Banner Image component was not successfully dropped.");
                 System.out.println("Successfully dragged and dropped the Banner Image component.");

                 // Verify categories on the right side
                 for (int i = 1; i <= 3; i += 2) {
                     try {
                         WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
                         js.executeScript("arguments[0].scrollIntoView(true);", category);
                         System.out.println("Category: " + category.getText());
                     } catch (Exception e) {
                         System.err.println("Error in category verification: " + e.getMessage());
                     }
                 }

                 // Save the application
                 WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
                 clickSave.click();
                 System.out.println("Clicked on the save button.");

                 WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
                 String actualMessage = appliSave.getText();
                 Assert.assertTrue(
                     actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"),
                     "Unexpected save confirmation message: " + actualMessage
                 );
                 System.out.println("Application saved with message: " + actualMessage);

                 // Switch to the front-end window
                 driver.switchTo().window(secondWindowHandle);
                 driver.navigate().refresh();
                 System.out.println("Switched to front-end window and refreshed the page.");

                 // Verify "Banner image" visibility
                 try {
                     WebElement Banner_Inage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='Banner Image']")));
                     Assert.assertTrue(Banner_Inage.isDisplayed(), "'File Uploads' is not visible on the page!");
                     System.out.println("'Banner Inage' is visible.");
                 } catch (Exception e) {
                     System.err.println("Error verifying 'Banner Image': " + e.getMessage());
                 }

                 // Switch back to the original window
                 driver.switchTo().window(firstWindowHandle);
                 System.out.println("Switched to the backend window.");
                 Thread.sleep(3000);

                 // Click the "Appearance" category and interact with checkboxes
                 try {
                     WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                     js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                     js.executeScript("arguments[0].click();", clickAppearance);
                 } catch (Exception e) {
                     System.out.println("Error clicking Appearance: " + e.getMessage());
                 }

                 try {
                     // Interact with "Min Style" checkbox
                     WebElement miniCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//span[text()=' Min Style ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//input")
                     ));
                     if (!miniCheckbox.isSelected()) {
                         js.executeScript("arguments[0].click();", miniCheckbox);
                         System.out.println("Checked: Min Style");
                         Assert.assertTrue(miniCheckbox.isSelected(), "Min Style checkbox is not selected!");
                     }
                 } catch (Exception e) {
                     System.out.println("Error with 'Min Style' checkbox: " + e.getMessage());
                 }

                 try {
                     // Interact with "Show on Details Page" checkbox
                     WebElement detailsCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//span[text()=' Show on Details Page ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//input")
                     ));
                     if (!detailsCheckbox.isSelected()) {
                         js.executeScript("arguments[0].click();", detailsCheckbox);
                         System.out.println("Checked: Show on Details Page");
                         Assert.assertTrue(detailsCheckbox.isSelected(), "Show on Details Page checkbox is not selected!");
                     }
                 } catch (Exception e) {
                     System.out.println("Error with 'Show on Details Page' checkbox: " + e.getMessage());
                 }

                 clickSave.click();

                 // Verify front-end "Min Style" changes
                 driver.switchTo().window(secondWindowHandle);
                 driver.navigate().refresh();
                 System.out.println("Refreshed front-end window after selecting 'Min Style'.");

                 WebElement miniFront = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Banner Image']/ancestor::div[contains(@class,'field-container position')]//p[contains(text(),'Supported:')]")));
                 js.executeScript("arguments[0].scrollIntoView(true);", miniFront);
                 Assert.assertTrue(miniFront.isDisplayed(), "Mini style is not reflected on the front end.");
                 System.out.println("Mini style successfully reflected on the front end.");
            

                 Thread.sleep(2000);
                 
           	     // Switch to the back-end window
              // Switch back to the original window
                 driver.switchTo().window(firstWindowHandle);
                 System.out.println("Switched back to the backend.");

                 
                 
                 try {
                     // Click the Validation section
                        WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                        js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                        js.executeScript("arguments[0].click();", clickValidation);
                        System.out.println("User clicked the validation category.");

                        // Handle checkboxes: Required and Disable
                        
                            WebElement requiredCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                            if (!requiredCheckbox1.isSelected()) {
                                js.executeScript("arguments[0].click();", requiredCheckbox1);
                                System.out.println("Checked: Required");
                                Assert.assertTrue(requiredCheckbox1.isSelected(), "Required checkbox is not selected after clicking!");
                            } else {
                                System.out.println("Required checkbox is already selected.");
                            }

                            WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                            if (!disableCheckbox.isSelected()) {
                                js.executeScript("arguments[0].click();", disableCheckbox);
                                System.out.println("Checked: Disable");
                                Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                            } else {
                                System.out.println("Disable checkbox is already selected.");
                            }
                           
                            
                            Thread.sleep(2000);
                            clickSave.click();
                            Thread.sleep(2000);
                            // Switch to the front-end window
                            driver.switchTo().window(secondWindowHandle);
                            Thread.sleep(2000);
                            driver.navigate().refresh();
                            System.out.println("Switched to the front-end window. Page refreshed.");
                            
                            
                            
                            // verify  the "required" * icone is visible or not
                            
                            WebElement Required_icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Banner Image']/span[@class='impfieldstyle error ng-star-inserted']")));
                            js.executeScript("arguments[0].scrollIntoView(true);", Required_icon);
                            if (Required_icon.isEnabled()) {
                                System.out.println("The 'Required icon' is enable.");
                            } else {
                                System.out.println("The 'Required icon' is disable.");
                            }
                            
                            

                            // Check if the Disable text field is enabled or not in FRONT-END
                            WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                            js.executeScript("arguments[0].scrollIntoView(true);", Required_icon);
                            if (disableField.isEnabled()) {
                                System.out.println("The text field is disabled.");
                            } else {
                                System.out.println("The text field is enable.");
                            }
                            
                            Thread.sleep(2000);
                            // Switch back to the original window
                            driver.switchTo().window(firstWindowHandle);
                            System.out.println("Switched to the backend-end window");
                            
                            
                            
                            //unchecked the Disible check box -BACK-END
                            
                         // Uncheck the "Disable" checkbox if it is selected
                            WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                            if (disableCheckbox1.isSelected()) {
                                // Use JavaScript to uncheck the checkbox
                          	  js.executeScript("arguments[0].scrollIntoView(true);", disableCheckbox1);
                                js.executeScript("arguments[0].click();", disableCheckbox1);
                                System.out.println("Unchecked: Disable");
                                
                                // Verify the checkbox is now unchecked
                                Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                            } else {
                                System.out.println("Disable checkbox is already unchecked.");
                            }

                            Thread.sleep(2000);
                            clickSave.click();
                            Thread.sleep(2000);

                            // Switch to the front-end window
                            driver.switchTo().window(secondWindowHandle);
                            Thread.sleep(2000);
                            driver.navigate().refresh();
                            System.out.println("Switched to the front-end window. Page refreshed.");

                            // Check if the "Disable" text field is enabled or not --FRONT-END
                            
                            WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, ' ng-star-inserted') and contains(text(), ' Banner Image ')]")));
                            js.executeScript("arguments[0].scrollIntoView(true);", disableField1);
                            if (disableField1.isEnabled()) {
                                System.out.println("The text field is enable.");
                            } else {
                                System.out.println("The text field is Disable.");
                            }
                            Thread.sleep(2000);
                            // Switch back to the original window
                            driver.switchTo().window(firstWindowHandle);
                            System.out.println("Switched to the backend-end window");
                            
                             
                        } catch (Exception e) {
                            System.out.println("Error handling validation checkboxes: " + e.getMessage());
                        }

                 
                 // check the  File Extension
                 
                 try {
                	    // Interact with "File Extension" dropdown
                	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='File Extensions']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                	    js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
                	    js.executeScript("arguments[0].click();", dropdown);
                	    System.out.println("Dropdown clicked.");

                	    // Select checkboxes (1 to 10)
                	    for (int j = 1; j <= 3; j++) {
                	        try {
                	            // Locate the checkbox element
                	            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@id,'mat-option')]/mat-pseudo-checkbox)[" + j + "]")));

                	            // Locate the corresponding text element within the mat-option
                	            WebElement textElement = driver.findElement(
                	                By.xpath("(//*[contains(@id,'mat-option')])[" + j + "]//span[@class='mat-option-text']"));
                	            
                	            // Retrieve the checkbox text
                	            String checkboxText = textElement.getText();

                	            // Check if the checkbox is already selected using isSelected() method
                	            if (checkbox.isSelected()) {
                	                System.out.println("Checkbox " + j + " (" + checkboxText + ") is already selected. Skipping.");
                	                continue; // Skip to the next checkbox
                	            }

                	            // Scroll to the checkbox
                	            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkbox);
                	            wait.until(ExpectedConditions.elementToBeClickable(checkbox)); // Wait for the checkbox to be clickable

                	            // Click the checkbox
                	            js.executeScript("arguments[0].click();", checkbox);

                	            // Print the name of the selected checkbox
                	            System.out.println("Checked: " + checkboxText);
                	        } catch (Exception e) {
                	            System.err.println("Error handling checkbox " + j + ": " + e.getMessage());
                	        }
                	    }

                	    // Close the dropdown after selecting checkboxes
                	    WebElement closeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='File Extensions']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                	    js.executeScript("arguments[0].click();", closeDropdown);
                	    System.out.println("Dropdown closed.");
                	} catch (Exception e) {
                	    System.err.println("Error in dropdown handling: " + e.getMessage());
                	}

                	    Thread.sleep(2000);
                	 // Save the application with double-click
                	    WebElement click1Save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
                	    Actions actions = new Actions(driver);
                	    actions.doubleClick(click1Save).perform();
                	    System.out.println("Double-clicked on the save button.");

                	    
                	    
                        // click the dropdown  "Maximum File Size"
                           WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Maximum File Size']/following-sibling::mat-form-field//div[contains(@class,'mat-select-arrow ng')]")));
                           dropdown1.click();
                           System.out.println("Dropdown clicked.");

                           // Verify all options and print their text
                           List<WebElement> options = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
                           System.out.println("Verifying and printing all options:");
                           int i = 1;
                           for (WebElement option : options) {
                               if (option.isDisplayed()) {
                                   System.out.println("Option " + i + ": " + option.getText());
                               } else {
                                   System.out.println("Option " + i + " is not visible.");
                               }
                               i++;
                           }

                           // Click the 6mb option
                           WebElement Option = wait.until(ExpectedConditions.elementToBeClickable(
                               By.xpath("(//span[@class='mat-option-text'])[6]")));
                           Option.click();
                           System.out.println("6th option clicked: " + Option.getText()); 
                           
                	    
                	   
                	    
                	

                 
               
                 
                            // save it
                 
                           clickSave.click();
                            
                 
                 
                 // Switch to the front-end window
                 driver.switchTo().window(secondWindowHandle);
                 Thread.sleep(2000);
                 driver.navigate().refresh();
                 System.out.println("Switched to the front-end window. Page refreshed.");  
                 
             
             
                 try {
                     // First Upload: File 1
                     String filePath1 = "C:\\Users\\HP\\Pictures\\Saved Pictures\\bannerImage.jpg";
                     WebElement attachment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//label[@for='Banner Image']/ancestor::div[contains(@class,'field-container position')]//input[@type='text']")));
                     if (attachment.isDisplayed()) {
                         attachment.click();
                         System.out.println("Attachment dialog opened for File 1.");

                         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);

                         Robot rb = new Robot();
                         rb.delay(2000);

                         // Simulate CTRL+V and ENTER for File 1
                         rb.keyPress(KeyEvent.VK_CONTROL);
                         rb.keyPress(KeyEvent.VK_V);
                         rb.keyRelease(KeyEvent.VK_V);
                         rb.keyRelease(KeyEvent.VK_CONTROL);
                         rb.keyPress(KeyEvent.VK_ENTER);
                         rb.keyRelease(KeyEvent.VK_ENTER);

                         System.out.println("File 1 uploaded successfully.");
                     } else {
                         System.out.println("Attachment button not visible for File 1.");
                     }
                 
         
                     // Verify File 1 Upload
                     WebElement uploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//label[@for='Banner Image']/ancestor::div[contains(@class,'field-container position')]//li/span")));
                     Assert.assertTrue(uploadedFile.isDisplayed(),"Uploaded file 1 is not displayed.");
                     System.out.println("File 1 is displayed: " + uploadedFile.getText());

                     // Delete File 1
                     WebElement deleteFile = wait.until(ExpectedConditions.elementToBeClickable(
                         By.xpath("//label[@for='Banner Image']/ancestor::div[contains(@class,'field-container position')]//mat-icon[text()=' delete_outline']")));
                     deleteFile.click();
                     System.out.println("File 1 deleted successfully.");

                     // Re-Upload: File 1
                     if (attachment.isDisplayed()) {
                         attachment.click();
                         System.out.println("Attachment dialog opened again for File 1.");

                         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath1), null);
                         Robot rb = new Robot();
                         rb.delay(2000);

                         // Simulate CTRL+V and ENTER for Re-Upload
                         rb.keyPress(KeyEvent.VK_CONTROL);
                         rb.keyPress(KeyEvent.VK_V);
                         rb.keyRelease(KeyEvent.VK_V);
                         rb.keyRelease(KeyEvent.VK_CONTROL);
                         rb.keyPress(KeyEvent.VK_ENTER);
                         rb.keyRelease(KeyEvent.VK_ENTER);

                         System.out.println("File 1 re-uploaded successfully.");
                     } else {
                         System.out.println("Attachment button not visible for re-upload.");
                     }

                    
                 } catch (Exception e) {
                     System.out.println("Error during file upload process: " + e.getMessage());
                 }
             
            
             
             } 
             
             
             
             @And("user verifies and changes the settings of the Url")
             public void user_verifies_and_changes_the_settings_of_the_url() throws Exception { 
             
            	// Frontend Verification
           	    ((JavascriptExecutor) driver).executeScript("window.open()");
           	    Set<String> windowHandles = driver.getWindowHandles();
           	    Iterator<String> iterator = windowHandles.iterator();
           	    String firstWindowHandle = iterator.next();
           	    String secondWindowHandle = iterator.next();
           	    
           	    
           	
           	    
             	 JavascriptExecutor js = (JavascriptExecutor) driver;
          	    
          	    // Verify and change the settings of the Multi Line Input
          	    WebElement MultiLine = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[13]")));
          	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
          	    
          	    // Perform drag-and-drop action
          	    Actions actions = new Actions(driver);
          	    actions.dragAndDrop(MultiLine, targetTabElement).perform();
          	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='URL ']")));
          	    
          	    Assert.assertTrue(droppedElement.isDisplayed(), "URL  component was not successfully dropped.");
          	    System.out.println("Successfully dragged and dropped the URL  component to the Response tab.");
          	    
          	    // Verify right-side settings/components
          	    for (int i = 1; i <= 9; i += 2) {
          	        try {
          	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
          	            js.executeScript("arguments[0].scrollIntoView(true);", category);
          	            String categoryText = category.getText();
          	            System.out.println("Category: " + categoryText);

          	            if (categoryText.isEmpty()) {
          	                System.err.println("Warning: Category text is empty for element at index " + i);
          	            }
          	        } catch (NoSuchElementException e) {
          	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
          	        } catch (Exception e) {
          	            System.err.println("An unexpected error occurred: " + e.getMessage());
          	        }
          	    }

          	    // Save the application
          	    try {
          	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
          	        clickSave.click();
          	        System.out.println("Clicked on the save button.");

          	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
          	        String actualMessage = appliSave.getText();

          	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
          	        System.out.println("Application saved with message: " + actualMessage);
          	    } catch (Exception e) {
          	        System.out.println("An error occurred while saving the application.");
          	        e.printStackTrace();
          	    }

          	   
          	    // Step 2: Switch to the front-end window
  	            driver.switchTo().window(secondWindowHandle);
  	            
  	           driver.navigate().refresh();
 	            System.out.println("Page refreshed successfully.");
  	            
  	           
          	    try {
          	        // Verify that "URL " is displayed
          	        WebElement URL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='URL']")));
          	        Assert.assertTrue(URL.isDisplayed(), "'URL Link' is not visible on the page!");
          	        System.out.println("'URL Link' is visible on the page.");
          	    } catch (Exception e) {
          	        System.err.println("Error while verifying 'URL' visibility: " + e.getMessage());
          	    }

  	            
  	         
                  // Switch back to the original window
                  driver.switchTo().window(firstWindowHandle);
                  System.out.println("Switched back to the original window.");
                  
                  
                  
               
               

                  try {
                      // Set the Field Label
                      WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group ng-star-inserted']/child::input")));
                      fieldLabelInput.clear();
                      fieldLabelInput.sendKeys("URL Link");
                      Assert.assertEquals(fieldLabelInput.getAttribute("value"), "URL Link", "Field Label input text does not match expected value.");
                      System.out.println("Field Label set to 'URL Link' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Field Label: " + e.getMessage());
                  }

                  try {
                      // Set the Placeholder Text
                      WebElement placeholderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Placeholder text']/following-sibling::input")));
                      placeholderInput.clear();
                      placeholderInput.sendKeys("https://admin-dev.aapli.app/");
                      Assert.assertEquals(placeholderInput.getAttribute("value"), "https://admin-dev.aapli.app/", "Placeholder text does not match expected value.");
                      System.out.println("Placeholder Text set to 'https://admin-dev.aapli.app/' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Placeholder Text: " + e.getMessage());
                  }

                  try {
                      // Set the Help Text
                      WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                      helpTextInput.clear();
                      helpTextInput.sendKeys("Page Link");
                      Assert.assertEquals(helpTextInput.getAttribute("value"), "Page Link", "Help Text does not match expected value.");
                      System.out.println("Help Text set to 'Page Link' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Help Text: " + e.getMessage());
                  }

                  try {
                      // Set the Tooltip
                      WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                      tooltipInput.clear();
                      tooltipInput.sendKeys("Valid Link");
                      Assert.assertEquals(tooltipInput.getAttribute("value"), "Valid Link", "Tooltip text does not match expected value.");
                      System.out.println("Tooltip set to 'Valid Link' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Tooltip: " + e.getMessage());
                  }
                  
                  
                  //select the enable duplicate 
                  
                 
                      try {
                          WebElement enableDuplicateCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Enable Duplicate ']/ancestor::mat-checkbox//input[@type='checkbox']")));
                          if (!enableDuplicateCheckbox.isSelected()) {
                          	 
                               js.executeScript("arguments[0].click();", enableDuplicateCheckbox);
                              System.out.println("Checked: Enable Duplicate");
                              Assert.assertTrue(enableDuplicateCheckbox.isSelected(), enableDuplicateCheckbox + " checkbox is not selected after clicking!");
                              System.out.println("Enable Duplicate checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                      }

                   // Index Numbers Checkbox
                      try {
                          WebElement indexNumbersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Index Numbers ']/ancestor::mat-checkbox//input[@type='checkbox']")));
                          if (!indexNumbersCheckbox.isSelected()) {
                          	 js.executeScript("arguments[0].click();", indexNumbersCheckbox);
                              System.out.println("Checked: Index Numbers");
                              // Verify if the checkbox is selected after clicking
                              Assert.assertTrue(indexNumbersCheckbox.isSelected(), indexNumbersCheckbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Index Numbers checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Index Numbers' checkbox: " + e.getMessage());
                      }

                      // Repeat Heading Checkbox
                      try {
                          WebElement repeatHeadingCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Repeat Heading ']/ancestor::mat-checkbox//input[@type='checkbox']")));
                          if (!repeatHeadingCheckbox.isSelected()) {
                          	 js.executeScript("arguments[0].click();", repeatHeadingCheckbox);
                              System.out.println("Checked: Repeat Heading");
                              // Verify if the checkbox is selected after clicking
                              Assert.assertTrue(repeatHeadingCheckbox.isSelected(), repeatHeadingCheckbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Repeat Heading checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Repeat Heading' checkbox: " + e.getMessage());
                      }

                      
                      
                      // click Filterable Checkbox
                      try {
                          WebElement allowAttachmentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Filterable ']/ancestor::mat-checkbox//input[@type='checkbox']")));
                          if (!allowAttachmentCheckbox.isSelected()) {
                          	js.executeScript("arguments[0].click();", allowAttachmentCheckbox);
                              System.out.println("Checked: Allow Attachment");
                              Assert.assertTrue(allowAttachmentCheckbox.isSelected(), allowAttachmentCheckbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Allow Attachment checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Allow Attachment' checkbox: " + e.getMessage());
                      }
                      
                      
                      
                   // Save the application
              	   
              	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
              	        clickSave1.click();
              	        System.out.println("Clicked on the save button.");

              	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
              	        String actualMessage1 = appliSave1.getText();

              	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
              	        System.out.println("Application saved with message: " + actualMessage1);
         
                      
                      
                      
                      
                      
                   // Step 1: Pause briefly to allow for any loading or processing
      	            Thread.sleep(2000);

      	            // Step 2: Switch to the front-end window
      	            driver.switchTo().window(secondWindowHandle);
      	            
      	            // Step 3: Refresh the page to reload all elements
      	            driver.navigate().refresh();
      	          System.out.println("Switched to the front-end window. Page refreshed.");


      	            
      	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
      	            
      	            
      	            try {
                          // Check if the feild label Name Reflected front end or not
                          WebElement employeeNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='URL Link']")));
                          Assert.assertTrue(employeeNameLabel.isDisplayed(), "feild label is not visible.");
                          System.out.println(employeeNameLabel.getText()+": label is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display feild label: " + e.getMessage());
                      }

                      try {
                          // Check if the Help text is reflected front end or not
                          WebElement enterYourText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='URL Link']/ancestor::div[contains(@class, 'field-container')]//p[@class='input-footer mb-2']")));
                          Assert.assertTrue(enterYourText.isDisplayed(), "Help text is not visible.");
                          System.out.println(enterYourText.getText()+" :text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display help text: " + e.getMessage());
                      }

                      try {
                          // Check if the placeholder text is visible
                          WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='URL Link']/ancestor::div[contains(@class, 'field-container')]//div/input[@type='text']")));
                          Assert.assertTrue(inputField.isDisplayed(), "Input field text is not visible.");
                          System.out.println("https://admin-dev.aapli.app/ :Input field text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display the input field text: " + e.getMessage());
                      }

                      try {
                          // Click on the tooltip icon near 'multiple Employee Name '
                          WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='URL Link']/child::span/mat-icon")));
                          tooltipIcon.click();
                          System.out.println("Tooltip icon clicked successfully.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                      }

                      try {
                          // Verify that the tooltip content text is visible after clicking the tooltip icon
                          WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='URL Link']/child::span/div/p")));
                          Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                          System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                      }
              	    
                      
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
      	            
                      // verify the repeated duplicate headings with add index number ----in ADMIN FRONT-END
                      try {
                     	    Actions action = new Actions(driver);
                     	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

                     	    // Step 1: Hover over the tooltip icon and click the duplicate icon
                     	    System.out.println("Step 1: Hovering over tooltip icon and clicking duplicate icon.");
                     	    WebElement tooltipIcon1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='URL Link']/child::span/mat-icon")));
                     	    action.moveToElement(tooltipIcon1).perform();
                     	    System.out.println("Tooltip hovered successfully.");

                     	    WebElement duplication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),' URL Link ')]/following::mat-icon[text()='add_circle_outline']")));
                     	    duplication.click();
                     	    System.out.println("Duplicate icon clicked. New heading should be added.");
                     	    Thread.sleep(2000);

                     	    // Step 2: Verify duplicate headings
                     	    System.out.println("Step 2: Verifying duplicate headings.");
                     	    List<WebElement> duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' URL Link ')]"));
                     	    Assert.assertTrue(duplicateHeadings.size() > 1, "No duplicate headings found. Verification failed.");

                     	    System.out.println("Verification successful. Total occurrences of ' URL Link ': " + duplicateHeadings.size());

                     	    for (int i = 0; i < duplicateHeadings.size(); i++) {
                     	        WebElement heading = duplicateHeadings.get(i);
                     	        if (heading.isDisplayed()) {
                     	            System.out.println("Duplicate " + (i + 1) + ": " + heading.getText());
                     	        } else {
                     	            System.out.println("Duplicate " + (i + 1) + " is not visible on the page.");
                     	        }
                     	    }

                     	    // Step 3: Click the remove icon to delete a heading
                     	    System.out.println("Step 3: Removing duplicate heading.");
                     	    
                     	    WebElement tooltipIcon12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' URL Link 2 ')]//span/child::mat-icon")));
                     	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tooltipIcon12);
                     	    action.moveToElement(tooltipIcon12).perform();  
              
                     	    WebElement clickRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), ' URL Link 2 ')]/following::mat-icon[text()='remove_circle_outline']")));
                     	    action.moveToElement(clickRemove).perform();
                     	    clickRemove.click();
                     	    System.out.println("Remove icon clicked. Heading should be removed.");

                     	    // Step 4: Verify the heading is removed
                     	    System.out.println("Step 4: Verifying removal of duplicate headings.");
                     	    duplicateHeadings = driver.findElements(By.xpath("//*[contains(text(),' URL Link ')]"));
                     	    Assert.assertEquals(duplicateHeadings.size(), 1, "Duplication removal failed. Multiple headings still found.");

                     	    System.out.println("Duplication successfully removed. Only one occurrence of 'URL Link' found.");
                     	} catch (Exception e) {
                     	    e.printStackTrace();
                     	    System.out.println("An error occurred: " + e.getMessage());
                     	    Assert.fail("Test case failed due to unexpected error: " + e.getMessage());
                     	}

                      
                      
                      
                      // Switch back to the original window
                      driver.switchTo().window(firstWindowHandle);
                      System.out.println("Switched back to the original window.");
                       
                      
                      
                      
                      
                  // ----CONDITION-----
                  
                      
                      // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                      try {
                      WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                      js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                      js.executeScript("arguments[0].click();", click_condition);
                      
                      System.out.println("user click the condition category");
                      
                  
                      
                          WebElement enable_Use_in_workflow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Use in workflow ']/ancestor::mat-checkbox//input[@type='checkbox']")));
                          if (!enable_Use_in_workflow.isSelected()) {
                          	 
                               js.executeScript("arguments[0].click();", enable_Use_in_workflow);
                              System.out.println("Checked: Use in workflow");
                              Assert.assertTrue(enable_Use_in_workflow.isSelected(), enable_Use_in_workflow + " checkbox is not selected after clicking!");
                              System.out.println("Use in workflow checkbox is  selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                      }
                    
                      
                      //step:2 verify the unique varible name input
                      
                      WebElement unique_varibleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group d-flex ng-star-inserted']/child::label")));
                    

                      if (unique_varibleInput.isDisplayed()) {
                          System.out.println(" unique varible name input : is displayed.");
                      } else {
                          System.out.println("The unique varible name input is not displayed.");
                      }
                      
                      //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                      
                      try {
                          // Step 1: Click on the edit icon
                          WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                          js.executeScript("arguments[0].click();", clickedit_icon);
                          System.out.println("User clicked the edit icon");

                          // Step 2: Verify the expected text on the edit page
                          String expected_text12 = "Rule Name:";
                          WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                          String actual_text12 = edit_rules.getText();
                          
                          // Assertion with custom message for failure
                          Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                          System.out.println("Edit rules for Employee Name: page opened successfully");

                          // Step 3: Close the page
                          WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                          js.executeScript("arguments[0].click();", close_page);
                          System.out.println("Edit page closed successfully");

                      } catch (Exception e) {
                          // Print the stack trace and a custom error message
                          e.printStackTrace();
                          System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                      }
                      
                      
                      
                      
                      
                      //---------APPEARENCE----------
                      
                      
                      
                      // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                      
                      // click the appearence 
                      
                   // Click the Appearance category
                      try {
                          WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                          js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                          js.executeScript("arguments[0].click();", clickAppearance);
                          
                          
                       
                          
                          
                          

                          // Define the sizes to loop through
                          String[] sizes = {"medium", "small", "large"};

                          for (String size : sizes) {
                              try {
                                  // Click the size (medium, small, large)
                                  WebElement sizeElement = driver.findElement(By.xpath("//label[text()='" + size + "']"));
                                  js.executeScript("arguments[0].click();", sizeElement);
                                  clickSave1.click();
                                  
                                 

                                  // Switch to the front-end window
                                  driver.switchTo().window(secondWindowHandle);
                                  driver.navigate().refresh();
                                  System.out.println("User selected the " + size + " size, reflected on the front end.");
                                  Thread.sleep(3000);
                                  // Verify field size
                                  WebElement SIZE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='URL Link']/ancestor::div[contains(@class, 'field-container')]//div[@class='position-relative ng-star-inserted']/input[@type='text']")));
                                  Dimension fieldSize = SIZE.getSize();
                                  System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Width: " + fieldSize.getWidth());
                                  System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Height: " + fieldSize.getHeight());
                                  
                                  
                                  
                                  
                                  // Switch back to the original window
                                  driver.switchTo().window(firstWindowHandle);
                                  System.out.println("Switched back to the original window.");
                                  Thread.sleep(2000);
                                 
                                  } catch (Exception e1) {
                                      System.out.println(e1);
                                  }
                          }
                          
                          
                          
                          
                          
                          
                          
                         //---------Validation------ 
                          
                          
                             
                             try {
                          // Click the Validation section
                             WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                             js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                             js.executeScript("arguments[0].click();", clickValidation);
                             System.out.println("User clicked the validation category.");

                             // Handle checkboxes: Required and Disable
                             
                                 WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                 if (!requiredCheckbox.isSelected()) {
                                     js.executeScript("arguments[0].click();", requiredCheckbox);
                                     System.out.println("Checked: Required");
                                     Assert.assertTrue(requiredCheckbox.isSelected(), "Required checkbox is not selected after clicking!");
                                 } else {
                                     System.out.println("Required checkbox is already selected.");
                                 }

                                 WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                                 if (!disableCheckbox.isSelected()) {
                                     js.executeScript("arguments[0].click();", disableCheckbox);
                                     System.out.println("Checked: Disable");
                                     Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                                 } else {
                                     System.out.println("Disable checkbox is already selected.");
                                 }
                                
                                 
                                 Thread.sleep(2000);
                                 clickSave1.click();
                                 Thread.sleep(2000);
                                 // Switch to the front-end window
                                 driver.switchTo().window(secondWindowHandle);
                                 Thread.sleep(2000);
                                 driver.navigate().refresh();
                                 System.out.println("Switched to the front-end window. Page refreshed.");

                                 // Check if the Disable text field is enabled
                                 WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                                 if (disableField.isEnabled()) {
                                     System.out.println("The text field is disabled.");
                                 } else {
                                     System.out.println("The text field is enable.");
                                 }
                                 
                                 Thread.sleep(2000);
                                 // Switch back to the original window
                                 driver.switchTo().window(firstWindowHandle);
                                 
                                 
                                 
                                 
                                 //unchecked the Disible check box
                                 
                              // Uncheck the "Disable" checkbox if it is selected
                                 WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                                 if (disableCheckbox1.isSelected()) {
                                     // Use JavaScript to uncheck the checkbox
                                     js.executeScript("arguments[0].click();", disableCheckbox1);
                                     System.out.println("Unchecked: Disable checkbox.");
                                     
                                     // Verify the checkbox is now unchecked
                                     Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                                 } else {
                                     System.out.println("Disable checkbox is already unchecked.");
                                 }

                                 Thread.sleep(2000);
                                 clickSave1.click();
                                 Thread.sleep(2000);

                                 // Switch to the front-end window
                                 driver.switchTo().window(secondWindowHandle);
                                 Thread.sleep(2000);
                                 driver.navigate().refresh();
                                 System.out.println("Switched to the front-end window. Page refreshed.");

                                 // Check if the "Disable" text field is enabled
                                 WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, 'ng-star-inserted') and contains(text(), ' URL Link ')]")));
                                 if (disableField1.isEnabled()) {
                                     System.out.println("The text field is enable.");
                                 } else {
                                     System.out.println("The text field is Disable.");
                                 }
                                 Thread.sleep(2000);
                                 // Switch back to the original window
                                 driver.switchTo().window(firstWindowHandle);
                                 System.out.println("switch to admin backend");
                                 
                                 
                                 
                                 
                                 
                                 
                             } catch (Exception e) {
                                 System.out.println("Error handling validation checkboxes: " + e.getMessage());
                             }

                         } catch (Exception e) {
                             System.out.println("An error occurred in the appearance and validation process: " + e.getMessage());
                         }

                         
                      
                      
                  */    
                    
              
              
             
             
             
             
             
             
             @And("user verifies and changes the settings of the Dropdown")
             public void user_verifies_and_changes_the_settings_of_the_Dropdown() throws Exception { 
            	 
            	 
            	// Frontend Verification
           	    ((JavascriptExecutor) driver).executeScript("window.open()");
           	    Set<String> windowHandles = driver.getWindowHandles();
           	    Iterator<String> iterator = windowHandles.iterator();
           	    String firstWindowHandle = iterator.next();
           	    String secondWindowHandle = iterator.next();
           	    
           	    
           	
           	    
             	 JavascriptExecutor js = (JavascriptExecutor) driver;
          	    
          	    // Verify and change the settings of the Multi Line Input
          	    WebElement MultiLine = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Basic')]//following::h5[3]")));
          	    WebElement targetTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Description ']")));
          	    
          	    // Perform drag-and-drop action
          	    Actions actions = new Actions(driver);
          	    actions.dragAndDrop(MultiLine, targetTabElement).perform();
          	    WebElement droppedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Dropdown ']")));
          	    
          	    Assert.assertTrue(droppedElement.isDisplayed(), "Dropdown  component was not successfully dropped.");
          	    System.out.println("Successfully dragged and dropped the Dropdown  component to the Response tab.");
          	    
          	    // Verify right-side settings/components
          	    for (int i = 1; i <= 9; i += 2) {
          	        try {
          	            WebElement category = driver.findElement(By.xpath("(//div[@class='d-flex align-items-center py-2 right-tabs-border px-3 ng-star-inserted']/child::div)[" + i + "]"));
          	            js.executeScript("arguments[0].scrollIntoView(true);", category);
          	            String categoryText = category.getText();
          	            System.out.println("Category: " + categoryText);

          	            if (categoryText.isEmpty()) {
          	                System.err.println("Warning: Category text is empty for element at index " + i);
          	            }
          	        } catch (NoSuchElementException e) {
          	            System.err.println("Element not found at index " + i + ". Error: " + e.getMessage());
          	        } catch (Exception e) {
          	            System.err.println("An unexpected error occurred: " + e.getMessage());
          	        }
          	    }

          	    // Save the application
          	    try {
          	        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
          	        clickSave.click();
          	        System.out.println("Clicked on the save button.");

          	        WebElement appliSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
          	        String actualMessage = appliSave.getText();

          	        Assert.assertTrue(actualMessage.equals("Application updated.") || actualMessage.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage);
          	        System.out.println("Application saved with message: " + actualMessage);
          	    } catch (Exception e) {
          	        System.out.println("An error occurred while saving the application.");
          	        e.printStackTrace();
          	    }

          	   
          	    // Step 2: Switch to the front-end window
  	            driver.switchTo().window(secondWindowHandle);
  	            
  	           driver.navigate().refresh();
 	            System.out.println("Page refreshed successfully.");
  	            
  	           
          	    try {
          	        // Verify that "Dropdown" is displayed
          	        WebElement singleLineInputLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='d-flex mt-2 align-items-center']/label[@for='Dropdown']")));
          	        Assert.assertTrue(singleLineInputLabel.isDisplayed(), "'Dropdown' is not visible on the page!");
          	        System.out.println("'Dropdown' is visible on the page.");
          	    } catch (Exception e) {
          	        System.err.println("Error while verifying 'Dropdown' visibility: " + e.getMessage());
          	    }

  	            
  	         
                  // Switch back to the original window
                  driver.switchTo().window(firstWindowHandle);
                  System.out.println("Switched back to the original window.");
                  
                  
                  
               
               

                  try {
                      // Set the Field Label
                      WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group ng-star-inserted']/label[text()='Field label']/following-sibling::input")));
                      fieldLabelInput.clear();
                      fieldLabelInput.sendKeys("Dropdown");
                      Assert.assertEquals(fieldLabelInput.getAttribute("value"), "Dropdown", "Field Label input text does not match expected value.");
                      System.out.println("Field Label set to 'Dropdown' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Field Label: " + e.getMessage());
                  }

                  try {
                      // Set the Placeholder Text
                      WebElement placeholderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Placeholder text']/following-sibling::input")));
                      placeholderInput.clear();
                      placeholderInput.sendKeys("selected dropdown");
                      Assert.assertEquals(placeholderInput.getAttribute("value"), "selected dropdown", "Placeholder text does not match expected value.");
                      System.out.println("Placeholder Text set to 'selected dropdown' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Placeholder Text: " + e.getMessage());
                  }

                  try {
                      // Set the Help Text
                      WebElement helpTextInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Help text']/following-sibling::input")));
                      helpTextInput.clear();
                      helpTextInput.sendKeys("Select");
                      Assert.assertEquals(helpTextInput.getAttribute("value"), "Select", "Help Text does not match expected value.");
                      System.out.println("Help Text set to 'Select' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Help Text: " + e.getMessage());
                  }

                  try {
                      // Set the Tooltip
                      WebElement tooltipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tooltip']/following-sibling::input")));
                      tooltipInput.clear();
                      tooltipInput.sendKeys("choose the option");
                      Assert.assertEquals(tooltipInput.getAttribute("value"), "choose the option", "Tooltip text does not match expected value.");
                      System.out.println("Tooltip set to 'choose the option' successfully.");
                  } catch (Exception e) {
                      System.err.println("Failed to set the Tooltip: " + e.getMessage());
                  }
                  
                
                      
                   // Save the application
              	   
              	        WebElement clickSave1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='save']")));
              	        clickSave1.click();
              	        System.out.println("Clicked on the save button.");

              	        WebElement appliSave1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content-container']/child::div")));
              	        String actualMessage1 = appliSave1.getText();

              	        Assert.assertTrue(actualMessage1.equals("Application updated.") || actualMessage1.equals("Application with same name already exists"), "Unexpected save confirmation message: " + actualMessage1);
              	        System.out.println("Application saved with message: " + actualMessage1);
         
                      
                      
                      
                      
                      
                   // Step 1: Pause briefly to allow for any loading or processing
      	            Thread.sleep(2000);

      	            //  Switch to the front-end window
      	            driver.switchTo().window(secondWindowHandle);
      	           
      	            driver.navigate().refresh();
      	          System.out.println("Switched to the front-end window. Page refreshed.");



      	            
      	            //FORNT END -----VERIFY THE FEILD LABEL,HELP TEXT,PLACEHOLDER TERXT,TOOL TIP
      	            
      	            
      	            try {
                          // Check if the feild label Name Reflected front end or not
                          WebElement employeeNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Dropdown']")));
                          Assert.assertTrue(employeeNameLabel.isDisplayed(), "Dropdown label is not visible.");
                          System.out.println(employeeNameLabel.getText()+": label is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display 'Dropdown' label: " + e.getMessage());
                      }

                      try {
                          // Check if the Help text is reflected front end or not
                          WebElement enterYourText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Dropdown']/ancestor::div[contains(@class, 'field-container')]//div[@class='inner-field-container mb-3']/p[@class='input-footer mb-2']")));
                          Assert.assertTrue(enterYourText.isDisplayed(), "Help text is not visible.");
                          System.out.println(enterYourText.getText()+" :text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display help text: " + e.getMessage());
                      }
/*
                      try {
                          // Check if the placeholder text is visible
                          WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mat-select-value-3']")));
                          Assert.assertTrue(inputField.isDisplayed(), "Input field text is not visible.");
                          System.out.println("selected dropdown :Input field text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display the input field text: " + e.getMessage());
                      }
*/
                      try {
                          // Click on the tooltip icon near 'Dropdown '
                          WebElement tooltipIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Dropdown']/child::span/mat-icon")));
                          tooltipIcon.click();
                          System.out.println("Tooltip icon clicked successfully.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or click the tooltip icon: " + e.getMessage());
                      }

                      try {
                          // Verify that the tooltip content text is visible after clicking the tooltip icon
                          WebElement tooltipContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Dropdown']/child::span/div/p")));
                          Assert.assertTrue(tooltipContent.isDisplayed(), "Tooltip content text is not visible.");
                          System.out.println(tooltipContent.getText()+" :Tooltip content text is visible on the page.");
                      } catch (Exception e) {
                          System.err.println("Failed to locate or display tooltip content text: " + e.getMessage());
                      }
              	    
                      
      	            
                   // Wait for the dropdown element to be clickable
                      WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                          By.xpath("//label[@for='Dropdown']/ancestor::div[contains(@class, 'field-container')]//div[contains(@class,'mat-mdc-select-arrow ng-tns')]")
                      ));

                      // Scroll the dropdown element into view using JavaScript
                      JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                      jsExecutor.executeScript("arguments[0].scrollIntoView(true);", dropdown);

                      // Click the dropdown using JavaScript
                      jsExecutor.executeScript("arguments[0].click();", dropdown);
                      System.out.println("Dropdown clicked");

                      // Wait for the options to be visible and print them
                      List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[@role='option']")));
                      Assert.assertFalse(options.isEmpty(), "Options list should not be empty");
                      System.out.println("Options in dropdown:");
                      for (WebElement option : options) {
                          System.out.println(option.getText());
                      }

                      // Switch back to the original window
                      driver.switchTo().window(firstWindowHandle);
                      System.out.println("Switched back to the original window.");

                      // Multi Select Checkbox
                      try {
                          WebElement multiSelectCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                              By.xpath("//span[text()=' Multi Select ']/preceding::input[@type='checkbox']")
                          ));
                          if (!multiSelectCheckbox.isSelected()) {
                              jsExecutor.executeScript("arguments[0].click();", multiSelectCheckbox);
                              System.out.println("Checked: Multi Select");
                              Assert.assertTrue(multiSelectCheckbox.isSelected(), "Multi Select checkbox is not selected after clicking!");
                          } else {
                              System.out.println("Multi Select is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Multi Select' checkbox: " + e.getMessage());
                      }

                      // Save the application
                      clickSave1.click();
                      System.out.println("Application saved.");

                      // Switch to the front-end window
                      driver.switchTo().window(secondWindowHandle);
                      driver.navigate().refresh();
                      System.out.println("Switched to the front-end window. Page refreshed.");

                      // Wait for the dropdown element to be clickable in the second window
                      WebElement dropdown12 = wait.until(ExpectedConditions.elementToBeClickable(
                          By.xpath("//*[@id=\"Dropdown\"]/div/div[2]")
                      ));

                      // Scroll the dropdown element into view using JavaScript
                      jsExecutor.executeScript("arguments[0].scrollIntoView(true);", dropdown12);
                      jsExecutor.executeScript("arguments[0].click();", dropdown12);
                      System.out.println("Dropdown clicked again.");
                      
                      

                      // Wait for the checkboxes to be visible
                      WebElement option1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//mat-option[@role='option']/mat-pseudo-checkbox)[1]")));
                      WebElement option2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//mat-option[@role='option']/mat-pseudo-checkbox)[2]")));

                      // Click the first option
                      option1.click();
                      System.out.println("Option 1 clicked");

                      // Assert and print the state of the first option
                      boolean isOption1Checked = option1.getAttribute("class").contains("mat-pseudo-checkbox-checked");
                      Assert.assertTrue(isOption1Checked, "Option 1 is not checked!");
                      System.out.println("Option 1 is checked");

                      // Click the second option
                      option2.click();
                      System.out.println("Option 2 clicked");

                      // Assert and print the state of the second option
                      boolean isOption2Checked = option2.getAttribute("class").contains("mat-pseudo-checkbox-checked");
                      Assert.assertTrue(isOption2Checked, "Option 2 is not checked!");
                      System.out.println("Option 2 is checked");

                      
                      
                   // Switch back to the original window
                      driver.switchTo().window(firstWindowHandle);
                      System.out.println("Switched back to the original window.");
                      
                      // unchecked the multi select checkbox
                      
                      try {
                    	    WebElement multiSelectCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                    	        By.xpath("//span[text()=' Multi Select ']/preceding::input[@type='checkbox']")
                    	    ));

                    	    if (multiSelectCheckbox.isSelected()) {
                    	        // Uncheck the checkbox if it is already selected
                    	        jsExecutor.executeScript("arguments[0].click();", multiSelectCheckbox);
                    	        System.out.println("Unchecked: Multi Select");
                    	        Assert.assertFalse(multiSelectCheckbox.isSelected(), "Multi Select checkbox is still selected after clicking!");
                    	    } else {
                    	        // Checkbox is already unchecked
                    	        System.out.println("Multi Select is already unchecked.");
                    	    }
                    	} catch (Exception e) {
                    	    System.out.println("Error locating or toggling 'Multi Select' checkbox: " + e.getMessage());
                    	}

                      
                   // Save the application
                      clickSave1.click();
                      System.out.println("Application saved.");
                      
                      // Switch to the front-end window
                      driver.switchTo().window(secondWindowHandle);
                      driver.navigate().refresh();
                      System.out.println("Switched to the front-end window. Page refreshed.");
                      
                      
                      
                      //click the dropdown
                      WebElement dropdown121 = wait.until(ExpectedConditions.elementToBeClickable(
                              By.xpath("//*[@id=\"Dropdown\"]/div/div[2]")
                          ));

                          // Scroll the dropdown element into view using JavaScript
                          jsExecutor.executeScript("arguments[0].scrollIntoView(true);", dropdown121);
                          jsExecutor.executeScript("arguments[0].click();", dropdown121);
                          System.out.println("Dropdown clicked again.");

                          try {
                        	    // Find all matching elements
                        	    List<WebElement> options1 = driver.findElements(By.xpath("//mat-option[@role='option']/mat-pseudo-checkbox"));

                        	    // Check if any options are found
                        	    if (options1.size() > 0) {
                        	        boolean allEnabled = true; // Flag to track if all options are enabled

                        	        for (int i = 0; i < options1.size(); i++) {
                        	            WebElement option = options1.get(i);

                        	            // Check if the element is visible and enabled
                        	            if (option.isDisplayed() && option.isEnabled()) {
                        	                System.out.println("Option " + (i + 1) + " is visible and enabled.");
                        	            } else {
                        	                System.out.println("Option " + (i + 1) + " is either not visible or disabled.");
                        	                allEnabled = false; // Set flag to false if any option is not enabled
                        	            }
                        	        }

                        	        // Print final result based on the flag
                        	        if (allEnabled) {
                        	            System.out.println("All options are enabled.");
                        	        } else {
                        	            System.out.println("Some options are not visible or disabled.");
                        	        }
                        	    } else {
                        	        // If no elements found
                        	        System.out.println(" multi select check boxes are not found . so  successfully un-checked the multi select check box");
                        	    }
                        	} catch (Exception e) {
                        	    // Catch any exception and print the error message
                        	    System.out.println("An error occurred: " + e.getMessage());
                        	}


                      
                      
                      
                      
                   // Switch back to the original window
                      driver.switchTo().window(firstWindowHandle);
                      System.out.println("Switched back to the original window.");
                      
                      
                      
                   // Include Blank Option Checkbox---CHECKED
                      try {
                          WebElement includeBlankOptionCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                              By.xpath("(//span[text()=' Include Blank Option ']/preceding::input[@type='checkbox'])[2]")
                          ));

                          if (!includeBlankOptionCheckbox.isSelected()) {
                              // Check the checkbox if not already selected
                              jsExecutor.executeScript("arguments[0].click();", includeBlankOptionCheckbox);
                              System.out.println("Checked: Include Blank Option");
                              Assert.assertTrue(includeBlankOptionCheckbox.isSelected(), "Include Blank Option checkbox is not selected after clicking!");
                          } else {
                              // Uncheck the checkbox if it is already selected
                              jsExecutor.executeScript("arguments[0].click();", includeBlankOptionCheckbox);
                              System.out.println("Unchecked: Include Blank Option");
                              Assert.assertFalse(includeBlankOptionCheckbox.isSelected(), "Include Blank Option checkbox is still selected after clicking!");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or toggling 'Include Blank Option' checkbox: " + e.getMessage());
                      }
 
                      
                      
                   // Save the application
                      clickSave1.click();
                      System.out.println("Application saved.");
                      
                      // Switch to the front-end window
                      driver.switchTo().window(secondWindowHandle);
                      driver.navigate().refresh();
                      System.out.println("Switched to the front-end window. Page refreshed."); 
                      
                      try { 
                      
                      // Wait for the dropdown element to be clickable in the second window
              	    WebElement dropdown1211 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Dropdown\"]/div/div[2]")));

              	    // Scroll the dropdown element into view using JavaScript
              	    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", dropdown1211);
              	  jsExecutor.executeScript("arguments[0].click();", dropdown1211);
              	    System.out.println("Dropdown clicked again.");

                      
                      
                      
              	// verify the '--Please Select--' element is present are not
              	  By elementXPath = By.xpath("//span[@class='mdc-list-item__primary-text' and text()='--Please Select--']");

              	  // Wait for the element to be visible
              	  WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementXPath));

              	  // Check if the element is displayed
              	  if (element.isDisplayed()) {
              	      // Retrieve and print the text of the element
              	      String text = element.getText();
              	      System.out.println("Element is visible and text is: " + text);
              	  } else {
              	      System.out.println("Element is not displayed.");
              	  }
 
                      }catch(Exception e){
                    	  System.out.println(e);
            	  }        
              
                      
                      
                   // Switch back to the original window
                      driver.switchTo().window(firstWindowHandle);
                      System.out.println("Switched back to the original window.");      
                      
                      
                   // Include Blank Option Checkbox-----UNCHECKED
                      try {
                          WebElement includeBlankOptionCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                              By.xpath("(//span[text()=' Include Blank Option ']/preceding::input[@type='checkbox'])[2]")
                          ));

                          if (includeBlankOptionCheckbox.isSelected()) {
                              // Uncheck the checkbox if it's already selected
                              jsExecutor.executeScript("arguments[0].click();", includeBlankOptionCheckbox);
                              System.out.println("Unchecked: Include Blank Option");
                              Assert.assertFalse(includeBlankOptionCheckbox.isSelected(), "Include Blank Option checkbox is still selected after clicking!");
                          } else {
                              System.out.println("Include Blank Option is already unchecked.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or toggling 'Include Blank Option' checkbox: " + e.getMessage());
                      }
  
                      
                      
                   // Save the application
                      clickSave1.click();
                      System.out.println("Application saved.");
                      
                      // Switch to the front-end window
                      driver.switchTo().window(secondWindowHandle);
                      driver.navigate().refresh();
                      System.out.println("Switched to the front-end window. Page refreshed.");    
                      
                   try {  
                	   
                	   
                	   
                	   WebElement dropdown1212 = wait.until(ExpectedConditions.elementToBeClickable(
                               By.xpath("//*[@id=\"Dropdown\"]/div/div[2]")
                           ));

                           // Scroll the dropdown element into view using JavaScript
                         
                           jsExecutor.executeScript("arguments[0].scrollIntoView(true);", dropdown1212);
                           jsExecutor.executeScript("arguments[0].click();", dropdown1212);
                           System.out.println("Dropdown clicked again.");

            

                     // Verify the '--Please Select--' element is present are not
                           
                           
                           if(driver.findElements(By.xpath("//span[@class='mdc-list-item__primary-text' and text()='--Please Select--']")).size() != 0) {
                        	    System.out.println("'--Please Select--' element is present");
                        	} else {
                        	    System.out.println("'--Please Select--' element is not present");
                        	}

                          	   
                    

                   }catch(Exception e) {
                	   System.out.println(e);
                   }
                      
                      
                      
                   // Switch back to the original window
                   driver.switchTo().window(firstWindowHandle);
                   System.out.println("Switched back to the original window.");
               
                      
                      
                    
                      
                      // click Filterable Checkbox
                      try {
                          WebElement Filter_Checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Filterable')]/preceding-sibling::span//input[@type='checkbox']")));
                          if (!Filter_Checkbox.isSelected()) {
                          	js.executeScript("arguments[0].click();", Filter_Checkbox);
                              System.out.println("Checked: Filterable");
                              Assert.assertTrue(Filter_Checkbox.isSelected(), Filter_Checkbox + " checkbox is not selected after clicking!");
                          } else {
                              System.out.println("'Filter' checkbox is already selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Filter' checkbox: " + e.getMessage());
                      }
                      
                      
                      // Final confirmation message
                      System.out.println("All specified checkboxes have been checked and verified.");

                  
                  
                      
                      // step:1click the condition category the user check the "Use in workflow"  and verify the"Unique variable name: input "
                      try {
                      WebElement click_condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Condition']")));
                      js.executeScript("arguments[0].scrollIntoView(true);", click_condition);
                      js.executeScript("arguments[0].click();", click_condition);
                      
                      System.out.println("user click the condition category");
                      
                  
                      
                          WebElement enable_Use_in_workflow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), ' Use in workflow ')]/preceding-sibling::span//input[@type='checkbox']")));
                          if (!enable_Use_in_workflow.isSelected()) {
                          	 
                               js.executeScript("arguments[0].click();", enable_Use_in_workflow);
                              System.out.println("Checked: Use in workflow");
                              Assert.assertTrue(enable_Use_in_workflow.isSelected(), enable_Use_in_workflow + " checkbox is not selected after clicking!");
                              System.out.println("Use in workflow checkbox is  selected.");
                          }
                      } catch (Exception e) {
                          System.out.println("Error locating or clicking 'Enable Duplicate' checkbox: " + e.getMessage());
                      }
                    
                      
                      //step:2 verify the unique varible name input
                      
                      WebElement unique_varibleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group d-flex ng-star-inserted']/child::label")));
                    

                      if (unique_varibleInput.isDisplayed()) {
                          System.out.println(" unique varible name input : is displayed.");
                      } else {
                          System.out.println("The unique varible name input is not displayed.");
                      }
                      
                      //step:3 then the user click the +edit icon and verify the "Edit rules for Employee Name" page open are not
                      
                      try {
                          // Step 1: Click on the edit icon
                          WebElement clickedit_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='icon-button d-flex button-top']/child::mat-icon")));
                          js.executeScript("arguments[0].click();", clickedit_icon);
                          System.out.println("User clicked the edit icon");

                          // Step 2: Verify the expected text on the edit page
                          String expected_text12 = "Rule Name:";
                          WebElement edit_rules = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@class='mt-2'])[1]")));
                          String actual_text12 = edit_rules.getText();
                          
                          // Assertion with custom message for failure
                          Assert.assertEquals(actual_text12, expected_text12, "The actual text does not match the expected text.");
                          System.out.println("Edit rules for Employee Name: page opened successfully");

                          // Step 3: Close the page
                          WebElement close_page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='close']")));
                          js.executeScript("arguments[0].click();", close_page);
                          System.out.println("Edit page closed successfully");

                      } catch (Exception e) {
                          // Print the stack trace and a custom error message
                          e.printStackTrace();
                          System.out.println("An error occurred during the edit icon click or verification process: " + e.getMessage());
                      }
                      
                      
                      
                      
                      // USER CLICK THE APPEARENCE CATEGORY-AND VERIFY THE SELECT SIZES: SMALL,MEDIUM,LARGE
                      
                      // click the appearence 
                      
                   // Click the Appearance category
                      try {
                          WebElement clickAppearance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Appearance']")));
                          js.executeScript("arguments[0].scrollIntoView(true);", clickAppearance);
                          js.executeScript("arguments[0].click();", clickAppearance);
                          
                          
                      }catch(Exception e) {
                    	  System.out.println(e);
                      }
                      
                          
                          
                          

                          // Define the sizes to loop through
                          String[] sizes = {"medium", "small", "large"};

                          for (String size : sizes) {
                              try {
                                  // Click the size (medium, small, large)
                                  WebElement sizeElement = driver.findElement(By.xpath("//label[text()='" + size + "']"));
                                  js.executeScript("arguments[0].click();", sizeElement);
                                  clickSave1.click();

                                  // Switch to the front-end window
                                  driver.switchTo().window(secondWindowHandle);
                                  driver.navigate().refresh();
                                  System.out.println("User selected the " + size + " size, reflected on the front end.");

                                  // Verify field size
                                  WebElement SIZE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Dropdown']/ancestor::div[contains(@class, 'field-container')]//div[contains(@class,'notched-outline--no-label ng-star-inserted')]")));
                                  Dimension fieldSize = SIZE.getSize();
                                  System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Width: " + fieldSize.getWidth());
                                  System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " Field Height: " + fieldSize.getHeight());
                                  
                                  
                                  
                                  
                                  // Switch back to the original window
                                  driver.switchTo().window(firstWindowHandle);
                                  System.out.println("Switched back to the original window.");
                                  
                                 
                                  } catch (Exception e1) {
                                      System.out.println(e1);
                                  }
                          
                          }
                          
                          
                          
                      
                             
                             
                              try {
                            	    // Wait for the dropdown to switch between windows
                            	    Thread.sleep(2000);
                            	    
                            	    // Switch to the backend window
                            	    driver.switchTo().window(firstWindowHandle);
                            	    System.out.println("Switched back to the backend.");

                            	    // Click the Validation section
                            	    WebElement clickValidation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Validation']/parent::div//div[@class='icon ml-auto']/child::mat-icon")));
                            	    js.executeScript("arguments[0].scrollIntoView(true);", clickValidation);
                            	    js.executeScript("arguments[0].click();", clickValidation);
                            	    System.out.println("User clicked the validation category.");

                            	    // Handle checkboxes: Required and Disable
                            	    WebElement requiredCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Required ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                            	    
                            	    // Check if "Required" checkbox is selected, if not select it
                            	    if (!requiredCheckbox.isSelected()) {
                            	        js.executeScript("arguments[0].click();", requiredCheckbox);
                            	        System.out.println("Checked: Required");
                            	        Assert.assertTrue(requiredCheckbox.isSelected(), "Required checkbox is not selected after clicking!");
                            	    } else {
                            	        System.out.println("Required checkbox is already selected.");
                            	    }

                            	    // Check "Disable" checkbox if not already selected
                            	    WebElement disableCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']/child::mat-checkbox//span/input")));
                            	    if (!disableCheckbox.isSelected()) {
                            	        js.executeScript("arguments[0].click();", disableCheckbox);
                            	        System.out.println("Checked: Disable");
                            	        Assert.assertTrue(disableCheckbox.isSelected(), "Disable checkbox is not selected after clicking!");
                            	    } else {
                            	        System.out.println("Disable checkbox is already selected.");
                            	    }

                            	    // Save the changes on backend
                            	    Thread.sleep(2000);
                            	    clickSave1.click();
                            	    Thread.sleep(2000);
                            	    
                            	    // Switch to the frontend window and refresh
                            	    driver.switchTo().window(secondWindowHandle);
                            	    Thread.sleep(2000);
                            	    driver.navigate().refresh();
                            	    System.out.println("Switched to the front-end window. Page refreshed.");

                            	    // Verify if the "Required" icon is visible in the frontend
                            	    WebElement requiredIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='Dropdown']/span[@class='impfieldstyle error ng-star-inserted']")));
                            	    js.executeScript("arguments[0].scrollIntoView(true);", requiredIcon);
                            	    
                            	    // Check if the Required icon is enabled or not
                            	    if (requiredIcon.isEnabled()) {
                            	        System.out.println("The 'Required icon' is enabled.");
                            	    } else {
                            	        System.out.println("The 'Required icon' is disabled.");
                            	    }

                            	    // Check if the Disable field is enabled in frontend
                            	    WebElement disableField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='mt-1 disabledFieldLabel ng-star-inserted']")));
                            	    js.executeScript("arguments[0].scrollIntoView(true);", disableField);

                            	    // Check if Disable field is enabled
                            	    if (disableField.isEnabled()) {
                            	        System.out.println("The text field is disabled.");
                            	    } else {
                            	        System.out.println("The text field is enabled.");
                            	    }

                            	    Thread.sleep(2000);
                            	    // Switch back to the backend window
                            	    driver.switchTo().window(firstWindowHandle);
                            	    System.out.println("Switched to the backend window.");

                            	    // Uncheck the "Disable" checkbox if it is selected in the backend
                            	    WebElement disableCheckbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable ']/ancestor::div[@class='form-group ng-star-inserted']//span/input")));
                            	    if (disableCheckbox1.isSelected()) {
                            	        js.executeScript("arguments[0].scrollIntoView(true);", disableCheckbox1);
                            	        js.executeScript("arguments[0].click();", disableCheckbox1);
                            	        System.out.println("Unchecked: Disable");
                            	        
                            	        // Verify the checkbox is now unchecked
                            	        Assert.assertFalse(disableCheckbox1.isSelected(), "Disable checkbox is still selected after unchecking!");
                            	    } else {
                            	        System.out.println("Disable checkbox is already unchecked.");
                            	    }

                            	    // Save changes again
                            	    Thread.sleep(2000);
                            	    clickSave1.click();
                            	    Thread.sleep(2000);

                            	    // Switch to the frontend window and refresh
                            	    driver.switchTo().window(secondWindowHandle);
                            	    Thread.sleep(2000);
                            	    driver.navigate().refresh();
                            	    System.out.println("Switched to the front-end window. Page refreshed.");

                            	    // Verify if the Disable field is enabled or not in the frontend after unchecking
                            	    WebElement disableField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class, ' ng-star-inserted') and contains(text(), ' Dropdown ')]")));
                            	    js.executeScript("arguments[0].scrollIntoView(true);", disableField1);

                            	    if (disableField1.isEnabled()) {
                            	        System.out.println("The text field is enabled.");
                            	    } else {
                            	        System.out.println("The text field is disabled.");
                            	    }

                            	    
                            	    
                            	    
                            	    
                            	    Thread.sleep(2000);
                            	    // Switch back to the backend window after all checks
                            	    driver.switchTo().window(firstWindowHandle);
                            	    System.out.println("Switched to the backend window.");

                            	    
                            	    
                            	    
                            	} catch (Exception e) {
                            	    System.out.println("Error handling validation checkboxes: " + e.getMessage());
                            	}

                     
                    
                      // click --"Option Source "
                    
                              try {
                                  WebElement click_OptionSource = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Option Source']")));
                                  js.executeScript("arguments[0].scrollIntoView(true);", click_OptionSource);
                                  js.executeScript("arguments[0].click();", click_OptionSource);
                                  
                                  System.out.println("user click the click_OptionSource category");
                                  
                    
                              }catch(Exception e) {
                            	  System.out.println(e);
                              }
            	 
             
             
             
                              
                              
                              
                              try {
                            	    // Locate the 'Select Option' radio button's <input> element
                            	    WebElement radioButtonInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                            	        By.xpath("//span[text()='Select Option ']")));
                            	    
                            	     jsExecutor.executeScript("arguments[0].click();", radioButtonInput);

                            	  System.out.println("clicked the 'Select option' radio button");
                            	
                            	} catch (AssertionError e) {
                            	    System.out.println("Assertion failed: " + e.getMessage());
                            	}

             
             
                              
                              
                              try {
                                  // 2. Click the dropdown arrow
                                  WebElement dropdownArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@id,'mat-select')]/div/div)[4]")));
                                  jsExecutor.executeScript("arguments[0].click();", dropdownArrow);
                                  System.out.println("Dropdown arrow clicked");
                              } catch (NoSuchElementException e) {
                                  System.out.println("Error clicking the dropdown arrow: " + e.getMessage());
                              }
                              
                              
                              try {
                                  // 3. Click on the 'Department' label
                                  WebElement departmentLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Department ']")));
                                  jsExecutor.executeScript("arguments[0].click();", departmentLabel);
                                  System.out.println("'Department' label clicked");
                              } catch (NoSuchElementException e) {
                                  System.out.println("Error clicking the 'Department' label: " + e.getMessage());
                              }
                              
                              
                              try {
                            	    // Locate the checkbox for 'Use User Property'
                            	    WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=' Use User Property ']/parent::label//input[@type='checkbox']")));

                            	    // Use JavaScript Executor to click the checkbox
                            	    jsExecutor.executeScript("arguments[0].click();", checkbox);
                            	    System.out.println("Checkbox for 'Use User Property' clicked ");

                            	    // Assert that the checkbox is selected
                            	    boolean isSelected = (Boolean) jsExecutor.executeScript("return arguments[0].checked;", checkbox);
                            	    Assert.assertTrue(isSelected, "Checkbox 'Use User Property' is not selected");
                            	} catch (NoSuchElementException e) {
                            	    System.out.println("Error interacting with the checkbox: " + e.getMessage());
                            	}

                              
                              
                              try {
                                  // 5. Click the settings icon
                                  WebElement settingsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='settings']")));
                                  jsExecutor.executeScript("arguments[0].click();", settingsIcon);
                                  System.out.println("Settings icon clicked");
                              } catch (NoSuchElementException e) {
                                  System.out.println("Error clicking the settings icon: " + e.getMessage());
                              }
                              
                              
                              try {
                            	// Wait for theManage UI for Department  text to be visible on the page
                            	  WebElement Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(@class,'dialog-title')]")));

                            	  // Retrieve the text from the dialog and print it
                            	  String dialogMessage = Text.getText();
                            	  System.out.println("Text: " + dialogMessage);

                            	  // Assert that the dialog text is displayed
                            	  Assert.assertTrue(Text.isDisplayed(), "Dialog text is not displayed");

                              } catch (NoSuchElementException e) {
                                  System.out.println("Error getting dialog text: " + e.getMessage());
                              }
                              
                              
                              

                              
                              try {
                                  // 13. Click Cancle button
                                  WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Cancel ']")));
                               
                                  jsExecutor.executeScript("arguments[0].click();", okButton);
                                  System.out.println("cancel button clicked");
                              } catch (NoSuchElementException e) {
                                  System.out.println("Error clicking cancel button: " + e.getMessage());
                              }
                              
                              
                              
                              
                              
                              try {
                            	    // 5. Click the settings icon
                            	    WebElement settingsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='settings']")));
                            	    jsExecutor.executeScript("arguments[0].click();", settingsIcon);
                            	    System.out.println("Settings icon clicked");
                            	} catch (NoSuchElementException e) {
                            	    System.out.println("Error clicking the settings icon: " + e.getMessage());
                            	} catch (StaleElementReferenceException e) {
                            	    System.out.println("Stale element error while clicking settings icon: " + e.getMessage());
                            	    // Re-locate and click the settings icon again
                            	    WebElement settingsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='settings']")));
                            	    jsExecutor.executeScript("arguments[0].click();", settingsIcon);
                            	}

                            	try {
                            	    // Wait for the Manage UI for Department text to be visible on the page
                            	    WebElement Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(@class,'dialog-title')]")));
                            	    
                            	    // Retrieve the text from the dialog and print it
                            	    String dialogMessage = Text.getText();
                            	    System.out.println("Text: " + dialogMessage);
                            	    
                            	    // Assert that the dialog text is displayed
                            	    Assert.assertTrue(Text.isDisplayed(), "Dialog text is not displayed");

                            	} catch (NoSuchElementException e) {
                            	    System.out.println("Error getting dialog text: " + e.getMessage());
                            	} catch (StaleElementReferenceException e) {
                            	    System.out.println("Stale element error while getting dialog text: " + e.getMessage());
                            	    // Re-locate and retrieve the dialog text again
                            	    WebElement Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(@class,'dialog-title')]")));
                            	    String dialogMessage = Text.getText();
                            	    System.out.println("Text: " + dialogMessage);
                            	    Assert.assertTrue(Text.isDisplayed(), "Dialog text is not displayed");
                            	}

                            	try {
                            	    // 13. Click Cancel button
                            	    WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Cancel ']")));
                            	    jsExecutor.executeScript("arguments[0].click();", cancelButton);
                            	    System.out.println("Cancel button clicked");
                            	} catch (NoSuchElementException e) {
                            	    System.out.println("Error clicking cancel button: " + e.getMessage());
                            	} catch (StaleElementReferenceException e) {
                            	    System.out.println("Stale element error while clicking cancel button: " + e.getMessage());
                            	    // Re-locate and click the cancel button again
                            	    WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Cancel ']")));
                            	    jsExecutor.executeScript("arguments[0].click();", cancelButton);
                            	}

                            	try {
                            	    // Click the settings icon again
                            	    WebElement settingsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='settings']")));
                            	    jsExecutor.executeScript("arguments[0].click();", settingsIcon);
                            	    System.out.println("Settings icon clicked again");
                            	} catch (NoSuchElementException e) {
                            	    System.out.println("Error clicking the settings icon again: " + e.getMessage());
                            	} catch (StaleElementReferenceException e) {
                            	    System.out.println("Stale element error while clicking settings icon again: " + e.getMessage());
                            	    // Re-locate and click the settings icon again
                            	    WebElement settingsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='settings']")));
                            	    jsExecutor.executeScript("arguments[0].click();", settingsIcon);
                            	}

                            	try {
                            	    // Wait for the Manage UI for Department text to be visible on the page
                            	    WebElement Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(@class,'dialog-title')]")));
                            	    
                            	    // Retrieve the text from the dialog and print it
                            	    String dialogMessage = Text.getText();
                            	    System.out.println("Text: " + dialogMessage);
                            	    
                            	    // Assert that the dialog text is displayed
                            	    Assert.assertTrue(Text.isDisplayed(), "Dialog text is not displayed");

                            	} catch (NoSuchElementException e) {
                            	    System.out.println("Error getting dialog text again: " + e.getMessage());
                            	} catch (StaleElementReferenceException e) {
                            	    System.out.println("Stale element error while getting dialog text again: " + e.getMessage());
                            	    // Re-locate and retrieve the dialog text again
                            	    WebElement Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(@class,'dialog-title')]")));
                            	    String dialogMessage = Text.getText();
                            	    System.out.println("Text: " + dialogMessage);
                            	    Assert.assertTrue(Text.isDisplayed(), "Dialog text is not displayed");
                            	}

                            	try {
                            	    // Locate and click the radio button
                            	    WebElement radioButtonInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-radio-button[@value='Enable']//span[@class='mat-radio-label-content']")));
                            	    radioButtonInput.click();
                            	    System.out.println("Clicked the 'Enable' radio button");

                            	} catch (AssertionError e) {
                            	    System.out.println("Assertion failed: " + e.getMessage());
                            	} catch (StaleElementReferenceException e) {
                            	    System.out.println("Stale element error while clicking radio button: " + e.getMessage());
                            	    // Re-locate and click the radio button again
                            	    WebElement radioButtonInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-radio-button[@value='Enable']//span[@class='mat-radio-label-content']")));
                            	    radioButtonInput.click();
                            	    System.out.println("Clicked the 'Enable' radio button again");
                            	}

                            	try {
                            	    // Click Ok button
                            	    WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='primary-button ng-star-inserted']")));
                            	    jsExecutor.executeScript("arguments[0].click();", okButton);
                            	    System.out.println("Ok button clicked");

                            	} catch (NoSuchElementException e) {
                            	    System.out.println("Error clicking Ok button: " + e.getMessage());
                            	} catch (StaleElementReferenceException e) {
                            	    System.out.println("Stale element error while clicking Ok button: " + e.getMessage());
                            	    // Re-locate and click the Ok button again
                            	    WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='primary-button ng-star-inserted']")));
                            	    jsExecutor.executeScript("arguments[0].click();", okButton);
                            	    System.out.println("Ok button clicked again");
                            	}

                              
                              
                              // Save the application
                              clickSave1.click();
                              System.out.println("Application saved.");
                              
                              // Switch to the front-end window
                              driver.switchTo().window(secondWindowHandle);
                              driver.navigate().refresh();
                              System.out.println("Switched to the front-end window. Page refreshed.");
                                  
                              
                              
                              try {
                            	    // Wait for the dropdown arrow to be clickable and click it
                            	    WebElement dropdownArrowAgain = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Dropdown']/div/div[2]/div")));
                            	    jsExecutor.executeScript("arguments[0].click();", dropdownArrowAgain);
                            	    System.out.println("Dropdown arrow clicked successfully");

                            	    // Retrieve and print all dropdown options
                            	    List<WebElement> dropdownOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[@role='option']")));
                            	    System.out.println("Dropdown options available:");
                            	    for (WebElement option : dropdownOptions) {
                            	        System.out.println(" - " + option.getText());
                            	    }

                            	    // Choose the desired index value
                            	    int desiredIndex = 2;  // Index starts from 0, 1, 2, 3, 4
                            	    if (dropdownOptions.size() > desiredIndex) {
                            	        WebElement optionToSelect = dropdownOptions.get(desiredIndex);
                            	        String optionText = optionToSelect.getText();
                            	        jsExecutor.executeScript("arguments[0].click();", optionToSelect);
                            	        System.out.println("Selected option: " + optionText);

                            	        // Wait for the selected option to reflect in the dropdown
                            	        WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mat-select-value-1']/span[contains(@class,'mat-mdc-select-value-text ng-tns')]")));
                            	        String actualText = selectedOption.getText().trim();  // Trim to remove any extra whitespace
                            	        System.out.println("Displayed option after selection: " + actualText);

                            	        // Assert the selected value matches the expected value
                            	        String expectedText = "IT";
                            	        Assert.assertEquals(actualText, expectedText, "Verification failed: Selected option does not match the expected value");
                            	        System.out.println("Assertion successful: Selected option is '" + actualText + "'");
                            	    } else {
                            	        System.out.println("Desired index " + desiredIndex + " is out of bounds. Total options available: " + dropdownOptions.size());
                            	    }

                            	} catch (Exception e) {
                            	    System.out.println("An error occurred while interacting with the dropdown:");
                            	    e.printStackTrace();
                            	}

                          
                              
                              
                              // Switch back to the backend window after all checks
                      	    driver.switchTo().window(firstWindowHandle);
                      	    System.out.println("Switched to the backend window.");  
                              
                              
                              
                         // click the setting icon---   click disable radio button---ok  &   save  
                              
                      	  try {
                              //  Click the settings icon
                              WebElement settingsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[text()='settings']")));
                              
                              jsExecutor.executeScript("arguments[0].click();", settingsIcon);
                              System.out.println(" Again Settings icon clicked");
                          } catch (NoSuchElementException e) {
                              System.out.println("Error clicking the settings icon: " + e.getMessage());
                          }
                          
                          
                          try {
                          	// Wait for the dialog text to be visible
                          	  WebElement Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(@class,'dialog-title')]")));

                          	  // Retrieve the text from the dialog and print it
                          	  String dialogMessage = Text.getText();
                          	  System.out.println("Text: " + dialogMessage);

                          	  // Assert that the dialog text is displayed
                          	  Assert.assertTrue(Text.isDisplayed(), "Dialog text is not displayed");

                            } catch (NoSuchElementException e) {
                                System.out.println("Error getting dialog text: " + e.getMessage());
                            }
                          
                          
                          
                          try {
                    	      // Locate the 'Select Option' radio button's <input> element using the updated XPath
                    	      WebElement radioButtonInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-radio-button[@value='Disable']//span[@class='mat-radio-label-content']")));  

                    	   // Click the radio button using JavaScript
                    	      radioButtonInput.click();                            	      
                    	      
                    	      System.out.println("Clicked the 'Enable' radio button ");

                    	  } catch (AssertionError e) {
                    	      System.out.println("Assertion failed: " + e.getMessage());
                    	  } 
                          
                              
                              
                       // Save the application
                          clickSave1.click();
                          System.out.println("Application saved.");     
                              
                              
                           
                          
                          // Switch to the front-end window
                          driver.switchTo().window(secondWindowHandle);
                          driver.navigate().refresh();
                          System.out.println("Switched to the front-end window. Page refreshed.");
                          
                          
                              
                          //  check the dropdown is disable    
                          
                          try {
                              // Wait for the dropdown arrow to be clickable and click it
                              WebElement dropdownArrowAgain = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Dropdown']/div/div[2]/div")));
                              jsExecutor.executeScript("arguments[0].click();", dropdownArrowAgain);
                              System.out.println("Dropdown arrow clicked successfully");

                              // Retrieve and print all dropdown options
                              List<WebElement> dropdownOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[@role='option']")));
                              System.out.println("Dropdown options available:");
                              for (WebElement option : dropdownOptions) {
                                  System.out.println(" - " + option.getText());
                              }

                              // Choose the desired index value
                              int desiredIndex = 2; // Index starts from 0
                              if (dropdownOptions.size() > desiredIndex) {
                                  WebElement optionToSelect = dropdownOptions.get(desiredIndex);
                                  String optionText = optionToSelect.getText();
                                  jsExecutor.executeScript("arguments[0].click();", optionToSelect);
                                  System.out.println("Selected option: " + optionText);

                                  // Wait for the selected option to reflect in the dropdown
                                  WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mat-select-value-1']/span/span")));
                                  String actualText = selectedOption.getText();
                                  System.out.println("Displayed option after selection: " + actualText);

                                  // Assert the selected value matches the expected value
                                  String expectedText = "HR";
                                  Assert.assertEquals("Verification failed: Selected option does not match the expected value", expectedText, actualText);
                                  System.out.println("Assertion successful: Selected option is '" + actualText + "'");
                              } else {
                                  System.out.println("Desired index " + desiredIndex + " is out of bounds. Total options available: " + dropdownOptions.size());
                              }

                          } catch (Exception e) {
                              System.out.println("An error occurred while interacting with the dropdown:");
                              e.printStackTrace();
                          }
                            
                         
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                              
                              
             
                      }     
             
           
             
             
             
}            
             
             
             
             
            
             
   
       
