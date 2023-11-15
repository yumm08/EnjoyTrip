package com.ssafy.enjoytrip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.FileInfoDto;

@Mapper
public interface BoardMapper {
	void writeArticle(BoardDto boardDto) throws SQLException;
	//int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;

	List<BoardDto> searchListByOther(Map<String, String> param);
	List<BoardDto> searchListBySubject(Map<String, String> param);
	List<BoardDto> searchListAll(Map<String, String> param);

//	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
	void registerFile(BoardDto boardDto) throws Exception;
//	void deleteFile(int articleNo) throws Exception;
}
