package com.example.cms.serviceimpl;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponce;
import com.example.cms.exceptions.SQLDataIntegrityViolationException;
import com.example.cms.exceptions.UserAlreadyExistByEmailException;
import com.example.cms.model.User;
import com.example.cms.repository.UserRepo;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class UserSeviceImpl implements UserService {

	private UserRepo repository;
	private ResponseStructure<UserResponce> structure;
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponce>> userRegister(UserRequest userRequest){
		if(repository.existsByEmail(userRequest.getEmail())){
			throw new UserAlreadyExistByEmailException("failed to load user..");
		}
		User userObject = repository.save(mapToUserEntity(userRequest, new User()));
		return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
				.setMessage("Data Sucsessfully Saved")
				.setData(mapToUserResponce(userObject)));
	}

private UserResponce mapToUserResponce(User user) {
		
		return UserResponce.builder().userId(user.getUserId()).userName(user.getUserName()).email(user.getEmail())
				.lastModifiedAt(user.getLastModifiedAt())
				.createdAt(user.getCreatedAt())
				.build();
	}
	
	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setUserName(userRequest.getUserName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword())); //Encoded Password is been injected
		return user;
	}
}
