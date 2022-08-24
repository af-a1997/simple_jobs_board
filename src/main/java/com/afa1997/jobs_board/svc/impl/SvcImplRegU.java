package com.afa1997.jobs_board.svc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afa1997.jobs_board.model.RegisteredUsers;
import com.afa1997.jobs_board.repositories.RpRegisteredUsers;
import com.afa1997.jobs_board.svc.SvcRegisteredUsers;

@Service
public class SvcImplRegU implements SvcRegisteredUsers {
	@Autowired
	RpRegisteredUsers rru;

	@Override
	public List<RegisteredUsers> findAll() {
		return rru.findAll();
	}

	@Override
	public RegisteredUsers findById(int p_uid) {
		return rru.findById(p_uid).get();
	}

	@Override
	public RegisteredUsers save(RegisteredUsers p_udata) {
		return rru.save(p_udata);
	}
}
