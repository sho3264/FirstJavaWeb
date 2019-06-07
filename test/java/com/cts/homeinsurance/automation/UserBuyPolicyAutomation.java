package com.cts.homeinsurance.automation;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cts.homeinsurance.dao.OracleConnection;
import com.cts.homeinsurance.model.*;

public class UserBuyPolicyAutomation {
	private static WebDriver driver = null;
	private static OracleConnection orcl;
	private static Connection con;
	private static Boolean databaseIsOnline;
	private static Users u = null;
	private static Location l = null;
	private static Property prop = null;
	private static Homeowner hm = null;
	private static JavascriptExecutor js=null;
	
	@BeforeClass
	public static void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Student\\eclipse-jee-photon-R-win32-x86_64\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		u= new Users("asdf","asdf");
		hm = new Homeowner("Batmo","Biel","123456789","aaa@gmail.com",0);
		l= new Location("Condo", "line1","","Fairbanks","Alaska","997010000","Primary");
		prop= new Property(25000f,2016,2000,1,1,"1 Story","Concrete","Attached",0);
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
	public void buyPolicyByGetQuoteTest() throws IOException
	{	
		// Click Get Quote Link in nav block
		driver.findElement(By.cssSelector("body > header > nav > a:nth-child(2)")).click();
		
		// On Get Quote Page
		// fill out form
		WebElement Addline1 = driver.findElement(By.name("AddressLine1"));
		Addline1.sendKeys(l.getAddressLine1());
		WebElement city = driver.findElement(By.name("City"));
		city.sendKeys(l.getCity());
		WebElement state= driver.findElement(By.name("State"));
		state.sendKeys(l.getLocationState());
		WebElement zip = driver.findElement(By.name("Zip"));
		zip.sendKeys(l.getZipCode());
		driver.findElement(By.name("ResidentType")).click();
		driver.findElement(By.cssSelector("body > form > select:nth-child(1) > option:nth-child(2)")).click();
		driver.findElement(By.name("ResidenceUse")).click();
		driver.findElement(By.cssSelector("body >form > select:nth-child(2) > option:nth-child(1)")).click();
		// go to next page
		driver.findElement(By.cssSelector("body > form > input[type=\"submit\"]:nth-child(15)"));
		
		// On Homeowner information page
		// insert Homeowner info
		WebElement fname= driver.findElement(By.name("FirstName"));
		fname.sendKeys(hm.getFirstName());
		WebElement lname= driver.findElement(By.name("LastName"));
		lname.sendKeys(hm.getLastName());
		WebElement dob = driver.findElement(By.name("dob"));
		dob.sendKeys("12122016");
		WebElement ssn = driver.findElement(By.name("ssn"));
		ssn.sendKeys(hm.getSsn());
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys(hm.getEmail());
		driver.findElement(By.cssSelector("body > form > input[type=\"radio\"]:nth-child(8)"));
		//go to next page
		lname.sendKeys(Keys.ENTER);
		
		// On Property Details Page
		// fill out form 
		WebElement mValue = driver.findElement(By.name("MarketVale"));
		mValue.sendKeys(String.valueOf(prop.getMarketValue()));
		WebElement yearBuilt = driver.findElement(By.name("YearBuilt"));
		yearBuilt.sendKeys(String.valueOf(prop.getYearBuilt()));
		WebElement sqft = driver.findElement(By.name("SqFootage"));
		sqft.sendKeys(String.valueOf(prop.getSquareFootage()));
		driver.findElement(By.cssSelector("body > form > input[type=\"radio\"]:nth-child(18)"));
		// Go to next Page
		sqft.sendKeys(Keys.ENTER);

		// go to next page
		driver.findElement(By.cssSelector("body > a:nth-child(6) > input[type=\"submit\"]")).click();
		
		WebElement today = driver.findElement(By.name("PolicyDate"));
		// date[0]= year
		// date[1]=month
		// date[2]=day
		String[] date = LocalDate.now().toString().split("-");
		today.sendKeys(date[1]+date[2]+date[0]);
		// open up new tab
		driver.findElement(By.cssSelector("body > div:nth-child(4) > form > a")).click();
		// close tab
		//only for chrome
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"w");
		// click check box
		driver.findElement(By.id("chkBox")).click();
		// take screen shot
		takeScreenShot(driver);
		// next page
		driver.findElement(By.id("sub")).click();
		
		//takeScreenShot
		takeScreenShot(driver);
		// check Policy Status
		assertThat(driver.findElement(By.cssSelector("body > section > article:nth-child(2) "
				+ "> table > tbody > tr:nth-child(6) > td:nth-child(2)")).getText(),
				equalTo("Active"));
		
		
		
	}
	
	@Test
	public void buyPolicyByRetrieveQuoteTest() throws IOException
	{	
		// click Retrieve Quote Link in nav blokc
		driver.findElement(By.cssSelector("body > header > nav > a:nth-child(3)")).click();
		// On retrieve Quote Page		
		// click on quote link
		driver.findElement(By.cssSelector("body > table > tbody > tr:nth-child(2) > td:nth-child(1) > a")).click();
		
		// On Coverage Details Page
		// go to next page
		driver.findElement(By.cssSelector("body > a:nth-child(6) > input[type=\"submit\"]")).click();
		
		// go to next page
		driver.findElement(By.cssSelector("body > form:nth-child(3) > section > input[type=\"submit\"]")).click();
		
		WebElement today = driver.findElement(By.name("PolicyDate"));
		// date[0]= year
		// date[1]=month
		// date[2]=day
		String[] date = LocalDate.now().toString().split("-");
		today.sendKeys(date[1]+date[2]+date[0]);
		// open up new tab
		driver.findElement(By.cssSelector("body > div:nth-child(4) > form > a")).click();
		// close tab
		//only for chrome
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"w");
		// click check box
		driver.findElement(By.id("chkBox")).click();
		// take screen shot
		takeScreenShot(driver);
		// next page
		driver.findElement(By.id("sub")).click();
		
		//takeScreenShot
		takeScreenShot(driver);
		// check Policy Status
		assertThat(driver.findElement(By.cssSelector("body > section > article:nth-child(2) "
				+ "> table > tbody > tr:nth-child(6) > td:nth-child(2)")).getText(),
				equalTo("Active"));
		
	}
	
	
	private void takeScreenShot(WebDriver driver) throws IOException {
		File scrFile  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\Screenshots\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss.SSS")) + ".png"));
		//	    	FileUtils.copyFile(scrFile, new File("C:./target/" + screenshotFolder + ".png"));
	}
}

