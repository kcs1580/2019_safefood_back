
package com.safefood.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

import com.safefood.dto.BoardDTO;
import com.safefood.dto.ChatDTO;
import com.safefood.dto.CommentDTO;
import com.safefood.dto.NoticeDTO;
import com.safefood.service.IBoardService;
import com.safefood.service.IMemberService;
import com.safefood.service.INoticeService;
import com.safefood.service.NoticeServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")  
public class RestBoardController {     
	private static final Logger logger = LoggerFactory.getLogger(RestBoardController.class);
       
	@ExceptionHandler(Exception.class)
	public String allException(Exception e, HttpServletRequest req) {
		req.setAttribute("message", e.getStackTrace());
		return "allErrorPage";
	}

	@Autowired
	@Qualifier("BoardServiceImpl")
	IBoardService bSer;
	
	@Autowired
	@Qualifier("MemberServiceImpl")
	IMemberService user;
	
	@GetMapping("/Boardpage")
	@ApiOperation(value = "baordlist 페이지 이동 서비스")
	public String noticeInsertPage() {
		return "/board/board_list";
	}


	@PostMapping("/registerboard")
	@ApiOperation(value = "board 등록 서비스")
	private ResponseEntity<Map<String, Object>> registerBoard(@RequestBody BoardDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		System.out.println(dto.toString());
		try {
			System.out.println("1");
			boolean res = bSer.registerBoard(dto.getUser_id(), dto.getBtitle(), dto.getBcontent(), dto.getUser_name());
			System.out.println("2");
			Map<String, Object> msg = new HashMap();
			msg.put("resCode", "입력 성공");
			msg.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap();
			msg.put("resCode", "입력 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/listboard")
	@ApiOperation(value = "Board 리스트 서비스", response = List.class)
	private ResponseEntity<Map<String, Object>> listNotice() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<BoardDTO> list = null;
		try {
			list = bSer.listBoard();
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 성공");
			map.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(RuntimeException e){
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}

	@GetMapping("/infoboard/{bid}")
	@ApiOperation(value = "Board 정보 서비스")
	private ResponseEntity<Map<String, Object>> infoNotice(@PathVariable("bid") int bid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		BoardDTO board = null;
		try {
			boolean res = bSer.countUp(bid);
			board = bSer.infoBoard(bid);
			Map<String, Object> map = new HashMap();
			map.put("resmsg", bid + "조회 성공");
			map.put("resvalue", board);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	
		}
		return resEntity;
	}

	@PutMapping("/updateboard")
	@ApiOperation(value = "Board 업데이트 서비스")
	private ResponseEntity<Map<String, Object>> updateNotice(@RequestBody BoardDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		System.out.println(dto.toString());
		try {
			boolean res = bSer.updateBoard(dto.getUser_id(), dto.getBid(), dto.getBtitle(), dto.getBcontent());
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "수정 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "수정 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
		

	}

	@DeleteMapping("/deleteboard/{bid}/{user_id}")
	@ApiOperation(value = "Board 제거 서비스")
	private ResponseEntity<Map<String, Object>> deleteBoard(@PathVariable("bid") int bid,@PathVariable("user_id") String uid, ModelAndView mv) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = bSer.deleteBoard(uid, bid);
			Map<String, Object> map = new HashMap();
			map.put("resmsg", bid + "제거 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "제거 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		
		return resEntity;
	}

	@GetMapping("/searchboard/{keyword}")
	@ApiOperation(value = "Board 탐색 서비스")
	private ResponseEntity<Map<String, Object>> searchBoard(@PathVariable("keyword") String keyword, ModelAndView mv) {
		
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<BoardDTO> list = bSer.searchBoard(keyword);
			Map<String, Object> map = new HashMap();
			map.put("resmsg", keyword + "탐색 성공");
			map.put("resvalue", 1);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", keyword + "탐색 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/listcomment/{bid}")
	@ApiOperation(value = "Comment 리스트 서비스", response = List.class)
	private ResponseEntity<Map<String, Object>> listComment(@PathVariable("bid") int bid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<CommentDTO> list = null;
		try {
			list = bSer.commentList(bid);			
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 성공");
			map.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(RuntimeException e){
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}
	
	
	@DeleteMapping("/deletecomment/{cnum}")
	@ApiOperation(value = "Comment 제거 서비스")
	private ResponseEntity<Map<String, Object>> deleteComment(@PathVariable("cnum") int cnum) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		//System.out.println(cnum);
		try {
			boolean res = bSer.commentDelete(cnum);
			Map<String, Object> map = new HashMap();
			map.put("resmsg", cnum + "제거 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "제거 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@PostMapping("/registercomment")
	@ApiOperation(value = "Comment 등록 서비스")
	private ResponseEntity<Map<String, Object>> registerComment(@RequestBody CommentDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;		
		try {
			System.out.println(dto.toString());
			boolean res = bSer.registerComment(0,dto.getBid(),dto.getCcontent() , dto.getUser_id(),dto.getUser_name());
			
			Map<String, Object> msg = new HashMap();
			msg.put("resCode", "succ");
			msg.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap();
			msg.put("resCode", "입력 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@PostMapping("/registerchat")
	@ApiOperation(value = "chat 등록 서비스")
	private ResponseEntity<Map<String, Object>> registerChat(@RequestBody ChatDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;		
		try {
			
			boolean res = bSer.registerChat(0,dto.getCcontent() , dto.getUser_id(),dto.getUser_name());
			
			Map<String, Object> msg = new HashMap();
			msg.put("resCode", "succ");
			msg.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> msg = new HashMap();
			msg.put("resCode", "입력 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@GetMapping("/listchat")
	@ApiOperation(value = "Chat 리스트 서비스", response = List.class)
	private ResponseEntity<Map<String, Object>> listChat() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<ChatDTO> list = null;
		try {
			list = bSer.chatList();
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 성공");
			map.put("resvalue", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(RuntimeException e){
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}
}