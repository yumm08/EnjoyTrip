package com.ssafy.enjoytrip.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.PlanSeqDto;

@Mapper
public interface PlanMapper {
	void registPlan(PlanDto planDto) throws SQLException;
	void registPlanSeqs(PlanDto planDto) throws SQLException;
	void modifyPlan(PlanDto planDto) throws SQLException;
	void deletePlan(int planNo) throws SQLException;
	void deletePlanSeqs(int planNo) throws SQLException;

	List<PlanDto> listPlan() throws SQLException;
	List<PlanDto> searchListByTitle(String word) throws SQLException;
	
	PlanDto getPlan(int planNo) throws SQLException;
	List<PlanSeqDto> getPlanSeqs(int planNo) throws SQLException;
}
