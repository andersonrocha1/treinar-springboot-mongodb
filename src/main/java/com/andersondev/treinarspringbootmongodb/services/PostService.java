package com.andersondev.treinarspringbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersondev.treinarspringbootmongodb.domain.Post;
import com.andersondev.treinarspringbootmongodb.repository.PostRepository;
import com.andersondev.treinarspringbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;

	
	public Post findById(String id) {
		
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	
	public List<Post> findByTitle(String text){
		
		return repository.findByTitleContainingIgnoreCase(text);
		
	}

}
