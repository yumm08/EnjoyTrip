package com.ssafy.enjoytrip.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
		
	@Override
	public MemberDto loginMember(MemberDto memberDto) {
		return memberMapper.loginMember(memberDto);
	}

	@Override
	@Transactional
	public void updateMember(MemberDto memberDto) {
		memberMapper.updateMember(memberDto);
	}

	@Override
	@Transactional
	public void joinMember(MemberDto memberDto) {
		memberMapper.joinMember(memberDto);
	}

	@Override
	@Transactional
	public void delete(String userId) {
		memberMapper.delete(userId);
	}
	
	@Override
	public MemberDto getMyInfo(String userId) {
		return memberMapper.getMyInfo(userId);
	}
	
	@Override
	public String findId(MemberDto memberDto) {
		return memberMapper.findId(memberDto);
	}
	
	@Override
	public String findPwd(String userId) {
		return memberMapper.findPwd(userId);
	}
	
	// JWT 토큰
	
	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
//		System.out.println("map in MemberServiceImpl : " + map);
//		System.out.println("len : " + refreshToken.length());
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public void deleteRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		System.out.println("logout map : " + map);
		memberMapper.deleteRefreshToken(map);
	}	
	
	// 즐겨찾기
	
	@Override
	public void addBookmark(String userId, String planNo) {
		memberMapper.addBookmark(userId, planNo);
	}
	
	@Override
	public List<Map<String, String>> getBookmark(String userId) {
		return memberMapper.getBookmark(userId);
	}
	
	@Override
	public void deleteBookmark(String userId, String planNo) {
		memberMapper.deleteBookmark(userId, planNo);
	}
	
	@Override
	public List<Map<String, String>> getBookmarkDetail(String planNo) {
		return memberMapper.getBookmarkDetail(planNo);
	}
}
