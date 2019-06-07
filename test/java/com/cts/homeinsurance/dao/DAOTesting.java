package com.cts.homeinsurance.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({UsersDAOTest.class,QuoteDAOTest.class,PropertyDAOTest.class,
	PolicyDAOTest.class,LocationDAOTest.class,HomeownerDAOTest.class})
public class DAOTesting {

}
