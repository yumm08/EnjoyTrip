package com.ssafy.enjoytrip.attraction.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.ContentTypeDto;
import com.ssafy.enjoytrip.attraction.model.SearchConditionDto;
import com.ssafy.enjoytrip.attraction.model.SidoGugunDto;

@Mapper
public interface AttractionMapper {
	List<AttractionDto> searchTrip(SearchConditionDto searchConditionDto);
	List<SidoGugunDto> searchGugun(int sido);
	List<SidoGugunDto> searchSido();
	List<ContentTypeDto> searchContents();
}
