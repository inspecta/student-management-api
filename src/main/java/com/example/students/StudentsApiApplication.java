package com.example.students;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class StudentsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsApiApplication.class, args);
	}

}
