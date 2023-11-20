package com.ssafy.enjoytrip.planboard.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlanReviewDto {
    private int articleNo;
    private int seq;
    private int contentId;
    private String content;
}
