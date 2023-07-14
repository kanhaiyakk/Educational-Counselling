package com.boot.educationalCounselling.backend.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.educationalCounselling.backend.entity.School;
import com.boot.educationalCounselling.backend.exceptionHandling.ResourceNotFoundException;
import com.boot.educationalCounselling.backend.repository.SchoolRepository;


@Service
public class SchoolService {
    
    private final SchoolRepository schoolRepository;
    
    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }
    
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }
    
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }
    
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("School", "id", id));
    }
    
    public School updateSchool(Long id, School schoolDetails) {
        School school = schoolRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("School", "id", id));
        school.setClgName(schoolDetails.getClgName());
        school.setClgLocation(schoolDetails.getClgLocation());
        school.setClgId(schoolDetails.getClgId());
        school.setCourse(schoolDetails.getCourse());
        school.setCutoff_10th(schoolDetails.getCutoff_10th());
        school.setCutoff_12th(schoolDetails.getCutoff_12th());
        school.setClgFees(schoolDetails.getClgFees());
        return schoolRepository.save(school);
    }

    public List<School> getSchoolDataByStudentFee(Long id) {
        return schoolRepository.findSchoolDataByStudentFee(id);
    }
    public List<School> getcourseByStudentcourse(Long id) {
        return schoolRepository.getcourseByStudentcourse(id);
    }
    public List<School> getSchoolDataByStudentdetails(Long id) {
        return schoolRepository.findSchoolDataByStudentdetails(id);
    }

	public void deleteSchool(School any) {
		// TODO Auto-generated method stub
		
	}
}
    
   
