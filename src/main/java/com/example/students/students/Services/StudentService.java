package com.example.students.students.Services;

import com.example.students.students.Model.Student;
import com.example.students.students.Repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    // We want to use the Repository here
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public ResponseEntity<String> registerStudent(Student student) {
        // Check in an email already exists
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        // If it does, throw an exception
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists.");
        }

        // Else, save the student
        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully!");

    }

    public ResponseEntity<String> removeStudent(Long id) {
        try {
            boolean exists = studentRepository.existsById(id);
            if(!exists) {
                String errorMessage = "Student with id " + id + " does not exist!";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Student with id: " + id + " deleted successfully!");

        }catch (Exception e) {

            String errorMessage = "An error occurred while deleting the student: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);

        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        // First find the student with student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " doesn't exist!"
                ));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            // Check whether email already exists or not
            Optional<Student> studentEmailOptional = studentRepository.findStudentByEmail(email);

            if(studentEmailOptional.isPresent()){
                throw new IllegalStateException("Sorry, Email already in use.");
            }
            student.setEmail(email);
        }
    }

    public Student getOneStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " doesn't exist!"
                ));
        return student;
    }
}
