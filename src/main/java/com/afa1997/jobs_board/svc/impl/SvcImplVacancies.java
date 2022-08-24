package com.afa1997.jobs_board.svc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afa1997.jobs_board.model.Vacancies;
import com.afa1997.jobs_board.repositories.RpVacancies;
import com.afa1997.jobs_board.svc.SvcVacancies;

@Service
public class SvcImplVacancies implements SvcVacancies {
	@Autowired
	RpVacancies rv;

	@Override
	public List<Vacancies> findAll() {
		return rv.findAll();
	}

	@Override
	public Vacancies findById(int p_vid) {
		return rv.findById(p_vid).get();
	}

	@Override
	public Vacancies save(Vacancies p_vdata) {
		return rv.save(p_vdata);
	}

	@Override
	public List<Vacancies> findVacanciesByNameLike(String search_param) {
		return rv.findVacanciesByNameLike(search_param);
	}
	
	@Override
	public List<Vacancies> findVacanciesByPublisher(int company_id) {
		return rv.findVacanciesByPublisher(company_id);
	}

	@Override
	public List<Vacancies> findVacanciesByCategory(String job_cat_param) {
		return rv.findVacanciesByCategory(job_cat_param);
	}

}
