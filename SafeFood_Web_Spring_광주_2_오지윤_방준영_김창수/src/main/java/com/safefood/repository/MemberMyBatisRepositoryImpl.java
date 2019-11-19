package com.safefood.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safefood.dto.MemDTO;

@Repository("MemberMyBatisRepositoryImpl")
public class MemberMyBatisRepositoryImpl implements IMemberRepository {
	@Autowired
	SqlSession session;
		
	public MemberMyBatisRepositoryImpl() {
	}

	@Override
	public void memInsert(MemDTO m) {
		session.insert("com.member.memInsert",m);
	}

	@Override
	public List<MemDTO> memList() {
		List<MemDTO> selectList = session.selectList("com.member.memList");
		return selectList;
	}

	@Override
	public MemDTO memInfo(String id) {
		MemDTO mem = session.selectOne("com.member.memInfo",id);
		return mem;
	}

	@Override
	public void memUpdate(MemDTO m) {
		session.update("com.member.memUpdate",m); 
	}

	@Override
	public MemDTO memFind(String id, String question, String answer) {
		session.selectOne("com.member.memFind");
		return null;
	}
	@Override
	public boolean isLogin(String id, String password) {
		//System.out.println("로그인에 들어 오나요"+id+" "+password);
		List<MemDTO> list = memList();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id) && list.get(i).getPassword().equals(password)) {
			//	System.out.println("로그인완료");
				return true;
			}
		}
		return false;
	}

}
