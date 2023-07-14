package com.boot.educationalCounselling.backend.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Field should not be empty")
	@Size(max = 20, message = "size must not exceed 20 letters,")
	@Pattern(regexp = "^[A-Z][a-z]*$", message = "First Letter must be in captital")
	private String firstName;

	@NotEmpty(message = "Field should not be empty")
	@Size(max =20, message = "size must not exceed 20 letters,")
	@Pattern(regexp = "^[A-Z][a-z]*$", message = "First letter must be in capital")
	private String lastName;

	@NotEmpty(message = "Field should not be empty")
	@Size(min = 10, max = 10, message = "Phone number must have 10 digits only")
	@Pattern(regexp = "^[6789][0-9]*$", message = "phone number must start with 6/7/8/9 ; No special characters/alphabets")
	private String phoneNo;

	@NotNull(message = "Field should not be null")
	@Past(message = "Invalid date ; Birth date should be in the past.")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateOfBirth;

	@Email(message = "Enter a valid email")
	@NotEmpty(message = "Field should not be empty")
	private String email;

	@NotEmpty(message = "Field should not be empty")
	@Size(max = 30, message = "size must not exceed 10 letters,")
	private String courseOfChoice;

	@NotEmpty(message = "Field should not be empty")
	@DecimalMin("0.0")
	@DecimalMax("100.0")
	@Pattern(regexp = "^[0-9]*.[0-9]{1,2}+$", message = "Decimal number can have ONE or TWO digits after decimal point.")
	private String percentageIn10th;

	@NotEmpty(message = "Field should not be empty")
	@DecimalMin("0.0")
	@DecimalMax("100.0")
	@Pattern(regexp = "^[0-9]*.[0-9]{1,2}+$", message = "Decimal number can have ONE or TWO digits after decimal point.")
	private String percentageIn12th;

	@NotEmpty(message = "Field should not be empty")
	@Size(max = 10, message = "size must not exceed 10 letters,")
	private String locationPreferred;

	@NotNull(message = "Field should not be null")
	@Range(min = 100000, max = 1000000, message = "fees must be between 1Lakh to 10Lakhs")
	private long feeCapability;
	
	
	public Student() {

	}

	public Student(String firstName, String lastName, String phoneNo, Date dateOfBirth, String email,
			String courseOfChoice, String percentageIn10th, String percentageIn12th, String locationPreferred,
			long feeCapability) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.dateOfBirth = dateOfBirth;
		this.email= email;
		this.courseOfChoice = courseOfChoice;
		this.percentageIn10th = percentageIn10th;
		this.percentageIn12th = percentageIn12th;
		this.locationPreferred = locationPreferred;
		this.feeCapability = feeCapability;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
	this.email = email;
	}

	public String getCourseOfChoice() {
		return courseOfChoice;
	}

	public void setCourseOfChoice(String courseOfChoice) {
		this.courseOfChoice = courseOfChoice;
	}

	public String getPercentageIn10th() {
		return percentageIn10th;
	}

	public void setPercentageIn10th(String percentageIn10th) {
		this.percentageIn10th = percentageIn10th;
	}

	public String getPercentageIn12th() {
		return percentageIn12th;
	}

	public void setPercentageIn12th(String percentageIn12th) {
		this.percentageIn12th = percentageIn12th;
	}

	public String getLocationPreferred() {
		return locationPreferred;
	}

	public void setLocationPreferred(String locationPreferred) {
		this.locationPreferred = locationPreferred;
	}

	public long getFeeCapability() {
		return feeCapability;
	}

	public void setFeeCapability(long feeCapability) {
		this.feeCapability = feeCapability;
	}

}
