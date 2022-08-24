package com.afa1997.jobs_board.svc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afa1997.jobs_board.model.Company;
import com.afa1997.jobs_board.repositories.RpCompany;
import com.afa1997.jobs_board.svc.SvcCompany;

@Service
public class SvcImplCompany implements SvcCompany{
	@Autowired
	RpCompany rc;

	@Override
	public List<Company> findAll() {
		return rc.findAll();
	}

	@Override
	public Company findById(int p_cid) {
		return rc.findById(p_cid).get();
	}

	@Override
	public Company save(Company p_cdata) {
		return rc.save(p_cdata);
	}

}
