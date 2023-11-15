package com.ssafy.enjoytrip.member.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.member.model.MemberDto;

public interface MemberService {
	MemberDto loginMember(String userId, String userPass);
	void updateMember(MemberDto memberDto);
	void joinMember(MemberDto memberDto);
	void delete(String userId);
	MemberDto getMyInfo(String userId);
	
	void addBookmark(String userId, String planId);
	List<Map<String, String>> getBookmark(String userId);
	void deleteBookmark(String userId, String planId);
	List<Map<String, String>> getBookmarkDetail(String planId);
}
