package com.safefood.service;

import java.util.List;
import java.util.List;

import com.safefood.dto.FoodDTO;

public interface IFoodService {
	public int deleteFood(int code);

	public int updateFood(FoodDTO f);

	public FoodDTO infoFood(int code);

	public List<FoodDTO> listFood();

	public int registerFood(FoodDTO f);

	public List<FoodDTO> searchFoodByName(String keyword);

	public List<FoodDTO> searchFoodByMake(String keyword);

	public List<FoodDTO> searchFoodByMaterial(String keyword);

	public int countFood();

	public boolean ajaxToDb(List<FoodDTO> foods);

	public int searchCountUp(int code);

	public List<FoodDTO> searchByNameToFirst(String keyword);

	public List<FoodDTO> searchByMakerToFirst(String keyword);

	public List<FoodDTO> searchByMaterialToFirst(String keyword);
}
