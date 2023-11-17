package com.ssafy.enjoytrip.plan.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ssafy.enjoytrip.plan.model.PlanDto;

public interface PlanService {
	void registPlan(PlanDto planDto) throws Exception;
	void modifyPlan(PlanDto planDto) throws Exception;
	void deletePlan(int planNo) throws Exception;

	List<PlanDto> listPlan(String userId) throws Exception;
	List<PlanDto> searchListByTitle(HashMap<String, String> map) throws Exception;
	
	PlanDto getPlan(int planNo) throws Exception;
}
