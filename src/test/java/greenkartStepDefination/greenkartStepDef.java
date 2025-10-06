package greenkartStepDefination;

import TestPackage.baseclass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class greenkartStepDef extends baseclass
{
	@Given("user is on greenkart site")
	public void sitelaunch() throws Exception
	{
		openUrl("https://rahulshettyacademy.com/seleniumPractise/#/");
	}

	@When("user add vegitable in basket")
	public void itemsadding() throws Exception
	{
		String veglist=	datareading();  //veglist =brocloki, cucjumber, beetroot ,pumpkin
		String veg[] =veglist.split(",");  // veg[] ={brocloki, cucjumber, beetroot ,pumpkin}
		
		for(String temp:veg)
		{
			System.out.println(temp);
			d.addtocart(temp);
		}
	}
	
	@Then("perform chekout")
	public void doingChekout() throws Exception
	{
		b.checkout("India");
	}
	
	@And("Successfull message should display.")
	public void successfullmessgae() throws Exception 
	{
		getscreenshot("greenkartTest", w);
		System.out.println("order place  successfully");
		terminate();
	}
	
	
}
