package com.springcourse.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.RequestStage;
import com.springcourse.dto.RequestStageSavedto;
import com.springcourse.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stages")
public class RequestStageResource {
	@Autowired private RequestStageService stageService;

	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSavedto requestStagedto) {
		RequestStage requestStage = requestStagedto.transformToRequestStage();
		RequestStage createdRequestStage = stageService.save(requestStage);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id) {
		RequestStage stage = stageService.getById(id);
		return ResponseEntity.ok(stage);
	}
	
}
