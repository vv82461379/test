package com.shop.service;


import com.shop.model.Users;

public interface UserService{
	

    public Users queryById(Long id);
    
    public void print();

    
}
