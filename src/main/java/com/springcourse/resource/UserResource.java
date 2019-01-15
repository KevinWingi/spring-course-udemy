package com.springcourse.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLogindto;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	@Autowired private UserService userService;
	@Autowired private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user) {
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAll() {
		List<User> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLogindto user) {
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsById(@PathVariable(name = "id") Long id) {
		List<Request> requests = requestService.listAllByOwnerId(id);
		return ResponseEntity.ok(requests);
	}
}
