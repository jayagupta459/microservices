 package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
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


@RestController
public class UserJpaResource {

	@Autowired
	private UserDAOService service;
	
	@Autowired
	private UserRespository userRepository; 
	
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		 return userRepository.findAll();
	}

	
	@GetMapping("/jpa/users/{id}")
	public Optional<User> retrieveById(@PathVariable int id){
		 Optional<User> user =  userRepository.findById(id);
		 
		
		if(!user.isPresent()) 
			 throw new UserNotFoundException("id-"+id);
		return user;
		
  }
	
	

   @DeleteMapping("/jpa/users/{id}")
	public ResponseEntity<Object>  deleteUser(@PathVariable int id){
		 userRepository.deleteById(id);
		 return ResponseEntity.ok("profile has been removed successfully");
  }		 

	



	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		 User savedUser= userRepository.save(user);
		 
		 URI location = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("{/id}")
				 .buildAndExpand(savedUser.getId()).toUri();
		 
		 return ResponseEntity.created(location).build();
	}
	

	@PutMapping("/jpa/users")
	public ResponseEntity<Object> UpdateUser(@Valid @RequestBody User user,@PathVariable int id){
		Optional<User> user1 =  userRepository.findById(id);
		if(!user1.isPresent())
			return ResponseEntity.notFound().build();
		
		user.setId(id);
	    userRepository.save(user);
		return ResponseEntity.notFound().build();
	}
	
}
