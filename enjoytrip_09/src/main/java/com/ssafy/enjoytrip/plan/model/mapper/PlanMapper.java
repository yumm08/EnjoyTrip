package com.ssafy.enjoytrip.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.enjoytrip.plan.model.PlanDto;

@Mapper
public interface PlanMapper {
	void registPlan(PlanDto planDto) throws SQLException;
	void registPlanSeqs(PlanDto planDto) throws SQLException;
	void modifyPlan(PlanDto planDto) throws SQLException;
	void deletePlan(String planId) throws SQLException;
	void deletePlanSeqs(String planId) throws SQLException;

	List<PlanDto> listPlan() throws SQLException;
	List<PlanDto> searchListByTitle(String word) throws SQLException;
	
	PlanDto getPlan(String planId) throws SQLException;
}
