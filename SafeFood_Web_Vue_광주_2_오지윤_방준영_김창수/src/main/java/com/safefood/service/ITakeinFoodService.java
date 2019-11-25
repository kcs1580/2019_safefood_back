package com.safefood.service;

import java.util.ArrayList;
import java.util.List;

import com.safefood.dto.FoodDTO;
import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
import com.safefood.dto.TakeinSearchDTO;
import com.safefood.dto.TakeinSumDTO;

public interface ITakeinFoodService {
	public int selectOne(TakeInDTO t);
	public List<TakeinFoodDTO> intakeList(String id);
	public TakeinFoodDTO intakeInfo(TakeInDTO t);
	public int intakeDelete(TakeInDTO t);
	public TakeinSumDTO intakeSum(String id);
	public List<TakeinFoodDTO> intakeSearch(TakeinSearchDTO t);
	public int intakeInsert(TakeInDTO takeInDTO);
}
