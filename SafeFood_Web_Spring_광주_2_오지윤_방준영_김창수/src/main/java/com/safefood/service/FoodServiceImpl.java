package com.safefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safefood.dto.FoodDTO;
import com.safefood.repository.IFoodRepository;

@Service("FoodServiceImpl")
public class FoodServiceImpl implements IFoodService {
	@Autowired
	@Qualifier("FoodRepositoryImpl")
	IFoodRepository repo;

	@Override
	public int deleteFood(int code) {
		// TODO Auto-generated method stub
		return repo.foodDelete(code);
	}

	@Override
	public int updateFood(FoodDTO f) {
		// TODO Auto-generated method stub
		return repo.foodUpdate(f);
	}

	@Override
	public FoodDTO infoFood(int code) {
		// TODO Auto-generated method stub
		return repo.foodInfo(code);
	}

	@Override
	public List<FoodDTO> listFood() {
		// TODO Auto-generated method stub
		return repo.foodList();
	}

	@Override
	public int registerFood(FoodDTO f) {
		// TODO Auto-generated method stub
		return repo.foodInsert(f);
	}

	@Override
	public List<FoodDTO> searchFoodByName(String keyword) {
		// TODO Auto-generated method stub
		return repo.proSearchByName(keyword);
	}

	@Override
	public List<FoodDTO> searchFoodByMake(String keyword) {
		// TODO Auto-generated method stub
		return repo.proSearchByMake(keyword);
	}

	@Override
	public List<FoodDTO> searchFoodByMaterial(String keyword) {
		// TODO Auto-generated method stub
		return repo.proSearchByMaterial(keyword);
	}

	@Override
	public int countFood() {
		// TODO Auto-generated method stub
		return repo.foodCount();
	}

	@Override
	public boolean ajaxToDb(List<FoodDTO> foods) {
		// TODO Auto-generated method stub
		return repo.ajaxToDb(foods);
	}

	@Override
	public int searchCountUp(int code) {
		// TODO Auto-generated method stub
		return repo.searchCountUp(code);
	}

	@Override
	public List<FoodDTO> searchByNameToFirst(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchByNameToFirst(keyword);
	}

	@Override
	public List<FoodDTO> searchByMakerToFirst(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchByMakeToFirst(keyword);
	}

	@Override
	public List<FoodDTO> searchByMaterialToFirst(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchByMaterialToFirst(keyword);
	}

}
