package com.nataliegusso.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nataliegusso.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//Busca personalizada //https://docs.mongodb.com/manual/reference/operator/query/regex/
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")  //?0: primeiro parâmetro (só tem 1) //i:ignora maíuscula/minúscula
	List<Post> searchTitle(String text);  
	
	//https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference //Table: Supported keywords for query methods
	List<Post> findByTitleContainingIgnoreCase(String text);  //Busca título contendo... ignora maíuscula/minúscula

	//https://docs.mongodb.com/manual/reference/operator/query/or/   //https://docs.mongodb.com/manual/reference/operator/query/and/
	//https://docs.mongodb.com/manual/reference/operator/query/gte/   //https://docs.mongodb.com/manual/reference/operator/query/lte/
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	//gte=>, ?1: segundo parâmetro (minDate)  e  lte=<, ?2: terceiro parâmetro (maxDate)  ou   texto no título ou corpo do post ou comentários	

}