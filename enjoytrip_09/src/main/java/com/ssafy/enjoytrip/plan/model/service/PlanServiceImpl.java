package com.ssafy.enjoytrip.plan.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.mapper.PlanMapper;

@Service
public class PlanServiceImpl implements PlanService {
	
	private PlanMapper planMapper;

	public PlanServiceImpl(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}

	@Override
	@Transactional
	public void registPlan(PlanDto planDto) throws Exception {
		planMapper.registPlan(planDto);
	}

	@Override
	@Transactional
	public void modifyPlan(PlanDto planDto) throws Exception {
		planMapper.modifyPlan(planDto);
	}

	@Override
	@Transactional
	public void deletePlan(String planId) throws Exception {
		planMapper.deletePlan(planId);
	}

	@Override
	public List<PlanDto> listPlan() throws Exception {
		return planMapper.listPlan();
	}

	@Override
	public List<PlanDto> searchListByTitle(String word) throws Exception {
		return planMapper.searchListByTitle(word);
	}

	@Override
	public PlanDto getPlan(String planId) throws Exception {
		return planMapper.getPlan(planId);
	}

}
