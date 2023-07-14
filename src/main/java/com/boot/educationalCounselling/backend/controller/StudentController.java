package com.boot.educationalCounselling.backend.controller;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.educationalCounselling.backend.entity.Student;
import com.boot.educationalCounselling.backend.exceptionHandling.ResourceNotFoundException;
import com.boot.educationalCounselling.backend.service.StudentService;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
	
	Logger logger= LoggerFactory.getLogger(StudentController.class);

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/add")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	 public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);
            return ResponseEntity.ok(createdStudent);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create student.");
        }
    }
	

	@Secured("ROLE_ADMIN")
	@GetMapping("/getall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Student> displayStudents() {
		logger.info("Displaying All Student's Details. ");
		return studentService.displayStudents();

	}

	@GetMapping("getby/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Student displayById(@PathVariable("id") long id) {
		logger.info("Displaying All Student Details of Student with Id: " +id);
		return studentService.displayStudentById(id);

	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@PutMapping("/update/{id}")
	public Student updateStudentById(@Valid @RequestBody Student student, @PathVariable("id") long id) {
		logger.info("Updating Student Details of Student with Id: " +id);
		return studentService.updateStudentById(student, id);
	}

	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("delete/{id}")
	public String deleteStudentById(@PathVariable("id") long id) {
		logger.info("Deleting Student Details of Student with Id: " +id);
		studentService.deleteStudentById(id);
		return "Deleted Student Successfully";
	}
}
