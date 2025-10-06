package greenkartStepDefination;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features = "src/test/java/features/greenkart.feature",
	glue = "greenkartStepDefination",
	plugin = 
	{ "pretty","html:Report/cucumberReport/greenkart/greenkart.html",
			   "json:Report/cucumberReport/greenkart/greenkart.json"},
	monochrome = true)

public class testrunnergreenkart extends AbstractTestNGCucumberTests
{

}
