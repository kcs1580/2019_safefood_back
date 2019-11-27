package com.safefood.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.safefood.dto.MemDTO;
import com.safefood.service.IMemberService;

import io.swagger.annotations.ApiOperation;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	@Qualifier("MemberServiceImpl")
	IMemberService user;

	@RequestMapping("/signuppage")
	public String signUpPage() {
		return "/member/signUp";
	}

	@GetMapping("#")
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
	public ModelAndView signUpMem(HttpServletRequest req, ModelAndView mv) {
		// System.out.println("회원가입 들어옴ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String mname = req.getParameter("mname");
		String addr = req.getParameter("addr");
		String tel = req.getParameter("tel");
		String[] allergyArr = req.getParameterValues("allergy");
		String allergy = "";
		for (String str : allergyArr)
			allergy += str + " ";
		user.signUpMem(id, password, mname, addr, tel, allergy.substring(0, allergy.length() - 1), "", "", 0);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/memlist")
	public ModelAndView findmem(@RequestParam("id") String id, ModelAndView mv) {
//		String id = req.getParameter("id");
		MemDTO mem = user.infoMem(id);
		System.out.println(mem);
		mv.addObject("mem", mem);
		mv.setViewName("/member/memview");
		return mv;
	}

	@PostMapping("/login")
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
	}

	@PostMapping("/memupdate")
	public ModelAndView updateMem(HttpServletRequest req, ModelAndView mv) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String mname = req.getParameter("mname");
		String addr = req.getParameter("addr");
		String tel = req.getParameter("tel");
		String[] allergyArr = req.getParameterValues("allergy");
		int calorie_goal = Integer.parseInt(req.getParameter("calorie_goal"));
		System.out.println(Arrays.toString(allergyArr));
		String allergy = "";
		for (String str : allergyArr)
			allergy += str + " ";
		user.updateMem(id, password, mname, addr, tel, allergy, calorie_goal);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/logoutmem")
	public ModelAndView logoutMem(HttpServletRequest req, ModelAndView mv) {
		req.getSession().invalidate();
		mv.setViewName("index");

		return mv;
	}
}
