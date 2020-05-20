package com.nataliegusso.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nataliegusso.workshopmongo.domain.User;
import com.nataliegusso.workshopmongo.dto.UserDTO;
import com.nataliegusso.workshopmongo.services.UserService;

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
	
	@RequestMapping(method=RequestMethod.POST)  //ou @PostMapping
 	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {  //@RequestBody p aceitar o objeto UserDTO
		User obj = service.fromDTO(objDto);  //Converte o UserDto p User
		obj = service.insert(obj);  //inseriu no BD
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();  //Apesar da resposta ser um void, é uma boa prática retornar um cabeçalho com a URL do código
		return ResponseEntity.created(uri).build();  //created retorna o cód 201 que é o cód de resposta http qdo cria um novo recurso
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();  //noContent: cód 204
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}