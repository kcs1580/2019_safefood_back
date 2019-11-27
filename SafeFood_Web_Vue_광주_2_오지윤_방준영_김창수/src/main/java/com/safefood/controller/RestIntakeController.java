package com.safefood.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
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

import net.nurigo.java_sdk.api.Message;
import com.safefood.dto.NoticeDTO;
import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
import com.safefood.dto.TakeinSearchDTO;
import com.safefood.dto.TakeinSumDTO;
import com.safefood.service.INoticeService;
import com.safefood.service.ITakeinFoodService;

import io.swagger.annotations.ApiOperation;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
//test
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
	private ResponseEntity<Map<String, Object>> searchintake(@PathVariable("keyword") String keyword,
			@PathVariable("id") String id) {
		System.out.println("Keyword, id ==>>" + keyword + " " + id);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {

			Map<String, Object> map = new HashMap();
			List<TakeinFoodDTO> list = tSer.intakeSearch(new TakeinSearchDTO(id, keyword));
			map.put("resmsg", id + "조회 성공");
			map.put("list", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			System.out.println(list.toString());
		} catch (Exception e) {

			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
		// request.getRequestDispatcher("myintakeinfo.jsp").forward(request);
	}

	@GetMapping("/searchallintake/{id}")
	@ApiOperation(value = "섭취정보 리스트 서비스", response = List.class)

	private ResponseEntity<Map<String, Object>> selectallIntake(@PathVariable("id") String id,
			HttpServletRequest request) {
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
		} catch (Exception e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

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

		} catch (Exception e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;

	}

	@DeleteMapping("/deleteintake/{id}/{code}")
	@ApiOperation(value = "섭취정보 삭제 서비스")
	private ResponseEntity<Map<String, Object>> deleteIntake(@PathVariable("id") String id,
			@PathVariable("code") int code) {

		ResponseEntity<Map<String, Object>> resEntity = null;
		try {

			int res = tSer.intakeDelete(new TakeInDTO(code, id));
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "succ");
			map.put("resvalue", res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap();

			map.put("resmsg", "제거 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/insertintake/{id}/{code}")
	@ApiOperation(value = "섭취정보 등록 서비스")
	private ResponseEntity<Map<String, Object>> insertIntake(@PathVariable("id") String id,
			@PathVariable("code") int code) {

		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			if (id != null) {
				Map<String, Object> msg = new HashMap();
				int icount = 1;
				int res = tSer.intakeInsert(new TakeInDTO(code, id));
				msg.put("resvalue", res);
				resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
			}
		} catch (Exception e) {

			Map<String, Object> msg = new HashMap();
			msg.put("resCode", "입력 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		}
		
		String api_key = "NCSOECLSYACOD9Y4";
		String api_secret = "TNSP80ZKRLPGJE4XZH6GWBMMYGQ8JFGU";
		Message coolsms = new Message(api_key, api_secret);
		
		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", "01063977067");
		params.put("from", "01063977067");
		params.put("type", "SMS");
		params.put("text", "문자가 보내집니다요ㅠㅠ");

		System.out.println(params.toString());
		JSONObject obj = null;
		try {
			obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}

		System.out.println("보내지나요4");

		if ((boolean) obj.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			System.out.println("성공");
			System.out.println(obj.get("group_id")); // 그룹아이디
			System.out.println(obj.get("result_code")); // 결과코드
			System.out.println(obj.get("result_message")); // 결과 메시지
			System.out.println(obj.get("success_count")); // 메시지아이디
			System.out.println(obj.get("error_count")); // 여러개 보낼시 오류난 메시지 수
		} else {
			// 메시지 보내기 실패
			System.out.println("실패");
			System.out.println(obj.get("code")); // REST API 에러코드
			System.out.println(obj.get("message")); // 에러메시지
		}
		return resEntity;
	}
}
