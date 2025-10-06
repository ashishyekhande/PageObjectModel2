package TestPackage;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.dashboard;

//@Listeners(listen.class)
public class greenkartTest extends baseclass 
{

	@Parameters({"url", "country"})
	@Test
	public void vegshoping(String url, String country) throws Exception
	{
		//given
		openUrl(url);
		
		//when
		String veglist=	datareading();  //veglist =brocloki, cucjumber, beetroot ,pumpkin
		String veg[] =veglist.split(",");  // veg[] ={brocloki, cucjumber, beetroot ,pumpkin}
		
		for(String temp:veg)
		{
			System.out.println(temp);
			d.addtocart(temp);
		}
		
		//Then
		b.checkout(country);
		
	}
	
}
