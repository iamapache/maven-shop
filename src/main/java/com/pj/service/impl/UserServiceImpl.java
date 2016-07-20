package com.pj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pj.bean.User;
import com.pj.bean.UserExample;
import com.pj.dao.UserMapper;
import com.pj.service.UserService;
import com.pj.util.UUIDUtils;


@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	public User selectByUsername(String username) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();   
		criteria.andUsernameEqualTo(username);    
		criteria.andNameIsNotNull(); 
		List<User> list = userMapper.selectByExample(userExample);
		if (list != null && list.size() != 0) {
			return list.get(0);
		} 
		return null;
	}

	public int insert(User user) {
		user.setState(0);
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		int i = userMapper.insert(user);
		return i;
	}

}
