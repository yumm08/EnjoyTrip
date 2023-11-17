package com.ssafy.enjoytrip.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	MemberDto loginMember(MemberDto memberDto);
	void updateMember(MemberDto memberDto);
	void joinMember(MemberDto memberDto);
	void delete(String userId);
	MemberDto getMyInfo(String userId);
	String findId(MemberDto memberDto);
	String findPwd(String userId);
	
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	void addBookmark(String userId, String planNo);
	List<Map<String, String>> getBookmark(String userId);
	void deleteBookmark(String userId, String planNo);
	List<Map<String, String>> getBookmarkDetail(String planNo);
}
