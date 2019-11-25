package com.safefood.dto;

public class TakeInDTO {
	int code;
	String id;
	int icount;
	String idate;
	
	public String getIdate() {
		return idate;
	}
	public void setIdate(String idate) {
		this.idate = idate;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIcount() {
		return icount;
	}
	public void setIcount(int icount) {
		this.icount = icount;
	}
	@Override
	public String toString() {
		return "TakeInDTO [code=" + code + ", id=" + id + ", icount=" + icount + ", idate=" + idate + "]";
	}
	public TakeInDTO(int code, String id, int icount, String idate) {
		super();
		this.code = code;
		this.id = id;
		this.icount = icount;
		this.idate = idate;
	}
	public TakeInDTO(int code, String id, int icount) {
		super();
		this.code = code;
		this.id = id;
		this.icount = icount;	}
	
	public TakeInDTO(int code, String id) {
		super();
		this.code = code;
		this.id = id;
			}

	
}
