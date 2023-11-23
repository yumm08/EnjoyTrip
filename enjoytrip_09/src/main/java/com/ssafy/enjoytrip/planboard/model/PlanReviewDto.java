package com.ssafy.enjoytrip.planboard.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlanReviewDto {
    private int articleNo;
    private int seq;
    private int contentId;
    private String contentTitle;
    private String image;
    private String content;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
