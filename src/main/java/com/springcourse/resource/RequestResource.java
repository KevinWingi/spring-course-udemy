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
import com.springcourse.service.RequestService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {
	@Autowired private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request) {
		Request createdRequest = requestService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody Request request) {
		request.setId(id);
		
		Request updatedRequest = requestService.update(request);
		return ResponseEntity.ok(updatedRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id) {
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
	}
	
	@GetMapping
	public ResponseEntity<List<Request>> listAll() {
		List<Request> requests = requestService.listAll();
		return ResponseEntity.ok(requests);
	}

}
