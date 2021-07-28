package dev.coralwombat.appointment.booking.clientservice;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docketConfiguration() {
	return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("dev.coralwombat.appointment.booking.clientservice.rest"))
		.build()
		.apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
	return new ApiInfo("Appointment Booking API",
		"Contains all APIs to configure and use a Appointment Booking system.",
		"1.0",
		null,
		new Contact("Kristóf Göncző", null, "kristofgonczo@gmail.com"),
		null,
		null,
		Collections.emptyList());
    }

}
