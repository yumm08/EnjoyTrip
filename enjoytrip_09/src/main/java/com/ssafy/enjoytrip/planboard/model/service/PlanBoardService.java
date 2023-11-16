package com.ssafy.enjoytrip.planboard.model.service;

import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PlanBoardService {
    void writePlanArticle(PlanBoardDto planBoardDto) throws SQLException;
    void modifyPlanArticle(PlanBoardDto planBoardDto) throws SQLException;
    void deletePlanArticle(int articleNo) throws SQLException;

    List<PlanBoardDto> searchPlanAll() throws SQLException;
    List<PlanBoardDto> searchPlanByTitle(String word) throws SQLException;

    PlanBoardDto getPlanArticle(int articleNo) throws SQLException;
}
