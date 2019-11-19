package com.safefood.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.safefood.dto.FoodDTO;
import com.safefood.handler.FoodSaxParser;
import com.safefood.service.IFoodService;

@Controller
public class FoodController {

	@Autowired
	@Qualifier("FoodServiceImpl")
	IFoodService ser;

	@RequestMapping("/registerfood")
	public ModelAndView registerFood(HttpServletRequest request, ModelAndView mv) {

		int code = Integer.parseInt(request.getParameter("code"));
		String name = request.getParameter("name");
		double supportpereat = Double.parseDouble(request.getParameter("supportpereat"));
		double calory = Double.parseDouble(request.getParameter("calory"));
		double carbo = Double.parseDouble(request.getParameter("carbo"));
		double protein = Double.parseDouble(request.getParameter("protein"));
		double fat = Double.parseDouble(request.getParameter("fat"));
		double sugar = Double.parseDouble(request.getParameter("sugar"));
		double natrium = Double.parseDouble(request.getParameter("natrium"));
		double chole = Double.parseDouble(request.getParameter("chole"));
		double fattyacid = Double.parseDouble(request.getParameter("fattyacid"));
		double transfat = Double.parseDouble(request.getParameter("transfat"));
		String maker = request.getParameter("maker");
		String material = request.getParameter("material");
		String img = request.getParameter("img");
		String allergy = request.getParameter("allergy");

		ser.registerFood(new FoodDTO(code, name, supportpereat, calory, carbo, protein, fat, sugar, natrium, chole,
				fattyacid, transfat, maker, material, img, allergy, 0));

		List<FoodDTO> list = ser.listFood();
		mv.setViewName("redirect:listfood");

		return mv;

	}

	@RequestMapping("/listfood")
	public ModelAndView listFood(HttpServletRequest request, ModelAndView mv) {
		List<FoodDTO> list = ser.listFood();
		mv.addObject("list", list);
		mv.setViewName("product/productinfo");

		return mv;
	}

	@RequestMapping("/infofood")
	public ModelAndView infoFood(@RequestParam("code") String code , ModelAndView mv) {
		int codeInt = Integer.parseInt(code);
		//System.out.println("ㅎㅇ");
		//System.out.println(codeInt);
		 ser.searchCountUp(codeInt);
		
			FoodDTO food = ser.infoFood(codeInt);
			mv.addObject("food", food);
			mv.setViewName("product/productdetail");

		

		return mv;
	}

	@RequestMapping("/updatefood")
	public ModelAndView updateFood(HttpServletRequest request, ModelAndView mv) {

		int code = Integer.parseInt(request.getParameter("code"));
		String name = request.getParameter("name");
		double supportpereat = Double.parseDouble(request.getParameter("supportpereat"));
		double calory = Double.parseDouble(request.getParameter("calory"));
		double carbo = Double.parseDouble(request.getParameter("carbo"));
		double protein = Double.parseDouble(request.getParameter("protein"));
		double fat = Double.parseDouble(request.getParameter("fat"));
		double sugar = Double.parseDouble(request.getParameter("sugar"));
		double natrium = Double.parseDouble(request.getParameter("natrium"));
		double chole = Double.parseDouble(request.getParameter("chole"));
		double fattyacid = Double.parseDouble(request.getParameter("fattyacid"));
		double transfat = Double.parseDouble(request.getParameter("transfat"));
		String maker = request.getParameter("maker");
		String material = request.getParameter("material");
		String img = request.getParameter("img");
		String allergy = request.getParameter("allergy");
		int searchcount = Integer.parseInt(request.getParameter("searchcount"));

		int res = ser.updateFood(new FoodDTO(code, name, supportpereat, calory, carbo, protein, fat, sugar, natrium,
				chole, fattyacid, transfat, maker, material, img, allergy, searchcount));

		mv.setViewName("redirect:listfood");

		return mv;
	}

	@RequestMapping("/deletefood")
	public ModelAndView deleteFood(HttpServletRequest request, ModelAndView mv) {

		int code = Integer.parseInt(request.getParameter("code"));

		int res = ser.deleteFood(code);

		mv.setViewName("redirect:listfood");

		return mv;
	}

	@RequestMapping("/searchfood")
	public ModelAndView searchFood(@RequestParam("selection") String selection,
			@RequestParam("keyword") String keyword, ModelAndView mv) {
		List<FoodDTO> list = null;
		//System.out.println(selection + "ㄴㄴ" + keyword);
		if (selection.equals("name")) {
			list = ser.searchFoodByName(keyword);
		} else if (selection.equals("maker")) {
			list = ser.searchFoodByMake(keyword);
		} else if (selection.equals("material")) {
			list = ser.searchFoodByMaterial(keyword);
		}
		mv.addObject("list", list);
		mv.setViewName("product/productinfo");

		return mv;
	}

	@RequestMapping("/countfood")
	public ModelAndView countFood(HttpServletRequest request, ModelAndView mv) {
		int count = ser.countFood();
		System.out.println(count);
		if (count == 0) {
			// 절대경로 처리
			String path = request.getServletContext().getRealPath("/res");
			String foodPath = "file:\\" + path + "\\FoodInfo.xml";
			String nutPath = "file:\\" + path + "\\FoodNutritionInfo.xml";

			FoodSaxParser parser = new FoodSaxParser(nutPath, foodPath);
			List<FoodDTO> foods = parser.getFoods();
			//System.out.println(foods.toString());
			mv.addObject("foods", foods);
			boolean res = ser.ajaxToDb(foods);
			mv.setViewName("redirect:mainfood");

		} else {
			mv.setViewName("redirect:mainfood");
		}

		return mv;
	}

	@RequestMapping("/mainfood")
	public ModelAndView mainFood(HttpServletRequest request, ModelAndView mv) {
		List<FoodDTO> listFood = ser.listFood();
		List<FoodDTO> list = new ArrayList<FoodDTO>();
		for (int i = 0; i < 6; i++) {
			list.add(listFood.get(i));
		}

		mv.addObject("list", list);
		mv.setViewName("/main");

		return mv;
	}

	@RequestMapping("/listbyname")
	public ModelAndView listByName(HttpServletRequest request, ModelAndView mv) {
		List<FoodDTO> list = ser.listFood();
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

		mv.addObject("list", list);
		mv.addObject("sel", "name");
		mv.setViewName("product/productinfo");

		return mv;
	}

	@RequestMapping("/listbymaker")
	public ModelAndView listByMaker(HttpServletRequest request, ModelAndView mv) {
		List<FoodDTO> list = ser.listFood();
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

		mv.addObject("list", list);
		mv.addObject("sel", "maker");
		mv.setViewName("product/productinfo");

		return mv;
	}

	@RequestMapping("/recommendfood")
	public ModelAndView recommendFood(HttpServletRequest request, ModelAndView mv) {
		List<FoodDTO> list = ser.listFood();
		int size = list.size();
		int idx = (int) (Math.random() * size);
		FoodDTO food = list.get(idx);

		mv.addObject("recommend", food);
		mv.setViewName("/modal");

		return mv;
	}

	@RequestMapping("/searchfoodbykeyword")
	public ModelAndView searchFoodByKeyword(@RequestParam("selection") String selection,
			@RequestParam("keyword") String keyword, ModelAndView mv) {
		List<FoodDTO> list = null;
		if (selection.equals("name")) {
			list = ser.searchByNameToFirst(keyword);
		} else if (selection.equals("maker")) {
			list = ser.searchByMakerToFirst(keyword);
		} else if (selection.equals("material")) {
			list = ser.searchByMaterialToFirst(keyword);
		}
		mv.addObject("list", list);
		mv.setViewName("product/productlist");

		return mv;
	}

}
