package com.ssafy.enjoytrip.attraction.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDto {
	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr;
	private String image;
	private BigDecimal latitude;
	private BigDecimal longitude;
}
