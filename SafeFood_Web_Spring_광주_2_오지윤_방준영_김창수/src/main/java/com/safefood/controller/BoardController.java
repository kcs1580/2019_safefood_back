package com.safefood.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.safefood.dto.BoardDTO;
import com.safefood.dto.MemDTO;
import com.safefood.service.IBoardService;


@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	@Qualifier("BoardServiceImpl")
	IBoardService bSer;
	
	@RequestMapping("/boardpage")
	public String boardPage(){
		return "/board/board_list";
	}
	@RequestMapping("/boardinsertpage")
	public String boardInsertPage(){
		return "/board/board_insert";
	}
	
	
	@RequestMapping("/updateview")
	private ModelAndView updateView(@RequestParam("bid") int bid, @RequestParam("btitle") String btitle
			,@RequestParam("bcontent") String bcontent,  ModelAndView mv)  {
		mv.addObject("bid", bid);
		mv.addObject("btitle", btitle);
		mv.addObject("bcontent", bcontent);
		mv.setViewName("/board/board_update");
		return mv;
	}

	@RequestMapping("/registerboard")
	private ModelAndView registerBoard(HttpServletRequest request, ModelAndView mv)  {
		String user_id = request.getParameter("user_id");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		boolean res = bSer.registerBoard(user_id, btitle, bcontent);
		System.out.println("board test " + res);
		if(res) {
			mv.setViewName("redirect:listboard");
		}
		else {
			mv.setViewName("redirect:listboard");
		}
		return mv;
	}

	@RequestMapping("/listboard")
	private ModelAndView listBoard(HttpServletRequest request, ModelAndView mv)  {
		List<BoardDTO> list = bSer.listBoard();
		mv.addObject("list", list);
		System.out.println(list);
		mv.setViewName("/board/board_list");
		
		return mv;
	}

	@RequestMapping("/infoboard")
	private ModelAndView infoBoard(@RequestParam("bid") int bid, ModelAndView mv)  {
		System.out.println(bid+" " + "bid를 선택하셨습니다.");
		boolean res = bSer.countUp(bid);
		BoardDTO board = bSer.infoBoard(bid);
		mv.addObject("board", board);
		mv.setViewName("/board/board_view");	
		return mv;
	}

	@RequestMapping("/updateboard")
	private ModelAndView updateBoard(HttpServletRequest request, ModelAndView mv)  {
		String user_id = request.getParameter("user_id");
		int bid = Integer.parseInt(request.getParameter("bid"));
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		boolean res = bSer.updateBoard(user_id, bid, btitle, bcontent);

			mv.setViewName("redirect:listboard");
				
		return mv;
		
	}

	@RequestMapping("/deleteboard")
	private ModelAndView deleteBoard(@RequestParam("bid") int bid, @RequestParam("user_id") String user_id, HttpServletRequest request, ModelAndView mv) {
//		String user_id = request.getParameter("user_id");
		System.out.println(user_id + " 123213213 " + bid);
		boolean res = bSer.deleteBoard(user_id, bid);
		if(res) {
			mv.setViewName("redirect:listboard");
		}
		else {
			System.out.println("제거실패");
		}
		return mv;
	}

	@RequestMapping("/searchboard")
	private ModelAndView searchBoard(HttpServletRequest request, ModelAndView mv)  {
		String keyword = request.getParameter("keyword");
		List<BoardDTO> list = bSer.searchBoard(keyword);
		mv.addObject("list", list);
		mv.setViewName("/board/board_list");
		
		return mv;
	}
}
