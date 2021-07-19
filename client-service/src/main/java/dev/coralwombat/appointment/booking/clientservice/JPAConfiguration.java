package dev.coralwombat.appointment.booking.clientservice;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public DataSource dataSource() {
		var dataSource = new DriverManagerDataSource();

		dataSource.setUsername("testuser");
		dataSource.setPassword("testpass");
		dataSource.setUrl("jdbc:h2:tcp://localhost/./database");

		return dataSource;
	}
}
