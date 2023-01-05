package com.example.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	//@Query(name="select * from User where email=:email")
	//@Query(name="from User where email=:email")
	public User findByEmail(String email);
	
	public User findByEmailAndUserPwd(String email,String userPwd);

}
