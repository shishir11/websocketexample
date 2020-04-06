package com.upstox.stock.viewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
/**
 * 
 * @author shishir.sarkar
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.upstox.stock")
public class StockViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockViewerApplication.class, args);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	
}
