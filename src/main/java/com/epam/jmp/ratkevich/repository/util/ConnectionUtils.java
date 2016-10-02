package com.epam.jmp.ratkevich.repository.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import com.epam.jmp.ratkevich.repository.DataAccessException;

@Component
public final class ConnectionUtils {

	private BasicDataSource dataSource = null;

	private static final String DRIVER_NAME = "org.hsqldb.jdbc.JDBCDriver";

	private static final String URL = "jdbc:hsqldb:hsql://localhost:9001";

	private static final String USERNAME = "SA";

	private static final String PASSWORD = "";

	private ConnectionUtils() {
	}

	@PostConstruct
	public void init() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_NAME);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
	}

	@PreDestroy
	public void destroy() {
		try {
			dataSource.close();
		} catch (Exception ex) {
			System.err.println("Error occurred during closing dataSource.");
			ex.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void transact(Consumer<Connection> action) {
		try (Connection connection = getConnection();) {
			connection.setAutoCommit(false);
			action.accept(connection);
			connection.commit();
		} catch (Exception ex) {
			System.err.println("Something went wrong with DB.");
			throw new DataAccessException(ex);
		}
	}

}
