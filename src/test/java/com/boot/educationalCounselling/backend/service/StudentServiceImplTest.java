package com.boot.educationalCounselling.backend.service;

import com.boot.educationalCounselling.backend.entity.Student;
import com.boot.educationalCounselling.backend.exceptionHandling.ResourceNotFoundException;
import com.boot.educationalCounselling.backend.repository.StudentRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateStudent() {
        // Create a sample student
        Student student = new Student();
        student.setEmail("john.doe@example.com");

        Mockito.when(studentRepository.findByEmail("john.doe@example.com")).thenReturn(null);
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        try {
            Student createdStudent = studentService.createStudent(student);
            Assertions.assertEquals("john.doe@example.com", createdStudent.getEmail());
        } catch (ResourceNotFoundException e) {
            Assertions.fail("ResourceNotFoundException should not be thrown.");
        }
    }

    @Test
    public void testCreateStudentWithExistingEmail() {
        // Create a sample student
        Student student = new Student();
        student.setEmail("john.doe@example.com");

        Mockito.when(studentRepository.findByEmail("john.doe@example.com")).thenReturn(student);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            studentService.createStudent(student);
        });
    }

    @Test
    public void testDisplayStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());
        students.add(new Student());

        Mockito.when(studentRepository.findAll()).thenReturn(students);

        List<Student> returnedStudents = studentService.displayStudents();

        Assertions.assertEquals(3, returnedStudents.size());
    }

    @Test
    public void testDisplayStudentById() {
        Student student = new Student();
        student.setId(1L);

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student returnedStudent = studentService.displayStudentById(1L);

        Assertions.assertEquals(1L, returnedStudent.getId());
    }

    @Test
    public void testUpdateStudentById() {
        Student existingStudent = new Student();
        existingStudent.setId(1L);
        existingStudent.setFirstName("John");

        Student updatedStudent = new Student();
        updatedStudent.setFirstName("Jane");

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(updatedStudent);

        Student returnedStudent = studentService.updateStudentById(updatedStudent, 1L);

        Assertions.assertEquals("Jane", returnedStudent.getFirstName());
    }

    @Test
    public void testDeleteStudentById() {
        Student student = new Student();
        student.setId(1L);

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Assertions.assertDoesNotThrow(() -> {
            studentService.deleteStudentById(1L);
        });

        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(1L);
    }
}
