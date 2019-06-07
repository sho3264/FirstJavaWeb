package com.cts.homeinsurance.automation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cts.homeinsurance.dao.OracleConnection;
import com.cts.homeinsurance.model.Homeowner;
import com.cts.homeinsurance.model.Location;
import com.cts.homeinsurance.model.Property;
import com.cts.homeinsurance.model.Users;

public class UserViewPolicyAutomation {
	private static WebDriver driver = null;
	private static OracleConnection orcl;
	private static Connection con;
	private static Boolean databaseIsOnline;
	private static Users u = null;

	

	@BeforeClass
	public static void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Student\\eclipse-jee-photon-R-win32-x86_64\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		u= new Users("asdf","asdf");

		try {
			orcl=new OracleConnection();
			con= orcl.getConnection();
			databaseIsOnline=true;
			con.close();
		}
		catch(Exception e)
		{
			databaseIsOnline=false;
		}
		driver.get("http://localhost:8080/HomeInsurance_Stephen_Ho/");
		
	}

	@AfterClass
	public static void tearDown()
	{
		driver.close();
	}
	
	@Before
	public void login()
	{
		// check if database is online
		assertTrue(databaseIsOnline);
		// login user
		WebElement uname= driver.findElement(By.name("userName"));
		uname.sendKeys(u.getUserName());
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys(u.getPassword());
		driver.findElement(By.cssSelector("body > form > input[type=\"submit\"]:nth-child(5)"));
		
	}

	@After
	public void logout()
	{
		// logout user
		driver.findElement(By.cssSelector("body > header > nav > a:nth-child(5)")).click();
		
	}
	
	@Test
	public void viewPolicyTest() throws IOException
	{
		// go to My Policy Page
		driver.findElement(By.cssSelector("body > header > nav > a:nth-child(4)")).click();
		// Take screenshot
		takeScreenShot(driver);
		// test is more to see if there is an entry 
		assertThat(driver.findElement(By.cssSelector("body > table > tbody > tr:nth-child(2) > td:nth-child(6)")).getText(),
				equalTo("Active"));
	}
	
	private void takeScreenShot(WebDriver driver) throws IOException {
		File scrFile  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\Screenshots\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss.SSS")) + ".png"));
		//	    	FileUtils.copyFile(scrFile, new File("C:./target/" + screenshotFolder + ".png"));
	}
	
}


//body > table > tbody > tr:nth-child(2) > td:nth-child(6)