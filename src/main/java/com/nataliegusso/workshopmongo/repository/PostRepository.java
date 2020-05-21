package com.nataliegusso.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nataliegusso.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//Busca personalizada //https://docs.mongodb.com/manual/reference/operator/query/regex/
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")  //?0: 1 parâmetro  //i:ignora maíuscula/minúscula
	List<Post> searchTitle(String text);  
	
	//https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference //Table: Supported keywords for query methods
	List<Post> findByTitleContainingIgnoreCase(String text);  //Busca título contendo... ignora maíuscula/minúscula

}