package com.postr.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository repository;
	
	@Transactional
	@Override
	public UserModel registerNewUserAccount(UserDto accountDto) {
		UserModel user = new UserModel();
		user.setFirstName(accountDto.getFirstName());
		user.setLastName(accountDto.getLastName());
		user.setUserName(accountDto.getUserName());
		user.setPassword(accountDto.getPassword());
		return repository.save(user);
		
	}
}
