package com.andersondev.treinarspringbootmongodb.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andersondev.treinarspringbootmongodb.domain.Post;
import com.andersondev.treinarspringbootmongodb.resources.util.URL;
import com.andersondev.treinarspringbootmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;

	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value="/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {
		
		text = URL.decodeParam(text);
		List<Post> list = service.findBySearchTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	
	@RequestMapping(value="/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue = "") String text,
			@RequestParam(value="dateMin", defaultValue = "") String dateMin,
			@RequestParam(value="dateMax", defaultValue = "") String dateMax) {
		
		text = URL.decodeParam(text);
		Date min = URL.convertDate(dateMin, new Date(0L));
		Date max = URL.convertDate(dateMax, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}


}
