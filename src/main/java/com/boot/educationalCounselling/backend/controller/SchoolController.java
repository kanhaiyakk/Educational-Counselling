package com.boot.educationalCounselling.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

import com.boot.educationalCounselling.backend.entity.School;
import com.boot.educationalCounselling.backend.service.SchoolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        School school = schoolService.getSchoolById(id);
        return ResponseEntity.ok(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/fee/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<School>> getSchoolDataByStudentFee(@PathVariable("id") Long id) {
        List<School> school = schoolService.getSchoolDataByStudentFee(id);
        if (school.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(school);
        }
    }
    @GetMapping("/course/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<School>> getcourseByStudentcourse(@PathVariable("id") Long id) {
        List<School> school = schoolService.getcourseByStudentcourse(id);
        if (school.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(school);
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<School> createSchool(@Valid @RequestBody School school) {
        School createdSchool = schoolService.createSchool(school);
        return ResponseEntity.created(URI.create("/schools/" + createdSchool.getClgId()))
                .body(createdSchool);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @Valid @RequestBody School school) {
        School updatedSchool = schoolService.updateSchool(id, school);
        return ResponseEntity.ok(updatedSchool);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSchool(@PathVariable School id) {
        schoolService.createSchool(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/details/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<School>> getSchoolDataByStudentdetails(@PathVariable("id") Long id) {
        List<School> school = schoolService.getSchoolDataByStudentdetails(id);
        if (school.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(school);
        }
    }
   
}
