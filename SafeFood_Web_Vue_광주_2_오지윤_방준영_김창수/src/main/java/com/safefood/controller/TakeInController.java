package com.safefood.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
import com.safefood.dto.TakeinSumDTO;
import com.safefood.service.ITakeinFoodService;

import io.swagger.annotations.ApiOperation;

@Controller
public class TakeInController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	@Qualifier("TakeinFoodServiceImpl")
	ITakeinFoodService tSer;

//	
//	@RequestMapping("/intakelistpage")
//	public String intakePage(){
//		System.out.println("나가ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//		return "/intake/myintakeinfo";
//	}
//	
//	
	@RequestMapping("/searchintake")
	private ModelAndView searchintake(ModelAndView mv, HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String keyword = request.getParameter("keyword");
		List<TakeinFoodDTO> list = tSer.intakeSearch(id, keyword);
		TakeinSumDTO sum = tSer.intakeSum(id);
		// request.setAttribute("sum", sum);
		// request.setAttributess("list", list);
		mv.addObject("sum", sum);
		mv.addObject("list", list);
		mv.setViewName("intake/myintakeinfo");
		System.out.println(list);
		return mv;
		// request.getRequestDispatcher("myintakeinfo.jsp").forward(request);
	}

	@RequestMapping("/searchallintake")
	private ModelAndView selectallIntake(ModelAndView mv, HttpServletRequest request) {
		//System.out.println("들아옴?");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("currentId");
		System.out.println("id: "+id);
		List<TakeinFoodDTO> list = tSer.intakeList(id);
		TakeinSumDTO sum = tSer.intakeSum(id);
		request.setAttribute("sum", sum);
		request.setAttribute("list", list);
		mv.addObject("sum", sum);
		mv.addObject("list", list);
		System.out.println(list.toString());
		mv.setViewName("intake/myintakeinfo");
		return mv;
	}

	@RequestMapping("/selectoneintake")
	private ModelAndView selectOneIntake(@RequestBody TakeinFoodDTO dto, ModelAndView mv) {
		// String id = (String) session.getAttribute("currentId");
		int code = dto.getCode();
		String id = dto.getId();
		// int code = Integer.parseInt(request.getParameter("code"));
		TakeinFoodDTO food = tSer.intakeInfo(id, code);
		mv.addObject("dto", food);
		mv.setViewName("intake/myintakeinfo");
		return mv;

	}

	@RequestMapping("/deleteintake")
	private ModelAndView deleteIntake(@RequestBody TakeinFoodDTO dto, ModelAndView mv) {
		int code = dto.getCode();
		String id = dto.getId();
		tSer.intakeDelete(id, code);
		mv.setViewName("intake/myintakeinfo");
		return mv;

	}

	@RequestMapping("/insertintake")
	private ModelAndView insertIntake(ModelAndView mv, @RequestParam("id") String id,
			@RequestParam("code") int code) {
		System.out.println(id+"추가됩니다"+code);
		String msg = "";
		if (id != null) {
			int icount = 1;
			int intakeInsert = tSer.intakeInsert(new TakeInDTO(code, id, icount));
			System.out.println("intakeInsert result " + intakeInsert);
			msg = "섭취정보 등록 완료";
			
		} else {
			msg = "섭취정보 등록 오류";      
		}
		System.out.println(msg);
		mv.addObject("msg", msg);
		mv.setViewName("redirect:/searchallintake");
		return mv;

	}

}
