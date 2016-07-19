package com.pj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pj.bean.User;

public interface UserService {
	User selectByUsername(String username);
	
	int insert(User record);
}
