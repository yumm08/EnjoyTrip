package com.ssafy.enjoytrip.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	MemberDto loginMember(String userId, String userPass);
	void updateMember(MemberDto memberDto);
	void joinMember(MemberDto memberDto);
	void delete(String userId);
	void getMyInfo(String userId);
}
