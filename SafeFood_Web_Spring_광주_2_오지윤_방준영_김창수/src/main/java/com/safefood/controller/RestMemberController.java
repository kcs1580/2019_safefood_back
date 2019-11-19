package com.safefood.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.safefood.dto.MemDTO;
import com.safefood.service.IMemberService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
public class RestMemberController {
	private static final Logger logger = LoggerFactory.getLogger(RestMemberController.class);

	@Autowired
	@Qualifier("MemberServiceImpl")
	IMemberService user;

	@GetMapping("/signuppage")
	@ApiOperation(value = "페이지 이동 서비스")
	public String signUpPage() {
		return "/member/signUp";
	}
 
	@GetMapping("#")
	@ApiOperation(value = "비밀번호찾기")
	private ModelAndView findPw(@RequestBody MemDTO mem, ModelAndView mv) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = mem.getId();
		String question = mem.getQuestion();
		String answer = mem.getAnswer();
		user.findPw(id, question, answer);
		mv.setViewName("redirect:updatepersonalinfo");
		return mv;

	}

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	public ResponseEntity<Map<String, Object>> signUpMem(@RequestBody MemDTO dto, HttpServletRequest req) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			String[] allergyArr = req.getParameterValues("allergy");
			String allergy = "";
			for (String str : allergyArr)
				allergy += str + " ";
			
			user.signUpMem(dto.getId(), dto.getPassword(), dto.getMname(), dto.getAddr(), dto.getTel(), allergy,
					dto.getQuestion(), dto.getAnswer());
			Map msg = new HashMap();
			msg.put("resMSG", dto.getId() + "입력 성공");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map msg = new HashMap();
			msg.put("resMSG", dto.getId() + "입력 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);

		}
		return resEntity;

	}

	@GetMapping("/memlist")
	@ApiOperation(value = "회원정보찾기",response = List.class)
	public  @ResponseBody ResponseEntity<Map<String,Object>>  findmem(@PathVariable("id") String id) {
//		String id = req.getParameter("id");
//		MemDTO mem = user.infoMem(id);
//		mv.addObject("mem", mem);
//		mv.setViewName("/member/memview");
//		return mv;

		ResponseEntity<Map<String, Object>> resEntity = null;
		MemDTO mem = null;
		try {
			Map<String,Object> map = new HashMap();
			mem  = user.infoMem(id);
			map.put("resmsg", id+" 조회 성공");
			map.put("resvalue", mem);
			resEntity = new ResponseEntity<Map<String, Object>> (map,HttpStatus.OK);
		}catch (Exception e) {
			Map<String,Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			map.put("resvalue", mem);
			resEntity = new ResponseEntity<Map<String, Object>> (map,HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/login")
	@ApiOperation(value = "로그인 서비스")
	public ModelAndView loginMem(ModelAndView mv, HttpServletRequest req) throws IOException {
		// System.out.println("여기도 잘 들어옵니까? 로그인?");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		// System.out.println(id+" "+password+"~~~~~");
		if (user.loginMem(id, password)) {
			// System.out.println("로그인이 된거죠?");
			mv.addObject("islogin", "1");
			mv.addObject("currentId", id);
			req.getSession().setAttribute("currentId", id);
		} else {
			mv.addObject("islogin", "0");
		}
		mv.setViewName("index");
		return mv;
//		try {
//
//		}catch (Exception e) {
//		}
	}

	@PutMapping("/memupdate")
	@ApiOperation(value = "id를 받아 멤버 수정 서비스")
	public  ResponseEntity<Map<String, Object>> updateMem(@RequestBody MemDTO mem,HttpServletRequest req, ModelAndView mv) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
		String[] allergyArr = req.getParameterValues("allergy");
		String allergy = "";
		for (String str : allergyArr)
			allergy += str + " ";
		user.updateMem(mem.getId(),mem.getPassword(), mem.getMname(), mem.getAddr(), mem.getTel(), allergy);
		Map<String,Object> map = new HashMap();
		map.put("resmsg",mem.getId()+ "수정 성공");
		resEntity = new ResponseEntity<Map<String, Object>> (map,HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String,Object> map = new HashMap();
			map.put("resmsg", "수정 실패");
			resEntity = new ResponseEntity<Map<String, Object>> (map,HttpStatus.OK);
		}		
		return resEntity;
		
	}

	@GetMapping("/logoutmem")
	@ApiOperation(value = "로그아웃 서비스")
	public ModelAndView logoutMem(HttpServletRequest req, ModelAndView mv) {
		req.getSession().invalidate();
		mv.setViewName("index");

		return mv;
	}
}
