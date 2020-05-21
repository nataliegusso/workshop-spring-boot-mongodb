package com.nataliegusso.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nataliegusso.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	//https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference //Table: Supported keywords for query methods
	List<Post> findByTitleContainingIgnoreCase(String text);  //Busca título contendo... ignora maíuscula/minúscula
	
}