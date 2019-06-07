package com.cts.homeinsurance.mainsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.cts.homeinsurance.automation.*;
import com.cts.homeinsurance.runner.*;


@RunWith(Suite.class)
@SuiteClasses({RegisterUserRunner.class, LoginRunner.class,
	UserCreateQuoteAutomation.class, UserCreateQuoteAutomation.class,
	UserViewPolicyAutomation.class, AdminAutomation.class})
public class MainSuite {

}
