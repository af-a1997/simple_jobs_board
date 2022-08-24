package com.afa1997.jobs_board.svc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.afa1997.jobs_board.model.JobApplications;
import com.afa1997.jobs_board.repositories.RpJobApplications;
import com.afa1997.jobs_board.svc.SvcJobApplications;

public class SvcImplJA implements SvcJobApplications {
	@Autowired
	RpJobApplications rja;

	@Override
	public List<JobApplications> findAll() {
		return rja.findAll();
	}

	@Override
	public JobApplications findById(int param_jaid) {
		return rja.findById(param_jaid).get();
	}

	@Override
	public JobApplications save(JobApplications save_ja_data) {
		return rja.save(save_ja_data);
	}

}
