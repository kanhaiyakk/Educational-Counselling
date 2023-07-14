package com.boot.educationalCounselling.backend.controllertest;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.boot.educationalCounselling.backend.controller.StudentController;
import com.boot.educationalCounselling.backend.entity.Student;
import com.boot.educationalCounselling.backend.exceptionHandling.ResourceNotFoundException;
import com.boot.educationalCounselling.backend.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateStudent() throws Exception {
        // Prepare mock data
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setPhoneNo("1234567890");
        student.setDateOfBirth(new Date());
        student.setEmail("john.doe@example.com");
        student.setCourseOfChoice("Engineering");
        student.setPercentageIn10th("95.5");
        student.setPercentageIn12th("92.0");
        student.setLocationPreferred("City");
        student.setFeeCapability(1000000L);

        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        // Perform the request
        mockMvc.perform(post("/student/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.phoneNo").value("1234567890"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.courseOfChoice").value("Engineering"))
                .andExpect(jsonPath("$.percentageIn10th").value("95.5"))
                .andExpect(jsonPath("$.percentageIn12th").value("92.0"))
                .andExpect(jsonPath("$.locationPreferred").value("City"))
                .andExpect(jsonPath("$.feeCapability").value(1000000));

        // Verify the service method is called
        verify(studentService).createStudent(any(Student.class));
    }

    @Test
    public void testCreateStudent_EmailAlreadyExists() throws Exception {
        // Prepare mock data
        Student student = new Student();
        student.setEmail("existing.email@example.com");

        when(studentService.createStudent(any(Student.class)))
                .thenThrow(new ResourceNotFoundException("Email already exists.", "existing.email@example.com", "existing.email@example.com"));

        // Perform the request
        mockMvc.perform(post("/student/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists."));

        // Verify the service method is called
        verify(studentService).createStudent(any(Student.class));
    }

    @Test
    public void testCreateStudent_FailedToCreate() throws Exception {
        // Prepare mock data
        Student student = new Student();
        student.setEmail("new.email@example.com");

        when(studentService.createStudent(any(Student.class))).thenThrow(new RuntimeException("Failed to create student."));

        // Perform the request
        mockMvc.perform(post("/student/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to create student."));

        // Verify the service method is called
        verify(studentService).createStudent(any(Student.class));
    }

    @Test
    public void testDisplayStudents() throws Exception {
        // Prepare mock data
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", "1234567890", new Date(), "john.doe@example.com",
                "Engineering", "95.5", "92.0", "City", 1000000L));
        students.add(new Student("Jane", "Smith", "9876543210", new Date(), "jane.smith@example.com",
                "Medicine", "90.0", "88.5", "Town", 800000L));

        when(studentService.displayStudents()).thenReturn(students);

        // Perform the request
        mockMvc.perform(get("/student/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"));

        // Verify the service method is called
        verify(studentService).displayStudents();
    }

    @Test
    public void testDisplayById() throws Exception {
        // Prepare mock data
        long id = 1L;
        Student student = new Student("John", "Doe", "1234567890", new Date(), "john.doe@example.com",
                "Engineering", "95.5", "92.0", "City", 1000000L);

        when(studentService.displayStudentById(id)).thenReturn(student);

        // Perform the request
        mockMvc.perform(get("/student/getby/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));

        // Verify the service method is called
        verify(studentService).displayStudentById(id);
    }

    @Test
    public void testUpdateStudentById() throws Exception {
        // Prepare mock data
        long id = 1L;
        Student student = new Student("John", "Doe", "1234567890", new Date(), "john.doe@example.com",
                "Engineering", "95.5", "92.0", "City", 1000000L);
        Student updatedStudent = new Student("John", "Smith", "9876543210", new Date(), "john.smith@example.com",
                "Medicine", "90.0", "88.5", "Town", 800000L);

        when(studentService.updateStudentById(any(Student.class), any(Long.class))).thenReturn(updatedStudent);

        // Perform the request
        mockMvc.perform(put("/student/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.phoneNo").value("9876543210"))
                .andExpect(jsonPath("$.email").value("john.smith@example.com"))
                .andExpect(jsonPath("$.courseOfChoice").value("Medicine"))
                .andExpect(jsonPath("$.percentageIn10th").value("90.0"))
                .andExpect(jsonPath("$.percentageIn12th").value("88.5"))
                .andExpect(jsonPath("$.locationPreferred").value("Town"))
                .andExpect(jsonPath("$.feeCapability").value(800000));

        // Verify the service method is called
        verify(studentService).updateStudentById(any(Student.class), any(Long.class));
    }

    @Test
    public void testDeleteStudentById() throws Exception {
        // Prepare mock data
        long id = 1L;

        // Perform the request
        mockMvc.perform(delete("/student/delete/{id}", id))
                .andExpect(status().isOk());

        // Verify the service method is called
        verify(studentService).deleteStudentById(id);
    }
}
