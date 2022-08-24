package com.afa1997.jobs_board.svc;

import java.util.List;

import com.afa1997.jobs_board.model.JobApplications;

public interface SvcJobApplications {
	List<JobApplications> findAll();
	
	JobApplications findById(int param_jaid);
	JobApplications save(JobApplications param_ja_data);
}
