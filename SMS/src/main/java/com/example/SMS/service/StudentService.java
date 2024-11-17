package com.example.SMS.service;

import com.example.SMS.entity.Student;
import com.example.SMS.payload.StudentDTO;
import com.example.SMS.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::toStudentDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO registerStudent(StudentDTO studentDTO) {
        Student student = toStudentEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return toStudentDTO(savedStudent);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setFName(studentDTO.getFName());
        student.setLName(studentDTO.getLName());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        Student updatedStudent = studentRepository.save(student);
        return toStudentDTO(updatedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    public StudentDTO toStudentDTO(Student student) {
        return new StudentDTO(
                student.getFName(),
                student.getLName(),
                student.getEmail(),
                student.getPhoneNumber()
        );
    }

    public Student toStudentEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFName(studentDTO.getFName());
        student.setLName(studentDTO.getLName());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        return student;
    }
}
