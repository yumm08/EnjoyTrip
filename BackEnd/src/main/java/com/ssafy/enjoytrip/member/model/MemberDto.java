package com.ssafy.enjoytrip.member.model;

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
public class MemberDto {
	private String userId;
	private String userName;
	private String userPass;
	private String userNickname;
	private String userEmail;
//	private String userDomain;
	private String refreshToken;
}