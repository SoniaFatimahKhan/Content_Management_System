package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponce;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	
	@PostMapping(value = "/users/register")
	public ResponseEntity<ResponseStructure<UserResponce>> userRegister(@RequestBody @Valid UserRequest userRequest) {
		return userService.userRegister(userRequest);
	}
	
	
	 @GetMapping("/test")
	 public String test() { 
		 return "Hello from cms"; 
		 }
	 
}

