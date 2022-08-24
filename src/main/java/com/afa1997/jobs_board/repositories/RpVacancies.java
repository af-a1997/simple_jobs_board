package com.afa1997.jobs_board.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afa1997.jobs_board.model.Vacancies;

public interface RpVacancies extends JpaRepository<Vacancies, Integer>{
	List<Vacancies> findVacanciesByNameLike(String search_param);
	List<Vacancies> findVacanciesByPublisher(int company_id);
	List<Vacancies> findVacanciesByCategory(String job_cat_param);
}
