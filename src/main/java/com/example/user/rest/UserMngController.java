package com.example.user.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.binding.LoginForm;
import com.example.user.binding.UnlockAccForm;
import com.example.user.binding.UserForm;
import com.example.user.entity.User;
import com.example.user.service.UserManagementService;

@RestController
public class UserMngController {

	@Autowired
	private UserManagementService userManagementService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
		String status = userManagementService.login(loginForm);
		return new ResponseEntity<String>(status,HttpStatus.OK);
		
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<String> getEmail(@PathVariable("email") String email) {
		String status = userManagementService.checkEmail(email);
		return new ResponseEntity<>(status, HttpStatus.OK);

	}
	@GetMapping("/countries")
	public Map<Integer,String> loadCountries(){
		return userManagementService.getCountries();
		
		
	}
	@GetMapping("/state/{countryId}")
	public Map<Integer,String> loadStates(@PathVariable("countryId") Integer countryId ){
		return userManagementService.getStates(countryId);
		
	}
	
	@GetMapping("/city/{stateId}")
	public Map<Integer,String> loadCities(@PathVariable("stateId") Integer stateId){
		return userManagementService.getCities(stateId);
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> registerUser(@RequestBody UserForm user){
		
		String registerUser = userManagementService.registerUser(user);
		return new ResponseEntity<String>(registerUser,HttpStatus.CREATED);
		
	}
	@PostMapping("/unlock")
	public ResponseEntity<String> unlockAccount(@RequestBody UnlockAccForm unlockAccForm){
		String unlockAccount = userManagementService.unlockAccount(unlockAccForm);
		return new ResponseEntity<String>(unlockAccount,HttpStatus.OK);
	}
	
	@GetMapping("/forgotPwd/{email}")
	public ResponseEntity<String> forgotPwd(@PathVariable("email") String email){
		String status =userManagementService.forgotPwd(email);
		return new ResponseEntity<String>(status,HttpStatus.OK);
		
	}

}
