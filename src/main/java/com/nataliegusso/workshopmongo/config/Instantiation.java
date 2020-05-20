package com.nataliegusso.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nataliegusso.workshopmongo.domain.User;
import com.nataliegusso.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {  //ApplicationRunner e CommandLineRunner: permitem executar o código imediatamente após o início do aplicativo Spring Boot.

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {  //Comando padrão qdo usa o CommandLineRunner

		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); //instanciar objetos e salvar no BD
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}  
}
