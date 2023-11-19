package com.ssafy.enjoytrip.planboard.model.service;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.mapper.PlanMapper;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.planboard.model.PlanReviewDto;
import com.ssafy.enjoytrip.planboard.model.mapper.PlanBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class PlanBoardServiceImpl implements PlanBoardService {

    private PlanBoardMapper planBoardMapper;
    private PlanMapper planMapper;

    public PlanBoardServiceImpl(PlanBoardMapper planBoardMapper, PlanMapper planMapper) {
        this.planBoardMapper = planBoardMapper;
        this.planMapper = planMapper;
    }

    @Override
    @Transactional
    public void writePlanArticle(PlanBoardDto planBoardDto) throws SQLException {
        planBoardMapper.writePlanArticle(planBoardDto);
        List<PlanReviewDto> planReviews = planBoardDto.getReviews();
        if (planReviews != null & !planReviews.isEmpty()) {
            planBoardMapper.writePlanReview(planBoardDto);
        }
    }

    @Override
    public PlanDto getPlan(int planNo) throws SQLException {
        return planMapper.getPlan(planNo);
    }

    @Override
    @Transactional
    public void modifyPlanArticle(PlanBoardDto planBoardDto) throws SQLException {
        planBoardMapper.modifyPlanArticle(planBoardDto);
        List<PlanReviewDto> planReviews = planBoardDto.getReviews();
        if (planReviews != null & !planReviews.isEmpty()) {
            planBoardMapper.modifyPlanReview(planBoardDto);
        }
    }

    @Override
    @Transactional
    public void deletePlanArticle(int articleNo) throws SQLException {
        planBoardMapper.deletePlanArticle(articleNo);
        planBoardMapper.deletePlanReview(articleNo);
    }

    @Override
    public List<PlanBoardDto> searchPlanAll() throws SQLException {
        return planBoardMapper.searchPlanAll();
    }

    @Override
    public List<PlanBoardDto> searchPlanByTitle(String word) throws SQLException {
        return planBoardMapper.searchPlanByTitle(word);
    }

    @Override
    public PlanBoardDto getPlanArticle(int articleNo) throws SQLException {
        return planBoardMapper.getPlanArticle(articleNo);
    }
}
