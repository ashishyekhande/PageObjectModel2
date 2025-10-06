package TestPackage;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

public class listen extends baseclass implements ITestListener {

	public WebDriver w;
	ExtentReports extent = extentReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		System.out.println("test is started");
		test = extent.createTest(result.getMethod().getMethodName());
		thread.set(test);  // to set thread
		thread.get().info("i am  running automation script for batch 3");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		System.out.println("test is successful");
		thread.get().log(Status.PASS,"test is pass successfully---------------------->>>>------------------------------------");
		

		try {
			w = (WebDriver) result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			String testname = result.getMethod().getMethodName();
			// File f =getscreenshot(testname,w);
			String path = getscreenshot(testname, w).getAbsolutePath();
			thread.get().addScreenCaptureFromPath(path);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		extent.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		System.out.println("test is failed");
		thread.get().log(Status.FAIL, "test is failed --------------------->>>>>>>------------------------");
		thread.get().fail(result.getThrowable());

		try {
			w = (WebDriver) result.getTestClass().getRealClass().getField("w").get(result.getInstance());
			String testname = result.getMethod().getMethodName();
			// File f =getscreenshot(testname,w);
			String path = getscreenshot(testname, w).getAbsolutePath();
			thread.get().addScreenCaptureFromPath(path);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extent.flush();

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		System.out.println("test is skiped");
	}

}
