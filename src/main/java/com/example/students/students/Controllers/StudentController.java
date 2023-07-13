package com.example.students.students.Controllers;

import com.example.students.students.Model.Student;
import com.example.students.students.Services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            description = "Fetch all the available students from the database.",
            summary = "Fetch all students endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Failure",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @Operation(
            summary = "Get student details endpoint",
            description = "Use a student ID to get the information about the student",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404"
                    )
            },
            parameters = @Parameter(
                    name = "Student ID",
                    description = "The unique ID of the required student"
            )
    )
    @GetMapping(path = "{studentId}")
    public Student getOneStudent(@PathVariable("studentId") Long studentId) {
        return studentService.getOneStudent(studentId);
    }

    @Operation(
            summary = "Add student endpoint",
            description = "Endpoint used to create a student entry in the database",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Student name"
                    ),
                    @Parameter(
                            name = "email",
                            description = "Email address"
                    ),
                    @Parameter(
                            name = "dob",
                            description = "Date of birth"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<String> addNewStudent(@RequestBody Student student){
        return studentService.registerStudent(student);
    }

    @Operation(
            summary = "Delete student endpoint",
            description = "Delete a student using Student ID",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    )
            },
            parameters = @Parameter(
                    name = "Student ID",
                    description = "ID of the student to Delete"
            )

    )
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
        return studentService.removeStudent(studentId);
    }

    @Operation(
            summary = "Update Student endpoint",
            description = "Update the details of a student",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "student ID",
                            description = "ID of the student to update"
                    ),
                    @Parameter(
                            name = "student name",
                            description = "The new name of the student"
                    ),
                    @Parameter(
                            name = "student email",
                            description = "The new email of the student"
                    )
            }
    )
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

}
