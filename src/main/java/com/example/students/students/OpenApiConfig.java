package com.example.students.students;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Derrick Mulinde",
                        email = "djmulinde@gmail.com",
                        url = "https://derrick-mulinde.netlify.app/"
                ),
                description = "API Documentation for a Student Management System. All endpoints are listed below.",
                license = @License(
                        name = "MIT License",
                        url = "https://github.com/inspecta/student-management-api/blob/develop/MIT.md"
                ),
                title = "Student Management API Documentation",
                version = "1.0.0",
                termsOfService = "Terms of Service"
        ),
        servers = {
                @Server(
                        description = "Development Environment",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {

}
