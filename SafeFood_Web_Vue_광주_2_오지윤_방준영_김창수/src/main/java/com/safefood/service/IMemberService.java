package com.safefood.service;

import java.util.List;

import com.safefood.dto.MemDTO;

public interface IMemberService {
	public boolean loginMem(String id, String password);

	public MemDTO findPw(String id, String question, String answer);
	
	public void signUpMem(String id, String password, String mname, String addr, String tel, String allergy, String question, String answer, int calorie_goal);
	
	public void updateMem(String id, String password, String mname, String addr, String tel, String allergy, int calorie_goal);

	public MemDTO infoMem(String id);

	public List<MemDTO> listMem();

}
