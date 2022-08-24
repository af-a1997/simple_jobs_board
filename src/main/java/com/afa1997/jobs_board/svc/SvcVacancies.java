package com.afa1997.jobs_board.svc;

import java.util.List;

import com.afa1997.jobs_board.model.Vacancies;

public interface SvcVacancies {
	List<Vacancies> findAll();
	List<Vacancies> findVacanciesByNameLike(String search_param);
	List<Vacancies> findVacanciesByPublisher(int company_id);
	List<Vacancies> findVacanciesByCategory(String job_cat_param);
	
	Vacancies findById(int p_vid);
	Vacancies save(Vacancies p_vdata);
}
