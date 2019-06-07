package com.cts.homeinsurance.runner;


import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/cts/homeinsurance/features/Login.feature",
		glue = {"com/cts/homeinsurance/stepdef"})
public class LoginRunner {

}
