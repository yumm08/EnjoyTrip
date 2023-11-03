package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.model.MemberDto;

public interface MemberService {
	MemberDto loginMember(String userId, String userPass);
	void updateMember(MemberDto memberDto);
	void joinMember(MemberDto memberDto);
	void delete(String userId);
	void getMyInfo(String userId);
}
