package com.boot.educationalCounselling.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "School")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clgId;
    private String clgName;
    private String clgLocation;
    private String course;
    public School(int clgId, String clgName, String clgLocation, String course, Long clgFees, String cutoff_10th,
			String cutoff_12th) {
		super();
		this.clgId = clgId;
		this.clgName = clgName;
		this.clgLocation = clgLocation;
		this.course = course;
		this.clgFees = clgFees;
		this.cutoff_10th = cutoff_10th;
		this.cutoff_12th = cutoff_12th;
	}
	public int getClgId() {
		return clgId;
	}
	public void setClgId(int clgId) {
		this.clgId = clgId;
	}
	public String getClgName() {
		return clgName;
	}
	public void setClgName(String clgName) {
		this.clgName = clgName;
	}
	public String getClgLocation() {
		return clgLocation;
	}
	public void setClgLocation(String clgLocation) {
		this.clgLocation = clgLocation;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Long getClgFees() {
		return clgFees;
	}
	public void setClgFees(Long clgFees) {
		this.clgFees = clgFees;
	}
	public String getCutoff_10th() {
		return cutoff_10th;
	}
	public void setCutoff_10th(String cutoff_10th) {
		this.cutoff_10th = cutoff_10th;
	}
	public String getCutoff_12th() {
		return cutoff_12th;
	}
	public void setCutoff_12th(String cutoff_12th) {
		this.cutoff_12th = cutoff_12th;
	}
	private Long clgFees;
	private String cutoff_10th;
	private String cutoff_12th;
	
	 public School() {
	 }
	 
	
}
