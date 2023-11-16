package com.ssafy.enjoytrip.planboard.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlanBoardDto {

    private int articleNo;
    private String userId;
    private String userNickname;
    private String articleTitle;
    private int articleHit;
    private int bookmarkCnt;
    private int planNo;
    private String img;
    private String registerTime;

}
