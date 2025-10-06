package TestPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.opentelemetry.context.Context;

//@Listeners(listen.class)  // ths line gave problem
public class login extends baseclass
{

	@Test(dataProvider = "exceldata")
	public void loginTest(String user, String pass) throws Exception
	{
		openUrl("https://www.saucedemo.com");
		String expectedURL ="https://www.saucedemo.com/inventory.html";
		lp.loginSwaglab(user, pass,expectedURL);
	}
	
	@DataProvider(name = "exceldata")
	public Object[][] datamethod(ITestContext context) throws Exception
	{
		
		String url = context.getCurrentXmlTest().getParameter("url");
		FileInputStream fis = new FileInputStream("./Data/veg.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(1);
		
		int rowcount = sh.getLastRowNum();
		int colcount = sh.getRow(0).getLastCellNum();
		
		Object obj[][] = new Object[rowcount][colcount];
		
		for(int i = 0;i<rowcount;i++) //0,1
		{
			XSSFRow row = sh.getRow(i+1); //1,2 
			
			for(int j= 0;j<colcount;j++) //0,1 ,2
			{
				obj[i][j]= row.getCell(j).getStringCellValue();												
			}
					
		}
		return obj;
	}
	
}
