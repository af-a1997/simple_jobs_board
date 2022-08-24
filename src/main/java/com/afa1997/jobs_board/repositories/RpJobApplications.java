package com.afa1997.jobs_board.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afa1997.jobs_board.model.JobApplications;

public interface RpJobApplications extends JpaRepository<JobApplications, Integer> {

}
