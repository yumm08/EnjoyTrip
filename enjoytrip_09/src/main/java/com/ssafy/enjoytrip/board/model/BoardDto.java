package com.ssafy.enjoytrip.board.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

	private int articleNo;
	@NonNull
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;

}
