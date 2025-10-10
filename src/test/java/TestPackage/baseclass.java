package TestPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import Pages.basket;
import Pages.dashboard;
import Pages.loginPage;

public class baseclass {
	//global
	public WebDriver w;
	public dashboard d ;
	public basket b;
	public loginPage lp;
	
	public void initialize() throws Exception
	{
			 
		System.out.println(" i am on lopgin branch");
		
		System.out.println("created feature 1");
		
		FileInputStream fis = new FileInputStream("./Data/config.properties");
		Properties p = new Properties();
		p.load(fis);
		
		//if get input from terminal then use that else use from config file
		String browser;
		if(System.getProperty("browser")!=null) 
		{
			browser = System.getProperty("browser");  // terminal  --> edge
		}
		else 
		{
			browser =p.getProperty("browser").trim();
			
		}
			
		
		String env =p.getProperty("env").trim();
		
		if(env.equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName(browser);
			cap.getBrowserVersion();
			URL url = new URL("http://localhost:4444/wd/hub");
			w= new RemoteWebDriver(url, cap);
		}
		else if(env.equalsIgnoreCase("local")) //local
		{
			
			if(browser.contains("chrome"))     // chrome , chromeheadless  
			{
				
				ChromeOptions op = new ChromeOptions();
				op.addArguments("--incognito");
				
				if(browser.contains("headless")) //chromeheadless
				{
					op.addArguments("headless");
				}
				
				w = new ChromeDriver(op);
					
			}
			if(browser.equals("edge"))
			{
				EdgeOptions op = new EdgeOptions();
				op.addArguments("-inprivate");
				w = new EdgeDriver(op);
				
			}			
		}
			
		w.manage().window().maximize();
		w.manage().deleteAllCookies();
		w.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		d = new dashboard(w);
		b = new basket(w);
		lp = new loginPage(w);
				
	}
	
	public void openUrl(String url) throws Exception
	{
		initialize();
		w.get(url);
	}
	
	public String datareading() throws Exception
	{
		FileInputStream fis = new FileInputStream("./Data/veg.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		int rowcount = sh.getLastRowNum();  //10
		String veglist ="";
		for(int i =1;i <= rowcount;i++)  //  i=9 10<10
		{
			XSSFRow row = sh.getRow(i);
			String veg = row.getCell(0).getStringCellValue();
			veglist = veglist+ veg  +",";								// veglist = broc ,beetroot, carrot ,cucumbe	
		}
		
		return veglist;
	}
	
	public ExtentReports extentReport()
	{
		ExtentSparkReporter reporter = new ExtentSparkReporter("./Report/testreport.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	
	public File getscreenshot(String testname, WebDriver w) throws Exception
	{
		 LocalDateTime now = LocalDateTime.now();
		 // Simple format: dd-MM-yyyy HH:mm:ss
	     String formatted = now.format(DateTimeFormatter.ofPattern("MMM dd HH.mm.ss"));
		TakesScreenshot tc = (TakesScreenshot) w;
		File src = tc.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+testname+formatted+".png");   //screenshot/login.png
		Files.copy(src, dest);
		return dest;
	}
	
	@AfterMethod
  	public void terminate()
	{
		w.quit();
	}
	

}
