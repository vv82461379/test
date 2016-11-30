package com.shop.service.imlp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.UsersMapping;
import com.shop.model.Users;
import com.shop.service.UserService;

@Service
@Transactional
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
	/**
	 * ֻ��Ҫ���������@Transactional�����൱�������������ܣ�����׳��쳣��������ع���
	 */
	public void save(Users users){
		usersMapping.save(users);
		System.out.println("transcation start......................");
//		throw new RuntimeException("transcation end...........");
	}

}
