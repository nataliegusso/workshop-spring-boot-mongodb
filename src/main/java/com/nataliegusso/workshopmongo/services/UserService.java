package com.nataliegusso.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nataliegusso.workshopmongo.domain.User;
import com.nataliegusso.workshopmongo.dto.UserDTO;
import com.nataliegusso.workshopmongo.repository.UserRepository;
import com.nataliegusso.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {  //O que o usuário digitou na tela
		User newObj = findById(obj.getId()); //pega no BD
		updateData(newObj, obj);  //atualiza (ver abaixo)
		return repo.save(newObj);  //salva o digitado no BD
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserDTO objDto) {  //Pega um dto e instancia um usuário user  //Poderia ser feito na classe UserDTO, mas aqui já tem dependência no BD, o que facilita
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}