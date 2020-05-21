package com.nataliegusso.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nataliegusso.workshopmongo.domain.Post;
import com.nataliegusso.workshopmongo.domain.User;
import com.nataliegusso.workshopmongo.dto.AuthorDTO;
import com.nataliegusso.workshopmongo.repository.PostRepository;
import com.nataliegusso.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {  //ApplicationRunner e CommandLineRunner: permitem executar o código imediatamente após o início do aplicativo Spring Boot.

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postReposiroty;
	
	@Override
	public void run(String... args) throws Exception {  //Comando padrão qdo usa o CommandLineRunner

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));  //GMT: Londres
		
		userRepository.deleteAll();
		postReposiroty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); //instanciar objetos e salvar no BD
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 

		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		postReposiroty.saveAll(Arrays.asList(post1, post2));
	}  
}
