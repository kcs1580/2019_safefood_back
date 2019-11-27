package com.safefood.dto;

public class BoardDTO {
	private int bid;
	private String btitle;
	private String bcontent;
	private int bcount;
	private String user_id;
	private String user_name;
	
	
	public BoardDTO(int bid, String btitle, String bcontent, int bcount, String user_id, String user_name) {
		super();
		this.bid = bid;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bcount = bcount;
		this.user_id = user_id;
		this.user_name = user_name;
	}
	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BoardDTO [bid=" + bid + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bcount=" + bcount
				+ ", user_id=" + user_id + ", user_name=" + user_name + "]";
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public int getBcount() {
		return bcount;
	}
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}
