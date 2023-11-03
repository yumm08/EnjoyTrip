package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.GugunDto;
import com.ssafy.enjoytrip.attraction.model.SearchConditionDto;

public interface AttractionService {
	List<AttractionDto> searchTrip(SearchConditionDto searchConditionDto);
	List<GugunDto> searchGugun(int sido);
}
