package com.cts.homeinsurance.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.SQLException;
import java.io.InputStream;

public class OracleConnection {

	public Connection getConnection() throws ClassNotFoundException,
	IOException, SQLException {
	final Properties prop = new Properties();
	final InputStream inputStream = OracleConnection.class.getClassLoader()
			.getResourceAsStream(
					"properties/db.properties");
	
	prop.load(inputStream);
	Class.forName(prop.getProperty("driver"));
	final Connection connection =
			DriverManager.getConnection(prop.getProperty("url"),
			prop.getProperty("user"), prop.getProperty("password"));
	return connection;
	}
}
