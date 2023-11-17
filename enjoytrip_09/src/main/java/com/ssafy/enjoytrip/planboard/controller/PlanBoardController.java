package com.ssafy.enjoytrip.planboard.controller;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.planboard.model.service.PlanBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/planboard")
@CrossOrigin("*")
public class PlanBoardController {
    private final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);

    private PlanBoardService planBoardService;

    public PlanBoardController(PlanBoardService planBoardService) {
        super();
        this.planBoardService = planBoardService;
    }

    @PostMapping("/regist")
    public ResponseEntity<String> write(@RequestBody PlanBoardDto planBoardDto) {
        logger.debug("write planArticle : {}", planBoardDto);

        try {
            planBoardService.writePlanArticle(planBoardDto);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (SQLException e) {
            return exceptionHandling(e);
        }
    }

    //update
    @GetMapping("/modify/{articleNo}")
    public ResponseEntity<?> getUpdateArticle(@PathVariable int articleNo) {
        logger.debug("get plan ArticleNo : {}", articleNo);

        try {
            PlanDto plan = planBoardService.getPlan(articleNo);
            return new ResponseEntity<PlanDto>(plan, HttpStatus.OK);
        } catch (SQLException e) {
            return exceptionHandling(e);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody PlanBoardDto planBoardDto) {
        logger.debug("update planArticle : {}", planBoardDto);

        try {
            planBoardService.modifyPlanArticle(planBoardDto);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (SQLException e) {
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("{articleNo}")
    public ResponseEntity<String> delete(@PathVariable int articleNo){
        logger.debug("delete plan ArticleNo : {}", articleNo);

        try {
            planBoardService.deletePlanArticle(articleNo);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (SQLException e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        logger.debug("list all plan article");

        try {
            List<PlanBoardDto> planArticles = planBoardService.searchPlanAll();
            if (planArticles.isEmpty()) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
            else return new ResponseEntity<List<PlanBoardDto>>(planArticles, HttpStatus.OK);
        } catch (SQLException e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/list/{word}")
    public ResponseEntity<?> listByTitle(@PathVariable String word) {
        logger.debug("search by title : {}", word);

        try {
            List<PlanBoardDto> planArticles = planBoardService.searchPlanByTitle(word);
            if (planArticles.isEmpty()) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
            else return new ResponseEntity<List<PlanBoardDto>>(planArticles, HttpStatus.OK);
        } catch (SQLException e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/view/{articleNo}")
    public ResponseEntity<?> view(@PathVariable int articleNo) {
        logger.debug("view plan Article : {}", articleNo);

        try {
            PlanBoardDto planArticle = planBoardService.getPlanArticle(articleNo);
            if (planArticle == null) return new ResponseEntity<String>("no data", HttpStatus.NO_CONTENT);
            else return new ResponseEntity<PlanBoardDto>(planArticle, HttpStatus.OK);
        } catch (SQLException e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<String>("errors : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
