package com.safefood.repository;

import java.util.List;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.safefood.dto.FoodDTO;

@Repository("FoodRepositoryImpl")
public class FoodRepositoryImpl implements IFoodRepository {

	@Autowired
	SqlSession session;

	@Override
	public List<FoodDTO> foodList() {
		// TODO Auto-generated method stub
		List<FoodDTO> list = session.selectList("com.food.foodSelectList");
		return list;
	}

	@Override
	public FoodDTO foodInfo(int code) {
		// TODO Auto-generated method stub
		FoodDTO f = session.selectOne("com.food.foodSelectOne", code);
		return f;
	}

	@Override
	public int foodDelete(int code) {
		// TODO Auto-generated method stub
		return session.delete("com.food.foodDelete", code);
	}

	@Override
	public List<FoodDTO> proSearchByName(String keyword) {
		// TODO Auto-generated method stub
		System.out.println("도도" + keyword);
		return session.selectList("com.food.proSearchByName", keyword);
	}

	@Override
	public List<FoodDTO> proSearchByMake(String keyword) {
		// TODO Auto-generated method stub
		return session.selectList("com.food.proSearchByMake", keyword);
	}

	@Override
	public List<FoodDTO> proSearchByMaterial(String keyword) {
		// TODO Auto-generated method stub

		return session.selectList("com.food.proSearchByMaterial", keyword);
	}

	@Override
	public int foodCount() {
		// TODO Auto-generated method stub
		List<FoodDTO> list = session.selectList("com.food.foodSelectList");
		System.out.println(list.toString());
		return list.size();
	}

	@Override
	public boolean ajaxToDb(List<FoodDTO> foods) {
		// TODO Auto-generated method stub
		for (FoodDTO f : foods) {
			System.out.println(f.toString());
			session.insert("com.food.foodInsert", f);

		}

		return true;
	}
	@Override
	public int foodInsert(FoodDTO f) {
		// TODO Auto-generated method stub
		return session.insert("com.food.foodInsert", f);
	}

	@Override
	public int searchCountUp(int code) {
		return session.update("com.food.searchCountUp", code);
	}

	@Override
	public List<FoodDTO> searchByNameToFirst(String keyword) {
		// TODO Auto-generated method stub
		return session.selectList("com.food.searchByNameToFirst", keyword);
	}

	@Override
	public List<FoodDTO> searchByMakeToFirst(String keyword) {
		// TODO Auto-generated method stub
		return session.selectList("searchByMakeToFirst", keyword);
	}

	@Override
	public List<FoodDTO> searchByMaterialToFirst(String keyword) {
		// TODO Auto-generated method stub
		return session.selectList("com.food.seachByMaterialToFirst", keyword);
	}


	@Override
	public int foodUpdate(FoodDTO f) {
		// TODO Auto-generated method stub
		return session.update("com.food.foodUpdate", f);
	}

}
