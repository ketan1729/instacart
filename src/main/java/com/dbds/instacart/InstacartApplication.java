package com.dbds.instacart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@ComponentScan(basePackages = "com.dbds.instacart")
//@Configuration
public class InstacartApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InstacartApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(InstacartApplication.class);
	}

}