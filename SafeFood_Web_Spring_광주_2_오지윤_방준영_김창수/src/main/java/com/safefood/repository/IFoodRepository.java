package com.safefood.repository;

import java.util.List;
import java.util.List;

import com.safefood.dto.FoodDTO;

public interface IFoodRepository {
	public int foodInsert(FoodDTO f);

	public List<FoodDTO> foodList();

	public FoodDTO foodInfo(int code);

	public int foodUpdate(FoodDTO f);

	public int foodDelete(int code);

	public List<FoodDTO> proSearchByName(String keyword);

	public List<FoodDTO> proSearchByMake(String keyword);

	public List<FoodDTO> proSearchByMaterial(String keyword);

	public int foodCount();

	public boolean ajaxToDb(List<FoodDTO> foods);

	public int searchCountUp(int code);

	public List<FoodDTO> searchByNameToFirst(String keyword);

	public List<FoodDTO> searchByMakeToFirst(String keyword);

	public List<FoodDTO> searchByMaterialToFirst(String keyword);
}
