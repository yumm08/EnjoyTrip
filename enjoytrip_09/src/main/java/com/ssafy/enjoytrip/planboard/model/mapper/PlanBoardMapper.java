package com.ssafy.enjoytrip.planboard.model.mapper;

import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PlanBoardMapper {
    void writePlanArticle(PlanBoardDto planBoardDto) throws SQLException;
    void modifyPlanArticle(PlanBoardDto planBoardDto) throws SQLException;
    void deletePlanArticle(int articleNo) throws SQLException;

    List<PlanBoardDto> searchPlanAll() throws SQLException;
    List<PlanBoardDto> searchPlanByTitle(String word) throws SQLException;

    PlanBoardDto getPlanArticle(int articleNo) throws SQLException;
}
