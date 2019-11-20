package com.safefood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
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
	public TakeinFoodDTO intakeInfo(String id, int code) {

		return repo.intakeInfo(id, code);
	}

	@Override
	public int intakeDelete(String id, int code) {
		return repo.intakeDelete(id, code);
	}

	@Override
	public TakeinSumDTO intakeSum(String id) {

		return repo.intakeSum(id);
	}

	@Override
	public List<TakeinFoodDTO> intakeSearch(String id, String keyword) {

		return repo.intakeSearch(id, keyword);
	}

	@Override
	public int selectOne(int code, String id, int icount) {
		return icount;
	}



	@Override
	public int intakeInsert(TakeInDTO dto) {
		int cnt = repo.selectOne(dto.getCode(),dto.getId(),dto.getIcount());
		int res=0;
		if(cnt!=0) {
			res = repo.intakeUpdate(dto);
		}else {
			res = repo.intakeInsert(dto);
		}
		System.out.println("selectone 들어오나요");
		return res;
		
	}
}
