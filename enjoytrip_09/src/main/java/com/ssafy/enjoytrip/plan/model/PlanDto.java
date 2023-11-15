package com.ssafy.enjoytrip.plan.model;

import java.util.List;

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
public class PlanDto {
	private String planId;
	private String userId;
	private String planTitle;
	private String updateTime;
	List<PlanSeqDto> planSeqs;
	private String img;
}
