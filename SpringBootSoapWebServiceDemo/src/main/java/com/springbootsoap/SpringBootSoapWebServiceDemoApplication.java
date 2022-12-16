package com.springbootsoap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootSoapWebServiceDemoApplication {

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSoapWebServiceDemoApplication.class, args);
	}

}
