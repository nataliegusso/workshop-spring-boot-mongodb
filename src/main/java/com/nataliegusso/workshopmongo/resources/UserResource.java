package com.nataliegusso.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nataliegusso.workshopmongo.domain.User;
import com.nataliegusso.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)  //ou @GetMapping
	public ResponseEntity<List<User>> findAll(){  //conserta erros http
		List<User> list = service.findAll();  //busca no BD
		return ResponseEntity.ok().body(list);
	}
}