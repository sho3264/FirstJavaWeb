package com.cts.homeinsurance.stepdef;

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

import cucumber.api.junit.Cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

@RunWith(Cucumber.class)
public class RegisterUserStepDef {
	private static WebDriver driver = null;
	private static OracleConnection orcl;
	private static Connection con;
	private static Boolean databaseIsOnline;


	// test creating a user with accepted username and password
	@Given("^UserName and Password, and User is on the registration page$")
	public void username_and_password_and_user_is_on_the_registration_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Student\\eclipse-jee-photon-R-win32-x86_64\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
		driver.get("http://localhost:8080/HomeInsurance_Stephen_Ho/showRegistrationPage");
		WebElement uname=driver.findElement(By.name("userName"));
		uname.sendKeys("asdf");
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys("asdf");
		WebElement passConfirm = driver.findElement(By.name("confirmPassword"));
		passConfirm.sendKeys("asdf");
		throw new PendingException();

	}

	@Then("^Page is redirected to the Login Page$")
	public void page_is_redirected_to_the_login_page() throws Throwable {
		assertThat(driver.findElement(By.cssSelector("body > h1")).getText(),equalTo("Login Page"));
		driver.close();
	}

	@And("^Clicks register$")
	public void clicks_register() throws Throwable {
		driver.findElement(By.cssSelector("body > form > input[type=\"submit\"]:nth-child(7)")).click();	
	}


}
