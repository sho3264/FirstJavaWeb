package com.cts.homeinsurance.automation;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cts.homeinsurance.dao.OracleConnection;
import com.cts.homeinsurance.model.Users;

public class AdminAutomation {
	private static WebDriver driver = null;
	private static OracleConnection orcl;
	private static Connection con;
	private static Boolean databaseIsOnline;
	private static Users u = null;
	private static String existingUser=null;

	@BeforeClass
	public static void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Student\\eclipse-jee-photon-R-win32-x86_64\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		u= new Users("admin","admin");
		existingUser="asdf";
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
		// go to Admin login screen
		driver.findElement(By.cssSelector("body > p > a:nth-child(1)")).click();
	}

	@AfterClass
	public static void tearDown()
	{
		driver.close();
	}
	@Before
	public void login()
	{
		assertTrue(databaseIsOnline);
		// on Admin Login Screen
		WebElement uname = driver.findElement(By.name("userName"));
		uname.sendKeys(u.getUserName());
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys(u.getPassword());
		driver.findElement(By.cssSelector("body > form > input[type=\"submit\"]:nth-child(5)")).click();

	}
	@After
	public void logout()
	{
		// logout
		driver.findElement(By.cssSelector("body > header > nav > a:nth-child(2)")).click();
	}


	@Test
	public void renewPolicyTest() throws IOException
	{
		WebElement input = driver.findElement(By.name("UserNameSearch"));
		// type in existing user and hits enter
		input.sendKeys(existingUser + Keys.ENTER);
		//take screenshot
		takeScreenShot(driver);
		
		// check if admin is searching for correct user
		assertThat(driver.findElement(By.cssSelector("body > h3")).getText(),
				equalTo("User: "+existingUser));
		
		// click on renew
		driver.findElement(By.cssSelector("body > table > tbody "
				+ "> tr:nth-child(2) > td:nth-child(7) > a")).click();
		// take screenshot
		takeScreenShot(driver);
		// check if it is renewed and term number changed
		assertThat(driver.findElement(By.cssSelector("body > section "
				+ "> article:nth-child(2) > h3")).getText(),
				equalTo("Policy Renewed Sucessfully"));
		assertThat(driver.findElement(By.cssSelector("body > section "
				+ "> article:nth-child(2) > h3")).getText(),
				equalTo("2"));
		assertThat(driver.findElement(By.cssSelector("body > section > article:nth-child(2) > table "
				+ "> tbody > tr:nth-child(6) > td:nth-child(2)")).getText(),
				equalTo("Active"));
		
	}
	
	@Test
	public void cancelPolicyTest() throws IOException
	{
		WebElement input = driver.findElement(By.name("UserNameSearch"));
		// type in existing user and hits enter
		input.sendKeys(existingUser + Keys.ENTER);
		//take screenshot
		takeScreenShot(driver);
		
		// check if admin is searching for correct user
		assertThat(driver.findElement(By.cssSelector("body > h3")).getText(),
				equalTo("User: "+existingUser));
		
		// click cancel link
		driver.findElement(By.cssSelector("body > table "
				+ "> tbody > tr:nth-child(2) > td:nth-child(8) > a")).click();
		// Check Policy Status and Header if it says policy is cancelled
		assertThat(driver.findElement(By.cssSelector("body > section "
				+ "> article:nth-child(2) > h3")).getText(),
				equalTo("Policy Cancelled Sucessfully"));
		assertThat(driver.findElement(By.cssSelector("body > section > article:nth-child(2) > table "
				+ "> tbody > tr:nth-child(6) > td:nth-child(2)")).getText(),
				equalTo("CANCELLED"));
	}

	private void takeScreenShot(WebDriver driver) throws IOException {
		File scrFile  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\Screenshots\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss.SSS")) + ".png"));
		//	    	FileUtils.copyFile(scrFile, new File("C:./target/" + screenshotFolder + ".png"));
	}

}
