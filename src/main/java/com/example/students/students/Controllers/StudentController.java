package com.example.students.students.Controllers;

import com.example.students.students.Model.Student;
import com.example.students.students.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Student getOneStudent(@PathVariable("studentId") Long studentId) {
        return studentService.getOneStudent(studentId);
    }

    @PostMapping
    public ResponseEntity<String> addNewStudent(@RequestBody Student student){
        return studentService.registerStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
        return studentService.removeStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

}
