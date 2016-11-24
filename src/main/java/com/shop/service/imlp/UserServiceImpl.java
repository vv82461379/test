package com.shop.service.imlp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.UsersMapping;
import com.shop.model.Users;
import com.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersMapping usersMapping;
	
	public Users queryById(Long id){
		try {
			Users users = usersMapping.queryById(id);
			 System.out.println("user:"+users);
			return users;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
	}
	public void print(){
		System.out.println("鍑芥暟杩愯涓�");
	}

}
