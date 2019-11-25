package com.safefood.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.safefood.dto.FoodDTO;
import com.safefood.handler.FoodSaxParser;
import com.safefood.service.IFoodService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
public class RestFoodController {
	private static final Logger logger = LoggerFactory.getLogger(RestFoodController.class);

	@Autowired
	@Qualifier("FoodServiceImpl")
	IFoodService ser;

//	@PostMapping("/registerfood/{FoodDTO}")
//	@ApiOperation(value = "food등록")
//	public ResponseEntity<Map<String, Object>> registerfood(@PathVariable("FoodDTO") FoodDTO dto, HttpServletRequest request) {
//		ResponseEntity<Map<String, Object>> resEntity = null;
//
//		try {
//			int code = Integer.parseInt(request.getParameter("code"));
//			String name = request.getParameter("name");
//			double supportpereat = Double.parseDouble(request.getParameter("supportpereat"));
//			double calory = Double.parseDouble(request.getParameter("calory"));
//			double carbo = Double.parseDouble(request.getParameter("carbo"));
//			double protein = Double.parseDouble(request.getParameter("protein"));
//			double fat = Double.parseDouble(request.getParameter("fat"));
//			double sugar = Double.parseDouble(request.getParameter("sugar"));
//			double natrium = Double.parseDouble(request.getParameter("natrium"));
//			double chole = Double.parseDouble(request.getParameter("chole"));
//			double fattyacid = Double.parseDouble(request.getParameter("fattyacid"));
//			double transfat = Double.parseDouble(request.getParameter("transfat"));
//			String maker = request.getParameter("maker");
//			String material = request.getParameter("material");
//			String img = request.getParameter("img");
//			String allergy = request.getParameter("allergy");
//
//			ser.registerFood(new FoodDTO(code, name, supportpereat, calory, carbo, protein, fat, sugar, natrium, chole,
//					fattyacid, transfat, maker, material, img, allergy, 0));
//
//			Map msg = new HashMap();
//			msg.put("resMSG", dto.getName() + "입력 성공");
//			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
//		} catch (RuntimeException e) {
//			Map msg = new HashMap();
//			msg.put("resMSG", dto.getName() + "입력 실패");
//			resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
//
//		}
//		return resEntity;
//
//	}

	@GetMapping("/listfood")
	@ApiOperation(value = "푸드리스트출력")
	public @ResponseBody ResponseEntity<Map<String, Object>> listfood() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<FoodDTO> list = null;
		
		try {
			Map<String, Object> map = new HashMap();
			list = ser.listFood();
			map.put("resmsg", " 조회 성공");
			map.put("list", list);
			
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			map.put("list", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/infofood/{code}")
	@ApiOperation(value = "상품정보찾기")
	public @ResponseBody ResponseEntity<Map<String, Object>> infofood(@PathVariable("code") int code) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		FoodDTO food = null;
		try {
			Map<String, Object> map = new HashMap();
			int codeInt = code;
			ser.searchCountUp(codeInt);
			food = ser.infoFood(codeInt);

			map.put("resmsg", code + " 조회 성공");
			map.put("food", food);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			map.put("food", food);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

//	@PutMapping("/updatefood/{FoodDTO}")
//	@ApiOperation(value = "상품정보수정")
//	public ResponseEntity<Map<String, Object>> updatefood(@PathVariable("FoodDTO") FoodDTO dto, HttpServletRequest request) {
//		ResponseEntity<Map<String, Object>> resEntity = null;
//
//		try {
//			int code = Integer.parseInt(request.getParameter("code"));
//			String name = request.getParameter("name");
//			double supportpereat = Double.parseDouble(request.getParameter("supportpereat"));
//			double calory = Double.parseDouble(request.getParameter("calory"));
//			double carbo = Double.parseDouble(request.getParameter("carbo"));
//			double protein = Double.parseDouble(request.getParameter("protein"));
//			double fat = Double.parseDouble(request.getParameter("fat"));
//			double sugar = Double.parseDouble(request.getParameter("sugar"));
//			double natrium = Double.parseDouble(request.getParameter("natrium"));
//			double chole = Double.parseDouble(request.getParameter("chole"));
//			double fattyacid = Double.parseDouble(request.getParameter("fattyacid"));
//			double transfat = Double.parseDouble(request.getParameter("transfat"));
//			String maker = request.getParameter("maker");
//			String material = request.getParameter("material");
//			String img = request.getParameter("img");
//			String allergy = request.getParameter("allergy");
//			int searchcount = Integer.parseInt(request.getParameter("searchcount"));
//
//			ser.updateFood(new FoodDTO(code, name, supportpereat, calory, carbo, protein, fat, sugar, natrium, chole,
//					fattyacid, transfat, maker, material, img, allergy, searchcount));
//
//			Map<String, Object> map = new HashMap();
//			map.put("resmsg", dto.getName() + "수정 성공");
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//
//		} catch (RuntimeException e) {
//			Map<String, Object> map = new HashMap();
//			map.put("resmsg", "수정 실패");
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		}
//		return resEntity;
//	}

//	@DeleteMapping("/deletefood/{code}")
//	@ApiOperation(value = "상품삭제")
//	public @ResponseBody ResponseEntity<Map<String, Object>> deletefood(@PathVariable("code") int code) {
//		ResponseEntity<Map<String, Object>> resEntity = null;
//		int res = 0;
//		try {
//			Map<String, Object> map = new HashMap();
//			
//			
//			res = ser.deleteFood(code);
//
//			map.put("resmsg", code + " 삭제 성공");
//			map.put("resvalue", res);
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		} catch (Exception e) {
//			Map<String, Object> map = new HashMap();
//			map.put("resmsg", "삭제 실패");
//			map.put("resvalue", res);
//			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		}
//		return resEntity;
//
//	}

	
	@GetMapping("/countfood")
	@ApiOperation(value = "상품개수세기")
	public @ResponseBody ResponseEntity<Map<String, Object>> countFood(HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;

		try {
			int count = ser.countFood();

			if (count == 0) {
				// 절대경로 처리
				String path = request.getServletContext().getRealPath("/res");
				String foodPath = "file:\\" + path + "\\FoodInfo.xml";
				String nutPath = "file:\\" + path + "\\FoodNutritionInfo.xml";

				FoodSaxParser parser = new FoodSaxParser(nutPath, foodPath);
				List<FoodDTO> foods = parser.getFoods();

				boolean res = ser.ajaxToDb(foods);
				Map<String, Object> map = new HashMap();
				map.put("resmsg", " 조회 성공");
				map.put("foods", foods);
				resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} else {
				Map<String, Object> map = new HashMap();
				map.put("resmsg", " 데이터이미있음");
				resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;

	}

	@GetMapping("/mainfood")
	@ApiOperation(value = "시작화면푸드리스트출력")
	public @ResponseBody ResponseEntity<Map<String, Object>> mainFood() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<FoodDTO> listFood = new ArrayList<FoodDTO>();
		List<FoodDTO> list = new ArrayList<FoodDTO>();
 
		try {
			listFood = ser.listFood();
			for (int i = 0; i < 6; i++) {
				list.add(listFood.get(i));
			}
			Map<String, Object> map = new HashMap();
			map.put("resmsg", " 조회 성공");
			map.put("list", list);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		System.out.println(",,,,");
		return resEntity;

	}

	@GetMapping("/listbyname")
	@ApiOperation(value = "이름순으로 상품 리스트")
	public @ResponseBody ResponseEntity<Map<String, Object>> listByName(HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<FoodDTO> list = new ArrayList<FoodDTO>();
		try {
			list = ser.listFood();

			list.sort(new Comparator<FoodDTO>() {

				@Override
				public int compare(FoodDTO o1, FoodDTO o2) {
					// TODO Auto-generated method stub
					String name1 = o1.getName();
					String name2 = o2.getName();

					if (name1.length() < name2.length()) {
						for (int i = 0; i < name1.length(); i++) {
							if ((int) name1.charAt(i) > (int) name2.charAt(i)) {
								return 1;
							} else if ((int) name1.charAt(i) < (int) name2.charAt(i)) {
								return -1;
							}
						}

						return 1;
					} else {
						for (int i = 0; i < name2.length(); i++) {
							if ((int) name1.charAt(i) > (int) name2.charAt(i)) {
								return 1;
							} else if ((int) name1.charAt(i) < (int) name2.charAt(i)) {
								return -1;
							}
						}

						return 1;
					}
				}
			});

			Map<String, Object> map = new HashMap();
			map.put("resmsg", " 조회 성공");
			map.put("list", list);
			map.put("sel", "name");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;

	}

	@GetMapping("/listbymaker")
	@ApiOperation(value = "제조사순으로 상품리스트")
	public @ResponseBody ResponseEntity<Map<String, Object>> listByMaker(HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<FoodDTO> list = new ArrayList<FoodDTO>();
		try {
			list = ser.listFood();
			list.sort(new Comparator<FoodDTO>() {

				@Override
				public int compare(FoodDTO o1, FoodDTO o2) {
					// TODO Auto-generated method stub
					String maker1 = o1.getMaker();
					String maker2 = o2.getMaker();

					if (maker1.length() < maker2.length()) {
						for (int i = 0; i < maker1.length(); i++) {
							if ((int) maker1.charAt(i) > (int) maker2.charAt(i)) {
								return 1;
							} else if ((int) maker1.charAt(i) < (int) maker2.charAt(i)) {
								return -1;
							}
						}

						return 1;
					} else {
						for (int i = 0; i < maker2.length(); i++) {
							if ((int) maker1.charAt(i) > (int) maker2.charAt(i)) {
								return 1;
							} else if ((int) maker1.charAt(i) < (int) maker2.charAt(i)) {
								return -1;
							}
						}

						return 1;
					}
				}
			});

			Map<String, Object> map = new HashMap();
			map.put("resmsg", " 조회 성공");
			map.put("list", list);
			map.put("sel", "maker");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}

	@GetMapping("/recommendFood")
	@ApiOperation(value = "추천상품")
	public @ResponseBody ResponseEntity<Map<String, Object>> recommendFood(HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<FoodDTO> list = new ArrayList<FoodDTO>();
		try {
			list = ser.listFood();
			int size = list.size();
			int idx = (int) (Math.random() * size);
			FoodDTO food = list.get(idx);

			Map<String, Object> map = new HashMap();
			map.put("resmsg", " 조회 성공");
			map.put("recommend", food);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}

	@GetMapping("/searchFoodByKeyword/{selection}/{keyword}")
	@ApiOperation(value = "selection기준으로 키워드로찾기")
	public @ResponseBody ResponseEntity<Map<String, Object>> searchFoodByKeyword(
			@PathVariable("selection") String selection, @PathVariable("keyword") String keyword) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		List<FoodDTO> list = new ArrayList<FoodDTO>();
		try {
			if (selection.equals("name")) {
				list = ser.searchFoodByName(keyword);
			} else if (selection.equals("maker")) {
				list = ser.searchFoodByMake(keyword);
			} else if (selection.equals("material")) {
				list = ser.searchFoodByMaterial(keyword);
			}
			

			Map<String, Object> map = new HashMap();
			map.put("resmsg", " 조회 성공");
			map.put("list", list);
			for(int i=0;i<list.size();i++)
			System.out.println(list.get(i).toString());
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}

}
