package com.ssafy.enjoytrip.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.ssafy.enjoytrip.util.JWTUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class MemberRestController {
	
	private MemberService memberService;
	private JWTUtil jwtUtil;

	public MemberRestController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
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
	public ResponseEntity<?> login(@RequestBody MemberDto memberDto){
//		System.out.println("memberDto : "+memberDto);
//		log.debug("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.loginMember(memberDto);
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
//				log.debug("access token : {}", accessToken);
//				log.debug("refresh token : {}", refreshToken);
				
//				발급받은 refresh token을 DB에 저장.
//				System.out.println("loginUser.getUserId() : " + loginUser.getUserId());
//				System.out.println("refreshToken : " + refreshToken);
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);
				
//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
			
		} catch (Exception e) {
//			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable("userId") String userId) {
		// removeToken
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleteRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
//			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	@PostMapping("/id")
	public ResponseEntity<?> findId(@RequestBody MemberDto memberDto){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String userId = memberService.findId(memberDto);
			if(userId != null) {
				resultMap.put("userId", userId);
				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "이름 또는 이메일을 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/pwd")
	public ResponseEntity<?> findPwd(@RequestBody String userId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String userPass = memberService.findPwd(userId);
			if(userId != null) {
				resultMap.put("userPass", userPass);
				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "아이디를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
	
	@GetMapping("/info/{userId}")
	public ResponseEntity<?> myInfo(@PathVariable("userId") String userId, HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println("request.getHeader : " + request.getHeader("Authorization"));
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
//			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.getMyInfo(userId);
//				System.out.println("myInfo memberDto : " + memberDto);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
//				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
//			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
//		System.out.println("status : " + status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
//		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
//				log.debug("token : {}", accessToken);
//				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
//			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/* ------------------------ 즐겨 찾기 ----------------------------- */
	
	@PostMapping("/mark")
	public ResponseEntity<?> addBookmark(@RequestBody String planNo, HttpSession session){
		MemberDto userinfo = (MemberDto)session.getAttribute("userinfo");
		System.out.println("info : "+userinfo);
		
		if(userinfo != null) {
			memberService.addBookmark(userinfo.getUserId(), planNo);
			return new ResponseEntity<String>("Add Bookmark !!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Login First ..", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/mark")
	public ResponseEntity<?> getBookmark(@RequestBody String planNo, HttpSession session){
		MemberDto userinfo = (MemberDto)session.getAttribute("userinfo");
		System.out.println("info : "+userinfo);
		
		if(userinfo != null) {
			List<Map<String, String>> bookmarkList = memberService.getBookmark(userinfo.getUserId());
			return new ResponseEntity<List<Map<String, String>>>(bookmarkList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Login First ..", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@DeleteMapping("/mark")
	public ResponseEntity<?> delete(@RequestBody String planNo, HttpSession session){
		MemberDto userinfo = (MemberDto)session.getAttribute("userinfo");
		System.out.println("info : "+userinfo);
		
		if(userinfo != null) {
			memberService.deleteBookmark(userinfo.getUserId(), planNo);
			return new ResponseEntity<String>("Bookmark Deleted !!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Login First ..", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/mark/{planNo}")
	public ResponseEntity<?> getBookmarkDetail(@PathVariable("planNo") String planNo){
		if(planNo != null) {
			List<Map<String, String>> bookmarkDetail = memberService.getBookmarkDetail(planNo);
			return new ResponseEntity<List<Map<String, String>>>(bookmarkDetail, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Login First ..", HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
