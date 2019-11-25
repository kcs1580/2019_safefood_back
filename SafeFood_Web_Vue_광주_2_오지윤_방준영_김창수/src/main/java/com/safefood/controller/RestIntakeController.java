package com.safefood.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.safefood.dto.NoticeDTO;
import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
import com.safefood.dto.TakeinSearchDTO;
import com.safefood.dto.TakeinSumDTO;
import com.safefood.service.INoticeService;
import com.safefood.service.ITakeinFoodService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
public class RestIntakeController {
	private static final Logger logger = LoggerFactory.getLogger(RestNoticeController.class);

	@ExceptionHandler(Exception.class)
	public String allException(Exception e, HttpServletRequest req) {
		req.setAttribute("message", e.getStackTrace());
		return "allErrorPage";
	}

	@Autowired
	@Qualifier("TakeinFoodServiceImpl")
	ITakeinFoodService tSer;

	@GetMapping("/searchintake/{keyword}/{id}")
	@ApiOperation(value = "섭취정보 검색 서비스")
	private ResponseEntity<Map<String, Object>> searchintake( @RequestBody TakeinSearchDTO t)
			 {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {		
		Map<String, Object> map = new HashMap();
		List<TakeinFoodDTO> list = tSer.intakeSearch(t);
		TakeinSumDTO sum = tSer.intakeSum(t.getId());
		map.put("resmsg", t.getId() + "조회 성공");
		map.put("list", list);
		}catch (Exception e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}
	return resEntity;
		// request.getRequestDispatcher("myintakeinfo.jsp").forward(request);
	}

	@GetMapping("/searchallintake/{id}")
	@ApiOperation(value = "섭취정보 리스트 서비스", response = List.class)

	private ResponseEntity<Map<String, Object>> selectallIntake(@PathVariable("id") String id,HttpServletRequest request) {
		// System.out.println("들아옴?");
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<TakeinFoodDTO> list = null;
		try {				
		list = tSer.intakeList(id);
		TakeinSumDTO sum = tSer.intakeSum(id);
		Map<String, Object> map = new HashMap();
		map.put("resmsg", "조회 성공");
		map.put("list", list);
		map.put("sum", sum);
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch (Exception e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);		}

		return resEntity;
	}

	@GetMapping("/selectoneintake")
	@ApiOperation(value = "섭취정보 선택 서비스")

	private ResponseEntity<Map<String, Object>> selectOneIntake(@RequestBody TakeInDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
		int code = dto.getCode();
		String id = dto.getId();
		// int code = Integer.parseInt(request.getParameter("code"));
		TakeinFoodDTO food = tSer.intakeInfo(dto);
		Map<String, Object> map = new HashMap();
		map.put("resmsg", code + "조회 성공");
		map.put("resvalue", " ");
		
		}catch (Exception e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);		}
		return resEntity;

	}

	@DeleteMapping("/deleteintake")
	@ApiOperation(value = "섭취정보 삭제 서비스")
	private ResponseEntity<Map<String, Object>> deleteIntake(@RequestBody TakeInDTO dto) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			
			String id = dto.getId();
			int code = dto.getCode();
			System.out.println(id+" "+code);
			int res = tSer.intakeDelete(dto);
			Map<String, Object> map = new HashMap();
			map.put("resmsg", code + "제거 성공");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap();
			String id = dto.getId();
			int code = dto.getCode();
			System.out.println(id+" "+code);
			map.put("resmsg", "제거 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/insertintake/{id}/{code}")
	@ApiOperation(value = "섭취정보 등록 서비스")
	private ResponseEntity<Map<String, Object>> insertIntake(@RequestBody TakeInDTO dto) {
		// System.out.println(id+"추가됩니다"+code);
		//System.out.println("테스트 " + id + " code" + code);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			if (dto.getId() != null) {
				int icount = 1;
				int res = tSer.intakeInsert(dto);
				Map<String, Object> msg = new HashMap();
				msg.put("resCode", "입력 성공");
				msg.put("resvalue", res);
				resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
			}
		} catch (Exception e) {

			Map<String, Object> msg = new HashMap();
			msg.put("resCode", "입력 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}

		return resEntity;

	}
}
