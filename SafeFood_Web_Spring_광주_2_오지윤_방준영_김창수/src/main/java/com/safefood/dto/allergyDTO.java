package com.safefood.dto;

public class allergyDTO {
	String allergy = "대두 땅콩 게 새우 연어 쑥 소고기 닭고기 돼지고기 복숭아 민들레 꼐란흰자";

	
	
	public allergyDTO() {
		this.allergy = "대두 땅콩 게 새우 연어 쑥 소고기 닭고기 돼지고기 복숭아 민들레 계란흰자";
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	
}
