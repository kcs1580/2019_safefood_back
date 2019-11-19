package com.safefood.handler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.safefood.dto.FoodDTO;
import com.safefood.dto.allergyDTO;


/**
 * FoodNutritionSAXHandler와 FoodSAXHandler를 이용해서 식품 정보를 load하는 SAX Parser 프로 그램  
 *
 */
public class FoodSaxParser {
	//private String nutritionFilePath = "res/FoodNutritionInfo.xml";
	//private String foodFilePath = "res/FoodInfo.xml";
	private StringBuilder xml ;
	private List<FoodDTO> foods;
 	public FoodSaxParser(String url, String url2) {
 		System.out.println("parse로 들어왔습니다.");
		loadData(url, url2);
	}
 	
 	/**
 	 * FoodNutritionSAXHandler와 FoodSAXHandler를 이용 파싱한 식품 정보와 식품 영양 정보를  Food에 병합한다. 
 	 */
	private void loadData(String nutritionFilePath, String foodFilePath) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try{
			SAXParser parser = factory.newSAXParser();
			FoodSAXHandler handler = new FoodSAXHandler();
			FoodNutritionSAXHandler nHandler = new FoodNutritionSAXHandler();
			parser.parse(foodFilePath,handler);
			parser.parse(nutritionFilePath,nHandler);
			Map<String, FoodDTO> foodMap = handler.getFoods();
			foods = nHandler.getList();
			FoodDTO find;
			String allergySample = new allergyDTO().getAllergy();
			HashSet<String> allergySet = new HashSet<String>();
			for (FoodDTO food : foods) {
				find = foodMap.get(food.getName());
				if(find!=null) {
					String allergy = "";
					allergySet.clear();
					food.setCode(find.getCode());
					food.setName(find.getName());
					food.setMaker(find.getMaker());
					food.setMaterial(find.getMaterial());
					String material = find.getMaterial();
					StringTokenizer st = new StringTokenizer(allergySample);
					while(st.hasMoreTokens()) {
						String next = st.nextToken();
						if(material.contains(next)) {
							allergySet.add(next);
						}
					}
					for (String s : allergySet) {
						allergy += s + " ";
					}
					food.setAllergy(allergy.trim());
					food.setImg(find.getImg());
				}
			}
			System.out.println(foods);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<FoodDTO> getFoods() {
		return foods;
	}
	public void setFoods(List<FoodDTO> foods) {
		this.foods = foods;
	}
	
}
