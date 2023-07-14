package com.boot.educationalCounselling.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.boot.educationalCounselling.backend.entity.Student;
import com.boot.educationalCounselling.backend.exceptionHandling.ResourceNotFoundException;
import com.boot.educationalCounselling.backend.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	// constructor injection
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	public Student createStudent(Student student) throws ResourceNotFoundException {
	    // Get the authenticated user's email
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String authenticatedEmail = authentication.getName();

	    // Ensure the authenticated user's email matches the email provided in the student object
	    if (!authenticatedEmail.equals(student.getEmail())) {
	        throw new ResourceNotFoundException("Email mismatch. The authenticated user's email does not match the provided email.", authenticatedEmail, student.getEmail());
	    }

	    // Check if a student with the email already exists
	    Student existingStudent = studentRepository.findByEmail(authenticatedEmail);
	    if (existingStudent != null) {
	        throw new ResourceNotFoundException("Email already exists", authenticatedEmail, authenticatedEmail);
	    }

	    try {
	        return studentRepository.save(student);
	    } catch (Exception e) {
	        // Handle any other exceptions that may occur during saving
	        throw new RuntimeException("Failed to create student.");
	    }
	}




	@Override
	public List<Student> displayStudents() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	public Student displayStudentById(long id) {
		return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student","Id",id));
		
	}

	@Override
	public Student updateStudentById(Student student, long id) {
		Student existingStudent=studentRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("Student","Id",id));
		 existingStudent.setFirstName(student.getFirstName());
         existingStudent.setLastName(student.getLastName());
         existingStudent.setPhoneNo(student.getPhoneNo());
		 existingStudent.setDateOfBirth(student.getDateOfBirth());
         existingStudent.setEmail(student.getEmail());
         existingStudent.setCourseOfChoice(student.getCourseOfChoice());
		 existingStudent.setPercentageIn10th(student.getPercentageIn10th());
		 existingStudent.setPercentageIn12th(student.getPercentageIn12th());
         existingStudent.setLocationPreferred(student.getLocationPreferred());
         existingStudent.setFeeCapability(student.getFeeCapability());
         return studentRepository.save(existingStudent);
	}

	@Override
	public void deleteStudentById(long id) {
		studentRepository.findById(id).orElseThrow(()->  new ResourceNotFoundException("Student","Id",id));
		studentRepository.deleteById(id);
	}
	@Override
	public void saveStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
