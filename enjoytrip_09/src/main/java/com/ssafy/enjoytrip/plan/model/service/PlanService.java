package com.ssafy.enjoytrip.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.plan.model.PlanDto;

public interface PlanService {
	void registPlan(PlanDto planDto) throws Exception;
	void modifyPlan(PlanDto planDto) throws Exception;
	void deletePlan(String planId) throws Exception;

	List<PlanDto> listPlan() throws Exception;
	List<PlanDto> searchListByTitle(String word) throws Exception;
	
	PlanDto getPlan(String planId) throws Exception;
}
