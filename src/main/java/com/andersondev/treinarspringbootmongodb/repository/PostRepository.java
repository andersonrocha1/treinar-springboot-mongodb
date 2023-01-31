package com.andersondev.treinarspringbootmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.andersondev.treinarspringbootmongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> findBySearchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);

}
