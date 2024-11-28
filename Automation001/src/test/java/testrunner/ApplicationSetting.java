package testrunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ".//features/Applicationsetting.feature", 
        glue = {"stepdefinition"}, 
        plugin = {
                "pretty", 
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports.json" 
        },
        monochrome = true,
        dryRun = false 
)
public class ApplicationSetting {

	

}
