package com.safefood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
import com.safefood.dto.TakeinSearchDTO;
import com.safefood.dto.TakeinSumDTO;
import com.safefood.repository.ITakeinFoodRepository;

@Service("TakeinFoodServiceImpl")
public class TakeinFoodServiceImpl implements ITakeinFoodService {

	@Autowired
	@Qualifier("TakeinFoodMyBatisRepositoryImpl")
	ITakeinFoodRepository repo;

	@Override
	public List<TakeinFoodDTO> intakeList(String id) {

		return repo.intakeList(id);
	}

	@Override
	public TakeinFoodDTO intakeInfo(TakeInDTO t) {

		return repo.intakeInfo(t);
	}

	@Override
	public int intakeDelete(TakeInDTO t) {
		
		return repo.intakeDelete(t);
		
	}

	@Override
	public TakeinSumDTO intakeSum(String id) {

		return repo.intakeSum(id);
	}

	@Override
	public List<TakeinFoodDTO> intakeSearch(TakeinSearchDTO dto) {

		return repo.intakeSearch(dto);
	}

	@Override
	public int selectOne(TakeInDTO t) {
		return t.getIcount();
	}



	@Override
	public int intakeInsert(TakeInDTO dto) {
		int cnt = repo.selectOne(dto);
		
		
		int res=0;
		if(cnt!=0) {
			res = repo.intakeUpdate(dto);
			
		}else {
			res = repo.intakeInsert(dto);
			
		}
		
		return res;
		
	}
}
