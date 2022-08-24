package com.afa1997.jobs_board.svc;

import java.util.List;

import com.afa1997.jobs_board.model.Company;

public interface SvcCompany {
	List<Company> findAll();
	
	Company findById(int p_cid);
	Company save(Company p_cdata);
}
