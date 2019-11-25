package com.safefood.dto;

public class TakeinSearchDTO {
	String id;
	String keyword;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public TakeinSearchDTO(String id, String keyword) {
		super();
		this.id = id;
		this.keyword = keyword;
	}
	public TakeinSearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TakeinSearchDTO [id=" + id + ", keyword=" + keyword + "]";
	}
	

}
