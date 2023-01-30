package com.andersondev.treinarspringbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersondev.treinarspringbootmongodb.domain.User;
import com.andersondev.treinarspringbootmongodb.dto.UserDTO;
import com.andersondev.treinarspringbootmongodb.repository.UserRepository;
import com.andersondev.treinarspringbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		
		return repository.findAll();
		
	}
	
	public User findById(String id) {
		
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	
	public User insert(User obj) {
		
		return repository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
		
	}
}
