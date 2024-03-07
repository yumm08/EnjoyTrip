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
	private int planNo;
	private int planSeq;
	private int contentId;
	private String title;
	private String image;
	private String overview;
}
