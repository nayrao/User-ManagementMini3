package com.example.user.service;

import java.util.Map;

import com.example.user.binding.LoginForm;
import com.example.user.binding.UnlockAccForm;
import com.example.user.binding.UserForm;
import com.example.user.entity.User;

public interface UserManagementService {
	
	public String checkEmail(String email);
	
	public Map<Integer,String> getCountries();
	
	public Map<Integer,String> getStates(Integer countryId);
	
	public Map<Integer,String> getCities(Integer stateId);
	
	public String registerUser(UserForm userForm);
	//it used to unlock account and change the password
	public String unlockAccount(UnlockAccForm unlockAccForm);
	
	public String login(LoginForm loginForm);

	public String forgotPwd(String email);



}
