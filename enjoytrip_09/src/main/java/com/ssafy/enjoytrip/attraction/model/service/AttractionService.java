package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.ContentTypeDto;
import com.ssafy.enjoytrip.attraction.model.SearchConditionDto;
import com.ssafy.enjoytrip.attraction.model.SidoGugunDto;

public interface AttractionService {
	List<AttractionDto> searchTrip(SearchConditionDto searchConditionDto);
	List<SidoGugunDto> searchGugun(int sido);
	List<SidoGugunDto> searchSido();
	List<ContentTypeDto> searchContents();
}
