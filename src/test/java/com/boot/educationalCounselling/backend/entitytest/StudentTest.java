package com.boot.educationalCounselling.backend.entitytest;

import com.boot.educationalCounselling.backend.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class StudentTest {

    @Test
    public void testStudentEntity() {
        // Create a sample Student object
        Student student = new Student();
        student.setId(1L);
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

        // Test getters
        Assertions.assertEquals(1L, student.getId());
        Assertions.assertEquals("John", student.getFirstName());
        Assertions.assertEquals("Doe", student.getLastName());
        Assertions.assertEquals("1234567890", student.getPhoneNo());
        Assertions.assertNotNull(student.getDateOfBirth());
        Assertions.assertEquals("john.doe@example.com", student.getEmail());
        Assertions.assertEquals("Engineering", student.getCourseOfChoice());
        Assertions.assertEquals("95.5", student.getPercentageIn10th());
        Assertions.assertEquals("92.0", student.getPercentageIn12th());
        Assertions.assertEquals("City", student.getLocationPreferred());
        Assertions.assertEquals(1000000L, student.getFeeCapability());

        // Test setters
        student.setId(2L);
        student.setFirstName("Jane");
        student.setLastName("Smith");
        student.setPhoneNo("9876543210");
        student.setDateOfBirth(new Date());
        student.setEmail("jane.smith@example.com");
        student.setCourseOfChoice("Medicine");
        student.setPercentageIn10th("90.0");
        student.setPercentageIn12th("88.5");
        student.setLocationPreferred("Town");
        student.setFeeCapability(800000L);

        Assertions.assertEquals(2L, student.getId());
        Assertions.assertEquals("Jane", student.getFirstName());
        Assertions.assertEquals("Smith", student.getLastName());
        Assertions.assertEquals("9876543210", student.getPhoneNo());
        Assertions.assertNotNull(student.getDateOfBirth());
        Assertions.assertEquals("jane.smith@example.com", student.getEmail());
        Assertions.assertEquals("Medicine", student.getCourseOfChoice());
        Assertions.assertEquals("90.0", student.getPercentageIn10th());
        Assertions.assertEquals("88.5", student.getPercentageIn12th());
        Assertions.assertEquals("Town", student.getLocationPreferred());
        Assertions.assertEquals(800000L, student.getFeeCapability());
    }

}
