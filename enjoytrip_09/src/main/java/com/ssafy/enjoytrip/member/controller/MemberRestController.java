package com.ssafy.enjoytrip.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.service.MemberService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class MemberRestController {
	
	private MemberService memberService;

	public MemberRestController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody MemberDto memberDto){
		try{
			memberService.joinMember(memberDto);
			return new ResponseEntity<String>("Welcome !!", HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<String>("Duplicate Member", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberDto userinfo, HttpSession session){
		MemberDto memberDto = memberService.loginMember(userinfo.getUserId(), userinfo.getUserPass());
		
		if(memberDto != null) {
			session.setAttribute("userinfo", memberDto);
			System.out.println("login : "+userinfo);
			return new ResponseEntity<String>("Login !!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Incorrect Info", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody MemberDto memberDto) {
		
		try {
			memberService.updateMember(memberDto);
			return new ResponseEntity<String>("Update !!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/info")
	public ResponseEntity<?> myInfo(HttpSession session) {
		MemberDto userinfo = (MemberDto)session.getAttribute("userinfo");
		System.out.println("info : "+userinfo);
		
		if(userinfo != null) {
			memberService.getMyInfo(userinfo.getUserId());
			return new ResponseEntity<String>("Get My Info !!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Login First ..", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(HttpSession session) {
		MemberDto userinfo = ((MemberDto)session.getAttribute("userinfo"));
		System.out.println("delete : "+userinfo);
		
		try {
			memberService.delete(userinfo.getUserId());
			return new ResponseEntity<String>("Bye !!", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Can't Delete ..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
