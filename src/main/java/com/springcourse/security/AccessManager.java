package com.springcourse.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.RequestService;

@Component("accessManager")
public class AccessManager {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RequestService requestService;
	
	public boolean isOwner(Long id) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> result = userRepository.findByEmail(email);
		
		if(!result.isPresent()) throw new NotFoundException("There are not user with email = " + email);
		
		User user = result.get();
		
		return user.getId() == id;
	}
	
	public boolean isRequestOwner(Long id) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> result = userRepository.findByEmail(email);
		
		if(!result.isPresent()) throw new NotFoundException("There are not user with email = " + email);
		
		User user = result.get();
		
		Request request = requestService.getById(id);
		
		return user.getId() == request.getOwner().getId();
		
	}

}
