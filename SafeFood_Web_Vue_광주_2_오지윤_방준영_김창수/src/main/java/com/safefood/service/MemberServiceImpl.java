package com.safefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safefood.dto.MemDTO;
import com.safefood.repository.IMemberRepository;

@Service("MemberServiceImpl")
public class MemberServiceImpl implements IMemberService {
	@Autowired
	@Qualifier("MemberMyBatisRepositoryImpl")
	IMemberRepository repo;

	public MemberServiceImpl() {
	}

	@Override
	public boolean loginMem(String id, String password) {
		return repo.isLogin(id, password);
	}

	@Override
	public MemDTO findPw(String id, String question, String answer) {
		repo.memFind(id, question, answer);
		return null;
	}

	@Override
	public void signUpMem(String id, String password, String mname, String addr, String tel, String allergy,
			String question, String answer, int calorie_goal) {
		repo.memInsert(new MemDTO(id, password, mname, addr, tel, allergy, question, answer, calorie_goal));
	}

	@Override
	public void updateMem(String id, String password, String mname, String addr, String tel, String allergy,
			int calorie_goal) {
		repo.memUpdate(new MemDTO(id, password, mname, addr, tel, allergy, calorie_goal));
	}

	@Override
	public MemDTO infoMem(String id) {
		return repo.memInfo(id);
	}

	@Override
	public List<MemDTO> listMem() {
		return repo.memList();
	}
}
