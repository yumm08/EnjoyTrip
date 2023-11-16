package com.ssafy.enjoytrip.member.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.member.model.MemberDto;

public interface MemberService {
	MemberDto loginMember(MemberDto memberDto);
	void updateMember(MemberDto memberDto);
	void joinMember(MemberDto memberDto);
	void delete(String userId);
	MemberDto getMyInfo(String userId);
	String findId(MemberDto memberDto);
	String findPwd(String userId);
	
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleteRefreshToken(String userId) throws Exception;
	
	void addBookmark(String userId, String planNo);
	List<Map<String, String>> getBookmark(String userId);
	void deleteBookmark(String userId, String planNo);
	List<Map<String, String>> getBookmarkDetail(String planNo);
}
