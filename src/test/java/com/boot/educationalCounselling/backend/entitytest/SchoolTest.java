package com.boot.educationalCounselling.backend.entitytest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.boot.educationalCounselling.backend.entity.School;

class SchoolTest {

    @Test
    void createSchool_ShouldSetProperties() {
        // Arrange
        int clgId = 1;
        String clgName = "School 1";
        String clgLocation = "Location 1";
        String course = "Course 1";
        Long clgFees = 1000L;
        String cutoff10th = "90%";
        String cutoff12th = "80%";

        // Act
        School school = new School(clgId, clgName, clgLocation, course, clgFees, cutoff10th, cutoff12th);

        // Assert
        Assertions.assertEquals(clgId, school.getClgId());
        Assertions.assertEquals(clgName, school.getClgName());
        Assertions.assertEquals(clgLocation, school.getClgLocation());
        Assertions.assertEquals(course, school.getCourse());
        Assertions.assertEquals(clgFees, school.getClgFees());
        Assertions.assertEquals(cutoff10th, school.getCutoff_10th());
        Assertions.assertEquals(cutoff12th, school.getCutoff_12th());
    }
}
