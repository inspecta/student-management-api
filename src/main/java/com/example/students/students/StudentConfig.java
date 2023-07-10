package com.example.students.students;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return  args -> {
            Student jackson = new Student(
                "Jackson", "jacksob@email.com", LocalDate.of(1970, 12, 25)
            );

            Student derrick = new Student(
                    "Derrick", "derrick@email.com", LocalDate.of(1980, 9, 01)
            );

            repository.saveAll(List.of(derrick, jackson));
        };
    }
}
