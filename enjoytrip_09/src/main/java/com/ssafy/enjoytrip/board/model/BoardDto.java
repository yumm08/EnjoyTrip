package com.ssafy.enjoytrip.board.model;

import java.util.List;

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
	private List<FileInfoDto> fileInfos;

}
