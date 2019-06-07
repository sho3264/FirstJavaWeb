package com.cts.homeinsurance.stepdef;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cts.homeinsurance.dao.OracleConnection;

@RunWith(Cucumber.class)
public class LoginStepDef {
	private static WebDriver driver = null;
	private static OracleConnection orcl;
	private static Connection con;
	private static Boolean databaseIsOnline;
	private static String userName = null;
	private static String password =null;
	
	   @Given("^Valid Username and Password that has been registered$")
	    public void valid_username_and_password_that_has_been_registered() throws Throwable {
		   System.setProperty("webdriver.chrome.driver", "C:\\Users\\Student\\eclipse-jee-photon-R-win32-x86_64\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			userName="asdf";
			password="asdf";
			try
			{
				orcl= new OracleConnection();
				con = orcl.getConnection();
				databaseIsOnline = true;
				con.close();
			}
			catch(Exception e)
			{
				databaseIsOnline=false;
			}
			driver.get("http://localhost:8080/HomeInsurance_Stephen_Ho/showRegistrationPage");
	    }

	    @When("^User enters username and password $")
	    public void user_enters_username_and_password() throws Throwable {
	    	assertTrue(databaseIsOnline);
	    	WebElement uname= driver.findElement(By.name("userName"));
	    	uname.sendKeys(userName);
	    	WebElement pass = driver.findElement(By.name("password"));
	    	pass.sendKeys(password);
	    	
	        throw new PendingException();
	    }

	    @Then("^Page is redirected to Get Started Page$")
	    public void page_is_redirected_to_get_started_page() throws Throwable {
	    	assertThat(driver.findElement(By.cssSelector("body > h2")).getText(),equalTo("Welcome "+userName));
	    	// log out 
	    	driver.findElement(By.cssSelector("body > header > nav > a:nth-child(5)")).click();
	    	// close browser
	    	driver.close();
	        throw new PendingException();
	    }

	    @And("^Clicks login$")
	    public void clicks_login() throws Throwable {
	    	driver.findElement(By.cssSelector("body > form > input[type=\"submit\"]:nth-child(5)"));
	        throw new PendingException();
	    }

}
