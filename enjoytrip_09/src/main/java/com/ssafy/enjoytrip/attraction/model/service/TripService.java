package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.AttractionDto;
import com.ssafy.enjoytrip.attraction.model.GugunDto;

public interface TripService {
	List<AttractionDto> searchTrip(int sido, int gugun, int contentType);
	List<GugunDto> searchGugun(int sido);
}
