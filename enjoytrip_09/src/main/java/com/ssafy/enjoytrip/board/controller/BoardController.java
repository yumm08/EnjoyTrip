package com.ssafy.enjoytrip.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.service.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private BoardService boardService;

	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@PostMapping("/regist")
	public ResponseEntity<String> write(@RequestBody BoardDto boardDto, HttpSession session) {
		logger.debug("write boardDto : {}", boardDto);

		try {
			boardService.writeArticle(boardDto);
			return new ResponseEntity<String>("success", HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value = "/{key}/{word}")
	public ResponseEntity<?> list(@PathVariable String key, @PathVariable String word) {
		List<BoardDto> boards;
		try {
			boards = boardService.searchList(key, word);
			if (boards.isEmpty()) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<List<BoardDto>>(boards, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping(value = "/view/{articleNo}")
	public ResponseEntity<?> view(@PathVariable int articleNo) {
		logger.debug("view articleNo : {}", articleNo);
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			if (boardDto == null) {
				logger.debug("view articleNo : {}", articleNo);
				return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
			}else {
				boardService.updateHit(articleNo);
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/{articleNo}")
	public ResponseEntity<String> update(BoardDto boardDto) {
		logger.debug("modify boardDto : {}", boardDto);
		try {
			boardService.modifyArticle(boardDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/{articleNo}")
	public ResponseEntity<String> delete(@PathVariable int articleNo) {
		logger.debug("delete articleNo : {}", articleNo);
		try {
			boardService.deleteArticle(articleNo);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
    	HttpHeaders resHeaders = new HttpHeaders();
    	resHeaders.add("Content-Type", "application/json;charset=UTF-8");
    	return new ResponseEntity<String>("errors : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
