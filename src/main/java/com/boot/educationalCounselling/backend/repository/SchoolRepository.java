package com.boot.educationalCounselling.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boot.educationalCounselling.backend.entity.School;




@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
	
	@Query("SELECT s FROM School s WHERE s.clgFees <= (SELECT feeCapability FROM Student WHERE id = :id)")
    List<School> findSchoolDataByStudentFee(@Param("id") Long id);
	@Query("SELECT s FROM School s WHERE s.course = (SELECT courseOfChoice FROM Student WHERE id = :id)")
	List<School> getcourseByStudentcourse(Long id);
	
	@Query("SELECT s FROM School s WHERE s.cutoff_10th <= (SELECT st.percentageIn10th FROM Student st WHERE st.id = :id) AND s.cutoff_12th <= (SELECT st.percentageIn12th FROM Student st WHERE st.id = :id) AND s.clgFees <= (SELECT st.feeCapability FROM Student st WHERE st.id = :id)")

    List<School> findSchoolDataByStudentdetails(Long id);

}