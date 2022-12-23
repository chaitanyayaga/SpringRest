package com.chaitanya.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	
	
	@Autowired
	private UserJPARepository userJPARepository;
	
	@Autowired
	private PostJPARepository postRepository;

	
	@GetMapping("jpa/users")
	public List<User> retrieveAllUsersJPA() {
		return userJPARepository.findAll();
	}

	@GetMapping("jpa/users/{id}")
	public EntityModel<User> retrieveUserJPA(@PathVariable int id) {
		Optional<User> user = userJPARepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+ id);
		
		
		EntityModel<User> model = EntityModel.of(user.get());
		
		WebMvcLinkBuilder linktoUser = linkTo(methodOn(this.getClass()).retrieveAllUsersJPA());
		model.add(linktoUser.withRel("all-users"));
		
		return model;
	}
	
	@DeleteMapping("jpa/users/{id}")
	public void deleteUserJPA(@PathVariable int id) {
		 userJPARepository.deleteById(id);
	}

	
	@PostMapping("jpa/users")
	public ResponseEntity<Object> createUserJPA(@Valid @RequestBody User user) {
		User savedUser = userJPARepository.save(user);
		// CREATED
		// /user/{id}     savedUser.getId()
		// it will return created location and Id in headers
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable int id) {
		Optional<User> userOptional = userJPARepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+ id);
		}
		return userOptional.get().getPosts();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> createUser(@PathVariable int id,
			@RequestBody Post post) {
		Optional<User> userOptional=   userJPARepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+ id);
		}
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}


}
