package com.example.students.students;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void registerStudent(Student student) {
        // Check in an email already exists
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        // If it does, throw an exception

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists.");
        }
        // Else, save the student
        studentRepository.save(student);
    }

    public void removeStudent(Long id) {
//        studentRepository.deleteById(id);
        boolean exists = studentRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Student with id " + id + " does not exist!");
        }
        studentRepository.deleteById(id);
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
