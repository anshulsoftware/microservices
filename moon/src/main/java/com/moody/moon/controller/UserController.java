package com.moody.moon.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.moody.moon.dao.UserDao;
import com.moody.moon.exception.UserNotFoundException;
import com.moody.moon.model.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;
	
	@GetMapping(path = "/users")
	public List<User> findUsers() {
		return userDao.findAll();
	}
	@GetMapping(path = "/user/{id}")
	public Resource<User> findWithId(@PathVariable int id) {
		User user=userDao.findWithId(id);
		if(user == null) {
			throw new UserNotFoundException("Id-"+id);
		}
		
		Resource<User> resource=new Resource<User>(user);
		ControllerLinkBuilder controller=linkTo(methodOn(this.getClass()).findUsers());
		resource.add(controller.withRel("users"));
		return resource;
	}
	@PostMapping(path = "/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		userDao.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteById(@PathVariable int id) {
		User user=userDao.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("Id-"+id);
		}
		System.out.println("Deleted Sucessfully!");
	}
}
