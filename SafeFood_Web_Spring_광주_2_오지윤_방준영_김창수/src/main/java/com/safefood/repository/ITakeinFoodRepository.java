package com.safefood.repository;

import java.util.ArrayList;
import java.util.List;

import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
import com.safefood.dto.TakeinSumDTO;

public interface ITakeinFoodRepository {

	public List<TakeinFoodDTO> intakeList(String id);
	public TakeinFoodDTO intakeInfo(String id,int code);
	public int intakeDelete(String id,int code);
	public TakeinSumDTO intakeSum(String id);
	public List<TakeinFoodDTO> intakeSearch(String id, String keyword);
	public int intakeInsert(TakeInDTO t);
	public int selectOne(int code, String id, int icount);
	public int intakeUpdate(TakeInDTO t);
}

