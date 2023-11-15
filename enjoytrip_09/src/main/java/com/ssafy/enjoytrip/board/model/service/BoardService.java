package com.ssafy.enjoytrip.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardDto;


public interface BoardService {

	void writeArticle(BoardDto boardDto) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
    List<BoardDto> searchList(String key, String word) throws Exception;
}
