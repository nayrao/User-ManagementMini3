package com.example.user.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.binding.LoginForm;
import com.example.user.binding.UnlockAccForm;
import com.example.user.binding.UserForm;
import com.example.user.entity.Cities;
import com.example.user.entity.Country;
import com.example.user.entity.States;
import com.example.user.entity.User;
import com.example.user.repo.CityRepository;
import com.example.user.repo.CountryRepository;
import com.example.user.repo.StatesRepository;
import com.example.user.repo.UserRepository;
import com.example.user.util.EmailUtils;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StatesRepository statesRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String checkEmail(String email) {
		User findByEmail = userRepository.findByEmail(email);

		return (findByEmail == null) ? "Unique email" : "Email is AlreadyExisting";

	}

	@Override
	public Map<Integer, String> getCountries() {
		List<Country> countryList = countryRepository.findAll();
		return countryList.stream().collect(Collectors.toMap(Country::getCountryID, Country::getCountryName));
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<States> listOfStates = statesRepository.findByCountryId(countryId);
		return listOfStates.stream().collect(Collectors.toMap(States::getStateId, States::getStateName));
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<Cities> listOfCities = cityRepository.findByStateId(stateId);
		return listOfCities.stream().collect(Collectors.toMap(Cities::getCityId, Cities::getCityName));
	}

	@Override
	public String registerUser(UserForm userForm) {

		User entity = new User();

		// copy userform details into entity object
		BeanUtils.copyProperties(userForm, entity);
		// while creating the user status should be locked
		entity.setAccStatus("LOCKED");

		// Generate and set Random Password
		entity.setUserPwd(generateRandomPassword());

		userRepository.save(entity);

		// send email to unlock account
		String to = userForm.getEmail();
		String subject = "Registration Application";
		String body = readEmailBody("REG_EMAIL_BODY.txt", entity);
		emailUtils.sendHtmlEmail(to, subject, body);
		return "User Account is Created";

	}

	@Override
	public String unlockAccount(UnlockAccForm unlockAccForm) {
		String email = unlockAccForm.getEmail();
		User account = userRepository.findByEmail(email);
		// to check user unlock password and existing password is same or not
		if (account.getUserPwd().equals(unlockAccForm.getTempPwd())) {
			account.setUserPwd(unlockAccForm.getTempPwd());
			account.setAccStatus("UNLOCKED");
			userRepository.save(account);
			return "unlock -success";
		} else {
			return "unlock error";
		}
	}

	@Override
	public String login(LoginForm loginForm) {

		User findByEmailAndUserPwd = userRepository.findByEmailAndUserPwd(loginForm.getEmail(),
				loginForm.getPassword());

		if (findByEmailAndUserPwd == null) {
			return "Credentials are Invalid";
		}
		if (findByEmailAndUserPwd.getAccStatus().equals("LOCKED")) {
			return "Your Account Is Locked";
		}
		return "Login Success";
	}

	@Override
	public String forgotPwd(String email) {
		User findByEmail = userRepository.findByEmail(email);
		if (findByEmail == null) {
			return "No Account found with this ::" + email;
		}

		String subject = "Recover Password";
		String body = readEmailBody("FORGOT_PWD_EMAIL_BODY.txt", findByEmail);
		emailUtils.sendHtmlEmail(email, subject, body);
		return "Password sent to registred email:";
		// TODO Auto-generated method stub

	}

	private String generateRandomPassword() {
		String captialLeatters = "ABCDEFGHIJKLMNOPQRSTVXYZ";
		String smallLetters = "abcdefghijklmnopqrstuvxyz";
		String number = "0123456789";
		

		String combination = captialLeatters + smallLetters + number;
		int length = 6;
		char[] password = new char[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			password[i] = combination.charAt(random.nextInt(combination.length()));
		}

		return new String(password);

	}

	private String readEmailBody(String fileName, User user) {
		StringBuffer buffer = new StringBuffer();
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {

			lines.forEach(line -> {
				line = line.replace("${FNAME}", user.getFname());
				line = line.replace("${LNAME}", user.getLname());
				line = line.replace("${TEMP_PWD}", user.getUserPwd());
				line = line.replace("${EMAIL}", user.getEmail());
				line = line.replace("${PWD}", user.getUserPwd());
				buffer.append(line);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
