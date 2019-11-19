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
import com.safefood.dto.NoticeDTO;
import com.safefood.service.INoticeService;
import com.safefood.service.NoticeServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
public class RestNoticeController {
	private static final Logger logger = LoggerFactory.getLogger(RestNoticeController.class);

	@ExceptionHandler(Exception.class)
	public String allException(Exception e, HttpServletRequest req) {
		req.setAttribute("message", e.getStackTrace());
		return "allErrorPage";
	}

	@Autowired
	@Qualifier("NoticeServiceImpl")
	INoticeService nSer;

	@GetMapping("/noticeinsertpage")
	@ApiOperation(value = "공지사항 입력페이지 이동 서비스")
	public String noticeInsertPage() {
		return "/notice/noticeinsert";
	}

	@GetMapping("/sortrecent")
	@ApiOperation(value = "Notice 상품 id로 서비스")
	private  @ResponseBody ResponseEntity<Map<String,Object>>  sortRecent(){
		// TODO Auto-generated method stub
		ResponseEntity<Map<String,Object>> resEntity = null;
		try {
		ArrayList<NoticeDTO> list = nSer.listNotice();
		list.sort(new Comparator<NoticeDTO>() {
			@Override
			public int compare(NoticeDTO o1, NoticeDTO o2) {
				// TODO Auto-generated method stub
				int id1 = o1.getNid();
				int id2 = o2.getNid();
				return id2-id1;
			}
		});
		Map<String,Object> map = new HashMap();
		map.put("resmsg", "조회 성공");
		map.put("resvalue", list);
		resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}catch (Exception e) {
			Map<String,Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/sortview")
	@ApiOperation(value = "Notice view순 정렬 서비스")
	private   @ResponseBody ResponseEntity<Map<String,Object>> sortView() {
		// TODO Auto-generated method stub
		ResponseEntity<Map<String,Object>> resEntity = null;
		try {
		ArrayList<NoticeDTO> list = nSer.listNotice();
		list.sort(new Comparator<NoticeDTO>() {
			@Override
			public int compare(NoticeDTO o1, NoticeDTO o2) {
				// TODO Auto-generated method stub
				int cnt1 = o1.getNcount();
				int cnt2 = o2.getNcount();
				return cnt2-cnt1;
			}
		});
		Map<String,Object> map = new HashMap();
		map.put("resmsg", "조회 성공");
		map.put("resvalue", list);
		resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}catch (Exception e) {
			Map<String,Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/sortregist")
	@ApiOperation(value = "최신순 정렬", response = List.class)
	private  @ResponseBody ResponseEntity<Map<String,Object>>  sortRegist() {
		ResponseEntity<Map<String,Object>> resEntity = null;
		try {
		ArrayList<NoticeDTO> list = nSer.listNotice();
		list.sort(new Comparator<NoticeDTO>() {
			@Override
			public int compare(NoticeDTO o1, NoticeDTO o2) {
				// TODO Auto-generated method stub
				int id1 = o1.getNid();
				int id2 = o2.getNid();
				return id1-id2;
			}
		});
		Map<String,Object> map = new HashMap();
		map.put("resmsg", "조회 성공");
		map.put("resvalue", list);
		resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}catch (Exception e) {
			Map<String,Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		return resEntity;
	}

	@PostMapping("/registernotice")
	@ApiOperation(value = "Notice 등록 서비스")
	private ResponseEntity<Map<String, Object>> registerNotice(@RequestBody BoardDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = nSer.registerNotice(dto.getBtitle(), dto.getBcontent());
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

	@GetMapping("/listnotice")
	@ApiOperation(value = "Notice 리스트 서비스", response = List.class)
	private ResponseEntity<Map<String, Object>> listNotice() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		ArrayList<NoticeDTO> list = null;
		try {
			list = nSer.listNotice();
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

	@GetMapping("/infonotice/{nid}")
	@ApiOperation(value = "Notice 정보 서비스")
	private ResponseEntity<Map<String, Object>> infoNotice(@PathVariable("nid") int nid) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		NoticeDTO notice = null;
		try {
			notice = nSer.infoNotice(nid);
			Map<String, Object> map = new HashMap();
			map.put("resmsg", nid + "조회 성공");
			map.put("resvalue", nid);
		}catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	
		}
		return resEntity;
	}

	@PutMapping("/updatenotice")
	@ApiOperation(value = "Notice 업데이트 서비스")
	private ResponseEntity<Map<String, Object>> updateNotice(@RequestBody NoticeDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = nSer.updateNotice(dto.getNid(), dto.getNtitle(), dto.getNcontent());
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

	@DeleteMapping("/deletenotice/{nid}")
	@ApiOperation(value = "Notice 제거 서비스")
	private ResponseEntity<Map<String, Object>> deleteNotice(@PathVariable("nid") int nid, ModelAndView mv) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = nSer.deleteNotice(nid);
			Map<String, Object> map = new HashMap();
			map.put("resmsg", nid + "제거 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "제거 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		
		return resEntity;
	}

	@GetMapping("/searchnotice/{keyword}")
	@ApiOperation(value = "Notice 탐색 서비스")
	private ResponseEntity<Map<String, Object>> searchNotice(@PathVariable("keyword") String keyword, ModelAndView mv) {
		
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			ArrayList<NoticeDTO> list = nSer.searchNotice(keyword);
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

}
