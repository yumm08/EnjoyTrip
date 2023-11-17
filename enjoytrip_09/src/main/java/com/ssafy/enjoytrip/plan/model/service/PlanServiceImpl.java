package com.ssafy.enjoytrip.plan.model.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.PlanSeqDto;
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
		List<PlanSeqDto> planSeqs = planDto.getPlanSeqs();
		if (planSeqs != null && !planSeqs.isEmpty()) {
			planMapper.registPlanSeqs(planDto);
		}
	}

	@Override
	@Transactional
	public void modifyPlan(PlanDto planDto) throws Exception {
		planMapper.deletePlanSeqs(planDto.getPlanNo());
		planMapper.modifyPlan(planDto);
		List<PlanSeqDto> planSeqs = planDto.getPlanSeqs();
		if (planSeqs != null && !planSeqs.isEmpty()) {
			planMapper.registPlanSeqs(planDto);
		}
	}

	@Override
	@Transactional
	public void deletePlan(int planNo) throws Exception {
		planMapper.deletePlan(planNo);
		planMapper.deletePlanSeqs(planNo);
	}

	@Override
	public List<PlanDto> listPlan(String userId) throws Exception {
		return planMapper.listPlan(userId);
	}

	@Override
	public List<PlanDto> searchListByTitle(HashMap<String, String> map) throws Exception {
		return planMapper.searchListByTitle(map);
	}

	@Override
	public PlanDto getPlan(int planNo) throws Exception {
		return planMapper.getPlan(planNo);
	}

}
