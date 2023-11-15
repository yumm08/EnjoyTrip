package com.ssafy.enjoytrip.plan.model;

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
public class PlanSeqDto {
	private String planId;
	private int planSeq;
	private int planDate;
	private int content_id;
}
