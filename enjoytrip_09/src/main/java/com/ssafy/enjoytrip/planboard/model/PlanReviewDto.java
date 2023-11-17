package com.ssafy.enjoytrip.planboard.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlanReviewDto {
    private int articleNo;
    private String contentId;
    private String content;
}
