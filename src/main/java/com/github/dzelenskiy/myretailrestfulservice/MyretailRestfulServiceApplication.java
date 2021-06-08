package com.github.dzelenskiy.myretailrestfulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.github.dzelenskiy.myretailrestfulservice")
@SpringBootApplication
public class MyretailRestfulServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyretailRestfulServiceApplication.class, args);

	}

}
