package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class basket 
{
	public WebDriver w;

	@FindBy(xpath = "//a[@class='cart-icon']")WebElement carticon;
	@FindBy(xpath = "//button[contains(text(),'PROCEED TO CHECKOUT')]")WebElement proceed;
	@FindBy(xpath = "//input[@placeholder='Enter promo code']")WebElement promo;
	@FindBy(xpath = "//button[contains(text(),'Apply')]")WebElement Apply;
	@FindBy(xpath = "//button[contains(text(),'Place Order')]")WebElement PlaceOrder;
	@FindBy(xpath = "//select")WebElement countryselection;
	@FindBy(xpath = "//input[@type='checkbox']")WebElement checkbox;
	@FindBy(xpath = "//button[contains(text(),'Proceed')]")WebElement placeorder;
	
	public basket(WebDriver w) 
	{
		this.w = w;		// passing knowledge from object creation to local variable
		PageFactory.initElements(w, this);
	}


	public void checkout(String country) throws Exception
	{
		carticon.click();
		proceed.click();
		promo.sendKeys("ashish8087");
		Apply.click();
		Thread.sleep(5000);
		PlaceOrder.click();
		
		Select s = new Select(countryselection);
		s.selectByVisibleText(country);
		checkbox.click();
		placeorder.click();
		
	}
	
}
