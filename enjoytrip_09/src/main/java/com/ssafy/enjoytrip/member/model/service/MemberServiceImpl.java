package com.ssafy.enjoytrip.member.model.service;

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
	public MemberDto loginMember(String userId, String userPass) {
		return memberMapper.loginMember(userId, userPass);
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
	public void getMyInfo(String userId) {
		memberMapper.getMyInfo(userId);
	}

}
