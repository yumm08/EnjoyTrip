package com.ssafy.enjoytrip.attraction.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.GugunDto;
import com.ssafy.enjoytrip.attraction.model.SearchConditionDto;

@Mapper
public interface AttractionMapper {
	List<AttractionDto> searchTrip(SearchConditionDto searchConditionDto);
	List<GugunDto> searchGugun(int sido);
}
