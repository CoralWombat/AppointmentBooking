package dev.coralwombat.appointment.booking.admin;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public DataSource dataSource() {
		var dataSource = new DriverManagerDataSource();

		dataSource.setUsername("testuser");
		dataSource.setPassword("testpass");
		dataSource.setUrl("jdbc:h2:mem:testDb;DB_CLOSE_DELAY=-1");

		return dataSource;
	}

	Properties additionalProperties() {
		var properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		return properties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		var em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("dev.coralwombat.appointment.booking.entities");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}

}
