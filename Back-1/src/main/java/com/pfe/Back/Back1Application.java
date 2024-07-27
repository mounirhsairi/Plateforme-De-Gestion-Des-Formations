package com.pfe.Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class Back1Application {

	public static void main(String[] args) {
		SpringApplication.run(Back1Application.class, args);
		System.out.println("hello");
	}

}
