package com.nataliegusso.workshopmongo.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nataliegusso.workshopmongo.domain.User;
import com.nataliegusso.workshopmongo.dto.UserDTO;
import com.nataliegusso.workshopmongo.services.UserService;
import com.nataliegusso.workshopmongo.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)  //ou @GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {  //ResponseEntity: conserta erros http  //Vou buscar a lista do UserDTO e não do User
		List<User> list = service.findAll();  //busca no BD
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());  //Converter a lista que busquei no BD em uma lista DTO usando lambda
		//stream: coleção compativel com lambda //map: pega o obj x e retorna um new UserDTO //collection: transforma o stream em lista novamente
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);  
		return ResponseEntity.ok().body(new UserDTO(obj));  //500 - 404
	}
}