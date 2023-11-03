package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.GugunDto;
import com.ssafy.enjoytrip.attraction.model.SearchConditionDto;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {
	
	private AttractionMapper attractionMapper;
	
	@Autowired
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<AttractionDto> searchTrip(SearchConditionDto searchConditionDto) {
		return attractionMapper.searchTrip(searchConditionDto);
	}

	@Override
	public List<GugunDto> searchGugun(int sido) {
		return attractionMapper.searchGugun(sido);
	}

}
