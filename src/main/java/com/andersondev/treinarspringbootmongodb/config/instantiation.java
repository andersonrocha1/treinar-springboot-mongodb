package com.andersondev.treinarspringbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andersondev.treinarspringbootmongodb.domain.Post;
import com.andersondev.treinarspringbootmongodb.domain.User;
import com.andersondev.treinarspringbootmongodb.dto.AuthorDTO;
import com.andersondev.treinarspringbootmongodb.repository.PostRepository;
import com.andersondev.treinarspringbootmongodb.repository.UserRepository;

@Configuration
public class instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("30/01/2023"), "Chovendo aqui", "Muita chuva em São Paulo", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("30/01/2023"), "Vascão Hoje", "Vasco joga hoje em Cariacica", new AuthorDTO(maria));
		Post post3 = new Post(null, sdf.parse("30/01/2023"), "Flamengo Cheirinho", "Palmeiras detonou o flamengo", new AuthorDTO(bob));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		bob.getPosts().addAll(Arrays.asList(post3));
		userRepository.saveAll(Arrays.asList(maria, bob));
	}

}
