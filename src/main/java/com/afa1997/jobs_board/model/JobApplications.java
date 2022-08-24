package com.afa1997.jobs_board.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "job_applications")
public class JobApplications {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int jaid;

	@NotNull
	int job_id;
	
	@NotNull
	int user_idno;
	
	String dt_apply;
	
	// Getters and setters

	public int getJaid() {
		return jaid;
	}

	public void setJaid(int jaid) {
		this.jaid = jaid;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public int getUser_idno() {
		return user_idno;
	}

	public void setUser_idno(int user_idno) {
		this.user_idno = user_idno;
	}

	public String getDt_apply() {
		return dt_apply;
	}

	public void setDt_apply(String dt_apply) {
		this.dt_apply = dt_apply;
	}
}
