package Pages;

import java.net.http.WebSocket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dashboard 
{
	
	public WebDriver w; // null

	@FindBy(xpath = "//input[@type='search']")WebElement searchbox;
	@FindBy(xpath = "//a[contains(text(),'Top Deals')]")WebElement topdeal;
	@FindBy(xpath = "//button[contains(text(),'ADD TO CART')]")WebElement addtokart;
		
	public dashboard(WebDriver w) 
	{
		this.w = w;
		PageFactory.initElements(w, this);
		
	}


	public void addtocart(String veg) throws Exception 
	{
		searchbox.sendKeys(veg);
		Thread.sleep(1000);
		addtokart.click();	
		searchbox.clear();		
	}

}
