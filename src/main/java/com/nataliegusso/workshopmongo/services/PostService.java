package com.nataliegusso.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nataliegusso.workshopmongo.domain.Post;
import com.nataliegusso.workshopmongo.repository.PostRepository;
import com.nataliegusso.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}	

	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {  //Método de consulta
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);  //o Date pega às 0h do dia, mas quero todos os posts do dia até às 24h, logo, tenho que acrescentar 1 dia
		return repo.fullSearch(text, minDate, maxDate);
	}
}