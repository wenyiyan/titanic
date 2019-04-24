package com.ezops.assignments.titanic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ezops.assignments.titanic")
@EnableJpaRepositories(basePackages = "com.ezops.assignments.titanic.repository")
public class TitanicApplication extends SpringBootServletInitializer {
	static Logger logger = LoggerFactory.getLogger(TitanicApplication.class);
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TitanicApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TitanicApplication.class);
	}

}

