package com.ssafy.enjoytrip.plan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.service.PlanService;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
public class PlanRestController {
	private final Logger logger = LoggerFactory.getLogger(PlanRestController.class);

	private PlanService planService;

	public PlanRestController(PlanService planService) {
		super();
		this.planService = planService;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<String> write(@RequestBody PlanDto planDto) {
		logger.debug("write planDto : {}", planDto);
		
		try {
			planService.registPlan(planDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/{planId}")
	public ResponseEntity<String> update(@RequestBody PlanDto planDto) {
		logger.debug("modify planDto : {}", planDto);
		
		try {
			planService.modifyPlan(planDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/{planId}")
	public ResponseEntity<String> delete(@PathVariable String planId) {
		logger.debug("delete planId : {}", planId);
		
		try {
			planService.deletePlan(planId);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@GetMapping("/list/{word}")
	public ResponseEntity<?> listByTitle(@PathVariable String word) {
		logger.debug("search by title : {}", word);
		
		try {
			List<PlanDto> plans = planService.searchListByTitle(word);
			if (plans.isEmpty()) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<List<PlanDto>>(plans, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> list() {
		logger.debug("list all plans");
		
		try {
			List<PlanDto> plans = planService.listPlan();
			if (plans.isEmpty()) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<List<PlanDto>>(plans, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/view/{planId}")
	public ResponseEntity<?> view(@PathVariable String planId) {
		logger.debug("view plan : {}", planId);
		
		try {
			PlanDto plan = planService.getPlan(planId);
			if (plan == null) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<PlanDto>(plan, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
    	HttpHeaders resHeaders = new HttpHeaders();
    	resHeaders.add("Content-Type", "application/json;charset=UTF-8");
    	return new ResponseEntity<String>("errors : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
