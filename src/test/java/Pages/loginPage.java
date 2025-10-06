package Pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage 
{
	
	public WebDriver w; //local webdriver for this class
	
	@FindBy(css = "#user-name")WebElement username;
	@FindBy(xpath = "//input[@placeholder='Password']")WebElement password;
	@FindBy(css = "#login-button")WebElement loginbutton;
	
	public loginPage(WebDriver w)
	{
		this.w =w;
		PageFactory.initElements(w, this);
	}

	public void loginSwaglab(String user, String pass, String expectedURL)
	{
		username.sendKeys(user);
		password.sendKeys(pass);
		loginbutton.click();
		
		String act_currentURL = w.getCurrentUrl(); //ac
		assertEquals(act_currentURL, expectedURL);	
	}
	

}
