package com.safefood.repository;

import java.util.ArrayList;
import java.util.List;

import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
import com.safefood.dto.TakeinSearchDTO;
import com.safefood.dto.TakeinSumDTO;

public interface ITakeinFoodRepository {

	public List<TakeinFoodDTO> intakeList(String id);
	public TakeinFoodDTO intakeInfo(TakeInDTO t);
	public int intakeDelete(TakeInDTO t);
	public TakeinSumDTO intakeSum(String id);	
	public int intakeInsert(TakeInDTO t);
	public int selectOne(TakeInDTO t);
	public int intakeUpdate(TakeInDTO t);
	public List<TakeinFoodDTO> intakeSearch(TakeinSearchDTO dto);
}

