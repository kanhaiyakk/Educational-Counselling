package com.boot.educationalCounselling.backend.controllertest;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.boot.educationalCounselling.backend.controller.SchoolController;
import com.boot.educationalCounselling.backend.entity.School;
import com.boot.educationalCounselling.backend.service.SchoolService;

@ExtendWith(MockitoExtension.class)
class SchoolControllerTest {

    @Mock
    private SchoolService schoolService;

    @InjectMocks
    private SchoolController schoolController;

    @Captor
    private ArgumentCaptor<School> schoolCaptor;

    private MockMvc mockMvc;

    @Test
    void getSchoolById_ShouldReturnSchool() throws Exception {
        // Arrange
        long schoolId = 1L;
        School school = new School();
        school.setClgId((int) schoolId);
        school.setClgName("School 1");
        given(schoolService.getSchoolById(schoolId)).willReturn(school);

        // Act & Assert
        mockMvc.perform(get("/schools/{id}", schoolId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clgId", is((int) schoolId)))
                .andExpect(jsonPath("$.clgName", is("School 1")));
    }

    @Test
    void getAllSchools_ShouldReturnListOfSchools() throws Exception {
        // Arrange
        School school1 = new School();
        school1.setClgId(1);
        school1.setClgName("School 1");
        School school2 = new School();
        school2.setClgId(2);
        school2.setClgName("School 2");
        List<School> schools = Arrays.asList(school1, school2);
        given(schoolService.getAllSchools()).willReturn(schools);

        // Act & Assert
        mockMvc.perform(get("/schools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].clgId", is(1)))
                .andExpect(jsonPath("$[0].clgName", is("School 1")))
                .andExpect(jsonPath("$[1].clgId", is(2)))
                .andExpect(jsonPath("$[1].clgName", is("School 2")));
    }

    @Test
    void createSchool_ShouldReturnCreatedSchool() throws Exception {
        // Arrange
        School school = new School();
        school.setClgId(1);
        school.setClgName("School 1");
        given(schoolService.createSchool(any(School.class))).willReturn(school);

        // Act & Assert
        mockMvc.perform(post("/schools")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clgId\": 1, \"clgName\": \"School 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/schools/1"))
                .andExpect(jsonPath("$.clgId", is(1)))
                .andExpect(jsonPath("$.clgName", is("School 1")));
    }

    @Test
    void updateSchool_ShouldReturnUpdatedSchool() throws Exception {
        // Arrange
        long schoolId = 1L;
        School school = new School();
        school.setClgId((int) schoolId);
        school.setClgName("School 1");
        given(schoolService.updateSchool(schoolId, school)).willReturn(school);

        // Act & Assert
        mockMvc.perform(put("/schools/{id}", schoolId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clgId\": 1, \"clgName\": \"School 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clgId", is((int) schoolId)))
                .andExpect(jsonPath("$.clgName", is("School 1")));
    }

    @Test
    void deleteSchool_ShouldReturnNoContent() throws Exception {
        // Arrange
        long schoolId = 1L;
        doNothing().when(schoolService).deleteSchool(any(School.class));

        // Act & Assert
        mockMvc.perform(delete("/schools/{id}", schoolId))
                .andExpect(status().isNoContent());

        verify(schoolService).deleteSchool(schoolCaptor.capture());
        School capturedSchool = schoolCaptor.getValue();
        assertThat(capturedSchool.getClgId(), is((int) schoolId));
    }

    private void assertThat(int clgId, Matcher<Integer> matcher) {
		// TODO Auto-generated method stub
		
	}

	@BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(schoolController).build();
    }
}
