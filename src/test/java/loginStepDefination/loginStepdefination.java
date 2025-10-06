package loginStepDefination;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import TestPackage.baseclass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginStepdefination extends baseclass
{
	SoftAssert sa ;
	@Given("user is on login page")
	public void opensite() throws Exception
	{
		openUrl("https://www.saucedemo.com/");
	}
	
	@When("^user enters (.+) and (.+)$")  // Regular expression
	public void enterUserPass(String user, String pass)
	{
		w.findElement(By.cssSelector("#user-name")).sendKeys(user);
		w.findElement(By.cssSelector("#password")).sendKeys(pass);
	}
	
	@And("click on login button")
	public void clickLogin()
	{
		w.findElement(By.xpath("//input[@type='submit']")).click();
	}
	
	@Then ("login successfull page is displayed and verify {string}")
	public void verify(String expUrl) throws Exception
	{
		getscreenshot("logintest_cucumber", w);
		String Act_currentUrl = w.getCurrentUrl();
		sa = new SoftAssert();
		sa.assertEquals(Act_currentUrl, expUrl);
		terminate();
		sa.assertAll();
	}
	
	@And("successfull messgae {string}")
	public void passorFail(String message)
	{		
		System.out.println(message);
		
	}
	
}
