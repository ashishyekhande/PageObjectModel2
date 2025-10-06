package loginStepDefination;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		features = "src/test/java/features/login.feature",
		
		glue = "loginStepDefination",
		
		plugin = {
					"pretty" ,"html:Report/cucumberReport/login/login.html"	,
				              "json:Report/cucumberReport/login/login.json"	
				 },
		
		monochrome = true
)
public class loginTestrunner extends AbstractTestNGCucumberTests
{

}
