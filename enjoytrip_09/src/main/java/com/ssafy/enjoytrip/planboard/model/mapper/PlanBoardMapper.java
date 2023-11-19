package com.ssafy.enjoytrip.planboard.model.mapper;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.planboard.model.FileInfoDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlanBoardMapper {
    void writePlanArticle(PlanBoardDto planBoardDto) throws SQLException;
    void writePlanReview(PlanBoardDto planBoardDto) throws SQLException;
    void registerFile(PlanBoardDto planBoardDto) throws SQLException;
    void modifyPlanArticle(PlanBoardDto planBoardDto) throws SQLException;
    void modifyPlanReview(PlanBoardDto planBoardDto) throws SQLException;
    void deletePlanArticle(int articleNo) throws SQLException;
    void deletePlanReview(int articleNo) throws SQLException;
    void deleteFile(int articleNo) throws SQLException;

    List<PlanBoardDto> searchPlanAll() throws SQLException;
    List<PlanBoardDto> searchPlanByTitle(String word) throws SQLException;
    FileInfoDto getFile(int articleNo) throws SQLException;

    PlanBoardDto getPlanArticle(int articleNo) throws SQLException;
    PlanBoardDto getPlanReviews(int articleNo) throws SQLException;
}
