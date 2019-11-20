package com.safefood.repository;

import java.util.List;

import com.safefood.dto.MemDTO;

public interface IMemberRepository {
	public void memInsert(MemDTO m);

	public List<MemDTO> memList();

	public MemDTO memInfo(String id);

	public void memUpdate(MemDTO m);

	public boolean isLogin(String id, String password);
	
	public MemDTO memFind(String id, String question, String answer);
}
