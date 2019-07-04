package com.moody.moon.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.moody.moon.dao.PostRepository;
import com.moody.moon.dao.UserRepository;
import com.moody.moon.exception.UserNotFoundException;
import com.moody.moon.model.Post;
import com.moody.moon.model.User;

@RestController
public class JPAUserController {


	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	
	@GetMapping(path = "/jpa/users")
	public List<User> findUsers() {
		return userRepository.findAll();
	}
	@GetMapping(path = "/jpa/user/{id}")
	public Resource<User> findWithId(@PathVariable int id) {
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("Id-"+id);
		}
		
		Resource<User> resource=new Resource<User>(user.get());
		ControllerLinkBuilder controller=linkTo(methodOn(this.getClass()).findUsers());
		resource.add(controller.withRel("users"));
		return resource;
	}
	@PostMapping(path = "/jpa/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		userRepository.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/jpa/delete/{id}")
	public void deleteById(@PathVariable int id) {
		userRepository.deleteById(id);
		
	}
	
	
	@GetMapping(path = "/jpa/user/{id}/post")
	public List<Post> findPostUser(@PathVariable int id) {
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("Id :: "+id);
		}
		
		return user.get().getPost();	
	}
	
	
	@PostMapping(path = "/jpa/add/{id}/post")
	public ResponseEntity<User> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Id :: "+id);
		}
		User user =userOptional.get();
		
		post.setUser(user);
		postRepository.save(post);
		URI location=ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping(path = "/jpa/update/{id}/user")
	public void updateUser(@PathVariable int id, @RequestBody User user)
	{
		
	Optional<User>	userOptional =userRepository.findById(id);
	User u=userOptional.get();
	u.setDate(new Date());
	userRepository.save(u);
		
	}
}
