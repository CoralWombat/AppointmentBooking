package dev.coralwombat.appointment.booking.admin;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "dev.coralwombat.appointment.booking.admin")
@EnableConfigurationProperties
@EntityScan(basePackages = { "dev.coralwombat.appointment.booking.entities" })
public class AdminApplication {

	public static void main(String[] args) throws SQLException {
        Server.createTcpServer().start();
		SpringApplication.run(AdminApplication.class, args);
	}

}
