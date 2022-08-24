package com.afa1997.jobs_board.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "vacancies")
public class Vacancies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vid;
	
	// Foreign key to [company.cid]
	@NotNull
	private int publisher;
	
	@NotBlank
	private String name;
	
	@Lob
	@NotBlank
	private String job_description;
	
	@NotNull
	private int recruitment_slots;
	
	@NotNull
	private String dt_recruitment_begin;
	
	@NotNull
	private String dt_recruitment_end;
	
	@NotNull
	private String category;
	
	// Pointer to file (should be PDF/DOC/DOCX/ODT/TXT) describing the job in detail, requirements, and all the other information covered in job vacancy terms.
	private String terms_documentation;
	
	private double salary = -1;
	
	// To determine if more users can apply or not and let the company enable/disable applications. | 0 = NO, 1 = YES.
	@NotNull
	@Column(columnDefinition = "TINYINT")
	private int vacancy_status;
	
	// Getters and setters

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public int getPublisher() {
		return publisher;
	}

	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}

	public String getName() {
		return name;
	}

	public void setName(String job_name) {
		this.name = job_name;
	}

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}

	public int getRecruitment_slots() {
		return recruitment_slots;
	}

	public void setRecruitment_slots(int recruitment_slots) {
		this.recruitment_slots = recruitment_slots;
	}

	public String getDt_recruitment_begin() {
		return dt_recruitment_begin;
	}

	public void setDt_recruitment_begin(String dt_recruitment_begin) {
		this.dt_recruitment_begin = dt_recruitment_begin;
	}

	public String getDt_recruitment_end() {
		return dt_recruitment_end;
	}

	public void setDt_recruitment_end(String dt_recruitment_end) {
		this.dt_recruitment_end = dt_recruitment_end;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTerms_documentation() {
		return terms_documentation;
	}

	public void setTerms_documentation(String terms_documentation) {
		this.terms_documentation = terms_documentation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getVacancy_status() {
		return vacancy_status;
	}

	public void setVacancy_status(int vacancy_status) {
		this.vacancy_status = vacancy_status;
	}
}
