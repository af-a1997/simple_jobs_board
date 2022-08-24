package com.afa1997.jobs_board.svc;

import java.util.List;

import com.afa1997.jobs_board.model.RegisteredUsers;

public interface SvcRegisteredUsers {
	List<RegisteredUsers> findAll();
	
	RegisteredUsers findById(int p_uid);
	RegisteredUsers save(RegisteredUsers p_udata);
}
