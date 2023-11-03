package com.ssafy.enjoytrip.attraction.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.GugunDto;
import com.ssafy.enjoytrip.attraction.model.SearchConditionDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;

@RestController
@RequestMapping("/trip")
@CrossOrigin("*")
public class AttractionRestController {
	
	private AttractionService attractionService;

	public AttractionRestController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}
	
	@GetMapping("/{sido}/{gugun}/{contentType}")
	public ResponseEntity<?> getTrips(@PathVariable("sido") int sido, 
						 @PathVariable("gugun") int gugun, 
						 @PathVariable("contentType") int contentType) {
		
		List<AttractionDto> attractionList = attractionService.searchTrip(new SearchConditionDto(sido, gugun, contentType));
		
		if(!attractionList.isEmpty()) {
			return new ResponseEntity<List<AttractionDto>>(attractionList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{sido}")
	public ResponseEntity<?> getGuguns(@PathVariable("sido") int sido){
		List<GugunDto> gugunList = attractionService.searchGugun(sido);
		if(!gugunList.isEmpty()) {
			return new ResponseEntity<List<GugunDto>>(gugunList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
		}
	}
}
