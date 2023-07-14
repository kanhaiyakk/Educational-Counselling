package com.boot.educationalCounselling.backend.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.boot.educationalCounselling.backend.entity.School;
import com.boot.educationalCounselling.backend.exceptionHandling.ResourceNotFoundException;
import com.boot.educationalCounselling.backend.repository.SchoolRepository;

@ExtendWith(MockitoExtension.class)
class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @InjectMocks
    private SchoolService schoolService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Captor
    private ArgumentCaptor<School> schoolCaptor;

    @BeforeEach
    void setUp() {
        // Setup any necessary mock data or stubbing
    }

    @Test
    void createSchool_ShouldReturnCreatedSchool() {
        // Arrange
        School school = new School();
        given(schoolRepository.save(any(School.class))).willReturn(school);

        // Act
        School createdSchool = schoolService.createSchool(school);

        // Assert
        assertEquals(school, createdSchool);
        verify(schoolRepository).save(schoolCaptor.capture());
        School capturedSchool = schoolCaptor.getValue();
        assertEquals(school, capturedSchool);
    }

    @Test
    void getAllSchools_ShouldReturnListOfSchools() {
        // Arrange
        List<School> schools = new ArrayList<>();
        given(schoolRepository.findAll()).willReturn(schools);

        // Act
        List<School> result = schoolService.getAllSchools();

        // Assert
        assertEquals(schools, result);
        verify(schoolRepository).findAll();
    }

    @Test
    void getSchoolById_WithValidId_ShouldReturnSchool() {
        // Arrange
        long schoolId = 1L;
        School school = new School();
        given(schoolRepository.findById(schoolId)).willReturn(Optional.of(school));

        // Act
        School result = schoolService.getSchoolById(schoolId);

        // Assert
        assertEquals(school, result);
        verify(schoolRepository).findById(idCaptor.capture());
        Long capturedId = idCaptor.getValue();
        assertEquals(schoolId, capturedId);
    }

    @Test
    void getSchoolById_WithInvalidId_ShouldThrowException() {
        // Arrange
        long schoolId = 1L;
        given(schoolRepository.findById(schoolId)).willReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            schoolService.getSchoolById(schoolId);
        });
        verify(schoolRepository).findById(idCaptor.capture());
        Long capturedId = idCaptor.getValue();
        assertEquals(schoolId, capturedId);
    }

    @Test
    void updateSchool_WithValidId_ShouldReturnUpdatedSchool() {
        // Arrange
        long schoolId = 1L;
        School existingSchool = new School();
        existingSchool.setClgId((int) schoolId);
        existingSchool.setClgName("School 1");
        School updatedSchool = new School();
        updatedSchool.setClgId((int)schoolId);
        updatedSchool.setClgName("Updated School 1");
        given(schoolRepository.findById(schoolId)).willReturn(Optional.of(existingSchool));
        given(schoolRepository.save(any(School.class))).willReturn(updatedSchool);

        // Act
        School result = schoolService.updateSchool(schoolId, updatedSchool);

        // Assert
        assertEquals(updatedSchool, result);
        verify(schoolRepository).findById(idCaptor.capture());
        verify(schoolRepository).save(schoolCaptor.capture());
        Long capturedId = idCaptor.getValue();
        School capturedSchool = schoolCaptor.getValue();
        assertEquals(schoolId, capturedId);
        assertEquals(updatedSchool, capturedSchool);
    }

    @Test
    void updateSchool_WithInvalidId_ShouldThrowException() {
        // Arrange
        long schoolId = 1L;
        School updatedSchool = new School();
        updatedSchool.setClgId((int) schoolId);
        updatedSchool.setClgName("Updated School 1");
        given(schoolRepository.findById(schoolId)).willReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            schoolService.updateSchool(schoolId, updatedSchool);
        });
        verify(schoolRepository).findById(idCaptor.capture());
        Long capturedId = idCaptor.getValue();
        assertEquals(schoolId, capturedId);
    }

    // Add more test methods for other scenarios

}
