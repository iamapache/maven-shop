package com.pj.service;

import com.pj.bean.User;

public interface UserService {
	User selectByUsername(String username);
	
	int insert(User record);
}
