package com.safefood.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safefood.dto.TakeInDTO;
import com.safefood.dto.TakeinFoodDTO;
import com.safefood.dto.TakeinSearchDTO;
import com.safefood.dto.TakeinSumDTO;

@Repository("TakeinFoodMyBatisRepositoryImpl")
public class TakeinFoodMyBatisRepositoryImpl implements ITakeinFoodRepository {

	@Autowired
	SqlSession session;

	@Override
	public int intakeInsert(TakeInDTO t) {
		// TODO Auto-generated method stub
		return session.insert("com.takein.intakeInsert", t);
	}

	@Override
	public List<TakeinFoodDTO> intakeList(String id) {
		// TODO Auto-generated method stub
		return session.selectList("com.takein.intakeList", id);
	}

	@Override
	public TakeinFoodDTO intakeInfo(TakeInDTO t) {
		// TODO Auto-generated method stub
		return session.selectOne("com.takein.intakeInfo", t.getId());
	}

	@Override
	public int intakeDelete(TakeInDTO t) {
		// TODO Auto-generated method stub
		return session.delete("com.takein.intakeDelete", t);
	}

	@Override
	public TakeinSumDTO intakeSum(String id) {
		// TODO Auto-generated method stub
		return session.selectOne("com.takein.intakeSum", id);
	}

	@Override
	public List<TakeinFoodDTO> intakeSearch(TakeinSearchDTO t) {
		// TODO Auto-generated method stub
		return session.selectList("com.takein.intakeSearch", t);
	}

	@Override
	public int selectOne(TakeInDTO t) {
		TakeinFoodDTO m = session.selectOne("com.takein.intakeSelectOne", t);
		if (m == null) {
			return 0;
		} else {
			return m.getIcount();
		}

	}

	@Override
	public int intakeUpdate(TakeInDTO t) {
		return session.update("com.takein.intakeupdate", t);
	}

}
